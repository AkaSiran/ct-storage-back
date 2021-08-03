package com.ruoyi.common.enums.voc;

/**
 * Created by Fyc on 2021-8-3.
 * 出库类型
 */
public enum VocDeliverType
{
    SALE_DELIVER("001","销售出库"),

    PURCHASE_DELIVER("002","调拨出库");

    VocDeliverType(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    private final String code;

    private final String info;

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }

    public static String getDeliverName(String code)
    {
        for (VocDeliverType type : VocDeliverType.values())
        {
            if (code.equals(type.getCode()))
            {
                return type.info;
            }
        }
        return null;
    }
}
