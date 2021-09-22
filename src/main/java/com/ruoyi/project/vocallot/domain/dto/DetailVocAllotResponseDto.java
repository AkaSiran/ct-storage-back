package com.ruoyi.project.vocallot.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Fyc on 2021-8-11.
 */
@Data
@ApiModel(value = "调拨详情")
public class DetailVocAllotResponseDto extends BaseResponseDto
{
    @ApiModelProperty(value = "发货部门标识")
    private Long fromDeptId;

    @ApiModelProperty(value = "收货部门标识")
    private Long toDeptId;

    @ApiModelProperty(value = "操作部门标识")
    private Long operateDeptId;

    @ApiModelProperty(value = "调拨总数")
    private Integer totalAmount;

    @ApiModelProperty(value = "调拨类型")
    private String allotType;

    @ApiModelProperty(value = "调拨状态")
    private String allotStatus;

    @ApiModelProperty(value = "调拨商品列表")
    private List<DetailVocAllotItemResponseDto> detailVocAllotItemResponseDtoList;
}
