package com.ruoyi.framework.web.domain.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fyc on 2021-7-23.
 * 基础PO
 */
@Data
public class BasePo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**正常*/
    private static final String DELETE_NORMAL_FLAG = "0";

    /**删除*/
    private static final String DELETE_CANCEL_FLAG = "1";

    /**冻结*/
    private static final String DELERE_FREEZE_FLAG = "2";

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

    /**备注信息*/
    private String remarks;

    /**删除标志*/
    private String delFlag;
}
