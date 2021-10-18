package com.kingdee.gw.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kingdee.common.annotation.Log;
import com.kingdee.common.enums.BusinessType;
import com.kingdee.gw.domain.GwItemCategory;
import com.kingdee.gw.service.IGwItemCategoryService;
import com.kingdee.common.core.controller.BaseController;
import com.kingdee.common.core.domain.AjaxResult;
import com.kingdee.common.utils.poi.ExcelUtil;
import com.kingdee.common.core.page.TableDataInfo;

/**
 * 收费小类Controller
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Controller
@RequestMapping("/gw/ItemCategory")
public class GwItemCategoryController extends BaseController
{
    private String prefix = "gw/ItemCategory";

    @Autowired
    private IGwItemCategoryService gwItemCategoryService;

    @RequiresPermissions("gw:ItemCategory:view")
    @GetMapping()
    public String ItemCategory()
    {
        return prefix + "/ItemCategory";
    }

    /**
     * 查询收费小类列表
     */
    @RequiresPermissions("gw:ItemCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GwItemCategory gwItemCategory)
    {
        startPage();
        List<GwItemCategory> list = gwItemCategoryService.selectGwItemCategoryList(gwItemCategory);
        return getDataTable(list);
    }

    /**
     * 导出收费小类列表
     */
    @RequiresPermissions("gw:ItemCategory:export")
    @Log(title = "收费小类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GwItemCategory gwItemCategory)
    {
        List<GwItemCategory> list = gwItemCategoryService.selectGwItemCategoryList(gwItemCategory);
        ExcelUtil<GwItemCategory> util = new ExcelUtil<GwItemCategory>(GwItemCategory.class);
        return util.exportExcel(list, "收费小类数据");
    }

    /**
     * 新增收费小类
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存收费小类
     */
    @RequiresPermissions("gw:ItemCategory:add")
    @Log(title = "收费小类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GwItemCategory gwItemCategory)
    {
        return toAjax(gwItemCategoryService.insertGwItemCategory(gwItemCategory));
    }

    /**
     * 修改收费小类
     */
    @GetMapping("/edit/{icId}")
    public String edit(@PathVariable("icId") Long icId, ModelMap mmap)
    {
        GwItemCategory gwItemCategory = gwItemCategoryService.selectGwItemCategoryByIcId(icId);
        mmap.put("gwItemCategory", gwItemCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存收费小类
     */
    @RequiresPermissions("gw:ItemCategory:edit")
    @Log(title = "收费小类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GwItemCategory gwItemCategory)
    {
        return toAjax(gwItemCategoryService.updateGwItemCategory(gwItemCategory));
    }

    /**
     * 删除收费小类
     */
    @RequiresPermissions("gw:ItemCategory:remove")
    @Log(title = "收费小类", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gwItemCategoryService.deleteGwItemCategoryByIcIds(ids));
    }
}
