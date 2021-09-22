package com.ruoyi.project.vocallot.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Fyc on 2021-8-3.
 * 修改调拨信息
 */
@Data
@ApiModel(value = "修改调拨信息")
public class UpdateVocAllotRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "发货部门标识")
    private Long fromDeptId;

    @ApiModelProperty(value = "收货部门标识")
    private Long toDeptId;

    @ApiModelProperty(value = "调拨总数")
    private Integer totalAmount;

    @ApiModelProperty(value = "调拨类型")
    private String allotType;

    @ApiModelProperty(value = "调拨商品信息")
    private List<UpdateVocAllotItemRequestDto> updateVocAllotItemRequestDtoList;
}
