package com.ruoyi.project.vocinventory.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-28.
 * 库存信息
 */
@Data
@TableName("voc_inventory")
public class VocInventory extends BasePo
{
    /**商品标识*/
    private Long productId;

    /**部门标识*/
    private Long deptId;

    /**库存数量*/
    private Integer amount;
}
