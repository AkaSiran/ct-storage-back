package com.ruoyi.project.vocpurchase.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-29.
 * 采购商品信息
 */
@Data
@TableName("voc_purchase_item")
public class VocPurchaseItem extends BasePo
{
    /**采购标识*/
    private Long purchaseId;

    /**商品标识*/
    private Long productId;

    /**商品数量*/
    private Integer amount;

    /**商品单价*/
    private Integer singlePrice;

    /**商品总价*/
    private Integer totalPrice;

    /**商品排序*/
    private Integer sort;
}
