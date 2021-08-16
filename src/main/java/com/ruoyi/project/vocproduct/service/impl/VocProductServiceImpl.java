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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fyc on 2021-7-27.
 * 商品信息
 */
@Slf4j
@Service
public class VocProductServiceImpl extends ServiceImpl<VocProductMapper,VocProduct> implements VocProductService
{
    @Override
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
    public AjaxResult deleteProduct(Long id)
    {
        return null;
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
        if(StringUtils.isNotEmpty(list))
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
