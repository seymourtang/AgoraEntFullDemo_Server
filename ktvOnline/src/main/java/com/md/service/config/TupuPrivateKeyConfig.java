package com.md.service.config;


import com.md.service.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Configuration
@Slf4j
public class TupuPrivateKeyConfig {

    @Value("${tupu.privateKey}")
    private String privateKey;


    @Bean
    public PrivateKey readPrivateKey() throws Exception {
        byte[] buffer = Utils.base64Decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 获取密钥对象
        return keyFactory.generatePrivate(keySpec);
    }
}
