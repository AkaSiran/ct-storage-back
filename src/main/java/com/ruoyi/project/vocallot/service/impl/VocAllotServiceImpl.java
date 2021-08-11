package com.ruoyi.project.vocallot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
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
import com.ruoyi.project.vocdeliver.service.VocDeliverService;
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
}
