package com.ruoyi.project.vocsale.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-2.
 * 新增销售商品信息
 */
@Data
@ApiModel(value = "新增销售商品信息")
public class InsertVocSaleItemRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "商品标识")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private Integer amount;

    @ApiModelProperty(value = "商品单价")
    private Double singlePrice;

    @ApiModelProperty(value = "商品总价")
    private Double totalPrice;
}
