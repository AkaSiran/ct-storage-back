package com.ruoyi.project.vocsupplier.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocsupplier.domain.dto.InsertVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierResponseDto;
import com.ruoyi.project.vocsupplier.domain.dto.UpdateVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.po.VocSupplier;

import java.util.List;

/**
 * Created by Fyc on 2021-7-23.
 * 厂商
 */
public interface VocSupplierService extends IService<VocSupplier>
{

    /**
     * 新增厂商信息
     * @param insertVocSupplierRequestDto
     * @return
     */
    AjaxResult insertSupplier(InsertVocSupplierRequestDto insertVocSupplierRequestDto);

    /**
     * 修改厂商信息
     * @param updateVocSupplierRequestDto
     * @return
     */
    AjaxResult updateSupplier(UpdateVocSupplierRequestDto updateVocSupplierRequestDto);

    /**
     * 获取厂商信息
     * @param id
     * @return
     */
    AjaxResult supplierDetail(Long id);

    /**
     * 删除厂商信息
     * @param ids
     * @return
     */
    AjaxResult deleteSupplier(Long[] ids);

    /**
     * 获取厂商信息列表
     * @param selectVocSupplierRequestDto
     * @return
     */
    List<SelectVocSupplierResponseDto> supplierList(SelectVocSupplierRequestDto selectVocSupplierRequestDto);
}
