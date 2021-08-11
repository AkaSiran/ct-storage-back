package com.ruoyi.project.vocallot.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-3.
 * 调拨商品信息
 */
@Data
@TableName("voc_allot_item")
public class VocAllotItem extends BasePo
{

    /**调拨标识*/
    private Long allotId;

    /**商品标识*/
    private Long productId;

    /**商品数量*/
    private int amount;
}
