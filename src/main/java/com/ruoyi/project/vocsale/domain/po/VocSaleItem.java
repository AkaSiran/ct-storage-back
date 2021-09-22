package com.ruoyi.project.vocsale.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePoWithoutLogic;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-2.
 * 销售商品信息
 */
@Data
@TableName("voc_sale_item")
public class VocSaleItem extends BasePoWithoutLogic
{
    /**销售标识*/
    private Long saleId;

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
