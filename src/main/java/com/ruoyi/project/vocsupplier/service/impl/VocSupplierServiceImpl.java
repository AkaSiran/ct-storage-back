package com.ruoyi.project.vocsupplier.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.vocsupplier.domain.po.VocSupplier;
import com.ruoyi.project.vocsupplier.mapper.VocSupplierMapper;
import com.ruoyi.project.vocsupplier.service.VocSupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Fyc on 2021-7-23.
 */
@Slf4j
@Service
public class VocSupplierServiceImpl extends ServiceImpl<VocSupplierMapper,VocSupplier> implements VocSupplierService
{
    @Override
    public void saveSupplier()
    {
        VocSupplier supplier = new VocSupplier();
        supplier.setName("text");
        supplier.setNo("0001");
        save(supplier);
    }
}
