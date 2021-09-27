package com.ruoyi.project.vocpurchase.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Fyc on 2021-8-2.
 * 采购详情
 */
@Data
@ApiModel(value = "采购详情")
public class DetailVocPurchaseResponseDto extends BaseResponseDto
{
    @ApiModelProperty(value = "采购编号")
    private  String purchaseNo;

    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "厂商标识")
    private Long supplierId;

    @ApiModelProperty(value = "厂商名称")
    private String supplierName;

    @ApiModelProperty(value = "入库标识")
    private Long storeId;

    @ApiModelProperty(value = "采购类型")
    private String purchaseType;

    @ApiModelProperty(value = "采购类型名称")
    private String purchaseTypeName;

    @ApiModelProperty(value = "采购总数")
    private Integer totalAmount;

    @ApiModelProperty(value = "采购总价")
    private Double totalPrice;

    @ApiModelProperty(value = "采购状态")
    private String purchaseStatus;

    @ApiModelProperty(value = "采购商品详情列表")
    private List<DetailVocPurchaseItemResponseDto> itemList;
}
