package com.ruoyi.project.vocdeliver.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 出库商品信息
 */
@Data
@TableName("voc_deliver_item")
public class VocDeliverItem extends BasePo
{
    /**出库标识*/
    private Long deliverId;

    /**商品标识*/
    private Long productId;

    /**商品数量*/
    private int amount;
}
