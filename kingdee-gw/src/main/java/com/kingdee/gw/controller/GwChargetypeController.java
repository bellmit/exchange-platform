package com.kingdee.gw.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.gw.domain.GwOrganization;
import com.kingdee.gw.util.LinkedcareUtil;
import io.swagger.annotations.ApiOperation;
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

    /**
     * 组织架构同步
     */
    @ApiOperation("组织架构同步")
    @RequiresPermissions("gw:chargetype:syncData")
    @Log(title = "组织架构", businessType = BusinessType.OTHER)
    @PostMapping("/syncData")
    @ResponseBody
    public AjaxResult syncData(GwChargetype gwChargetype)
    {

        JSONArray datas = LinkedcareUtil.syncChargeType();
        if(datas !=null && datas.size() >0)
        {
            List <GwChargetype> os = new ArrayList<GwChargetype>();
            List <GwChargetype> ous = new ArrayList<GwChargetype>();
            for (int j = 0 ; j < datas.size() ; j++) {
                JSONObject data = datas.getJSONObject(j);
                GwChargetype o = new GwChargetype();
                o.setLcId(data.getString("id"));
                if (gwChargetypeService.selectGwChargetypeList(o).size() ==0 ){
                    // 不存在 新增
                    o.setCtName(data.getString("itemName"));
                    o.setOfficeId(data.getString("officeId"));
                    o.setCode(data.getString("code"));
                    o.setNotes(data.getString("notes"));
                    o.setItemType(data.getString("itemType"));
                    o.setStatus(1L);
                    o.setSort(data.getLong("id"));
                    os.add(o);
                }else {
                    //存在  判断名称是否一致

                    GwChargetype ou = gwChargetypeService.selectGwChargetypeList(o).get(0);
                    if(ou.getCtName().equals(data.getString("itemName"))){
                        ou.setCtName(data.getString("itemName"));

                    }

                }

            }

            if (os != null && os.size() >0) {
                for (int k = 0; k < os.size(); k++)
                    gwChargetypeService.insertGwChargetype(os.get(k));
            }

            if (ous != null && ous.size() >0) {
                for (int k = 0; k < ous.size(); k++)
                    gwChargetypeService.updateGwChargetype(ous.get(k));
            }

        }

        return  toAjax(1);
    }
}
