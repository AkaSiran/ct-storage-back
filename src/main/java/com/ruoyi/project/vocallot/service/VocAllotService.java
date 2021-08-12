package com.ruoyi.project.vocallot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocallot.domain.dto.InsertVocAllotRequestDto;
import com.ruoyi.project.vocallot.domain.dto.UpdateVocAllotRequestDto;
import com.ruoyi.project.vocallot.domain.po.VocAllot;

/**
 * Created by Fyc on 2021-8-11.
 * 调拨信息
 */
public interface VocAllotService extends IService<VocAllot>
{
    /**
     * 新增调拨信息
     * @param insertVocAllotRequestDto
     * @return
     */
    AjaxResult insertAllot(InsertVocAllotRequestDto insertVocAllotRequestDto);

    /**
     * 修改调拨信息
     * @param updateVocAllotRequestDto
     * @return
     */
    AjaxResult updateAllot(UpdateVocAllotRequestDto updateVocAllotRequestDto);

    /**
     * 调拨入库
     * @param allotId
     * @return
     */
    AjaxResult storageAllot(Long allotId);

    /**
     * 调拨出库
     * @param allotId
     * @return
     */
    AjaxResult deliverAllot(Long allotId);
}
