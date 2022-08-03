package com.md.service.utils;

import com.md.service.model.CacheDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CacheUtils {

    private static ConcurrentMap<String, CacheDTO> data = new ConcurrentHashMap<>();

    private static Integer refreshTime = 60;

    public static void set(String key,Object value){
        if(data.containsKey(key)){
            CacheDTO cacheDTO = data.get(key);
            cacheDTO.setObject(value);
            cacheDTO.setTime(LocalDateTime.now());
            data.put(key,cacheDTO);
        }else{
            CacheDTO cacheDTO = new CacheDTO();
            cacheDTO.setTime(LocalDateTime.now());
            cacheDTO.setObject(value);
            data.put(key,cacheDTO);
        }
    }

    public synchronized static Object get(String key){
        LocalDateTime now = LocalDateTime.now();
        if(data.containsKey(key)){
            CacheDTO cacheDTO = data.get(key);
            Duration duration = Duration.between(cacheDTO.getTime(),now);
            if(duration.toMinutes() < refreshTime){
                return cacheDTO.getObject();
            }
        }
        return null;
    }


    public synchronized static Object get(String key,Integer refreshTime){
        LocalDateTime now = LocalDateTime.now();
        if(data.containsKey(key)){
            CacheDTO cacheDTO = data.get(key);
            Duration duration = Duration.between(cacheDTO.getTime(),now);
            if(duration.toMinutes() < refreshTime){
                return cacheDTO.getObject();
            }
        }
        return null;
    }
}
