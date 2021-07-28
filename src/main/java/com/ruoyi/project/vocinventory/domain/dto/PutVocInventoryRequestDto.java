package com.ruoyi.project.vocinventory.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 商品入库
 */
@Data
@ApiModel("商品入库")
public class PutVocInventoryRequestDto extends BaseRequestDto
{
    @ApiModelProperty("部门标识")
    private Long deptId;

    @ApiModelProperty("商品标识")
    private Long productId;

    @ApiModelProperty("商品数量")
    private int amount;
}
