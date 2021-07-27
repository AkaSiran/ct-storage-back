package com.ruoyi.project.vocproduct.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-27.
 * 新增商品信息
 */
@Data
@ApiModel(value = "新增商品信息")
public class InsertVocProductRequestDto
{
    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品简称")
    private String shortName;

    @ApiModelProperty(value = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品型号")
    private String size;
}
