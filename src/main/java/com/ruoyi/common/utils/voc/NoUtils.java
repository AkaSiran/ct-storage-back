package com.ruoyi.common.utils.voc;

import com.ruoyi.common.utils.IdWorker;

/**
 * Created by Fyc on 2021-8-16.
 * 编号生成
 */
public class NoUtils
{
    public static String generateNo(String prefix)
    {
        IdWorker idWorker = new IdWorker(1L,1L,1L);
        return prefix+idWorker.nextId();
    }
}
