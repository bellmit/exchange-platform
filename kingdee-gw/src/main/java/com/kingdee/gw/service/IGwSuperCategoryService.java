package com.kingdee.gw.service;

import java.util.List;
import com.kingdee.gw.domain.GwSuperCategory;

/**
 * 收费类别Service接口
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
public interface IGwSuperCategoryService 
{
    /**
     * 查询收费类别
     * 
     * @param scId 收费类别主键
     * @return 收费类别
     */
    public GwSuperCategory selectGwSuperCategoryByScId(Long scId);

    /**
     * 查询收费类别列表
     * 
     * @param gwSuperCategory 收费类别
     * @return 收费类别集合
     */
    public List<GwSuperCategory> selectGwSuperCategoryList(GwSuperCategory gwSuperCategory);

    /**
     * 新增收费类别
     * 
     * @param gwSuperCategory 收费类别
     * @return 结果
     */
    public int insertGwSuperCategory(GwSuperCategory gwSuperCategory);

    /**
     * 修改收费类别
     * 
     * @param gwSuperCategory 收费类别
     * @return 结果
     */
    public int updateGwSuperCategory(GwSuperCategory gwSuperCategory);

    /**
     * 批量删除收费类别
     * 
     * @param scIds 需要删除的收费类别主键集合
     * @return 结果
     */
    public int deleteGwSuperCategoryByScIds(String scIds);

    /**
     * 删除收费类别信息
     * 
     * @param scId 收费类别主键
     * @return 结果
     */
    public int deleteGwSuperCategoryByScId(Long scId);
}
