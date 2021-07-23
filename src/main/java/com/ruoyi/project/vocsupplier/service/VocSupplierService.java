package com.ruoyi.project.vocsupplier.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.vocsupplier.domain.po.VocSupplier;

/**
 * Created by Fyc on 2021-7-23.
 * 供应商
 */
public interface VocSupplierService extends IService<VocSupplier>
{

    void saveSupplier();
}
