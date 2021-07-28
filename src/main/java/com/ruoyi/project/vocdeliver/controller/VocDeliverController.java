package com.ruoyi.project.vocdeliver.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocdeliver.domain.dto.InsertVocDeliverRequestDto;
import com.ruoyi.project.vocdeliver.service.VocDeliverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fyc on 2021-7-28.
 * 出库信息
 */
@Api(tags = "出库信息")
@RestController
@RequestMapping("/voc/deliver")
public class VocDeliverController extends BaseController
{

    @Autowired
    private VocDeliverService vocDeliverService;

    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "新增出库信息", notes = "添加商品出库信息")
    @PostMapping("/insert")
    public AjaxResult insertDeliver(@RequestBody InsertVocDeliverRequestDto insertVocDeliverRequestDto)
    {
        return vocDeliverService.insertVocDeliver(insertVocDeliverRequestDto);
    }
}
