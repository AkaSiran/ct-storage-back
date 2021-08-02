package com.ruoyi.project.vocsale.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Fyc on 2021-8-2.
 * 销售商品详情
 */
@Data
@ApiModel(value = "销售商品详情")
public class DetailVocSaleItemResponseDto extends BaseResponseDto
{

    @ApiModelProperty(value = "销售标识")
    private Long saleId;

    @ApiModelProperty(value = "商品标识")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private int amount;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal singlePrice;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal totalPrice;
}
