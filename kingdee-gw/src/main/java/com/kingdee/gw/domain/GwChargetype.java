package com.kingdee.gw.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.kingdee.common.annotation.Excel;
import com.kingdee.common.core.domain.BaseEntity;

/**
 * 支付方式对象 gw_chargetype
 * 
 * @author lei.ye
 * @date 2021-09-27
 */
public class GwChargetype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键标识 */
    private Long ctId;

    /** 名称 */
    @Excel(name = "名称")
    private String ctName;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 诊所编号 */
    @Excel(name = "诊所编号")
    private String officeId;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String itemType;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 领健ID */
    @Excel(name = "领健ID")
    private String lcId;

    public void setCtId(Long ctId) 
    {
        this.ctId = ctId;
    }

    public Long getCtId() 
    {
        return ctId;
    }
    public void setCtName(String ctName) 
    {
        this.ctName = ctName;
    }

    public String getCtName() 
    {
        return ctName;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setOfficeId(String officeId) 
    {
        this.officeId = officeId;
    }

    public String getOfficeId() 
    {
        return officeId;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }
    public void setItemType(String itemType) 
    {
        this.itemType = itemType;
    }

    public String getItemType() 
    {
        return itemType;
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
            .append("ctId", getCtId())
            .append("ctName", getCtName())
            .append("code", getCode())
            .append("officeId", getOfficeId())
            .append("notes", getNotes())
            .append("itemType", getItemType())
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
