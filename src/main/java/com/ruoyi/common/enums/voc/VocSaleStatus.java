package com.ruoyi.common.enums.voc;

/**
 * Created by Fyc on 2021-8-3.
 * 销售状态
 */
public enum VocSaleStatus
{
    PLACED("001","已下单"),

    DELIVER("002","已出库");

    VocSaleStatus(String code, String info)
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

    public static String getStatusName(String code)
    {
        for (VocSaleStatus type : VocSaleStatus.values())
        {
            if (code.equals(type.getCode()))
            {
                return type.info;
            }
        }
        return null;
    }
}
