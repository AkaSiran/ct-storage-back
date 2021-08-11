package com.ruoyi.common.enums.voc;

/**
 * Created by Fyc on 2021-8-3.
 * 调拨类型
 */
public enum VocAllotType
{
    CONSULT_ALLOT("001","协商调拨"),

    HQ_ALLOT("002","总部调拨");

    VocAllotType(String code, String info)
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

    public static String getTypeName(String code)
    {
        for (VocAllotType type : VocAllotType.values())
        {
            if (code.equals(type.getCode()))
            {
                return type.info;
            }
        }
        return null;
    }
}
