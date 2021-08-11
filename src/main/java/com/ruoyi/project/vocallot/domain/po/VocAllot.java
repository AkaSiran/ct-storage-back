package com.ruoyi.project.vocallot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-3.
 * 调拨信息
 */
@Data
@TableName("voc_allot")
public class VocAllot extends BasePo
{
    /**发货部门标识*/
    private Long fromDeptId;

    /**收货部门标识*/
    private Long toDeptId;

    /**操作部门标识*/
    private Long operateDeptId;

    /**调拨总数*/
    private int totalAmount;

    /**调拨类型*/
    private String allotType;

    /**调拨状态*/
    private String allotStatus;
}
