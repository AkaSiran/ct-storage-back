package com.ruoyi.project.vocsale.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.vocsale.domain.dto.*;
import com.ruoyi.project.vocsale.service.VocSaleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Fyc on 2021-8-3.
 * 销售信息
 */
@Api(tags = "销售信息")
@RequestMapping("/voc/sale")
@RestController
public class VocSaleController extends BaseController
{
    @Autowired
    private VocSaleService vocSaleService;

    /**
     * 新增销售信息
     * @param insertVocSaleRequestDto
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "新增销售信息", notes = "添加销售信息")
    @PostMapping("/insert")
    public AjaxResult insertSale(@RequestBody InsertVocSaleRequestDto insertVocSaleRequestDto)
    {
        return vocSaleService.insertSale(insertVocSaleRequestDto);
    }

    /**
     * 修改销售信息
     * @param updateVocSaleRequestDto
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "修改销售信息",notes = "根据主键修改销售信息")
    @PatchMapping("/update")
    public AjaxResult updateSale(@RequestBody UpdateVocSaleRequestDto updateVocSaleRequestDto)
    {
        return vocSaleService.updateSale(updateVocSaleRequestDto);
    }

    /**
     * 销售出库
     * @param id
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "销售出库", notes = "根据主键销售出库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @GetMapping("/deliver")
    public AjaxResult deliverSale(@PathVariable("id") Long id)
    {
        return vocSaleService.deliverSale(id);
    }

    /**
     * 获取销售详情
     * @param id
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "销售详情", notes = "根据主键获取销售详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = DetailVocSaleResponseDto.class)
    })
    @GetMapping("/detail/{id}")
    public AjaxResult saleDetail(@PathVariable("id") Long id)
    {
        return vocSaleService.saleDetail(id);
    }

    /**
     * 获取销售信息分页列表
     * @param selectVocSaleRequestDto
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "请求参数", dataTypeClass = SelectVocSaleRequestDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = ListVocSaleResponseDto.class)
    })
    @ApiOperation(value = "销售信息分页列表", notes = "获取销售信息分页列表")
    @ApiOperationSupport(author = "Fyc")
    @GetMapping("/page")
    public TableDataInfo salePage(SelectVocSaleRequestDto selectVocSaleRequestDto)
    {
        startPage();
        List<ListVocSaleResponseDto> list = vocSaleService.saleList(selectVocSaleRequestDto);
        return getDataTable(list);
    }

    /**
     * 删除销售信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除销售信息", notes = "根据主键删除销售信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiOperationSupport(author = "Fyc")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteSale(@PathVariable("id") Long id)
    {
        return vocSaleService.deliverSale(id);
    }
}
