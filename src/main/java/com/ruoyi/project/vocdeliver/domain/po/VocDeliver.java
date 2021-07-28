package com.ruoyi.project.vocdeliver.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 出库信息
 */
@Data
@TableName("voc_deliver")
public class VocDeliver extends BasePo
{
    /**部门标识*/
    private Long deptId;

    /**出库类型*/
    private String deliverType;
}
