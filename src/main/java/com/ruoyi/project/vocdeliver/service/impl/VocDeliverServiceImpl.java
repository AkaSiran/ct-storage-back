package com.ruoyi.project.vocdeliver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverItemRequestDto;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverRequestDto;
import com.ruoyi.project.vocdeliver.domain.po.VocDeliver;
import com.ruoyi.project.vocdeliver.domain.po.VocDeliverItem;
import com.ruoyi.project.vocdeliver.mapper.VocDeliverItemMapper;
import com.ruoyi.project.vocdeliver.mapper.VocDeliverMapper;
import com.ruoyi.project.vocdeliver.service.VocDeliverService;
import com.ruoyi.project.vocinventory.domain.dto.TakeVocInventoryRequestDto;
import com.ruoyi.project.vocinventory.service.VocInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Fyc on 2021-7-28.
 * 出库信息
 */
@Slf4j
@Service
public class VocDeliverServiceImpl extends ServiceImpl<VocDeliverMapper,VocDeliver> implements VocDeliverService
{

    @Autowired
    private VocDeliverItemMapper vocDeliverItemMapper;

    @Autowired
    private VocInventoryService vocInventoryService;

    @Override
    @Transactional
    public AjaxResult insertVocDeliver(InsertVocDeliverRequestDto insertVocDeliverRequestDto)
    {
        List<InsertVocDeliverItemRequestDto> deliverItemRequestDtoList = insertVocDeliverRequestDto.getInsertVocDeliverItemRequestDtoList();
        if(StringUtils.isEmpty(deliverItemRequestDtoList))
        {
            log.info("出库失败,商品信息列表为空");
            return AjaxResult.error("未获取到商品信息");
        }
        //出库信息保存
        VocDeliver vocDeliver = new VocDeliver();
        Long deptId = insertVocDeliverRequestDto.getDeptId();
        BeanUtils.copyProperties(insertVocDeliverRequestDto,vocDeliver);
        //vocDeliver.preInsert();
        save(vocDeliver);
        //出库商品信息保存
        Long deliverId = vocDeliver.getId();
        deliverItemRequestDtoList.forEach(deliverItemRequestDto ->
        {
            VocDeliverItem vocDeliverItem = new VocDeliverItem();
            BeanUtils.copyProperties(deliverItemRequestDto,vocDeliverItem);
            vocDeliverItem.setDeliverId(deliverId);
            //vocDeliverItem.preInsert();
            vocDeliverItemMapper.insert(vocDeliverItem);
            //库存信息保存
            TakeVocInventoryRequestDto takeVocInventoryRequestDto = new TakeVocInventoryRequestDto();
            BeanUtils.copyProperties(deliverItemRequestDto,takeVocInventoryRequestDto);
            takeVocInventoryRequestDto.setDeptId(deptId);
            AjaxResult result = vocInventoryService.takeVocInventory(takeVocInventoryRequestDto);
            if(!result.isOK())
            {
                log.info("出库失败");
                throw new CustomException("出库失败");
            }
        });
        return AjaxResult.success();
    }
}
