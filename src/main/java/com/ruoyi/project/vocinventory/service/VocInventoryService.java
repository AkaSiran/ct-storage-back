package com.ruoyi.project.vocinventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocinventory.domain.dto.PutVocInventoryRequestDto;
import com.ruoyi.project.vocinventory.domain.dto.TakeVocInventoryRequestDto;
import com.ruoyi.project.vocinventory.domain.po.VocInventory;

/**
 * Created by Fyc on 2021-7-28.
 * 库存信息
 */
public interface VocInventoryService extends IService<VocInventory>
{
    /**
     * 商品入库
     * @param putVocInventoryRequestDto
     * @return
     */
    AjaxResult putVocInventory(PutVocInventoryRequestDto putVocInventoryRequestDto);

    /**
     * 商品出库
     * @param takeVocInventoryRequestDto
     * @return
     */
    AjaxResult takeVocInventory(TakeVocInventoryRequestDto takeVocInventoryRequestDto);
}
