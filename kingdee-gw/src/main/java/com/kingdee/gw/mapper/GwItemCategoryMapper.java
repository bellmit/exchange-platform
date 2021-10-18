package com.kingdee.gw.mapper;

import java.util.List;
import com.kingdee.gw.domain.GwItemCategory;

/**
 * 收费小类Mapper接口
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
public interface GwItemCategoryMapper 
{
    /**
     * 查询收费小类
     * 
     * @param icId 收费小类主键
     * @return 收费小类
     */
    public GwItemCategory selectGwItemCategoryByIcId(Long icId);

    /**
     * 查询收费小类列表
     * 
     * @param gwItemCategory 收费小类
     * @return 收费小类集合
     */
    public List<GwItemCategory> selectGwItemCategoryList(GwItemCategory gwItemCategory);

    /**
     * 新增收费小类
     * 
     * @param gwItemCategory 收费小类
     * @return 结果
     */
    public int insertGwItemCategory(GwItemCategory gwItemCategory);

    /**
     * 修改收费小类
     * 
     * @param gwItemCategory 收费小类
     * @return 结果
     */
    public int updateGwItemCategory(GwItemCategory gwItemCategory);

    /**
     * 删除收费小类
     * 
     * @param icId 收费小类主键
     * @return 结果
     */
    public int deleteGwItemCategoryByIcId(Long icId);

    /**
     * 批量删除收费小类
     * 
     * @param icIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGwItemCategoryByIcIds(String[] icIds);
}
