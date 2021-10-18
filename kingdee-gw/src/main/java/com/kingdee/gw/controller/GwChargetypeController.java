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
import com.kingdee.gw.domain.GwChargetype;
import com.kingdee.gw.service.IGwChargetypeService;
import com.kingdee.common.core.controller.BaseController;
import com.kingdee.common.core.domain.AjaxResult;
import com.kingdee.common.utils.poi.ExcelUtil;
import com.kingdee.common.core.page.TableDataInfo;

/**
 * 支付方式Controller
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Controller
@RequestMapping("/gw/chargetype")
public class GwChargetypeController extends BaseController
{
    private String prefix = "gw/chargetype";

    @Autowired
    private IGwChargetypeService gwChargetypeService;

    @RequiresPermissions("gw:chargetype:view")
    @GetMapping()
    public String chargetype()
    {
        return prefix + "/chargetype";
    }

    /**
     * 查询支付方式列表
     */
    @RequiresPermissions("gw:chargetype:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GwChargetype gwChargetype)
    {
        startPage();
        List<GwChargetype> list = gwChargetypeService.selectGwChargetypeList(gwChargetype);
        return getDataTable(list);
    }

    /**
     * 导出支付方式列表
     */
    @RequiresPermissions("gw:chargetype:export")
    @Log(title = "支付方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GwChargetype gwChargetype)
    {
        List<GwChargetype> list = gwChargetypeService.selectGwChargetypeList(gwChargetype);
        ExcelUtil<GwChargetype> util = new ExcelUtil<GwChargetype>(GwChargetype.class);
        return util.exportExcel(list, "支付方式数据");
    }

    /**
     * 新增支付方式
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存支付方式
     */
    @RequiresPermissions("gw:chargetype:add")
    @Log(title = "支付方式", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GwChargetype gwChargetype)
    {
        return toAjax(gwChargetypeService.insertGwChargetype(gwChargetype));
    }

    /**
     * 修改支付方式
     */
    @GetMapping("/edit/{ctId}")
    public String edit(@PathVariable("ctId") Long ctId, ModelMap mmap)
    {
        GwChargetype gwChargetype = gwChargetypeService.selectGwChargetypeByCtId(ctId);
        mmap.put("gwChargetype", gwChargetype);
        return prefix + "/edit";
    }

    /**
     * 修改保存支付方式
     */
    @RequiresPermissions("gw:chargetype:edit")
    @Log(title = "支付方式", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GwChargetype gwChargetype)
    {
        return toAjax(gwChargetypeService.updateGwChargetype(gwChargetype));
    }

    /**
     * 删除支付方式
     */
    @RequiresPermissions("gw:chargetype:remove")
    @Log(title = "支付方式", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gwChargetypeService.deleteGwChargetypeByCtIds(ids));
    }
}
