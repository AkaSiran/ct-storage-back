package com.ruoyi.common.enums.voc;

/**
 * Created by Fyc on 2021-8-16.
 * 单据编号
 */
public enum VocNoPrefix
{
    PRODUCT("PR-","商品信息"),

    SUPPLIER("SU-","供应商信息"),

    ALLOT("AL-","调拨信息"),

    DELIVER("DE-","出库信息"),

    INVENTORY("IN-","库存信息"),

    PURCHASE("PU-","采购信息"),

    SALE("SA-","销售信息"),

    STORE("ST-","入库信息");
    private final String code;

    private final String info;

    VocNoPrefix(String code, String info)
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
