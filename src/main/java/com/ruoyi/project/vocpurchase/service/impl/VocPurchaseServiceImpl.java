package com.ruoyi.project.vocpurchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.enums.voc.VocNoPrefix;
import com.ruoyi.common.enums.voc.VocPurchaseStatus;
import com.ruoyi.common.enums.voc.VocStoreType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.voc.NoUtils;
import com.ruoyi.common.utils.voc.PriceUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.vocproduct.domain.po.VocProduct;
import com.ruoyi.project.vocproduct.service.VocProductService;
import com.ruoyi.project.vocpurchase.domain.dto.*;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchase;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchaseItem;
import com.ruoyi.project.vocpurchase.mapper.VocPurchaseMapper;
import com.ruoyi.project.vocpurchase.service.VocPurchaseItemService;
import com.ruoyi.project.vocpurchase.service.VocPurchaseService;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreItemRequestDto;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreRequestDto;
import com.ruoyi.project.vocstore.domain.po.VocStore;
import com.ruoyi.project.vocstore.service.VocStoreService;
import com.ruoyi.project.vocsupplier.domain.po.VocSupplier;
import com.ruoyi.project.vocsupplier.service.VocSupplierService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Fyc on 2021-7-29.
 * 采购信息
 */
@Slf4j
@Service
public class VocPurchaseServiceImpl extends ServiceImpl<VocPurchaseMapper,VocPurchase> implements VocPurchaseService
{

    @Autowired
    private VocStoreService vocStoreService;

    @Autowired
    private VocPurchaseItemService vocPurchaseItemService;

    @Autowired
    private VocSupplierService vocSupplierService;

    @Autowired
    private VocProductService vocProductService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Override
    @Transactional
    public AjaxResult insertPurchase(InsertVocPurchaseRequestDto insertVocPurchaseRequestDto)
    {
        List<InsertVocPurchaseItemRequestDto> insertVocPurchaseItemRequestDtoList = insertVocPurchaseRequestDto.getItemList();
        if(StringUtils.isEmpty(insertVocPurchaseItemRequestDtoList))
        {
            log.info("采购商品信息列表为空");
            return AjaxResult.error("未获取到采购商品信息");
        }
        //保存采购信息
        VocPurchase vocPurchase = new VocPurchase();
        BeanUtils.copyProperties(insertVocPurchaseRequestDto,vocPurchase);
        //采购总金额换算 double -> int
        vocPurchase.setTotalPrice(PriceUtils.d2i(insertVocPurchaseRequestDto.getTotalPrice()));
        vocPurchase.setPurchaseNo(NoUtils.generateNo(VocNoPrefix.PURCHASE.getCode()));
        vocPurchase.setPurchaseStatus(VocPurchaseStatus.PLACED.getCode());
        vocPurchase.preInsert();
        save(vocPurchase);
        //保存采购商品信息
        List<VocPurchaseItem> vocPurchaseItemList = Lists.newArrayList();
        Long purchaseId = vocPurchase.getId();
        int sort = 0;
        for(InsertVocPurchaseItemRequestDto insertVocPurchaseItemRequestDto : insertVocPurchaseItemRequestDtoList)
        {
            VocPurchaseItem vocPurchaseItem = new VocPurchaseItem();
            BeanUtils.copyProperties(insertVocPurchaseItemRequestDto,vocPurchaseItem);
            //商品单价换算 double -> int
            vocPurchaseItem.setSinglePrice(PriceUtils.d2i(insertVocPurchaseItemRequestDto.getSinglePrice()));
            //商品总价换算 double -> int
            vocPurchaseItem.setTotalPrice(PriceUtils.d2i(insertVocPurchaseItemRequestDto.getTotalPrice()));
            vocPurchaseItem.setPurchaseId(purchaseId);
            vocPurchaseItem.setSort(sort);
            vocPurchaseItem.preInsert();
            vocPurchaseItemList.add(vocPurchaseItem);
            sort++;
        }
        vocPurchaseItemService.saveBatch(vocPurchaseItemList);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult updatePurchase(UpdateVocPurchaseRequestDto updateVocPurchaseRequestDto)
    {
        List<UpdateVocPurchaseItemRequestDto> updateVocPurchaseItemRequestDtoList = updateVocPurchaseRequestDto.getItemList();
        if(CollectionUtils.isEmpty(updateVocPurchaseItemRequestDtoList))
        {
            log.info("采购商品信息列表为空");
            return AjaxResult.error("未获取到采购商品信息");
        }
        //修改采购信息
        VocPurchase vocPurchase = new VocPurchase();
        BeanUtils.copyProperties(updateVocPurchaseRequestDto,vocPurchase);
        //采购总金额换算 double -> int
        vocPurchase.setTotalPrice(PriceUtils.d2i(updateVocPurchaseRequestDto.getTotalPrice()));
        vocPurchase.preUpdate();
        updateById(vocPurchase);
        //修改采购商品信息
        List<VocPurchaseItem> vocPurchaseItemList = Lists.newArrayList();
        Long purchaseId = vocPurchase.getId();
        vocPurchaseItemService.remove(new QueryWrapper<VocPurchaseItem>().lambda()
                .eq(VocPurchaseItem::getPurchaseId,purchaseId));
        int sort = 0;
        for(UpdateVocPurchaseItemRequestDto updateVocPurchaseItemRequestDto : updateVocPurchaseItemRequestDtoList)
        {
            VocPurchaseItem vocPurchaseItem = new VocPurchaseItem();
            BeanUtils.copyProperties(updateVocPurchaseItemRequestDto,vocPurchaseItem);
            //商品单价换算 double -> int
            vocPurchaseItem.setSinglePrice(PriceUtils.d2i(updateVocPurchaseItemRequestDto.getSinglePrice()));
            //商品总价换算 double -> int
            vocPurchaseItem.setTotalPrice(PriceUtils.d2i(updateVocPurchaseItemRequestDto.getTotalPrice()));
            vocPurchaseItem.setPurchaseId(purchaseId);
            vocPurchaseItem.setSort(sort);
            vocPurchaseItem.preInsert();
            vocPurchaseItemList.add(vocPurchaseItem);
            sort++;
        }
        vocPurchaseItemService.saveBatch(vocPurchaseItemList);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult storagePurchase(Long id)
    {
        VocPurchase vocPurchase = getById(id);
        String purchaseStatus = vocPurchase.getPurchaseStatus();
        if(!purchaseStatus.equals(VocPurchaseStatus.PLACED.getCode()))
        {
            log.info("当前采购单状态 = {}",VocPurchaseStatus.getStatusName(purchaseStatus));
            return AjaxResult.error("该采购单不符合入库条件");
        }
        List<VocPurchaseItem> vocPurchaseItemList = vocPurchaseItemService.list(new QueryWrapper<VocPurchaseItem>().lambda()
                .eq(VocPurchaseItem::getPurchaseId,id)
                .eq(VocPurchaseItem::getDelFlag,"0")
                .orderByAsc(VocPurchaseItem::getCreateTime));
        if(CollectionUtils.isEmpty(vocPurchaseItemList))
        {
            log.info("采购商品信息列表为空");
            return AjaxResult.error("未获取到采购商品信息");
        }
        //商品入库
        InsertVocStoreRequestDto insertVocStoreRequestDto = new InsertVocStoreRequestDto();
        List<InsertVocStoreItemRequestDto> insertVocStoreItemRequestDtoList = Lists.newArrayList();
        //拼接入库信息
        insertVocStoreRequestDto.setDeptId(vocPurchase.getDeptId());
        insertVocStoreRequestDto.setStoreType(VocStoreType.PURCHASE_STORAGE.getCode());
        //拼接入库商品信息
        vocPurchaseItemList.forEach(vocPurchaseItem ->
        {
            InsertVocStoreItemRequestDto insertVocStoreItemRequestDto = new InsertVocStoreItemRequestDto();
            insertVocStoreItemRequestDto.setProductId(vocPurchaseItem.getProductId());
            insertVocStoreItemRequestDto.setAmount(vocPurchaseItem.getAmount());
            insertVocStoreItemRequestDtoList.add(insertVocStoreItemRequestDto);
        });
        insertVocStoreRequestDto.setInsertVocStoreItemRequestList(insertVocStoreItemRequestDtoList);
        AjaxResult storeResult =  vocStoreService.insertVocStore(insertVocStoreRequestDto);
        //采购单状态变更
        if(storeResult.isOK())
        {
            Object storeObj = storeResult.get("data");
            VocStore vocStore = (VocStore)storeObj;
            Long storeId = vocStore.getId();
            VocPurchase vocPurchaseStore = new VocPurchase();
            vocPurchaseStore.setId(vocPurchase.getId());
            vocPurchaseStore.setPurchaseStatus(VocPurchaseStatus.STORAGE.getCode());
            vocPurchaseStore.setStoreId(storeId);
            updateById(vocPurchaseStore);
            return AjaxResult.success();
        }
        return AjaxResult.error("采购入库失败");
    }

    @Override
    public AjaxResult purchaseDetail(Long id)
    {
        //获取采购信息
        VocPurchase vocPurchase = getById(id);
        DetailVocPurchaseResponseDto detailVocPurchaseResponseDto = new DetailVocPurchaseResponseDto();
        BeanUtils.copyProperties(vocPurchase,detailVocPurchaseResponseDto);
        //采购总金额换算 int -> double
        detailVocPurchaseResponseDto.setTotalPrice(PriceUtils.i2d(vocPurchase.getTotalPrice()));
        //获取厂商名称
        VocSupplier vocSupplier = vocSupplierService.getById(vocPurchase.getSupplierId());
        String supplierName = vocSupplier.getName();
        detailVocPurchaseResponseDto.setSupplierName(supplierName);
        //获取门店名称
        SysDept sysDept = sysDeptService.selectDeptById(vocPurchase.getDeptId());
        String deptName = sysDept.getDeptName();
        detailVocPurchaseResponseDto.setDeptName(deptName);
        //获取采购类型名称
        String purchaseTypeName = VocPurchaseStatus.getStatusName(vocPurchase.getPurchaseStatus());
        detailVocPurchaseResponseDto.setPurchaseTypeName(purchaseTypeName);
        //获取采购商品信息
        List<DetailVocPurchaseItemResponseDto> detailVocPurchaseItemResponseDtoList = Lists.newArrayList();
        List<VocPurchaseItem> vocPurchaseItemList = vocPurchaseItemService.list(new QueryWrapper<VocPurchaseItem>().lambda()
                .eq(VocPurchaseItem::getPurchaseId,id)
                .eq(VocPurchaseItem::getDelFlag,"0")
                .orderByAsc(VocPurchaseItem::getCreateTime));
        if(CollectionUtils.isNotEmpty(vocPurchaseItemList))
        {
            vocPurchaseItemList.forEach(vocPurchaseItem ->
            {
                DetailVocPurchaseItemResponseDto detailVocPurchaseItemResponseDto = new DetailVocPurchaseItemResponseDto();
                BeanUtils.copyProperties(vocPurchaseItem,detailVocPurchaseItemResponseDto);
                //商品单价换算 int -> double
                detailVocPurchaseItemResponseDto.setSinglePrice(PriceUtils.i2d(vocPurchaseItem.getSinglePrice()));
                //商品总价换算 int -> double
                detailVocPurchaseItemResponseDto.setTotalPrice(PriceUtils.i2d(vocPurchaseItem.getTotalPrice()));
                //获取商品名称
                VocProduct vocProduct = vocProductService.getById(vocPurchaseItem.getProductId());
                detailVocPurchaseItemResponseDto.setProductName(vocProduct.getName());
                detailVocPurchaseItemResponseDtoList.add(detailVocPurchaseItemResponseDto);
            });
            detailVocPurchaseResponseDto.setItemList(detailVocPurchaseItemResponseDtoList);
        }
        return AjaxResult.success(detailVocPurchaseResponseDto);
    }

    @Override
    public List<ListVocPurchaseResponseDto> purchaseList(SelectVocPurchaseRequestDto selectVocPurchaseRequestDto)
    {
        QueryWrapper<VocPurchase> queryWrapper = new QueryWrapper<VocPurchase>();
        Long deptId = selectVocPurchaseRequestDto.getDeptId();
        Long supplierId = selectVocPurchaseRequestDto.getSupplierId();
        String purchaseType = selectVocPurchaseRequestDto.getPurchaseType();
        String puechaseStatus = selectVocPurchaseRequestDto.getPuechaseStatus();
        if(StringUtils.isNotNull(deptId)) queryWrapper.lambda().eq(VocPurchase::getDeptId,deptId);
        if(StringUtils.isNotNull(supplierId)) queryWrapper.lambda().eq(VocPurchase::getSupplierId,supplierId);
        if(StringUtils.isNotBlank(purchaseType)) queryWrapper.lambda().eq(VocPurchase::getPurchaseType,purchaseType);
        if(StringUtils.isNotBlank(puechaseStatus)) queryWrapper.lambda().eq(VocPurchase::getPurchaseStatus,puechaseStatus);
        List<VocPurchase> list = list(queryWrapper);
        List<ListVocPurchaseResponseDto> resultList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(list))
        {
            list.forEach(vocPurchase ->
            {
                ListVocPurchaseResponseDto listVocPurchaseResponseDto = new ListVocPurchaseResponseDto();
                BeanUtils.copyProperties(vocPurchase,listVocPurchaseResponseDto);
                //采购总金额换算 int -> double
                listVocPurchaseResponseDto.setTotalPrice(PriceUtils.i2d(vocPurchase.getTotalPrice()));
                //获取厂商名称
                VocSupplier vocSupplier = vocSupplierService.getById(vocPurchase.getSupplierId());
                listVocPurchaseResponseDto.setSupplierName(vocSupplier.getName());
                //获取门店名称
                SysDept sysDept = sysDeptService.selectDeptById(vocPurchase.getDeptId());
                listVocPurchaseResponseDto.setDeptName(sysDept.getDeptName());
                resultList.add(listVocPurchaseResponseDto);
            });
        }
        return resultList;
    }

    @Override
    @Transactional
    public AjaxResult deletePurchase(Long id)
    {
        VocPurchase vocPurchase = getById(id);
        String purchaseStatus = vocPurchase.getPurchaseStatus();
        if(!purchaseStatus.equals(VocPurchaseStatus.PLACED.getCode()))
        {
            log.info("当前采购单状态 = {}",VocPurchaseStatus.getStatusName(purchaseStatus));
            return AjaxResult.error("该采购单不符合删除条件");
        }
        removeById(id);
        vocPurchaseItemService.remove(new QueryWrapper<VocPurchaseItem>().lambda().eq(VocPurchaseItem::getPurchaseId,id));
        return AjaxResult.success();
    }
}
