package com.ruoyi.project.vocsale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.enums.voc.VocDeliverType;
import com.ruoyi.common.enums.voc.VocSaleStatus;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverItemRequestDto;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverRequestDto;
import com.ruoyi.project.vocdeliver.service.VocDeliverService;
import com.ruoyi.project.vocsale.domain.dto.*;
import com.ruoyi.project.vocsale.domain.po.VocSale;
import com.ruoyi.project.vocsale.domain.po.VocSaleItem;
import com.ruoyi.project.vocsale.mapper.VocSaleMapper;
import com.ruoyi.project.vocsale.service.VocSaleItemService;
import com.ruoyi.project.vocsale.service.VocSaleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Fyc on 2021-8-2.
 * 销售商品信息
 */
@Slf4j
@Service
public class VocSaleServiceImpl extends ServiceImpl<VocSaleMapper,VocSale> implements VocSaleService
{

    @Autowired
    private VocSaleItemService vocSaleItemService;

    @Autowired
    private VocDeliverService vocDeliverService;

    @Override
    @Transactional
    public AjaxResult insertSale(InsertVocSaleRequestDto insertVocSaleRequestDto)
    {
        List<InsertVocSaleItemRequestDto> insertVocSaleItemRequestDtoList = insertVocSaleRequestDto.getInsertVocSaleItemRequestDtoList();
        if(CollectionUtils.isEmpty(insertVocSaleItemRequestDtoList))
        {
            log.info("销售商品信息列表为空");
            return AjaxResult.error("未获取到销售商品信息");
        }
        //保存销售信息
        VocSale vocSale = new VocSale();
        BeanUtils.copyProperties(insertVocSaleRequestDto,vocSale);
        vocSale.setSaleStatus(VocSaleStatus.PLACED.getCode());
        vocSale.preInsert();
        save(vocSale);
        //保存销售商品信息
        List<VocSaleItem> vocSaleItemList = Lists.newArrayList();
        Long saleId = vocSale.getId();
        int sort = 0;
        for(InsertVocSaleItemRequestDto insertVocSaleItemRequestDto : insertVocSaleItemRequestDtoList)
        {
            VocSaleItem vocSaleItem = new VocSaleItem();
            BeanUtils.copyProperties(insertVocSaleItemRequestDto,vocSaleItem);
            vocSaleItem.setSaleId(saleId);
            vocSaleItem.setSort(sort);
            vocSaleItem.preInsert();
            vocSaleItemList.add(vocSaleItem);
            sort++;
        }
        vocSaleItemService.saveBatch(vocSaleItemList);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult updateSale(UpdateVocSaleRequestDto updateVocSaleRequestDto)
    {
        List<UpdateVocSaleItemRequestDto> updateVocSaleItemRequestDtoList = updateVocSaleRequestDto.getUpdateVocSaleItemRequestDtoList();
        if(CollectionUtils.isEmpty(updateVocSaleItemRequestDtoList))
        {
            log.info("销售商品信息列表为空");
            return AjaxResult.error("未获取到销售商品信息");
        }
        //修改销售信息
        VocSale vocSale = new VocSale();
        BeanUtils.copyProperties(updateVocSaleRequestDto,vocSale);
        vocSale.setSaleStatus(VocSaleStatus.PLACED.getCode());
        vocSale.preUpdate();
        updateById(vocSale);
        //修改销售商品信息
        List<VocSaleItem> vocSaleItemList = Lists.newArrayList();
        Long saleId = vocSale.getId();
        vocSaleItemService.remove(new QueryWrapper<VocSaleItem>()
                .eq("sale_id",saleId));
        int sort = 0;
        for(UpdateVocSaleItemRequestDto updateVocSaleItemRequestDto : updateVocSaleItemRequestDtoList)
        {
            VocSaleItem vocSaleItem = new VocSaleItem();
            BeanUtils.copyProperties(updateVocSaleItemRequestDto,vocSaleItem);
            vocSaleItem.setSaleId(saleId);
            vocSaleItem.setSort(sort);
            vocSaleItem.preInsert();
            vocSaleItemList.add(vocSaleItem);
            sort++;
        }
        vocSaleItemService.saveBatch(vocSaleItemList);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult deliverSale(Long id)
    {
        VocSale vocSale = getById(id);
        String saleStatus = vocSale.getSaleStatus();
        if(saleStatus.equals(VocSaleStatus.PLACED.getCode()) )
        {
            log.info("当前销售单状态 = {}",VocSaleStatus.getStatusName(saleStatus));
            return AjaxResult.error("该销售单不符合出库条件");
        }
        List<VocSaleItem> vocSaleItemList = vocSaleItemService.list(new QueryWrapper<VocSaleItem>()
                .eq("sale_id",id)
                .eq("del_flag","0")
                .orderByAsc("create_time"));
        if(CollectionUtils.isEmpty(vocSaleItemList))
        {
            log.info("销售商品信息列表为空");
            return AjaxResult.error("未获取到销售商品信息");
        }
        //商品出库
        InsertVocDeliverRequestDto insertVocDeliverRequestDto = new InsertVocDeliverRequestDto();
        List<InsertVocDeliverItemRequestDto> insertVocDeliverItemRequestDtoList = Lists.newArrayList();
        //拼接出库信息
        insertVocDeliverRequestDto.setDeptId(vocSale.getDeptId());
        insertVocDeliverRequestDto.setDeliverType(VocDeliverType.SALE_DELIVER.getCode());
        //拼接出库商品信息
        vocSaleItemList.forEach(vocSaleItem ->
        {
            InsertVocDeliverItemRequestDto insertVocDeliverItemRequestDto = new InsertVocDeliverItemRequestDto();
            insertVocDeliverItemRequestDto.setProductId(vocSaleItem.getProductId());
            insertVocDeliverItemRequestDto.setAmount(vocSaleItem.getAmount());
            insertVocDeliverItemRequestDtoList.add(insertVocDeliverItemRequestDto);
        });
        insertVocDeliverRequestDto.setInsertVocDeliverItemRequestDtoList(insertVocDeliverItemRequestDtoList);
        return vocDeliverService.insertVocDeliver(insertVocDeliverRequestDto);
    }

    @Override
    public AjaxResult saleDetail(Long id)
    {
        //获取销售信息
        VocSale vocSale = getById(id);
        DetailVocSaleResponseDto detailVocSaleResponseDto = new DetailVocSaleResponseDto();
        BeanUtils.copyProperties(vocSale,detailVocSaleResponseDto);
        //获取销售商品信息
        List<DetailVocSaleItemResponseDto> detailVocSaleItemResponseDtoList = Lists.newArrayList();
        List<VocSaleItem> vocSaleItemList = vocSaleItemService.list(new QueryWrapper<VocSaleItem>()
                .eq("sale_id",id)
                .eq("del_flag","0")
                .orderByAsc("create_time"));
        if(CollectionUtils.isNotEmpty(vocSaleItemList))
        {
            vocSaleItemList.forEach(vocSaleItem ->
            {
                DetailVocSaleItemResponseDto detailVocSaleItemResponseDto = new DetailVocSaleItemResponseDto();
                BeanUtils.copyProperties(vocSaleItem,detailVocSaleItemResponseDto);
                detailVocSaleItemResponseDtoList.add(detailVocSaleItemResponseDto);
            });
        }
        detailVocSaleResponseDto.setDetailVocSaleItemResponseDtoList(detailVocSaleItemResponseDtoList);
        return AjaxResult.success(detailVocSaleResponseDto);
    }

    @Override
    @Transactional
    public AjaxResult deleteSale(Long id)
    {
        VocSale vocSale = getById(id);
        String saleStatus = vocSale.getSaleStatus();
        if(saleStatus.equals(VocSaleStatus.DELIVER.getCode()))
        {
            log.info("销售单处于出库状态");
            return AjaxResult.error("销售单处于出库状态,无法删除");
        }
        removeById(id);
        vocSaleItemService.remove(new QueryWrapper<VocSaleItem>().eq("sale_id",id));
        return AjaxResult.success();
    }

    @Override
    public List<ListVocSaleResponseDto> saleList(SelectVocSaleRequestDto selectVocSaleRequestDto)
    {
        QueryWrapper queryWrapper = new QueryWrapper();
        Long deptId = selectVocSaleRequestDto.getDeptId();
        String saleStatus = selectVocSaleRequestDto.getSaleStatus();
        String saleType = selectVocSaleRequestDto.getSaleType();
        if(StringUtils.isNotNull(deptId)) queryWrapper.eq("dept_id",deptId);
        if(StringUtils.isNotBlank(saleStatus)) queryWrapper.eq("sale_status",saleStatus);
        if(StringUtils.isNotBlank(saleType)) queryWrapper.eq("sale_type",saleType);
        List<VocSale> list = list(queryWrapper);
        List<ListVocSaleResponseDto> resultList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(list))
        {
            list.forEach(vocSale ->
            {
                ListVocSaleResponseDto listVocSaleResponseDto = new ListVocSaleResponseDto();
                BeanUtils.copyProperties(vocSale,listVocSaleResponseDto);
                resultList.add(listVocSaleResponseDto);
            });
        }
        return resultList;
    }
}
