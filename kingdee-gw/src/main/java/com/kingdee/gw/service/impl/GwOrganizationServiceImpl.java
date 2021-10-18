package com.kingdee.gw.service.impl;

import java.util.List;
import com.kingdee.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kingdee.gw.mapper.GwOrganizationMapper;
import com.kingdee.gw.domain.GwOrganization;
import com.kingdee.gw.service.IGwOrganizationService;
import com.kingdee.common.core.text.Convert;

/**
 * 组织架构Service业务层处理
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Service
public class GwOrganizationServiceImpl implements IGwOrganizationService 
{
    @Autowired
    private GwOrganizationMapper gwOrganizationMapper;

    /**
     * 查询组织架构
     * 
     * @param oId 组织架构主键
     * @return 组织架构
     */
    @Override
    public GwOrganization selectGwOrganizationByOId(Long oId)
    {
        return gwOrganizationMapper.selectGwOrganizationByOId(oId);
    }

    /**
     * 查询组织架构列表
     * 
     * @param gwOrganization 组织架构
     * @return 组织架构
     */
    @Override
    public List<GwOrganization> selectGwOrganizationList(GwOrganization gwOrganization)
    {
        return gwOrganizationMapper.selectGwOrganizationList(gwOrganization);
    }

    /**
     * 新增组织架构
     * 
     * @param gwOrganization 组织架构
     * @return 结果
     */
    @Override
    public int insertGwOrganization(GwOrganization gwOrganization)
    {
        gwOrganization.setCreateTime(DateUtils.getNowDate());
        return gwOrganizationMapper.insertGwOrganization(gwOrganization);
    }

    /**
     * 修改组织架构
     * 
     * @param gwOrganization 组织架构
     * @return 结果
     */
    @Override
    public int updateGwOrganization(GwOrganization gwOrganization)
    {
        gwOrganization.setUpdateTime(DateUtils.getNowDate());
        return gwOrganizationMapper.updateGwOrganization(gwOrganization);
    }

    /**
     * 批量删除组织架构
     * 
     * @param oIds 需要删除的组织架构主键
     * @return 结果
     */
    @Override
    public int deleteGwOrganizationByOIds(String oIds)
    {
        return gwOrganizationMapper.deleteGwOrganizationByOIds(Convert.toStrArray(oIds));
    }

    /**
     * 删除组织架构信息
     * 
     * @param oId 组织架构主键
     * @return 结果
     */
    @Override
    public int deleteGwOrganizationByOId(Long oId)
    {
        return gwOrganizationMapper.deleteGwOrganizationByOId(oId);
    }
}
