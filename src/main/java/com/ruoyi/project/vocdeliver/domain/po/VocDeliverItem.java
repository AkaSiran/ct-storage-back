package com.ruoyi.project.vocdeliver.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePoWithoutLogic;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 出库商品信息
 */
@Data
@TableName("voc_deliver_item")
public class VocDeliverItem extends BasePoWithoutLogic
{
    /**出库标识*/
    private Long deliverId;

    /**商品标识*/
    private Long productId;

    /**商品数量*/
    private Integer amount;

    /**商品排序*/
    private Integer sort;
}
