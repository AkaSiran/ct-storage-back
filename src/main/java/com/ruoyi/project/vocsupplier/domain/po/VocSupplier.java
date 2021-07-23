package com.ruoyi.project.vocsupplier.domain.po;

import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-23.
 * 供应商
 */
@Data
public class VocSupplier extends BasePo
{
    private String no;

    private String name;

    private String shortName;

    private String phone;

    private String fax;

    private String address;

    private String managerName;

    private String managerPhone;
}
