package com.md.service.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.md.service.config.QiNiuYunProperties;
import com.md.service.service.QiNiuYunService;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class QiNiuYunServiceImpl implements QiNiuYunService {
    @Resource
    private Auth auth;
    @Resource
    private QiNiuYunProperties qiNiuProperties;
    @Resource
    private RestTemplate restTemplate;

    public QiNiuYunServiceImpl() {
    }

    @Override
    public String checkImage(String imagUrl) {
        try{
            String url = "http://ai.qiniuapi.com/v3/image/censor";
            JSONObject jsonObject = new JSONObject();
            JSONObject urlObj = new JSONObject();
            JSONArray scenes = new JSONArray();
            urlObj.set("uri",imagUrl);
            jsonObject.set("data",urlObj);
            scenes.add("pulp");
            jsonObject.set("params",scenes);
            String authorizationHeader = auth.signQiniuAuthorization(url,"POST",jsonObject.toString().getBytes(),"application/json");
            log.info("url : {}",url);
            MultiValueMap<String,String> paramJson = new LinkedMultiValueMap<>();
            HttpEntity<?> httpEntity = new HttpEntity<>(paramJson, getJsonHeaderTwo(authorizationHeader));
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            log.info("responseEntity : {}",responseEntity.getBody());
            return responseEntity.getBody();
        }catch (Exception e){
            log.info("error",e);
        }
        return null;
    }

    private LinkedMultiValueMap<String, String> getJsonHeaderTwo(String authorizationHeader) {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Content-Type","application/json");
        headers.set("Host","ai.qiniuapi.com");
        headers.set("Authorization","Qiniu " + authorizationHeader);
        return headers;
    }


}
