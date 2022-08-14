package com.md.service.utils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.LinkedMultiValueMap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtil {

    /**
     * GET请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * GET请求
     *
     * @param url
     * @param headers
     * @return
     */
    public static String doGet(String url, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            if (headers != null && !headers.isEmpty()) {
                headers.forEach((key, value) -> {
                    request.setHeader(key, value);
                });
            }
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            return httpClient.execute(request, responseHandler);
        } catch (IOException e) {
            log.error("\n【请求地址】：{}\n【异常信息】：{}", url, e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map params) {
        return doPost(url, null, params);
    }

    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> headers, Map params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<>();
            if (params != null && !params.isEmpty()) {
                params.forEach((key, value) -> {
                    nvps.add(new BasicNameValuePair(String.valueOf(key), String.valueOf(value)));
                });
            }
            if (headers != null && !headers.isEmpty()) {
                headers.forEach((key, value) -> {
                    request.setHeader(key, value);
                });
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            return httpClient.execute(request, responseHandler);
        } catch (Exception e) {
            log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, JSON.toJSONString(params), e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return null;
    }

    /**
     * 请求JSON格式
     *
     * @param url
     * @param context
     * @return
     */
    public static String doPostJson(String url, String context) {
        return doPostJson(url, null, context);
    }

    /**
     * 请求JSON格式
     *
     * @param url
     * @param headers 请求头
     * @param context
     * @return
     * @throws Exception
     */
    public static String doPostJson(String url, LinkedMultiValueMap<String, String> headers, String context) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(context, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            request.setHeader("Content-type", "application/json");
            if (headers != null && !headers.isEmpty()) {
                headers.forEach((key, value) -> {
                    request.setHeader(key, value.toString());
                });
            }
            request.setEntity(requestEntity);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            return httpClient.execute(request, responseHandler);
        } catch (Exception e) {
            log.error("\n【请求地址】：{}\n【请求数据】：{}\n【异常信息】：{}", url, context, e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return null;
    }

    public static void main(String[] argus) throws UnsupportedEncodingException {
        String WECHAT_WORK_CROP_ID = "wwcbb98dbdfc68dbf2";
        String WECHAT_WORK_AGENT_WORK_SECRET = "KC1OdRyYZ6fM70Fh-tiazYaYMCS9X2Nne-quRCVcUZE";

        System.out.println(doGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + WECHAT_WORK_CROP_ID
                + "&corpsecret=KC1OdRyYZ6fM70Fh-tiazYaYMCS9X2Nne-quRCVcUZE"));

    }

}
