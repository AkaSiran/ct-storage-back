package com.ruoyi.project.vocallot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.enums.voc.VocAllotStatus;
import com.ruoyi.common.enums.voc.VocDeliverType;
import com.ruoyi.common.enums.voc.VocStoreType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocallot.domain.dto.InsertVocAllotItemRequestDto;
import com.ruoyi.project.vocallot.domain.dto.InsertVocAllotRequestDto;
import com.ruoyi.project.vocallot.domain.dto.UpdateVocAllotItemRequestDto;
import com.ruoyi.project.vocallot.domain.dto.UpdateVocAllotRequestDto;
import com.ruoyi.project.vocallot.domain.po.VocAllot;
import com.ruoyi.project.vocallot.domain.po.VocAllotItem;
import com.ruoyi.project.vocallot.mapper.VocAllotMapper;
import com.ruoyi.project.vocallot.service.VocAllotItemService;
import com.ruoyi.project.vocallot.service.VocAllotService;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverItemRequestDto;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverRequestDto;
import com.ruoyi.project.vocdeliver.service.VocDeliverService;
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
 * Created by Fyc on 2021-8-11.
 * 调拨信息
 */
@Slf4j
@Service
public class VocAllotServiceImpl extends ServiceImpl<VocAllotMapper,VocAllot> implements VocAllotService
{

    @Autowired
    private VocAllotItemService vocAllotItemService;

    @Autowired
    private VocDeliverService vocDeliverService;

    @Autowired
    private VocStoreService vocStoreService;

    @Override
    @Transactional
    public AjaxResult insertAllot(InsertVocAllotRequestDto insertVocAllotRequestDto)
    {
        List<InsertVocAllotItemRequestDto> insertVocAllotItemRequestDtoList = insertVocAllotRequestDto.getInsertVocAllotItemRequestDtoList();
        if(CollectionUtils.isEmpty(insertVocAllotItemRequestDtoList))
        {
            log.info("调拨商品信息列表为空");
            return AjaxResult.error("未获取到调拨商品信息");
        }
        //保存调拨信息
        VocAllot vocAllot = new VocAllot();
        BeanUtils.copyProperties(insertVocAllotRequestDto,vocAllot);
        vocAllot.setOperateDeptId(SecurityUtils.getDeptId());
        vocAllot.setAllotStatus(VocAllotStatus.ALLOT_DELIVER.getCode());
        vocAllot.preInsert();
        save(vocAllot);
        //保存调拨商品信息
        List<VocAllotItem> vocAllotItemList = Lists.newArrayList();
        Long allotId = vocAllot.getId();
        int sort = 0;
        for(InsertVocAllotItemRequestDto insertVocAllotItemRequestDto : insertVocAllotItemRequestDtoList)
        {
            VocAllotItem vocAllotItem = new VocAllotItem();
            BeanUtils.copyProperties(insertVocAllotItemRequestDto,vocAllotItem);
            vocAllotItem.setAllotId(allotId);
            vocAllotItem.setSort(sort);
            vocAllotItem.preInsert();
            vocAllotItemList.add(vocAllotItem);
            sort++;
        }
        vocAllotItemService.saveBatch(vocAllotItemList);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult updateAllot(UpdateVocAllotRequestDto updateVocAllotRequestDto)
    {
        List<UpdateVocAllotItemRequestDto> updateVocAllotItemRequestDtoList = updateVocAllotRequestDto.getUpdateVocAllotItemRequestDtoList();
        if(CollectionUtils.isEmpty(updateVocAllotItemRequestDtoList))
        {
            log.info("调拨商品信息列表为空");
            return AjaxResult.error("未获取到调拨商品信息");
        }
        //修改调拨信息
        VocAllot vocAllot = new VocAllot();
        BeanUtils.copyProperties(updateVocAllotRequestDto,vocAllot);
        vocAllot.setOperateDeptId(SecurityUtils.getDeptId());
        vocAllot.setAllotStatus(VocAllotStatus.ALLOT_DELIVER.getCode());
        vocAllot.preUpdate();
        updateById(vocAllot);
        //修改调拨商品信息
        List<VocAllotItem> vocAllotItemList = Lists.newArrayList();
        Long allotId = vocAllot.getId();
        vocAllotItemService.remove(new QueryWrapper<VocAllotItem>()
                .eq("allot_id",allotId));
        int sort = 0;
        for(UpdateVocAllotItemRequestDto updateVocAllotItemRequestDto : updateVocAllotItemRequestDtoList)
        {
            VocAllotItem vocAllotItem = new VocAllotItem();
            BeanUtils.copyProperties(updateVocAllotItemRequestDto,vocAllotItem);
            vocAllotItem.setAllotId(allotId);
            vocAllotItem.setSort(sort);
            vocAllotItem.preInsert();
            vocAllotItemList.add(vocAllotItem);
            sort++;
        }
        vocAllotItemService.saveBatch(vocAllotItemList);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult storageAllot(Long allotId)
    {
        VocAllot vocAllot = getById(allotId);
        String allotStatus = vocAllot.getAllotStatus();
        if(!allotStatus.equals(VocAllotStatus.ALLOT_INTRANSIT.getCode()) || !allotStatus.equals(VocAllotStatus.ALLOT_BACK.getCode()))
        {
            log.info("当前调拨单状态 = {}",VocAllotStatus.getAllotName(allotStatus));
            return AjaxResult.error("该调拨单不符合入库条件");
        }
        //调拨入库
        if(allotStatus.equals(VocAllotStatus.ALLOT_INTRANSIT.getCode()))
        {
            //修改调拨单信息
            VocAllot newAllot = new VocAllot();
            newAllot.setId(vocAllot.getId());
            newAllot.setAllotStatus(VocAllotStatus.ALLOT_STORAGE.getCode());
            updateById(newAllot);
            //新增入库商品信息
            List<InsertVocStoreItemRequestDto> insertVocStoreItemRequestDtoList = Lists.newArrayList();
            List<VocAllotItem> vocAllotItemList = vocAllotItemService.list(new QueryWrapper<VocAllotItem>()
                    .eq("allot_id",allotId)
                    .eq("del_flag","0"));
            for(VocAllotItem vocAllotItem : vocAllotItemList)
            {
                InsertVocStoreItemRequestDto insertVocStoreItemRequestDto = new InsertVocStoreItemRequestDto();
                Long productId = vocAllotItem.getProductId();
                int amount = vocAllotItem.getAmount();
                insertVocStoreItemRequestDto.setProductId(productId);
                insertVocStoreItemRequestDto.setAmount(amount);
                insertVocStoreItemRequestDtoList.add(insertVocStoreItemRequestDto);
            }
            //新增入库信息
            InsertVocStoreRequestDto insertVocStoreRequestDto = new InsertVocStoreRequestDto();
            Long toDeptId = vocAllot.getToDeptId();
            insertVocStoreRequestDto.setDeptId(toDeptId);
            insertVocStoreRequestDto.setStoreType(VocStoreType.ALLOT_STORAGE.getCode());
            insertVocStoreRequestDto.setInsertVocStoreItemRequestList(insertVocStoreItemRequestDtoList);
            return vocStoreService.insertVocStore(insertVocStoreRequestDto);
        }
        //撤回入库
        if(allotStatus.equals(VocAllotStatus.ALLOT_BACK.getCode()))
        {
            //修改调拨单信息
            VocAllot newAllot = new VocAllot();
            newAllot.setId(vocAllot.getId());
            newAllot.setAllotStatus(VocAllotStatus.BACK_STORAGE.getCode());
            updateById(newAllot);
            //新增入库商品信息
            List<InsertVocStoreItemRequestDto> insertVocStoreItemRequestDtoList = Lists.newArrayList();
            List<VocAllotItem> vocAllotItemList = vocAllotItemService.list(new QueryWrapper<VocAllotItem>()
                    .eq("allot_id",allotId)
                    .eq("del_flag","0"));
            for(VocAllotItem vocAllotItem : vocAllotItemList)
            {
                InsertVocStoreItemRequestDto insertVocStoreItemRequestDto = new InsertVocStoreItemRequestDto();
                Long productId = vocAllotItem.getProductId();
                int amount = vocAllotItem.getAmount();
                insertVocStoreItemRequestDto.setProductId(productId);
                insertVocStoreItemRequestDto.setAmount(amount);
                insertVocStoreItemRequestDtoList.add(insertVocStoreItemRequestDto);
            }
            //新增入库信息
            InsertVocStoreRequestDto insertVocStoreRequestDto = new InsertVocStoreRequestDto();
            Long fromDeptId = vocAllot.getFromDeptId();
            insertVocStoreRequestDto.setDeptId(fromDeptId);
            insertVocStoreRequestDto.setStoreType(VocStoreType.ALLOT_STORAGE.getCode());
            insertVocStoreRequestDto.setInsertVocStoreItemRequestList(insertVocStoreItemRequestDtoList);
            return vocStoreService.insertVocStore(insertVocStoreRequestDto);
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult deliverAllot(Long allotId)
    {
        VocAllot vocAllot = getById(allotId);
        String allotStatus = vocAllot.getAllotStatus();
        if(!allotStatus.equals(VocAllotStatus.ALLOT_DELIVER.getCode()))
        {
            log.info("当前调拨单状态 = {}",VocAllotStatus.getAllotName(allotStatus));
            return AjaxResult.error("该调拨单不符合出库条件");
        }
        //新增出库商品信息
        List<InsertVocDeliverItemRequestDto> insertVocDeliverItemRequestDtoList = Lists.newArrayList();
        List<VocAllotItem> vocAllotItemList = vocAllotItemService.list(new QueryWrapper<VocAllotItem>()
                .eq("allot_id",allotId)
                .eq("del_flag","0"));
        for(VocAllotItem vocAllotItem : vocAllotItemList)
        {
            InsertVocDeliverItemRequestDto insertVocDeliverItemRequestDto = new InsertVocDeliverItemRequestDto();
            Long productId = vocAllotItem.getProductId();
            int amount = vocAllotItem.getAmount();
            insertVocDeliverItemRequestDto.setProductId(productId);
            insertVocDeliverItemRequestDto.setAmount(amount);
            insertVocDeliverItemRequestDtoList.add(insertVocDeliverItemRequestDto);
        }
        //新增出库信息
        InsertVocDeliverRequestDto insertVocDeliverRequestDto = new InsertVocDeliverRequestDto();
        Long fromDeptId = vocAllot.getFromDeptId();
        insertVocDeliverRequestDto.setDeliverType(VocDeliverType.PURCHASE_DELIVER.getCode());
        insertVocDeliverRequestDto.setDeptId(fromDeptId);
        insertVocDeliverRequestDto.setInsertVocDeliverItemRequestDtoList(insertVocDeliverItemRequestDtoList);
        return vocDeliverService.insertVocDeliver(insertVocDeliverRequestDto);
    }
}
