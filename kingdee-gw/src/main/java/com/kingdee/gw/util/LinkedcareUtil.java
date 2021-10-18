package com.kingdee.gw.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.gw.constant.LinkedcareConstants;
import com.kingdee.gw.domain.GwSuperCategory;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedcareUtil {

    /**
     * 获取token
     * @return token
     */
    private static String getToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token ="";
        // 组装body参数
        JSONObject bodyObj = new JSONObject();
        bodyObj.put("tenantId", LinkedcareConstants.TENANT_ID);
        bodyObj.put("ticket",LinkedcareConstants.TICKET);
        // 将map转为json串，放入restTemplate的参数对象中
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(bodyObj), headers);
        // 将参数拼入请求url中
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(LinkedcareConstants.URI_BASE+LinkedcareConstants.URN_TOKEN);
        // 发起请求
        RestTemplate template = new RestTemplate();
        MappingJackson2HttpMessageConverter c = new MappingJackson2HttpMessageConverter();
        c.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON,MediaType.APPLICATION_OCTET_STREAM));
        template.getMessageConverters().add(c);
        ResponseEntity<JSONObject> responseEntity = template.postForEntity(builder.toUriString(), request, JSONObject.class);
        if (HttpStatus.OK == responseEntity.getStatusCode())
            token = responseEntity.getBody().get("token").toString();
        return  token;
    }


    /**
     *
     * @return
     */
    public static JSONArray syncOrganization(){
        String token = getToken();
        JSONArray array = null ;
        if (token !=null && !"".equals(token)){
            String url = LinkedcareConstants.URI_BASE+LinkedcareConstants.URN_OFFICE+"?startTime=2019-01-01";
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION,token);
            HttpEntity<String> request = new HttpEntity<>(null, headers);
            ResponseEntity<JSONObject> responseEntity =  template.exchange(url,HttpMethod.GET,request,JSONObject.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()){
                JSONObject entityBody = responseEntity.getBody();
                if(entityBody.get("data")!=null && !"".equals(entityBody.get("data").toString()))
                     array = entityBody.getJSONArray("data");
            }
        }
        return array;
    }


    /**
     *
     * @return
     */
    public static JSONArray syncChargeType(){
        String token = getToken();
        JSONArray array = null ;
        if (token !=null && !"".equals(token)){
            String url = LinkedcareConstants.URI_BASE+LinkedcareConstants.URN_CHARGET_TYPE+"?fromTime=2019-01-01&officeId=168";
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION,token);
            HttpEntity<String> request = new HttpEntity<>(null, headers);
            ResponseEntity<JSONObject> responseEntity =  template.exchange(url,HttpMethod.GET,request,JSONObject.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()){
                JSONObject entityBody = responseEntity.getBody();
                if(entityBody.get("data")!=null && !"".equals(entityBody.get("data").toString()))
                    array = entityBody.getJSONArray("data");
            }
        }
        return array;
    }



    public static List<GwSuperCategory> getSuperCategoryList(){
        String token = getToken();
        List<GwSuperCategory> list = new ArrayList<GwSuperCategory>();
        if (token !=null && !"".equals(token)){
            String url = LinkedcareConstants.URI_BASE+LinkedcareConstants.URN_CHARGE_SUPER_CATEGORY+"?officeId=168";
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION,token);
            HttpEntity<String> request = new HttpEntity<>(null, headers);
            ResponseEntity<JSONObject> responseEntity =  template.exchange(url,HttpMethod.GET,request,JSONObject.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()){
                int pageCount = responseEntity.getBody().getIntValue("pageCount");
                if (pageCount > 0 ){
                    for (int i = 0 ; i < pageCount-1 ; i++){
                        url = url+"&pageIndex="+i+1;
                        responseEntity =  template.exchange(url,HttpMethod.GET,request,JSONObject.class);
                        JSONObject entityBody = responseEntity.getBody();
                        if(entityBody.get("data")!=null && !"".equals(entityBody.get("data").toString())){
                            JSONArray datas = entityBody.getJSONArray("data");
                            for (int j = 0 ; j < datas.size() ; j++){
                                JSONObject data = datas.getJSONObject(j);
                                GwSuperCategory vo = new GwSuperCategory();
                                vo.setLcId(data.getString("id"));
                                vo.setOfficeId(data.getString("officeId"));
                                vo.setScName(data.getString("itemName"));
                                vo.setStatus(data.getLong("status"));
                                list.add(vo);
                            }
                        }
                    }
                }
            }
        }
        return  list;
    }

}
