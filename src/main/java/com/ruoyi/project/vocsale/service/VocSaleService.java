package com.ruoyi.project.vocsale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocsale.domain.dto.InsertVocSaleRequestDto;
import com.ruoyi.project.vocsale.domain.dto.ListVocSaleResponseDto;
import com.ruoyi.project.vocsale.domain.dto.SelectVocSaleRequestDto;
import com.ruoyi.project.vocsale.domain.dto.UpdateVocSaleRequestDto;
import com.ruoyi.project.vocsale.domain.po.VocSale;

import java.util.List;

/**
 * Created by Fyc on 2021-8-2.
 * 销售信息
 */
public interface VocSaleService extends IService<VocSale>
{
    /**
     * 新增销售信息
     * @param insertVocSaleRequestDto
     * @return
     */
    AjaxResult insertSale(InsertVocSaleRequestDto insertVocSaleRequestDto);

    /**
     * 修改销售信息
     * @param updateVocSaleRequestDto
     * @return
     */
    AjaxResult updateSale(UpdateVocSaleRequestDto updateVocSaleRequestDto);

    /**
     * 销售出库
     * @param id
     * @return
     */
    AjaxResult deliverSale(Long id);

    /**
     * 获取销售详情
     * @param id
     * @return
     */
    AjaxResult saleDetail(Long id);

    /**
     * 删除销售详情
     * @param id
     * @return
     */
    AjaxResult deleteSale(Long id);

    /**
     * 获取销售信息列表
     * @param selectVocSaleRequestDto
     * @return
     */
    List<ListVocSaleResponseDto> saleList(SelectVocSaleRequestDto selectVocSaleRequestDto);
}
