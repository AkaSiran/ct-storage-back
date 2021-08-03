package com.ruoyi.project.vocsupplier.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.vocsupplier.domain.dto.InsertVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierResponseDto;
import com.ruoyi.project.vocsupplier.domain.dto.UpdateVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.service.VocSupplierService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Fyc on 2021-7-23.
 * 供应商
 */
@Api(tags = "供应商管理")
@RequestMapping("/voc/supplier")
@RestController
public class VocSupplierController extends BaseController
{

    @Autowired
    private VocSupplierService vocSupplierService;

    /**
     * 新增供应商
     * @param insertVocSupplierRequestDto
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "新增供应商", notes = "添加供应商信息")
    @PostMapping("/insert")
    public AjaxResult insertSupplier(@RequestBody InsertVocSupplierRequestDto insertVocSupplierRequestDto)
    {
        return vocSupplierService.insertSupplier(insertVocSupplierRequestDto);
    }

    /**
     * 修改供应商
     * @param updateVocSupplierRequestDto
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "修改供应商",notes = "根据主键修改供应商信息")
    @PatchMapping("/update")
    public AjaxResult updateSupplier(@RequestBody UpdateVocSupplierRequestDto updateVocSupplierRequestDto)
    {
        return vocSupplierService.updateSupplier(updateVocSupplierRequestDto);
    }

    /**
     * 获取供应商详情
     * @param id
     * @return
     */
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "供应商详情", notes = "根据主键获取供应商详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = SelectVocSupplierResponseDto.class)
    })
    @GetMapping("/detail/{id}")
    public AjaxResult supplierDetail(@PathVariable("id") Long id)
    {
        return vocSupplierService.supplierDetail(id);
    }

    /**
     * 获取供应商分页列表
     * @param selectVocSupplierRequestDto
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "请求参数", dataTypeClass = SelectVocSupplierRequestDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = SelectVocSupplierResponseDto.class)
    })
    @ApiOperation(value = "供应商分页列表", notes = "获取供应商分页列表信息")
    @ApiOperationSupport(author = "Fyc")
    @GetMapping("/page")
    public TableDataInfo supplierPage(SelectVocSupplierRequestDto selectVocSupplierRequestDto)
    {
        startPage();
        List<SelectVocSupplierResponseDto> list = vocSupplierService.supplierList(selectVocSupplierRequestDto);
        return getDataTable(list);
    }

    /**
     * 删除供应商信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除供应商", notes = "根据主键删除供应商信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiOperationSupport(author = "Fyc")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteSupplier(@PathVariable("id") Long id)
    {
        return toAjax(vocSupplierService.removeById(id));
    }
}
