package com.ruoyi.project.vocsale.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Fyc on 2021-8-2.
 * 修改销售商品信息
 */
@Data
@ApiModel(value = "修改销售商品信息")
public class UpdateVocSaleItemRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "商品标识")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private int amount;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal singlePrice;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal totalPrice;
}
