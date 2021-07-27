package com.ruoyi.project.vocproduct.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-27.
 */
@Data
@ApiModel(value = "修改商品信息")
public class UpdateVocProductRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    @ApiModelProperty(value = "商品编号")
    private String no;

    @ApiModelProperty(value = "商品名称", required = true)
    private String name;

    @ApiModelProperty(value = "商品简称")
    private String shortName;

    @ApiModelProperty(value = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品型号")
    private String size;
}
