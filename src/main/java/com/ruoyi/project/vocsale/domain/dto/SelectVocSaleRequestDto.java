package com.ruoyi.project.vocsale.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-2.
 * 获取销售商品信息
 */
@Data
@ApiModel(value = "获取销售商品信息")
public class SelectVocSaleRequestDto extends BaseRequestDto
{
    @ApiModelProperty("部门标识")
    private Long deptId;

    @ApiModelProperty("销售状态")
    private String saleStatus;

    @ApiModelProperty("销售类型")
    private String saleType;
}
