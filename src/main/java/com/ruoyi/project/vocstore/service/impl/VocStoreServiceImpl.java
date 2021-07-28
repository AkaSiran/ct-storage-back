package com.ruoyi.project.vocstore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.vocinventory.domain.dto.PutVocInventoryRequestDto;
import com.ruoyi.project.vocinventory.service.VocInventoryService;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreItemRequestDto;
import com.ruoyi.project.vocstore.domain.dto.InsertVocStoreRequestDto;
import com.ruoyi.project.vocstore.domain.po.VocStore;
import com.ruoyi.project.vocstore.domain.po.VocStoreItem;
import com.ruoyi.project.vocstore.mapper.VocStoreItemMapper;
import com.ruoyi.project.vocstore.mapper.VocStoreMapper;
import com.ruoyi.project.vocstore.service.VocStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Fyc on 2021-7-28.
 * 入库信息
 */
@Slf4j
@Service
public class VocStoreServiceImpl extends ServiceImpl<VocStoreMapper,VocStore> implements VocStoreService
{
    @Autowired
    private VocStoreItemMapper vocStoreItemMapper;

    @Autowired
    private VocInventoryService vocInventoryService;

    @Override
    @Transactional
    public AjaxResult insertVocStore(InsertVocStoreRequestDto insertVocStoreRequestDto)
    {
        List<InsertVocStoreItemRequestDto> storeItemRequestDtoList = insertVocStoreRequestDto.getInsertVocStoreItemRequestList();
        if(StringUtils.isEmpty(storeItemRequestDtoList))
        {
            log.info("入库失败,商品信息列表为空");
            return AjaxResult.error("未获取到商品信息");
        }
        //入库信息保存
        VocStore vocStore = new VocStore();
        Long deptId = insertVocStoreRequestDto.getDeptId();
        BeanUtils.copyProperties(insertVocStoreRequestDto,vocStore);
        //vocStore.preInsert();
        save(vocStore);
        //入库商品信息保存
        Long storeId = vocStore.getId();
        storeItemRequestDtoList.forEach(storeItemRequestDto ->
        {
            VocStoreItem vocStoreItem = new VocStoreItem();
            BeanUtils.copyProperties(storeItemRequestDto,vocStoreItem);
            vocStoreItem.setStoreId(storeId);
            //vocStoreItem.preInsert();
            vocStoreItemMapper.insert(vocStoreItem);
            //库存信息保存
            PutVocInventoryRequestDto putVocInventoryRequestDto = new PutVocInventoryRequestDto();
            BeanUtils.copyProperties(storeItemRequestDto,putVocInventoryRequestDto);
            putVocInventoryRequestDto.setDeptId(deptId);
            vocInventoryService.putVocInventory(putVocInventoryRequestDto);
        });
        return AjaxResult.success();
    }
}
