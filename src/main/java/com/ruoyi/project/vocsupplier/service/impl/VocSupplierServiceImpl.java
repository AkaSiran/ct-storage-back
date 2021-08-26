package com.ruoyi.project.vocsupplier.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.enums.voc.VocNoPrefix;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.voc.NoUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchase;
import com.ruoyi.project.vocpurchase.service.VocPurchaseService;
import com.ruoyi.project.vocsupplier.domain.dto.InsertVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierResponseDto;
import com.ruoyi.project.vocsupplier.domain.dto.UpdateVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.po.VocSupplier;
import com.ruoyi.project.vocsupplier.mapper.VocSupplierMapper;
import com.ruoyi.project.vocsupplier.service.VocSupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Fyc on 2021-7-23.
 * 厂商信息
 */
@Slf4j
@Service
public class VocSupplierServiceImpl extends ServiceImpl<VocSupplierMapper,VocSupplier> implements VocSupplierService
{

    @Autowired
    private VocPurchaseService vocPurchaseService;

    @Override
    public AjaxResult insertSupplier(InsertVocSupplierRequestDto insertVocSupplierRequestDto)
    {
        VocSupplier supplierPo = new VocSupplier();
        BeanUtils.copyProperties(insertVocSupplierRequestDto,supplierPo);
        supplierPo.setNo(NoUtils.generateNo(VocNoPrefix.SUPPLIER.getCode()));
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
    public AjaxResult deleteSupplier(Long[] ids)
    {
        List<Long> list = Arrays.asList(ids);
        for(Long id : list)
        {
            int purchaseCount = vocPurchaseService.count(new QueryWrapper<VocPurchase>().eq("supplier_id",id));
            if(purchaseCount>0)
            {
                VocSupplier vocSupplier = getById(id);
                String supplierNo = vocSupplier.getNo();
                String supplierName = vocSupplier.getName();
                log.info("厂商被占用,厂商标识={},厂商编号={},厂商名称={}",id,supplierNo,supplierName);
                return AjaxResult.error("厂商使用中,不允许删除.厂商编号:"+supplierNo+",厂商名称:"+supplierName);
            }
        }
        removeByIds(list);
        return AjaxResult.success();
    }

    @Override
    public List<SelectVocSupplierResponseDto> supplierList(SelectVocSupplierRequestDto selectVocSupplierRequestDto)
    {
        QueryWrapper<VocSupplier> queryWrapper = new QueryWrapper<VocSupplier>();
        List<SelectVocSupplierResponseDto> resultList = Lists.newArrayList();
        String no = selectVocSupplierRequestDto.getNo();
        String name = selectVocSupplierRequestDto.getName();
        String shortName = selectVocSupplierRequestDto.getShortName();
        if(StringUtils.isNotBlank(no)) queryWrapper.like("no",no);
        if(StringUtils.isNotBlank(name)) queryWrapper.like("name",name);
        if(StringUtils.isNotBlank(shortName)) queryWrapper.like("shortName",shortName);
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
