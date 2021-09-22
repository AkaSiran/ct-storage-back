package com.ruoyi.project.vocsale.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-2.
 * 获取销售信息列表
 */
@Data
@ApiModel(value = "获取销售信息列表")
public class ListVocSaleResponseDto extends BaseResponseDto
{
    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "销售类型")
    private String saleType;

    @ApiModelProperty(value = "销售总量")
    private Integer totalAmount;

    @ApiModelProperty(value = "销售总价")
    private Double totalPrice;

    @ApiModelProperty(value = "销售状态")
    private String saleStatus;
}
