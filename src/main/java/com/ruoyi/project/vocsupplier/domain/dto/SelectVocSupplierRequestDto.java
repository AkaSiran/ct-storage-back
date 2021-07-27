package com.ruoyi.project.vocsupplier.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-26.
 * 供应商信息
 */
@Data
@ApiModel("供应商信息分页列表")
public class SelectVocSupplierRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "供应商编号")
    private String no;

    @ApiModelProperty(value = "供应商名称")
    private String name;

    @ApiModelProperty(value = "供应商简称")
    private String shortName;
}
