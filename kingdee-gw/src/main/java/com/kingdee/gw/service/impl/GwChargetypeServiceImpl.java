package com.kingdee.gw.service.impl;

import java.util.List;
import com.kingdee.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kingdee.gw.mapper.GwChargetypeMapper;
import com.kingdee.gw.domain.GwChargetype;
import com.kingdee.gw.service.IGwChargetypeService;
import com.kingdee.common.core.text.Convert;

/**
 * 支付方式Service业务层处理
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Service
public class GwChargetypeServiceImpl implements IGwChargetypeService 
{
    @Autowired
    private GwChargetypeMapper gwChargetypeMapper;

    /**
     * 查询支付方式
     * 
     * @param ctId 支付方式主键
     * @return 支付方式
     */
    @Override
    public GwChargetype selectGwChargetypeByCtId(Long ctId)
    {
        return gwChargetypeMapper.selectGwChargetypeByCtId(ctId);
    }

    /**
     * 查询支付方式列表
     * 
     * @param gwChargetype 支付方式
     * @return 支付方式
     */
    @Override
    public List<GwChargetype> selectGwChargetypeList(GwChargetype gwChargetype)
    {
        return gwChargetypeMapper.selectGwChargetypeList(gwChargetype);
    }

    /**
     * 新增支付方式
     * 
     * @param gwChargetype 支付方式
     * @return 结果
     */
    @Override
    public int insertGwChargetype(GwChargetype gwChargetype)
    {
        gwChargetype.setCreateTime(DateUtils.getNowDate());
        return gwChargetypeMapper.insertGwChargetype(gwChargetype);
    }

    /**
     * 修改支付方式
     * 
     * @param gwChargetype 支付方式
     * @return 结果
     */
    @Override
    public int updateGwChargetype(GwChargetype gwChargetype)
    {
        gwChargetype.setUpdateTime(DateUtils.getNowDate());
        return gwChargetypeMapper.updateGwChargetype(gwChargetype);
    }

    /**
     * 批量删除支付方式
     * 
     * @param ctIds 需要删除的支付方式主键
     * @return 结果
     */
    @Override
    public int deleteGwChargetypeByCtIds(String ctIds)
    {
        return gwChargetypeMapper.deleteGwChargetypeByCtIds(Convert.toStrArray(ctIds));
    }

    /**
     * 删除支付方式信息
     * 
     * @param ctId 支付方式主键
     * @return 结果
     */
    @Override
    public int deleteGwChargetypeByCtId(Long ctId)
    {
        return gwChargetypeMapper.deleteGwChargetypeByCtId(ctId);
    }
}
