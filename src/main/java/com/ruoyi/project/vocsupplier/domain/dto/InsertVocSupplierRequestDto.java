package com.ruoyi.project.vocsupplier.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-26.
 * 厂商
 */
@Data
@ApiModel("新增厂商信息")
public class InsertVocSupplierRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "厂商名称", required = true)
    private String name;

    @ApiModelProperty(value = "厂商简称")
    private String shortName;

    @ApiModelProperty(value = "厂商联系方式")
    private String phone;

    @ApiModelProperty(value = "厂商传真")
    private String fax;

    @ApiModelProperty(value = "厂商地址")
    private String address;

    @ApiModelProperty(value = "厂商负责人")
    private String managerName;

    @ApiModelProperty(value = "厂商负责人联系方式")
    private String managerPhone;
}
