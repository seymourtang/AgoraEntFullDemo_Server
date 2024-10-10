package com.md.service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.md.service.common.ErrorCodeEnum;
import com.md.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.ArrayList;

@Component
@Slf4j
public class TupuUtils {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PrivateKey privateKey;

    @Value("${tupu.sid}")
    private String sid;

    @Value("${tupu.url}")
    private String tupuUrl;

    public void checkTupuText(String msg) {
        try {

            String url = tupuUrl + sid;
            log.info("url : {}", url);

            JSONObject body = textModerationBuilder(msg);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);
            log.info("tupu:{}", httpEntity);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            log.info("responseEntity : {}", responseEntity.getBody());

            String ret;
            ret = new String(responseEntity.getBody().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            JSONObject result = JSON.parseObject(ret);
            String json = result.getString("json");
            JSONObject jsonResult = JSON.parseObject(json);

            if (!jsonResult.getInteger("code").equals(0)) {
                log.error("tupu text moderation check failed: {}", result);
                return;
            }


            JSONArray summaryArray = jsonResult.getJSONArray("summary");

            for (int i = 0; i < summaryArray.size(); i++) {
                JSONObject summary = summaryArray.getJSONObject(i);
                if (!summary.getInteger("riskType").equals(0)) {
                    throw new BaseException(ErrorCodeEnum.please_dont_upload_illegal_content);
                }
            }
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            log.error("tupu text moderation check failed: {}", e.getMessage());
        }
    }


    private JSONObject textModerationBuilder(String msg) {
        String secretId = sid;
        Long timestamp = Math.round(System.currentTimeMillis() / 1000.0);
        Double nonce = Math.random();
        String sign_string = secretId + "," + timestamp + "," + nonce;

        JSONObject textContent = new JSONObject();
        textContent.put("content", msg);

        JSONObject body = new JSONObject();
        body.put("text", new ArrayList<JSONObject>() {{
            add(textContent);
        }});
        body.put("nonce", nonce);
        body.put("timestamp", timestamp);

        try {
            String signature = signature(privateKey, sign_string);
            body.put("signature", signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return body;
    }

    public String signature(PrivateKey privateKey, String sign_string) throws Exception {
        // 用私钥对信息生成数字签名
        Signature signer = Signature.getInstance("SHA256WithRSA");
        signer.initSign(privateKey);
        signer.update(sign_string.getBytes());
        byte[] signed = signer.sign();
        return Utils.base64Encode(signed);
    }
}
