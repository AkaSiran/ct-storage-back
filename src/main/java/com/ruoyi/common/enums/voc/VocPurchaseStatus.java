package com.ruoyi.common.enums.voc;

/**
 * Created by Fyc on 2021-7-29.
 * 采购状态
 */
public enum VocPurchaseStatus
{

    PLACED("001","已下单"),

    STORAGE("002","已入库");

    private final String code;

    private final String info;

    VocPurchaseStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
