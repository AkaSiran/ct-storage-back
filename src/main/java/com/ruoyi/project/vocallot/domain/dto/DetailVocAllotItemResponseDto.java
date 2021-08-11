package com.ruoyi.project.vocallot.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseResponseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-11.
 */
@Data
@ApiModel(value = "调拨商品详情")
public class DetailVocAllotItemResponseDto extends BaseResponseDto
{
    @ApiModelProperty(value = "调拨标识")
    private Long allotId;

    @ApiModelProperty(value = "商品标识")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private int amount;

    @ApiModelProperty(value = "排序")
    private int sort;
}
