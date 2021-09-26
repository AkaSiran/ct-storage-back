package com.ruoyi.project.vocpurchase.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Fyc on 2021-7-29.
 * 新增采购信息
 */
@Data
@ApiModel(value = "新增采购信息")
public class InsertVocPurchaseRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "厂商标识")
    private Long supplierId;

    @ApiModelProperty(value = "采购类型")
    private String purchaseType;

    @ApiModelProperty(value = "采购总数")
    private Integer totalAmount;

    @ApiModelProperty(value = "采购总价")
    private Double totalPrice;

    @ApiModelProperty(value = "采购商品信息列表")
    private List<InsertVocPurchaseItemRequestDto> itemList;
}
