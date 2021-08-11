package com.ruoyi.project.vocallot.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-11.
 */
@Data
@ApiModel(value = "调拨信息")
public class SelectVocAllotRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "发货部门标识")
    private Long fromDeptId;

    @ApiModelProperty(value = "收货部门标识")
    private Long toDeptId;

    @ApiModelProperty(value = "调拨类型")
    private String allotType;

    @ApiModelProperty(value = "调拨状态")
    private String allotStatus;
}
