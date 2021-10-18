package com.kingdee.gw.controller;

import java.nio.charset.Charset;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.gw.constant.LinkedcareConstants;
import com.kingdee.gw.util.LinkedcareUtil;
import com.kingdee.system.domain.SysConfig;
import com.kingdee.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kingdee.common.annotation.Log;
import com.kingdee.common.enums.BusinessType;
import com.kingdee.gw.domain.GwOrganization;
import com.kingdee.gw.service.IGwOrganizationService;
import com.kingdee.common.core.controller.BaseController;
import com.kingdee.common.core.domain.AjaxResult;
import com.kingdee.common.utils.poi.ExcelUtil;
import com.kingdee.common.core.page.TableDataInfo;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 组织架构Controller
 * 
 * @author lei.ye
 * @date 2021-09-25
 */
@Api("组织架构管理")
@Controller
@RequestMapping("/gw/organization")
public class GwOrganizationController extends BaseController
{
    private String prefix = "gw/organization";

    @Autowired
    private IGwOrganizationService gwOrganizationService;

    @Autowired
    private ISysConfigService configService;


    @RequiresPermissions("gw:organization:view")
    @GetMapping()
    public String organization()
    {
        return prefix + "/organization";
    }

    /**
     * 查询组织架构列表
     */
    @RequiresPermissions("gw:organization:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GwOrganization gwOrganization)
    {
        startPage();
        List<GwOrganization> list = gwOrganizationService.selectGwOrganizationList(gwOrganization);
        return getDataTable(list);
    }

    /**
     * 导出组织架构列表
     */
    @RequiresPermissions("gw:organization:export")
    @Log(title = "组织架构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GwOrganization gwOrganization)
    {
        List<GwOrganization> list = gwOrganizationService.selectGwOrganizationList(gwOrganization);
        ExcelUtil<GwOrganization> util = new ExcelUtil<GwOrganization>(GwOrganization.class);
        return util.exportExcel(list, "组织架构数据");
    }

    /**
     * 新增组织架构
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存组织架构
     */
    @RequiresPermissions("gw:organization:add")
    @Log(title = "组织架构", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GwOrganization gwOrganization)
    {
        return toAjax(gwOrganizationService.insertGwOrganization(gwOrganization));
    }

    /**
     * 修改组织架构
     */
    @GetMapping("/edit/{oId}")
    public String edit(@PathVariable("oId") Long oId, ModelMap mmap)
    {
        GwOrganization gwOrganization = gwOrganizationService.selectGwOrganizationByOId(oId);
        mmap.put("gwOrganization", gwOrganization);
        return prefix + "/edit";
    }

    /**
     * 修改保存组织架构
     */
    @RequiresPermissions("gw:organization:edit")
    @Log(title = "组织架构", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GwOrganization gwOrganization)
    {
        return toAjax(gwOrganizationService.updateGwOrganization(gwOrganization));
    }

    /**
     * 删除组织架构
     */
    @RequiresPermissions("gw:organization:remove")
    @Log(title = "组织架构", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(gwOrganizationService.deleteGwOrganizationByOIds(ids));
    }


    /**
     * 组织架构同步
     */
    @ApiOperation("组织架构同步")
    @RequiresPermissions("gw:organization:syncData")
    @Log(title = "组织架构", businessType = BusinessType.OTHER)
    @PostMapping("/syncData")
    @ResponseBody
    public AjaxResult syncData(GwOrganization gwOrganization)
    {
        JSONArray datas = LinkedcareUtil.syncOrganization();
        if(datas !=null && datas.size() >0)
        {
            List <GwOrganization> os = new ArrayList<GwOrganization>();
            List <GwOrganization> ous = new ArrayList<GwOrganization>();
            for (int j = 0 ; j < datas.size() ; j++){
                JSONObject data = datas.getJSONObject(j);
                GwOrganization o = new GwOrganization();
                o.setLcId(data.getString("id"));
                if(gwOrganizationService.selectGwOrganizationList(o).size() == 0){
                    o.setAbbreviation(data.getString("abbreviation"));
                    o.setoName(data.getString("name"));
                    o.setProvince(data.getString("province"));
                    o.setCity(data.getString("city"));
                    o.setInstitutionCode(data.getString("institutionCode"));
                    o.setSort(data.getLong("id"));
                    o.setTenantId(data.getString("tenantId"));
                    if(data.getBooleanValue("isInactive"))
                        o.setIsInactive("0");
                    else
                        o.setIsInactive("1");
                    o.setDistrict(data.getString("district"));
                    o.setCode(data.getString("id"));
                    os.add(o);
                }else{
                    GwOrganization uo = gwOrganizationService.selectGwOrganizationList(o).get(0);
                    if (!uo.getoName().equals(data.getString("name"))){
                        uo.setoName(data.getString("name"));
                        ous.add(uo);
                    }
                }

            }

            if (os != null && os.size() >0){
                for (int k = 0 ; k < os.size(); k++)
                    gwOrganizationService.insertGwOrganization(os.get(k));

            }


            if (ous != null && ous.size() >0){
                for (int k = 0 ; k < ous.size(); k++)
                    gwOrganizationService.updateGwOrganization(ous.get(k));
            }

        }
        return  toAjax(1);
    }

}
