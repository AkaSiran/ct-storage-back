package com.ruoyi.project.vocproduct.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.vocproduct.domain.po.VocProduct;

import java.util.HashMap;

/**
 * Created by Fyc on 2021-7-27.
 * 商品信息
 */
public interface VocProductMapper extends BaseMapper<VocProduct>
{
    /**
     * 获取各单据使用商品数量
     * @return
     */
    HashMap<String,Object> checkUsedCount(Long productId);
}
