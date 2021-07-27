package com.ruoyi.project.vocsupplier.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-26.
 * 供应商
 */
@Data
@ApiModel("新增供应商信息")
public class InsertVocSupplierRequestDto
{

    @ApiModelProperty(value = "供应商名称", required = true)
    private String name;

    @ApiModelProperty(value = "供应商简称")
    private String shortName;

    @ApiModelProperty(value = "供应商联系方式")
    private String phone;

    @ApiModelProperty(value = "供应商传真")
    private String fax;

    @ApiModelProperty(value = "供应商地址")
    private String address;

    @ApiModelProperty(value = "供应商负责人")
    private String managerName;

    @ApiModelProperty(value = "供应商负责人联系方式")
    private String managerPhone;
}
