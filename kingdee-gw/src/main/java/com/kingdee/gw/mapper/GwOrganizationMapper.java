package com.kingdee.gw.mapper;

import java.util.List;
import com.kingdee.gw.domain.GwOrganization;

/**
 * 组织架构Mapper接口
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
public interface GwOrganizationMapper 
{
    /**
     * 查询组织架构
     * 
     * @param oId 组织架构主键
     * @return 组织架构
     */
    public GwOrganization selectGwOrganizationByOId(Long oId);

    /**
     * 查询组织架构列表
     * 
     * @param gwOrganization 组织架构
     * @return 组织架构集合
     */
    public List<GwOrganization> selectGwOrganizationList(GwOrganization gwOrganization);

    /**
     * 新增组织架构
     * 
     * @param gwOrganization 组织架构
     * @return 结果
     */
    public int insertGwOrganization(GwOrganization gwOrganization);

    /**
     * 修改组织架构
     * 
     * @param gwOrganization 组织架构
     * @return 结果
     */
    public int updateGwOrganization(GwOrganization gwOrganization);

    /**
     * 删除组织架构
     * 
     * @param oId 组织架构主键
     * @return 结果
     */
    public int deleteGwOrganizationByOId(Long oId);

    /**
     * 批量删除组织架构
     * 
     * @param oIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGwOrganizationByOIds(String[] oIds);
}
