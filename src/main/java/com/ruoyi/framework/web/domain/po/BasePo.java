package com.ruoyi.framework.web.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
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

    /**删除标志*/
    @TableLogic
    private String delFlag;

    /**
     * 新增初始化,需手动调用
     */
    public void preInsert()
    {
        this.createBy = SecurityUtils.getUserId();
        this.updateBy = SecurityUtils.getUserId();
        this.createTime = DateUtils.getNowDate();
        this.updateTime = DateUtils.getNowDate();
        this.delFlag = DELETE_NORMAL_FLAG;
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
