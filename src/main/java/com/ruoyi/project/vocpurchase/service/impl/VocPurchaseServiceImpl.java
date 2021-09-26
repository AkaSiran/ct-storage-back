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
import com.ruoyi.project.vocpurchase.domain.dto.*;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchase;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchaseItem;
import com.ruoyi.project.vocpurchase.mapper.VocPurchaseMapper;
import com.ruoyi.project.vocpurchase.service.VocPurchaseItemService;
import com.ruoyi.project.vocpurchase.service.VocPurchaseService;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreItemRequestDto;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreRequestDto;
import com.ruoyi.project.vocstore.service.VocStoreService;
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
        vocPurchaseItemService.remove(new QueryWrapper<VocPurchaseItem>()
                .eq("purchase_id",purchaseId));
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
        List<VocPurchaseItem> vocPurchaseItemList = vocPurchaseItemService.list(new QueryWrapper<VocPurchaseItem>()
                .eq("purchase_id",id)
                .eq("del_flag","0")
                .orderByAsc("create_time"));
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
        return vocStoreService.insertVocStore(insertVocStoreRequestDto);
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
        //获取采购商品信息
        List<DetailVocPurchaseItemResponseDto> detailVocPurchaseItemResponseDtoList = Lists.newArrayList();
        List<VocPurchaseItem> vocPurchaseItemList = vocPurchaseItemService.list(new QueryWrapper<VocPurchaseItem>()
                .eq("purchase_id",id)
                .eq("del_flag","0")
                .orderByAsc("create_time"));
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
        if(StringUtils.isNotNull(deptId)) queryWrapper.eq("dept_id",deptId);
        if(StringUtils.isNotNull(supplierId)) queryWrapper.eq("supplier_id",supplierId);
        if(StringUtils.isNotBlank(purchaseType)) queryWrapper.eq("puechase_type",purchaseType);
        if(StringUtils.isNotBlank(puechaseStatus)) queryWrapper.eq("puechase_status",puechaseStatus);
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
        vocPurchaseItemService.remove(new QueryWrapper<VocPurchaseItem>().eq("purchase_id",id));
        return AjaxResult.success();
    }
}
