package com.ruoyi.project.vocsupplier.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocsupplier.service.VocSupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fyc on 2021-7-23.
 * 供应商
 */
@Api(tags = "供应商管理")
@RequestMapping("/voc/supplier")
@RestController
public class VocSupplierController
{

    @Autowired
    private VocSupplierService vocSupplierService;

    @ApiOperation(value = "添加供应商")
    @GetMapping("/save")
    public AjaxResult saveSupplier()
    {
        vocSupplierService.saveSupplier();
        return AjaxResult.success();
    }
}
