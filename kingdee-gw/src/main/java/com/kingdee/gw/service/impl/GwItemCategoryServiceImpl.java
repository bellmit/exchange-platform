package com.kingdee.gw.service.impl;

import java.util.List;
import com.kingdee.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kingdee.gw.mapper.GwItemCategoryMapper;
import com.kingdee.gw.domain.GwItemCategory;
import com.kingdee.gw.service.IGwItemCategoryService;
import com.kingdee.common.core.text.Convert;

/**
 * 收费小类Service业务层处理
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Service
public class GwItemCategoryServiceImpl implements IGwItemCategoryService 
{
    @Autowired
    private GwItemCategoryMapper gwItemCategoryMapper;

    /**
     * 查询收费小类
     * 
     * @param icId 收费小类主键
     * @return 收费小类
     */
    @Override
    public GwItemCategory selectGwItemCategoryByIcId(Long icId)
    {
        return gwItemCategoryMapper.selectGwItemCategoryByIcId(icId);
    }

    /**
     * 查询收费小类列表
     * 
     * @param gwItemCategory 收费小类
     * @return 收费小类
     */
    @Override
    public List<GwItemCategory> selectGwItemCategoryList(GwItemCategory gwItemCategory)
    {
        return gwItemCategoryMapper.selectGwItemCategoryList(gwItemCategory);
    }

    /**
     * 新增收费小类
     * 
     * @param gwItemCategory 收费小类
     * @return 结果
     */
    @Override
    public int insertGwItemCategory(GwItemCategory gwItemCategory)
    {
        gwItemCategory.setCreateTime(DateUtils.getNowDate());
        return gwItemCategoryMapper.insertGwItemCategory(gwItemCategory);
    }

    /**
     * 修改收费小类
     * 
     * @param gwItemCategory 收费小类
     * @return 结果
     */
    @Override
    public int updateGwItemCategory(GwItemCategory gwItemCategory)
    {
        gwItemCategory.setUpdateTime(DateUtils.getNowDate());
        return gwItemCategoryMapper.updateGwItemCategory(gwItemCategory);
    }

    /**
     * 批量删除收费小类
     * 
     * @param icIds 需要删除的收费小类主键
     * @return 结果
     */
    @Override
    public int deleteGwItemCategoryByIcIds(String icIds)
    {
        return gwItemCategoryMapper.deleteGwItemCategoryByIcIds(Convert.toStrArray(icIds));
    }

    /**
     * 删除收费小类信息
     * 
     * @param icId 收费小类主键
     * @return 结果
     */
    @Override
    public int deleteGwItemCategoryByIcId(Long icId)
    {
        return gwItemCategoryMapper.deleteGwItemCategoryByIcId(icId);
    }
}
