package com.ruoyi.project.vocstore.domain.dto;

import com.ruoyi.framework.web.domain.dto.BaseRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Fyc on 2021-7-28.
 * 新增入库信息
 */
@Data
@ApiModel("新增入库信息")
public class InsertVocStoreRequestDto extends BaseRequestDto
{
    @ApiModelProperty(value = "部门标识", required = true)
    private Long deptId;

    @ApiModelProperty(value = "入库类型", required = true)
    private String storeType;

    @ApiModelProperty(value = "入库商品列表", required = true)
    List<InsertVocStoreItemRequestDto> insertVocStoreItemRequestList;
}
