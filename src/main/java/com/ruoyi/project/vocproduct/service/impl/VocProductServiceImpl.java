package com.ruoyi.project.vocproduct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.enums.voc.VocNoPrefix;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.voc.NoUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocproduct.domain.dto.InsertVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductResponseDto;
import com.ruoyi.project.vocproduct.domain.dto.UpdateVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.po.VocProduct;
import com.ruoyi.project.vocproduct.mapper.VocProductMapper;
import com.ruoyi.project.vocproduct.service.VocProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fyc on 2021-7-27.
 * 商品信息
 */
@Slf4j
@Service
public class VocProductServiceImpl extends ServiceImpl<VocProductMapper,VocProduct> implements VocProductService
{
    @Autowired
    private VocProductMapper vocProductMapper;

    @Override
    @Transactional
    public AjaxResult insertProduct(InsertVocProductRequestDto insertVocProductRequestDto)
    {
        VocProduct vocProduct = new VocProduct();
        BeanUtils.copyProperties(insertVocProductRequestDto,vocProduct);
        vocProduct.setNo(NoUtils.generateNo(VocNoPrefix.PRODUCT.getCode()));
        vocProduct.preInsert();
        save(vocProduct);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult updateProduct(UpdateVocProductRequestDto updateVocProductRequestDto)
    {
        VocProduct vocProduct = new VocProduct();
        BeanUtils.copyProperties(updateVocProductRequestDto,vocProduct);
        vocProduct.preUpdate();
        updateById(vocProduct);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult productDetail(Long id)
    {
        VocProduct vocProduct = getById(id);
        SelectVocProductResponseDto selectVocProductResponseDto = new SelectVocProductResponseDto();
        BeanUtils.copyProperties(vocProduct,selectVocProductResponseDto);
        return AjaxResult.success(selectVocProductResponseDto);
    }

    @Override
    @Transactional
    public AjaxResult deleteProduct(Long[] ids)
    {
        List<Long> list = Arrays.asList(ids);
        for(Long id : list)
        {
            HashMap<String,Object> countMap = vocProductMapper.checkUsedCount(id);
            int allotCount = Integer.valueOf(countMap.get("allot_count").toString());
            int purchaseCount = Integer.valueOf(countMap.get("purchase_count").toString());
            int saleCount = Integer.valueOf(countMap.get("sale_count").toString());
            int storeCount = Integer.valueOf(countMap.get("store_count").toString());
            int deliverCount = Integer.valueOf(countMap.get("deliver_count").toString());
            int inventoryCount = Integer.valueOf(countMap.get("inventory_count").toString());
            if(allotCount>0 || purchaseCount>0 || saleCount>0 || storeCount>0 || deliverCount>0 || inventoryCount>0)
            {
                VocProduct vocProduct = getById(id);
                String productNo = vocProduct.getNo();
                String productName = vocProduct.getName();
                log.info("商品被占用,商品标识={},商品编号={},商品名称={},调拨={},采购={},销售={},入库={},出库={},库存={}",id,productNo,productName,allotCount,purchaseCount,saleCount,storeCount,deliverCount,inventoryCount);
                return AjaxResult.error("商品使用中,不允许删除.商品编号:"+productNo+",商品名称:"+productName);
            }
        }
        removeByIds(list);
        return AjaxResult.success();
    }

    @Override
    public List<SelectVocProductResponseDto> productList(SelectVocProductRequestDto selectVocProductRequestDto)
    {
        List<SelectVocProductResponseDto> resultList = Lists.newArrayList();
        QueryWrapper<VocProduct> queryWrapper = new QueryWrapper<VocProduct>();
        String no = selectVocProductRequestDto.getNo();
        String name = selectVocProductRequestDto.getName();
        String shortName = selectVocProductRequestDto.getShortName();
        String type = selectVocProductRequestDto.getType();
        String size = selectVocProductRequestDto.getSize();
        if(StringUtils.isNotBlank(no)) queryWrapper.eq("no",no);
        if(StringUtils.isNotBlank(name)) queryWrapper.eq("name",name);
        if(StringUtils.isNotBlank(shortName)) queryWrapper.eq("shortName",shortName);
        if(StringUtils.isNotBlank(type)) queryWrapper.eq("type",type);
        if(StringUtils.isNotBlank(size)) queryWrapper.eq("size",size);
        queryWrapper.orderByDesc("create_time");
        List<VocProduct> list = list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list))
        {
            list.forEach(item ->
            {
                SelectVocProductResponseDto selectVocProductResponseDto = new SelectVocProductResponseDto();
                BeanUtils.copyProperties(item,selectVocProductResponseDto);
                resultList.add(selectVocProductResponseDto);
            });
        }
        return resultList;
    }
}
