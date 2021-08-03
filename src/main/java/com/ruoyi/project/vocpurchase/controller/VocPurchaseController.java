package com.ruoyi.project.vocpurchase.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.vocpurchase.domain.dto.*;
import com.ruoyi.project.vocpurchase.service.VocPurchaseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PatchMapping("/update")
    public AjaxResult updatePurchase(@RequestBody UpdateVocPurchaseRequestDto updateVocPurchaseRequestDto)
    {
        return vocPurchaseService.updatePurchase(updateVocPurchaseRequestDto);
    }

    /**
     * 采购入库
     * @param id
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "采购入库", notes = "采购订单入库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @GetMapping("/storage/{id}")
    public AjaxResult storagePurchase(@PathVariable("id") Long id)
    {
        return vocPurchaseService.storagePurchase(id);
    }

    /**
     * 获取采购详情
     * @param id
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "采购详情", notes = "根据主键获取采购详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = DetailVocPurchaseResponseDto.class)
    })
    @GetMapping("/detail/{id}")
    public AjaxResult purchaseDetail(@PathVariable("id") Long id)
    {
        return vocPurchaseService.purchaseDetail(id);
    }

    /**
     * 获取采购分页列表
     * @param selectVocPurchaseRequestDto
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "请求参数", dataTypeClass = SelectVocPurchaseRequestDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ListVocPurchaseResponseDto.class)
    })
    @ApiOperation(value = "采购分页列表", notes = "获取采购分页列表信息")
    @ApiOperationSupport(author = "Fyc")
    @GetMapping("/page")
    public TableDataInfo purchasePage(SelectVocPurchaseRequestDto selectVocPurchaseRequestDto)
    {
        startPage();
        List<ListVocPurchaseResponseDto> list = vocPurchaseService.purchaseList(selectVocPurchaseRequestDto);
        return getDataTable(list);
    }

    @ApiOperation(value = "删除采购信息", notes = "根据主键删除采购信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiOperationSupport(author = "Fyc")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deletePurchase(@PathVariable("id") Long id)
    {
        return vocPurchaseService.deletePurchase(id);
    }
}
