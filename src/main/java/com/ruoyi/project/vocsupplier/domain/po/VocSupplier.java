package com.ruoyi.project.vocsupplier.domain.po;

import com.ruoyi.framework.web.domain.po.BasePo;
import lombok.Data;

/**
 * Created by Fyc on 2021-7-23.
 * 厂商
 */
@Data
public class VocSupplier extends BasePo
{
    /**厂商编号*/
    private String no;

    /**厂商名称*/
    private String name;

    /**厂商简称*/
    private String shortName;

    /**厂商电话*/
    private String phone;

    /**厂商传真*/
    private String fax;

    /**厂商地址*/
    private String address;

    /**负责人姓名*/
    private String managerName;

    /**负责人联系方式*/
    private String managerPhone;
}
