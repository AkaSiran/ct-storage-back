package com.ruoyi.project.vocstore.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 新增入库商品信息
 */
@Data
@ApiModel("新增入库商品信息")
public class InsertVocStoreItemRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "商品标识", required = true)
    private Long productId;

    @ApiModelProperty(value = "商品数量", required = true)
    private Integer amount;
}
