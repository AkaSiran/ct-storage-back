package com.ruoyi.project.vocproduct.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.vocproduct.domain.dto.InsertVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductResponseDto;
import com.ruoyi.project.vocproduct.domain.dto.UpdateVocProductRequestDto;
import com.ruoyi.project.vocproduct.mapper.VocProductMapper;
import com.ruoyi.project.vocproduct.service.VocProductService;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierResponseDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Fyc on 2021-7-27.
 * 商品信息
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/base/product")
public class VocProductController extends BaseController
{
    @Autowired
    private VocProductService vocProductService;

    @Autowired
    private VocProductMapper vocProductMapper;

    /**
     * 新增商品
     * @param insertVocProductRequestDto
     * @return
     */
    @PreAuthorize("@ss.hasPermi('base:product:insert')")
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "新增商品", notes = "添加商品信息")
    @PostMapping("/insert")
    public AjaxResult insertProduct(@RequestBody InsertVocProductRequestDto insertVocProductRequestDto)
    {
        return vocProductService.insertProduct(insertVocProductRequestDto);
    }

    /**
     * 修改商品
     * @param updateVocProductRequestDto
     * @return
     */
    @PreAuthorize("@ss.hasPermi('base:product:update')")
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "修改商品", notes = "修改商品信息")
    @PutMapping("/update")
    public AjaxResult updateProduct(@RequestBody UpdateVocProductRequestDto updateVocProductRequestDto)
    {
        return vocProductService.updateProduct(updateVocProductRequestDto);
    }

    /**
     * 商品详情
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('base:product:detail')")
    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "商品详情", notes = "根据主键获取商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = SelectVocProductResponseDto.class)
    })
    @GetMapping("/detail/{id}")
    public AjaxResult productDetail(@PathVariable("id") Long id)
    {
        return vocProductService.productDetail(id);
    }

    /**
     * 获取商品分页列表
     * @param selectVocProductRequestDto
     * @return
     */
    @PreAuthorize("@ss.hasPermi('base:product:page')")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "请求参数", dataTypeClass = SelectVocSupplierRequestDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = SelectVocSupplierResponseDto.class)
    })
    @ApiOperation(value = "商品分页列表", notes = "获取商品分页列表信息")
    @ApiOperationSupport(author = "Fyc")
    @GetMapping("/page")
    public TableDataInfo productPage(SelectVocProductRequestDto selectVocProductRequestDto)
    {
        startPage();
        List<SelectVocProductResponseDto> list = vocProductService.productList(selectVocProductRequestDto);
        return getDataTable(list);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('base:product:delete')")
    @ApiOperation(value = "删除商品", notes = "根据主键删除商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true)
    })
    @ApiOperationSupport(author = "Fyc")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteProduct(@PathVariable("id") Long id)
    {
        return toAjax(vocProductService.removeById(id));
    }
}
