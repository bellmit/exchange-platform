package com.kingdee.gw.service.impl;

import java.util.List;
import com.kingdee.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kingdee.gw.mapper.GwSuperCategoryMapper;
import com.kingdee.gw.domain.GwSuperCategory;
import com.kingdee.gw.service.IGwSuperCategoryService;
import com.kingdee.common.core.text.Convert;

/**
 * 收费类别Service业务层处理
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Service
public class GwSuperCategoryServiceImpl implements IGwSuperCategoryService 
{
    @Autowired
    private GwSuperCategoryMapper gwSuperCategoryMapper;

    /**
     * 查询收费类别
     * 
     * @param scId 收费类别主键
     * @return 收费类别
     */
    @Override
    public GwSuperCategory selectGwSuperCategoryByScId(Long scId)
    {
        return gwSuperCategoryMapper.selectGwSuperCategoryByScId(scId);
    }

    /**
     * 查询收费类别列表
     * 
     * @param gwSuperCategory 收费类别
     * @return 收费类别
     */
    @Override
    public List<GwSuperCategory> selectGwSuperCategoryList(GwSuperCategory gwSuperCategory)
    {
        return gwSuperCategoryMapper.selectGwSuperCategoryList(gwSuperCategory);
    }

    /**
     * 新增收费类别
     * 
     * @param gwSuperCategory 收费类别
     * @return 结果
     */
    @Override
    public int insertGwSuperCategory(GwSuperCategory gwSuperCategory)
    {
        gwSuperCategory.setCreateTime(DateUtils.getNowDate());
        return gwSuperCategoryMapper.insertGwSuperCategory(gwSuperCategory);
    }

    /**
     * 修改收费类别
     * 
     * @param gwSuperCategory 收费类别
     * @return 结果
     */
    @Override
    public int updateGwSuperCategory(GwSuperCategory gwSuperCategory)
    {
        gwSuperCategory.setUpdateTime(DateUtils.getNowDate());
        return gwSuperCategoryMapper.updateGwSuperCategory(gwSuperCategory);
    }

    /**
     * 批量删除收费类别
     * 
     * @param scIds 需要删除的收费类别主键
     * @return 结果
     */
    @Override
    public int deleteGwSuperCategoryByScIds(String scIds)
    {
        return gwSuperCategoryMapper.deleteGwSuperCategoryByScIds(Convert.toStrArray(scIds));
    }

    /**
     * 删除收费类别信息
     * 
     * @param scId 收费类别主键
     * @return 结果
     */
    @Override
    public int deleteGwSuperCategoryByScId(Long scId)
    {
        return gwSuperCategoryMapper.deleteGwSuperCategoryByScId(scId);
    }
}
