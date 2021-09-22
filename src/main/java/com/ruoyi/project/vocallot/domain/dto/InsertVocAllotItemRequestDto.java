package com.ruoyi.project.vocallot.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-3.
 * 新增调拨商品信息
 */
@Data
@ApiModel(value = "新增调拨商品信息")
public class InsertVocAllotItemRequestDto extends BaseRequestDto
{

    @ApiModelProperty(value = "商品标识")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private Integer amount;
}
