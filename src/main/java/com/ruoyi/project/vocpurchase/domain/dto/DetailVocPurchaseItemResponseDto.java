package com.ruoyi.project.vocpurchase.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Fyc on 2021-8-2.
 * 采购商品详情
 */
@Data
@ApiModel(value = "采购商品详情")
public class DetailVocPurchaseItemResponseDto extends BaseResponseDto
{
    @ApiModelProperty(value = "采购标识")
    private Long purchaseId;

    @ApiModelProperty(value = "商品标识")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private int amount;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal singlePrice;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "排序")
    private int sort;
}
