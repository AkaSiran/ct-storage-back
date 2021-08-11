package com.ruoyi.common.enums.voc;

/**
 * Created by Fyc on 2021-8-3.
 * 调拨状态
 */
public enum VocAllotStatus
{
    ALLOT_DELIVER("001","调拨出库"),

    ALLOT_INTRANSIT("002","调拨在途"),

    ALLOT_STORAGE("003","调拨入库"),

    ALLOT_BACK("004","调拨撤回");

    VocAllotStatus(String code, String info)
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

    public static String getAllotName(String code)
    {
        for (VocAllotStatus type : VocAllotStatus.values())
        {
            if (code.equals(type.getCode()))
            {
                return type.info;
            }
        }
        return null;
    }
}
