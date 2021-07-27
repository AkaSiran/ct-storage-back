package com.ruoyi.project.vocproduct.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-27.
 */
@Data
@TableName(value = "voc_product")
public class VocProduct extends BasePo
{
    private String no;

    private String name;

    private String shortName;

    private String type;

    private String size;
}
