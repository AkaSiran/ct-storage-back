package com.ruoyi.project.common;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductRequestDto;
import com.ruoyi.project.vocproduct.domain.dto.SelectVocProductResponseDto;
import com.ruoyi.project.vocproduct.service.VocProductService;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierRequestDto;
import com.ruoyi.project.vocsupplier.domain.dto.SelectVocSupplierResponseDto;
import com.ruoyi.project.vocsupplier.service.VocSupplierService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Fyc on 2021-9-1.
 * 通用组件 Controller
 */
@RestController
@RequestMapping("/common/subunit")
public class SubUnitController extends BaseController
{

    @Autowired
    private VocProductService vocProductService;

    @Autowired
    private VocSupplierService vocSupplierService;

    /**
     * 获取商品分页列表
     * @param selectVocProductRequestDto
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "请求参数", dataTypeClass = SelectVocSupplierRequestDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = SelectVocSupplierResponseDto.class)
    })
    @ApiOperation(value = "商品分页列表", notes = "获取商品分页列表信息")
    @ApiOperationSupport(author = "Fyc")
    @GetMapping("/productPage")
    public TableDataInfo productPage(SelectVocProductRequestDto selectVocProductRequestDto)
    {
        startPage();
        List<SelectVocProductResponseDto> list = vocProductService.productList(selectVocProductRequestDto);
        return getDataTable(list);
    }

    /**
     * 获取厂商分页列表
     * @param selectVocSupplierRequestDto
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "请求参数", dataTypeClass = SelectVocSupplierRequestDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功", response = SelectVocSupplierResponseDto.class)
    })
    @ApiOperation(value = "厂商分页列表", notes = "获取厂商分页列表信息")
    @ApiOperationSupport(author = "Fyc")
    @GetMapping("/supplierPage")
    public TableDataInfo supplierPage(SelectVocSupplierRequestDto selectVocSupplierRequestDto)
    {
        startPage();
        List<SelectVocSupplierResponseDto> list = vocSupplierService.supplierList(selectVocSupplierRequestDto);
        return getDataTable(list);
    }
}
