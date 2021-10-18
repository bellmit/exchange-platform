package com.kingdee.gw.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kingdee.common.annotation.Excel;
import com.kingdee.common.core.domain.BaseEntity;

/**
 * 组织架构对象 gw_organization
 * 
 * @author lei.ye
 * @date 2021-09-27
 */
public class GwOrganization extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键标识 */
    private Long oId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String oName;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 是否已删除 */
    @Excel(name = "是否已删除")
    private String isInactive;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区/县 */
    @Excel(name = "区/县")
    private String district;

    /** 机构简称 */
    @Excel(name = "机构简称")
    private String abbreviation;

    /** 医疗机构代码 */
    @Excel(name = "医疗机构代码")
    private String institutionCode;

    /** 公司ID */
    @Excel(name = "公司ID")
    private String tenantId;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 领健ID */
    @Excel(name = "领健ID")
    private String lcId;

    public void setoId(Long oId) 
    {
        this.oId = oId;
    }

    public Long getoId() 
    {
        return oId;
    }
    public void setoName(String oName) 
    {
        this.oName = oName;
    }

    public String getoName() 
    {
        return oName;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setIsInactive(String isInactive) 
    {
        this.isInactive = isInactive;
    }

    public String getIsInactive() 
    {
        return isInactive;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }
    public void setAbbreviation(String abbreviation) 
    {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() 
    {
        return abbreviation;
    }
    public void setInstitutionCode(String institutionCode) 
    {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionCode() 
    {
        return institutionCode;
    }
    public void setTenantId(String tenantId) 
    {
        this.tenantId = tenantId;
    }

    public String getTenantId() 
    {
        return tenantId;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setLcId(String lcId) 
    {
        this.lcId = lcId;
    }

    public String getLcId() 
    {
        return lcId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oId", getoId())
            .append("oName", getoName())
            .append("code", getCode())
            .append("isInactive", getIsInactive())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("abbreviation", getAbbreviation())
            .append("institutionCode", getInstitutionCode())
            .append("tenantId", getTenantId())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("lcId", getLcId())
            .toString();
    }
}
