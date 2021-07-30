package com.ruoyi.project.vocpurchase.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocpurchase.domain.dto.InsertVocPurchaseRequestDto;
import com.ruoyi.project.vocpurchase.domain.dto.UpdateVocPurchaseRequestDto;
import com.ruoyi.project.vocpurchase.service.VocPurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fyc on 2021-7-30.
 * 采购信息
 */
@Api(tags = "采购信息")
@RestController
@RequestMapping("/voc/purchase")
public class VocPurchaseController extends BaseController
{
    @Autowired
    private VocPurchaseService vocPurchaseService;

    /**
     * 新增采购信息
     * @param insertVocPurchaseRequestDto
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "新增采购信息", notes = "添加采购信息")
    @PostMapping("/insert")
    public AjaxResult insertPurchase(@RequestBody InsertVocPurchaseRequestDto insertVocPurchaseRequestDto)
    {
        return vocPurchaseService.insertPurchase(insertVocPurchaseRequestDto);
    }

    /**
     * 修改采购信息
     * @param updateVocPurchaseRequestDto
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "修改采购信息", notes = "修改采购信息")
    @PostMapping("/update")
    public AjaxResult updatePurchase(@RequestBody UpdateVocPurchaseRequestDto updateVocPurchaseRequestDto)
    {
        return vocPurchaseService.updatePurchase(updateVocPurchaseRequestDto);
    }
}
