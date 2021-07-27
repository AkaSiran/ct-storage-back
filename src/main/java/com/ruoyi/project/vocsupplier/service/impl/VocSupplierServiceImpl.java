package com.ruoyi.project.vocsupplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocsupplier.domain.dto.InsertVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierResponseDto;
import com.ruoyi.project.vocsupplier.domain.dto.UpdateVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.po.VocSupplier;
import com.ruoyi.project.vocsupplier.mapper.VocSupplierMapper;
import com.ruoyi.project.vocsupplier.service.VocSupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fyc on 2021-7-23.
 * 供应商信息
 */
@Slf4j
@Service
public class VocSupplierServiceImpl extends ServiceImpl<VocSupplierMapper,VocSupplier> implements VocSupplierService
{

    @Override
    public AjaxResult insertSupplier(InsertVocSupplierRequestDto insertVocSupplierRequestDto)
    {
        VocSupplier supplierPo = new VocSupplier();
        BeanUtils.copyProperties(insertVocSupplierRequestDto,supplierPo);
        supplierPo.preInsert();
        save(supplierPo);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult updateSupplier(UpdateVocSupplierRequestDto updateVocSupplierRequestDto)
    {
        VocSupplier supplierPo = new VocSupplier();
        BeanUtils.copyProperties(updateVocSupplierRequestDto,supplierPo);
        supplierPo.preUpdate();
        updateById(supplierPo);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult supplierDetail(Long id)
    {
        VocSupplier supplierPo = getById(id);
        SelectVocSupplierResponseDto supplierResponseDto = new SelectVocSupplierResponseDto();
        BeanUtils.copyProperties(supplierPo,supplierResponseDto);
        return AjaxResult.success(supplierResponseDto);
    }

    @Override
    public List<SelectVocSupplierResponseDto> supplierList(SelectVocSupplierRequestDto selectVocSupplierRequestDto)
    {
        QueryWrapper<VocSupplier> queryWrapper = new QueryWrapper<VocSupplier>();
        List<SelectVocSupplierResponseDto> resultList = Lists.newArrayList();
        String no = selectVocSupplierRequestDto.getNo();
        String name = selectVocSupplierRequestDto.getName();
        String shortName = selectVocSupplierRequestDto.getShortName();
        if(StringUtils.isNotBlank(no)) queryWrapper.eq("no",no);
        if(StringUtils.isNotBlank(name)) queryWrapper.eq("name",name);
        if(StringUtils.isNotBlank(shortName)) queryWrapper.eq("shortName",shortName);
        queryWrapper.orderByDesc("create_time");
        List<VocSupplier> list = list(queryWrapper);
        if(StringUtils.isNotEmpty(list))
        {
            list.forEach(item ->
            {
                SelectVocSupplierResponseDto selectVocSupplierResponseDto = new SelectVocSupplierResponseDto();
                BeanUtils.copyProperties(item,selectVocSupplierResponseDto);
                resultList.add(selectVocSupplierResponseDto);
            });
        }
        return resultList;
    }
}
