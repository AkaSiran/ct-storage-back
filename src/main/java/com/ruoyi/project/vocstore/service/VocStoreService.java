package com.ruoyi.project.vocstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreRequestDto;
import com.ruoyi.project.vocstore.domain.po.VocStore;

/**
 * Created by Fyc on 2021-7-28.
 * 入库信息
 */
public interface VocStoreService extends IService<VocStore>
{
    /**
     * 新增入库信息
     * @param insertVocStoreRequestDto
     * @return
     */
    AjaxResult insertVocStore(InsertVocStoreRequestDto insertVocStoreRequestDto);
}
