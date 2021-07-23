package com.ruoyi.framework.web.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fyc on 2021-7-23.
 */
@Data
public class BaseResponseDto implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**主键*/
    private Long id;

    /**创建人*/
    private String createBy;

    /**创建时间*/
    private Date createTime;

    /**修改人*/
    private String updateBy;

    /**修改时间*/
    private Date updateTime;

}
