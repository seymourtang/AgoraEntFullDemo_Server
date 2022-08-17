package com.md.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
    prefix = "qiniuyun"
)
@Data
public class QiNiuYunProperties {
    private String accessKey = "your_accessKey";
    private String secretKey = "your_secretKey";
    private String bucket = "your_bucket";
    private String domain;

    public QiNiuYunProperties() {
    }

    @Override
    public String toString() {
        return "QiNiuYunProperties(accessKey=" + this.getAccessKey() + ", secretKey=" + this.getSecretKey() +  ", domain=" + this.getDomain() + ")";
    }
}
