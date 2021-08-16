package com.ruoyi.framework.web.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fyc  on 2021-8-16.
 */
@Data
public class BasePoWithoutLogic implements Serializable
{
    /**主键*/
    @TableId
    private Long id;

    /**创建人*/
    private Long createBy;

    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**修改人*/
    private Long updateBy;

    /**修改时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**备注信息*/
    private String remarks;

    /**
     * 新增初始化,需手动调用
     */
    public void preInsert()
    {
        this.createBy = SecurityUtils.getUserId();
        this.updateBy = SecurityUtils.getUserId();
        this.createTime = DateUtils.getNowDate();
        this.updateTime = DateUtils.getNowDate();
    }

    /**
     * 修改初始化,需手动调用
     */
    public void preUpdate()
    {
        this.updateBy = SecurityUtils.getUserId();
        this.updateTime = DateUtils.getNowDate();
    }
}
