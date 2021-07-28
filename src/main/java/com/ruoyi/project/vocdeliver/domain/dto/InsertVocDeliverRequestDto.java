package com.ruoyi.project.vocdeliver.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Fyc on 2021-7-28.
 * 新增出库信息
 */
@Data
@ApiModel("新增出库信息")
public class InsertVocDeliverRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "部门标识")
    private Long deptId;

    @ApiModelProperty(value = "出库类型")
    private String deliverType;

    @ApiModelProperty(value = "出库商品信息列表")
    private List<InsertVocDeliverItemRequestDto> insertVocDeliverItemRequestDtoList;

}
