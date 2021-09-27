package com.ruoyi.project.vocpurchase.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-2.
 * 获取销售信息列表
 */
@Data
@ApiModel(value = "获取销售信息列表")
public class ListVocPurchaseResponseDto extends BaseResponseDto
{

    @ApiModelProperty(value = "采购编号")
    private String purchaseNo;

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

    @ApiModelProperty(value = "采购总数")
    private Integer totalAmount;

    @ApiModelProperty(value = "采购总价")
    private Double totalPrice;

    @ApiModelProperty(value = "采购状态")
    private String purchaseStatus;
}
