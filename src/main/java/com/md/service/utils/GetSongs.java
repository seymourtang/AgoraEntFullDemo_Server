package com.md.service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.md.service.model.entity.Songs;
import com.md.service.service.SongsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;


@Component
@Slf4j
public class GetSongs {

    @Resource
    private RestTemplate restTemplate;

    @Value("${rtm.java.appId}")
    private String appId;

    @Value("${rtm.java.customerKey}")
    private String customerKey;

    @Value("${rtm.java.customerSecret}")
    private String customerSecret;

    @Value("${rtm.java.requestId}")
    private String requestId;

    @Resource
    private SongsService songsService;

    public JSONObject getAll(Integer pageType,String pageCode,Integer size) {
        while (true){
            String plainCredentials = customerKey + ":" + customerSecret;
            String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
            String authorizationHeader = "Basic " + base64Credentials;
            String url = "https://api.agora.io/cn/v1.0/projects/"+appId + "/ktv-service/api/serv/songs?requestId="+requestId
                    +"&pageType="+pageType+"&pageCode="+pageCode+"&size="+size;
            log.info("url : {}",url);
            MultiValueMap<String,String> paramJson = new LinkedMultiValueMap<>();
            HttpEntity<?> httpEntity = new HttpEntity<>(paramJson, getJsonHeaderTwo(authorizationHeader));
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            log.info("responseEntity : {}",responseEntity.getBody());
            String ret;
            try {
                ret = new String(responseEntity.getBody().getBytes("ISO-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            JSONObject result = JSON.parseObject(ret);
            if(!result.getString("msg").equals("ok")){
                log.info("导入所有歌曲");
                break;
            }
            JSONArray lists = result.getJSONObject("data").getJSONArray("list");
            for(int i = 0;i < lists.size();i++){
                JSONObject object = lists.getJSONObject(i);
                if(i == 0){
                    pageCode = object.getString("songCode");
                }
                songsService.init(object.getString("name"),"",
                        object.getString("songCode"),object.getString("poster"),object.getString("singer"));
            }
        }
        return null;
    }

    public JSONObject getSong(String songCode,Integer lyricType) {
//        List<Songs> songsList = songsService.list(new LambdaQueryWrapper<Songs>().eq(Songs::getLyric,""));
//        songsList.forEach(e -> {
            String plainCredentials = customerKey + ":" + customerSecret;
            String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
            String authorizationHeader = "Basic " + base64Credentials;
            String url = "https://api.agora.io/cn/v1.0/projects/"+appId + "/ktv-service/api/serv/song-url?requestId="+requestId
                    +"&songCode="+songCode+"&lyricType"+lyricType;
            log.info("url : {}",url);
            MultiValueMap<String,String> paramJson = new LinkedMultiValueMap<>();
            HttpEntity<?> httpEntity = new HttpEntity<>(paramJson, getJsonHeaderTwo(authorizationHeader));
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            log.info("responseEntity : {}",responseEntity.getBody());
            String ret;
            try {
                ret = new String(responseEntity.getBody().getBytes("ISO-8859-1"),"utf-8");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            JSONObject result = JSON.parseObject(ret);
//            e.setLyric(result.getJSONObject("data").getString("lyric"));
//            songsService.updateById(e);
//        });
        return result;
    }

    public JSONObject getSongDb(String songCode,Integer lyricType) {
        List<Songs> songsList = songsService.list(new LambdaQueryWrapper<Songs>().eq(Songs::getLyric,""));
        songsList.forEach(e -> {
        String plainCredentials = customerKey + ":" + customerSecret;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        String authorizationHeader = "Basic " + base64Credentials;
        String url = "https://api.agora.io/cn/v1.0/projects/"+appId + "/ktv-service/api/serv/song-url?requestId="+requestId
                +"&songCode="+songCode+"&lyricType"+lyricType;
        log.info("url : {}",url);
        MultiValueMap<String,String> paramJson = new LinkedMultiValueMap<>();
        HttpEntity<?> httpEntity = new HttpEntity<>(paramJson, getJsonHeaderTwo(authorizationHeader));
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        log.info("responseEntity : {}",responseEntity.getBody());
        String ret;
        try {
            ret = new String(responseEntity.getBody().getBytes("ISO-8859-1"),"utf-8");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        JSONObject result = JSON.parseObject(ret);
            e.setLyric(result.getJSONObject("data").getString("lyric"));
            songsService.updateById(e);
        });
        return null;
    }

    public JSONObject getAdd(Integer pageType,String pageCode,Integer size,String unix) {
        while (true){
            String plainCredentials = customerKey + ":" + customerSecret;
            String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
            String authorizationHeader = "Basic " + base64Credentials;
            String url = "https://api.agora.io/cn/v1.0/projects/"+appId + "/ktv-service/api/serv/songs-incr?requestId="+requestId
                    +"&pageType="+pageType+"&pageCode="+pageCode+"&size="+size + "&lastUpdateTime="+unix;
            log.info("url : {}",url);
            MultiValueMap<String,String> paramJson = new LinkedMultiValueMap<>();
            HttpEntity<?> httpEntity = new HttpEntity<>(paramJson, getJsonHeaderTwo(authorizationHeader));
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            log.info("responseEntity : {}",responseEntity.getBody());
            String ret;
            try {
                ret = new String(responseEntity.getBody().getBytes("ISO-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            JSONObject result = JSON.parseObject(ret);
            if(!result.getString("msg").equals("ok")){
                log.info("导入所有歌曲");
                break;
            }
            JSONArray lists = result.getJSONObject("data").getJSONArray("list");
            for(int i = 0;i < lists.size();i++){
                JSONObject object = lists.getJSONObject(i);
                if(i == 0){
                    pageCode = object.getString("songCode");
                }
                songsService.init(object.getString("name"),"",
                        object.getString("songCode"),object.getString("poster"),object.getString("singer"));
            }
        }
        return null;
    }


    public JSONObject songHot(Integer hotType) {
        String plainCredentials = customerKey + ":" + customerSecret;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        String authorizationHeader = "Basic " + base64Credentials;
        String url = "https://api.agora.io/cn/v1.0/projects/"+appId + "/ktv-service/api/serv/song-hot?requestId="+requestId
                +"&hotType="+hotType;
        log.info("url : {}",url);
        MultiValueMap<String,String> paramJson = new LinkedMultiValueMap<>();
        HttpEntity<?> httpEntity = new HttpEntity<>(paramJson, getJsonHeaderTwo(authorizationHeader));
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        log.info("responseEntity : {}",responseEntity.getBody());
        String ret;
        try {
            ret = new String(responseEntity.getBody().getBytes("ISO-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        JSONObject result = JSON.parseObject(ret);
        JSONArray lists = result.getJSONObject("data").getJSONArray("list");
        for(int i = 0;i < lists.size();i++){
            JSONObject object =lists.getJSONObject(i);
            String songCode = object.getString("songCode");
            songsService.update(new LambdaUpdateWrapper<Songs>().eq(Songs::getSongNo,songCode).set(Songs::getType,hotType));
        }
        return result;
    }

    private LinkedMultiValueMap<String, String> getJsonHeaderTwo(String authorizationHeader) {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Authorization", authorizationHeader);
        return headers;
    }
}