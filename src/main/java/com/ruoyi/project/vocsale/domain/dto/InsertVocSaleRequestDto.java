package com.ruoyi.project.vocsale.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Fyc on 2021-8-2.
 * 新增销售信息
 */
@Data
@ApiModel(value = "新增销售信息")
public class InsertVocSaleRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "销售类型")
    private String saleType;

    @ApiModelProperty(value = "销售总量")
    private Integer totalAmount;

    @ApiModelProperty(value = "销售总价")
    private Double totalPrice;

    @ApiModelProperty(value = "销售商品列表")
    List<InsertVocSaleItemRequestDto> insertVocSaleItemRequestDtoList;
}
