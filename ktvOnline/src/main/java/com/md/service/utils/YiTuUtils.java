package com.md.service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.md.service.common.ErrorCodeEnum;
import com.md.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;

@Component
@Slf4j
public class YiTuUtils {

    @Resource
    private RestTemplate restTemplate;

    @Value("${yitu.appId}")
    private String appId;

    @Value("${yitu.devId}")
    private String devId;

    @Value("${yitu.devKey}")
    private String devKey;

    @Value("${yitu.url}")
    private String yituUrl;

    public JSONObject checkTest(String msg) {
        String url = "https://as-staging.yitutech.com/v1/antispam/textscan";
        log.info("url : {}", url);
        JSONObject body = new JSONObject();
        body.put("appId", appId);
        body.put("text", msg);
        HttpEntity<?> httpEntity = new HttpEntity<>(body, getJsonHeaderTwo());
        log.info("yitu:{}", httpEntity);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        log.info("responseEntity : {}", responseEntity.getBody());
        String ret;
        ret = new String(responseEntity.getBody().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        JSONObject result = JSON.parseObject(ret);
        if (result.getString("textResult").equals("block")) {
            throw new BaseException(ErrorCodeEnum.please_dont_upload_illegal_content);
        }
        return result;
    }

    public JSONObject checkImage(String imageUrl) {
        String url = yituUrl;
        log.info("url : {}", url);
        JSONObject body = new JSONObject();
        body.put("appId", appId);
        JSONArray scenes = new JSONArray();
        scenes.add("porn");
        scenes.add("politics");
        body.put("scenes", scenes);
        JSONArray tasks = new JSONArray();
        JSONObject images = new JSONObject();
        images.put("url", imageUrl);
        tasks.add(images);
        try {
            body.put("tasks", tasks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HttpEntity<?> httpEntity = new HttpEntity<>(body.toString().replaceAll("\\\\", ""), getJsonHeaderTwo());
        log.info("checkImage yitu:{}", httpEntity);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        log.info("checkImage responseEntity : {}", responseEntity.getBody());
        String ret;
        ret = new String(responseEntity.getBody().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        JSONObject result = JSON.parseObject(ret);
        if (result.getInteger("rtn").equals(0)) {
            if (result.getJSONArray("tasks").getJSONObject(0).getString("result").equals("block")) {
                if (result.getJSONArray("tasks").getJSONObject(0).getJSONArray("detail").getJSONObject(0).getString("scene").equals("politics")) {
                    throw new BaseException(ErrorCodeEnum.please_dont_upload_contains_politically_sensitive_content);
                } else {
                    throw new BaseException(ErrorCodeEnum.please_dont_upload_illegal_content);
                }
            }
        } else {
            log.error("yifu checkImage error");
        }
        return null;
    }

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    private String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    public String calculateRFC2104HMAC(String data, String key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }

    private LinkedMultiValueMap<String, String> getJsonHeaderTwo() {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String accessId = devId;
        String accessKey = devKey;
        long timestamp = System.currentTimeMillis() / 1000L;
        String signKey = accessId + timestamp;
        try {
            String signature = calculateRFC2104HMAC(signKey, accessKey);
            headers.set("x-dev-id", accessId);
            headers.set("x-signature", signature);
            headers.set("x-request-send-timestamp", String.valueOf(timestamp));
            headers.set("content-type", "application/json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return headers;
    }
}
