package com.ruoyi.project.vocsupplier.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-26.
 * 供应商信息
 *
 */
@ApiModel("修改供应商信息")
@Data
public class UpdateVocSupplierRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "主键", required = true)
    private Long id;

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
