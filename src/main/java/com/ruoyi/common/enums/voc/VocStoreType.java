package com.ruoyi.common.enums.voc;

/**
 * Created by Fyc on 2021-7-30.
 * 入库类型
 */
public enum VocStoreType
{
    PURCHASE_STORAGE("001","采购入库"),

    ALLOT_STORAGE("002","调拨入库");


    VocStoreType(String code, String info)
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

    public static String getStorageName(String code)
    {
        for (VocStoreType type : VocStoreType.values())
        {
            if (code.equals(type.getCode()))
            {
                return type.info;
            }
        }
        return null;
    }
}
