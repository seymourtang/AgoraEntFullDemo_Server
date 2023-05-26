package com.md.service.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JsonUtil {
    private static volatile ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        return ObjectMapperInstance.INSTANCE;

    }

    public static <T> Map<String, String> changeObjToMap(T t) {

        String json = JSON.toJSONString(t);
        JSONObject jsonObject = JSON.parseObject(json);
        Map<String, String> retMap = new HashMap<>(jsonObject.size());
        jsonObject.keySet().forEach(key -> retMap.put(key, jsonObject.getString(key)));
        return retMap;

    }


    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return getObjectMapper().writer().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T parseObject(String json, Class<T> clz) {

        try {
            return getObjectMapper().readerFor(clz).readValue(json);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }

    private static class ObjectMapperInstance {
        private static ObjectMapper INSTANCE = Jackson2ObjectMapperBuilder.json().build();


    }

    public static JSONArray parseObject(String json) {

        try {
            return JSON.parseArray(json);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }


}