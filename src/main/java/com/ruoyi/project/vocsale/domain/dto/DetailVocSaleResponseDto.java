package com.ruoyi.project.vocsale.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Fyc on 2021-8-2.
 * 获取销售详情
 */
@Data
@ApiModel(value = "获取销售详情")
public class DetailVocSaleResponseDto extends BaseResponseDto
{
    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "销售类型")
    private String saleType;

    @ApiModelProperty(value = "销售总量")
    private int totalAmount;

    @ApiModelProperty(value = "销售总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "销售状态")
    private String saleStatus;

    @ApiModelProperty(value = "销售商品列表")
    private List<DetailVocSaleItemResponseDto> detailVocSaleItemResponseDtoList;
}
