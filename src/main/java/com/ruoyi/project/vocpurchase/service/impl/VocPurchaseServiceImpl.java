package com.ruoyi.project.vocpurchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocpurchase.domain.dto.InsertVocPurchaseItemRequestDto;
import com.ruoyi.project.vocpurchase.domain.dto.InsertVocPurchaseRequestDto;
import com.ruoyi.project.vocpurchase.domain.dto.UpdateVocPurchaseItemRequestDto;
import com.ruoyi.project.vocpurchase.domain.dto.UpdateVocPurchaseRequestDto;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchase;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchaseItem;
import com.ruoyi.project.vocpurchase.mapper.VocPurchaseMapper;
import com.ruoyi.project.vocpurchase.service.VocPurchaseItemService;
import com.ruoyi.project.vocpurchase.service.VocPurchaseService;
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
        List<InsertVocPurchaseItemRequestDto> insertVocPurchaseItemRequestDtoList = insertVocPurchaseRequestDto.getInsertVocPurchaseItemRequestDtoList();
        if(StringUtils.isEmpty(insertVocPurchaseItemRequestDtoList))
        {
            log.info("采购商品信息列表为空");
            return AjaxResult.error("未获取到采购商品信息");
        }
        //保存采购信息
        VocPurchase vocPurchase = new VocPurchase();
        BeanUtils.copyProperties(insertVocPurchaseRequestDto,vocPurchase);
        vocPurchase.preInsert();
        save(vocPurchase);
        //保存采购商品信息
        List<VocPurchaseItem> vocPurchaseItemList = Lists.newArrayList();
        Long purchaseId = vocPurchase.getId();
        insertVocPurchaseItemRequestDtoList.forEach(insertVocPurchaseItemRequestDto ->
        {
            VocPurchaseItem vocPurchaseItem = new VocPurchaseItem();
            BeanUtils.copyProperties(insertVocPurchaseItemRequestDto,vocPurchaseItem);
            vocPurchaseItem.setPurchaseId(purchaseId);
            vocPurchaseItem.preInsert();
            vocPurchaseItemList.add(vocPurchaseItem);
        });
        vocPurchaseItemService.saveBatch(vocPurchaseItemList);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult updatePurchase(UpdateVocPurchaseRequestDto updateVocPurchaseRequestDto)
    {
        List<UpdateVocPurchaseItemRequestDto> updateVocPurchaseItemRequestDtoList = updateVocPurchaseRequestDto.getUpdateVocPurchaseItemRequestDtoList();
        if(CollectionUtils.isEmpty(updateVocPurchaseItemRequestDtoList))
        {
            log.info("采购商品信息列表为空");
            return AjaxResult.error("未获取到采购商品信息");
        }
        //修改采购信息
        VocPurchase vocPurchase = new VocPurchase();
        BeanUtils.copyProperties(updateVocPurchaseRequestDto,vocPurchase);
        vocPurchase.preUpdate();
        updateById(vocPurchase);
        //修改采购商品信息
        List<VocPurchaseItem> vocPurchaseItemList = Lists.newArrayList();
        Long purchaseId = vocPurchase.getId();
        vocPurchaseItemService.remove(new QueryWrapper<VocPurchaseItem>().eq("purchase_id",purchaseId));
        updateVocPurchaseItemRequestDtoList.forEach(updateVocPurchaseItemRequestDto ->
        {
            VocPurchaseItem vocPurchaseItem = new VocPurchaseItem();
            BeanUtils.copyProperties(updateVocPurchaseItemRequestDto,vocPurchaseItem);
            vocPurchaseItem.setPurchaseId(purchaseId);
            vocPurchaseItem.preInsert();
            vocPurchaseItemList.add(vocPurchaseItem);
        });
        vocPurchaseItemService.saveBatch(vocPurchaseItemList);
        return AjaxResult.success();
    }
}
