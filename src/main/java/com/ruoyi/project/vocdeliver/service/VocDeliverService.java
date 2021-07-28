package com.ruoyi.project.vocdeliver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverRequestDto;
import com.ruoyi.project.vocdeliver.domain.po.VocDeliver;

/**
 * Created by Fyc on 2021-7-28.
 * 出库信息
 */
public interface VocDeliverService extends IService<VocDeliver>
{
    /**
     * 新增出库信息
     * @param insertVocDeliverRequestDto
     * @return
     */
    AjaxResult insertVocDeliver(InsertVocDeliverRequestDto insertVocDeliverRequestDto);
}
