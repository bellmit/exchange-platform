package com.kingdee.gw.mapper;

import java.util.List;
import com.kingdee.gw.domain.GwChargetype;

/**
 * 支付方式Mapper接口
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
public interface GwChargetypeMapper 
{
    /**
     * 查询支付方式
     * 
     * @param ctId 支付方式主键
     * @return 支付方式
     */
    public GwChargetype selectGwChargetypeByCtId(Long ctId);

    /**
     * 查询支付方式列表
     * 
     * @param gwChargetype 支付方式
     * @return 支付方式集合
     */
    public List<GwChargetype> selectGwChargetypeList(GwChargetype gwChargetype);

    /**
     * 新增支付方式
     * 
     * @param gwChargetype 支付方式
     * @return 结果
     */
    public int insertGwChargetype(GwChargetype gwChargetype);

    /**
     * 修改支付方式
     * 
     * @param gwChargetype 支付方式
     * @return 结果
     */
    public int updateGwChargetype(GwChargetype gwChargetype);

    /**
     * 删除支付方式
     * 
     * @param ctId 支付方式主键
     * @return 结果
     */
    public int deleteGwChargetypeByCtId(Long ctId);

    /**
     * 批量删除支付方式
     * 
     * @param ctIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGwChargetypeByCtIds(String[] ctIds);
}
