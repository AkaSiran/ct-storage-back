package com.ruoyi.project.vocstore.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 入库信息
 */
@Data
@TableName("voc_store")
public class VocStore extends BasePo
{

    /**入库编号*/
    private String storeNo;

    /**部门标识*/
    private Long deptId;

    /**入库类型*/
    private String storeType;
}
