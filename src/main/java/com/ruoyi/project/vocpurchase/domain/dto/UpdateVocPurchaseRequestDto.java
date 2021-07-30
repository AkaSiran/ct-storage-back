package com.ruoyi.project.vocpurchase.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Fyc on 2021-7-29.
 * 修改采购信息
 */
@Data
@ApiModel(value = "修改采购信息")
public class UpdateVocPurchaseRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "供应商标识")
    private Long supplierId;

    @ApiModelProperty(value = "采购类型")
    private String purchaseType;

    @ApiModelProperty(value = "采购总数")
    private int totalAmount;

    @ApiModelProperty(value = "采购总价")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "采购状态")
    private String purchaseStatus;

    @ApiModelProperty(value = "采购商品信息列表")
    private List<UpdateVocPurchaseItemRequestDto> updateVocPurchaseItemRequestDtoList;
}
