package com.ruoyi.common.utils.voc;

import com.ruoyi.common.utils.StringUtils;

/**
 * Created by Fyc on 2021-9-22.
 * 金额转换
 */
public class PriceUtils
{

    /**
     * Integer -> Double
     * @param sourcePrice 原始金额
     * @return targetPrice 目标金额
     */
    public static Double i2d(Integer sourcePrice)
    {
        Double targetPrice = 0.0;
        if(StringUtils.isNotNull(sourcePrice))
        {
            targetPrice = sourcePrice.doubleValue()/100.0;
        }
        return targetPrice;
    }

    /**
     * Double -> Integer
     * @param sourcePrice 原始金额
     * @return targetPrice 目标金额
     */
    public static Integer d2i(Double sourcePrice)
    {
        Integer targetPrice = 0;
        if(StringUtils.isNotNull(sourcePrice))
        {
            targetPrice = (new Double(sourcePrice * 100)).intValue();
        }
        return targetPrice;
    }
}
