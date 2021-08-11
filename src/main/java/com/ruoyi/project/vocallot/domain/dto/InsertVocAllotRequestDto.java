package com.ruoyi.project.vocallot.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-3.
 * 新增调拨信息
 */
@Data
@ApiModel(value = "新增调拨信息")
public class InsertVocAllotRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "发货部门标识")
    private Long fromDeptId;

    @ApiModelProperty(value = "收货部门标识")
    private Long toDeptId;

    @ApiModelProperty(value = "调拨总数")
    private int totalAmount;

    @ApiModelProperty(value = "调拨类型")
    private String allotType;
}
