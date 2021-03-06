package com.ruoyi.project.vocpurchase.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-29.
 * 采购信息
 */
@Data
@TableName("voc_purchase")
public class VocPurchase extends BasePo
{

    /**采购编号*/
    private String purchaseNo;

    /**部门标识*/
    private Long deptId;

    /**厂商标识*/
    private Long supplierId;

    /**入库标识*/
    private Long storeId;

    /**采购类型*/
    private String purchaseType;

    /**采购总数*/
    private Integer totalAmount;

    /**采购总价*/
    private Integer totalPrice;

    /**采购状态*/
    private String purchaseStatus;
}
