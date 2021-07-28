package com.ruoyi.project.vocstore.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreRequestDto;
import com.ruoyi.project.vocstore.service.VocStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fyc on 2021-7-28.
 * 入库信息
 */
@Api(tags = "入库信息")
@RestController
@RequestMapping("/voc/store")
public class VocStoreController extends BaseController
{

    @Autowired
    private VocStoreService vocStoreService;

    @ApiOperationSupport(author = "Fyc")
    @ApiOperation(value = "新增入库信息", notes = "添加商品入库信息")
    @PostMapping("/insert")
    public AjaxResult insertStore(@RequestBody InsertVocStoreRequestDto insertVocStoreRequestDto)
    {
        return vocStoreService.insertVocStore(insertVocStoreRequestDto);
    }
}
