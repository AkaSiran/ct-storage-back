package com.ruoyi.project.vocproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocproduct.domain.dto.InsertVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductResponseDto;
import com.ruoyi.project.vocproduct.domain.dto.UpdateVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.po.VocProduct;

import java.util.List;

/**
 * Created by Fyc on 2021-7-27.
 * 商品信息
 */
public interface VocProductService extends IService<VocProduct>
{
    /**
     * 新增商品信息
     * @param insertVocProductRequestDto
     * @return
     */
    AjaxResult insertProduct(InsertVocProductRequestDto insertVocProductRequestDto);

    /**
     * 修改商品信息
     * @param updateVocProductRequestDto
     * @return
     */
    AjaxResult updateProduct(UpdateVocProductRequestDto updateVocProductRequestDto);

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    AjaxResult productDetail(Long id);

    /**
     * 获取商品信息分页列表
     * @param selectVocProductRequestDto
     * @return
     */
    List<SelectVocProductResponseDto> productList(SelectVocProductRequestDto selectVocProductRequestDto);
}
