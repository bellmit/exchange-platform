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
import com.kingdee.gw.domain.GwSuperCategory;
import com.kingdee.gw.service.IGwSuperCategoryService;
import com.kingdee.common.core.controller.BaseController;
import com.kingdee.common.core.domain.AjaxResult;
import com.kingdee.common.utils.poi.ExcelUtil;
import com.kingdee.common.core.page.TableDataInfo;

/**
 * 收费类别Controller
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Controller
@RequestMapping("/gw/SuperCategory")
public class GwSuperCategoryController extends BaseController
{
    private String prefix = "gw/SuperCategory";

    @Autowired
    private IGwSuperCategoryService gwSuperCategoryService;

    @RequiresPermissions("gw:SuperCategory:view")
    @GetMapping()
    public String SuperCategory()
    {
        return prefix + "/SuperCategory";
    }

    /**
     * 查询收费类别列表
     */
    @RequiresPermissions("gw:SuperCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GwSuperCategory gwSuperCategory)
    {
        startPage();
        List<GwSuperCategory> list = gwSuperCategoryService.selectGwSuperCategoryList(gwSuperCategory);
        return getDataTable(list);
    }

    /**
     * 导出收费类别列表
     */
    @RequiresPermissions("gw:SuperCategory:export")
    @Log(title = "收费类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GwSuperCategory gwSuperCategory)
    {
        List<GwSuperCategory> list = gwSuperCategoryService.selectGwSuperCategoryList(gwSuperCategory);
        ExcelUtil<GwSuperCategory> util = new ExcelUtil<GwSuperCategory>(GwSuperCategory.class);
        return util.exportExcel(list, "收费类别数据");
    }

    /**
     * 新增收费类别
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存收费类别
     */
    @RequiresPermissions("gw:SuperCategory:add")
    @Log(title = "收费类别", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GwSuperCategory gwSuperCategory)
    {
        return toAjax(gwSuperCategoryService.insertGwSuperCategory(gwSuperCategory));
    }

    /**
     * 修改收费类别
     */
    @GetMapping("/edit/{scId}")
    public String edit(@PathVariable("scId") Long scId, ModelMap mmap)
    {
        GwSuperCategory gwSuperCategory = gwSuperCategoryService.selectGwSuperCategoryByScId(scId);
        mmap.put("gwSuperCategory", gwSuperCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存收费类别
     */
    @RequiresPermissions("gw:SuperCategory:edit")
    @Log(title = "收费类别", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GwSuperCategory gwSuperCategory)
    {
        return toAjax(gwSuperCategoryService.updateGwSuperCategory(gwSuperCategory));
    }

    /**
     * 删除收费类别
     */
    @RequiresPermissions("gw:SuperCategory:remove")
    @Log(title = "收费类别", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gwSuperCategoryService.deleteGwSuperCategoryByScIds(ids));
    }
}
