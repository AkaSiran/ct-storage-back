package com.ruoyi.project.vocstore.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePoWithoutLogic;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 入库商品信息
 */
@Data
@TableName("voc_store_item")
public class VocStoreItem extends BasePoWithoutLogic
{
    /**入库标识*/
    private Long storeId;

    /**商品标识*/
    private Long productId;

    /**商品数量*/
    private int amount;

    /**商品排序*/
    private int sort;
}
