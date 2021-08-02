package com.ruoyi.project.vocpurchase.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Fyc on 2021-8-2.
 * 采购详情
 */
@Data
@ApiModel(value = "采购详情")
public class DetailVocPurchaseResponseDto extends BaseResponseDto
{
    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "供应商标识")
    private Long supplierId;

    @ApiModelProperty(value = "入库标识")
    private Long storeId;

    @ApiModelProperty(value = "采购类型")
    private String purchaseType;

    @ApiModelProperty(value = "采购总数")
    private int totalAmount;

    @ApiModelProperty(value = "采购总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "采购状态")
    private String purchaseStatus;

    @ApiModelProperty(value = "采购商品详情列表")
    private List<DetailVocPurchaseItemResponseDto> detailVocPurchaseItemResponseDtoList;
}
