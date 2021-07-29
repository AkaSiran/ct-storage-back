package com.ruoyi.project.vocinventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocinventory.domain.dto.PutVocInventoryRequestDto;
import com.ruoyi.project.vocinventory.domain.dto.TakeVocInventoryRequestDto;
import com.ruoyi.project.vocinventory.domain.po.VocInventory;
import com.ruoyi.project.vocinventory.mapper.VocInventoryMapper;
import com.ruoyi.project.vocinventory.service.VocInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Fyc on 2021-7-28.
 * 库存信息
 */
@Slf4j
@Service
public class VocInventoryServiceImpl extends ServiceImpl<VocInventoryMapper,VocInventory> implements VocInventoryService
{
    @Override
    public AjaxResult putVocInventory(PutVocInventoryRequestDto putVocInventoryRequestDto)
    {
        Long productId = putVocInventoryRequestDto.getProductId();
        Long deptId = putVocInventoryRequestDto.getDeptId();
        int amount = putVocInventoryRequestDto.getAmount();
        VocInventory existInventory = getOne(new QueryWrapper<VocInventory>()
                .eq("product_id",productId)
                .eq("dept_id",deptId));
        //库存中已存在该商品信息
        if(StringUtils.isNotNull(existInventory) && StringUtils.isNotNull(existInventory.getId()))
        {
            Long id = existInventory.getId();
            int existAmount = existInventory.getAmount();
            int nowAmount =  existAmount+amount;
            VocInventory vocInventory = new VocInventory();
            vocInventory.setId(id);
            vocInventory.setAmount(nowAmount);
            vocInventory.preUpdate();
            log.info("旧商品入库:部门标识 = {},商品标识 = {},原数量 = {},增加数量 = {},现数量 = {}",deptId,productId,existAmount,amount,nowAmount);
            updateById(vocInventory);
            return AjaxResult.success(vocInventory);
        }else //库存中不存在该商品信息
        {
            VocInventory vocInventory = new VocInventory();
            vocInventory.setProductId(productId);
            vocInventory.setDeptId(deptId);
            vocInventory.setAmount(amount);
            vocInventory.preInsert();
            log.info("新商品入库:部门标识 = {},商品标识 = {},增加数量 = {}",deptId,productId,amount);
            save(vocInventory);
            return AjaxResult.success(vocInventory);
        }
    }

    @Override
    public AjaxResult takeVocInventory(TakeVocInventoryRequestDto takeVocInventoryRequestDto)
    {
        Long productId = takeVocInventoryRequestDto.getProductId();
        Long deptId = takeVocInventoryRequestDto.getDeptId();
        int amount = takeVocInventoryRequestDto.getAmount();
        VocInventory existInventory = getOne(new QueryWrapper<VocInventory>()
                .eq("product_id",productId)
                .eq("dept_id",deptId));
        if(StringUtils.isNotNull(existInventory) && StringUtils.isNotNull(existInventory.getId()))
        {
            int existAmount = existInventory.getAmount();
            Long id = existInventory.getId();
            if(amount-existAmount > 0)
            {
                log.info("库存不足,商品出库失败:部门标识 = {},商品标识 = {},原库存数量 = {},需出库数量 = {}",deptId,productId,existAmount,amount);
                return AjaxResult.error("库存数量不足");
            }
            int newAmount = existAmount-amount;
            VocInventory vocInventory = new VocInventory();
            vocInventory.setId(id);
            vocInventory.setAmount(newAmount);
            vocInventory.preUpdate();
            log.info("商品出库:部门标识 = {},商品标识 = {},原库存数量 = {},需出库数量 = {},现数量 = {}",deptId,productId,existAmount,amount,newAmount);
            updateById(vocInventory);
            return AjaxResult.success(vocInventory);
        }
        return AjaxResult.error("未获取到商品信息");
    }
}
