package com.ruoyi.project.vocpurchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocpurchase.domain.dto.InsertVocPurchaseRequestDto;
import com.ruoyi.project.vocpurchase.domain.dto.UpdateVocPurchaseRequestDto;
import com.ruoyi.project.vocpurchase.domain.po.VocPurchase;

/**
 * Created by Fyc on 2021-7-29.
 * 采购信息
 */
public interface VocPurchaseService extends IService<VocPurchase>
{
    /**
     * 新增采购信息
     * @param insertVocPurchaseRequestDto
     * @return
     */
    AjaxResult insertPurchase(InsertVocPurchaseRequestDto insertVocPurchaseRequestDto);

    /**
     * 修改采购信息
     * @param updateVocPurchaseRequestDto
     * @return
     */
    AjaxResult updatePurchase(UpdateVocPurchaseRequestDto updateVocPurchaseRequestDto);

    /**
     * 采购入库
     * @param id
     * @return
     */
    AjaxResult storagePurchase(Long id);
}
