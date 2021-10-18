package com.kingdee.gw.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kingdee.common.annotation.Excel;
import com.kingdee.common.core.domain.BaseEntity;

/**
 * 收费类别对象 gw_super_category
 * 
 * @author lei.ye
 * @date 2021-09-27
 */
public class GwSuperCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键标识 */
    private Long scId;

    /** 名称 */
    @Excel(name = "名称")
    private String scName;

    /** 诊所编号 */
    @Excel(name = "诊所编号")
    private String officeId;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 领健ID */
    @Excel(name = "领健ID")
    private String lcId;

    public void setScId(Long scId) 
    {
        this.scId = scId;
    }

    public Long getScId() 
    {
        return scId;
    }
    public void setScName(String scName) 
    {
        this.scName = scName;
    }

    public String getScName() 
    {
        return scName;
    }
    public void setOfficeId(String officeId) 
    {
        this.officeId = officeId;
    }

    public String getOfficeId() 
    {
        return officeId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("scId", getScId())
            .append("scName", getScName())
            .append("officeId", getOfficeId())
            .append("status", getStatus())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("lcId", getLcId())
            .toString();
    }
}
