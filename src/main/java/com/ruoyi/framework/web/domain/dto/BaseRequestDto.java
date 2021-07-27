package com.ruoyi.framework.web.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fyc on 2021-7-26.
 * 基础RequestDto
 */
@Data
@ApiModel("入参信息")
public class BaseRequestDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "主键", required = true)
    private Long id;

    @ApiModelProperty(notes = "创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

    @ApiModelProperty(notes = "修改人")
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "修改时间")
    private Date updateTime;

    @ApiModelProperty(notes = "备注信息")
    private String remarks;

    @ApiModelProperty(notes = "删除标志")
    private String delFlag;
}
