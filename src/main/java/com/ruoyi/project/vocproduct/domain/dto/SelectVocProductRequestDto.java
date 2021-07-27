package com.ruoyi.project.vocproduct.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-27.
 * 获取商品信息
 */
@Data
@ApiModel("获取商品信息")
public class SelectVocProductRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "商品编号")
    private String no;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品简称")
    private String shortName;

    @ApiModelProperty(value = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品型号")
    private String size;
}
