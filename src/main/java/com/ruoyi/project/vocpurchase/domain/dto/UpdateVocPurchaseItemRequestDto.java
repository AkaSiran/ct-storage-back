package com.ruoyi.project.vocpurchase.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-29.
 * 修改采购商品信息
 */
@Data
@ApiModel(value = "修改采购商品信息")
public class UpdateVocPurchaseItemRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "采购标识")
    private Long purchaseId;

    @ApiModelProperty(value = "商品标识")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private Integer amount;

    @ApiModelProperty(value = "商品单价")
    private Double singlePrice;

    @ApiModelProperty(value = "商品总价")
    private Double totalPrice;
}
