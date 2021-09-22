package com.ruoyi.project.vocsale.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-8-2.
 * 销售信息
 */
@Data
@TableName("voc_sale")
public class VocSale extends BasePo
{

    /**销售编号*/
    private String saleNo;

    /**部门标识*/
    private Long deptId;

    /**销售类型*/
    private String saleType;

    /**销售总量*/
    private Integer totalAmount;

    /**销售总价*/
    private Integer totalPrice;

    /**销售状态*/
    private String saleStatus;
}
