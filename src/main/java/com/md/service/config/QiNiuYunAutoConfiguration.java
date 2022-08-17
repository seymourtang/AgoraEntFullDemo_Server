package com.md.service.config;
import com.google.gson.Gson;
import com.md.service.service.QiNiuYunService;
import com.md.service.service.impl.QiNiuYunServiceImpl;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableConfigurationProperties({QiNiuYunProperties.class})
@ConditionalOnClass({QiNiuYunService.class})
@ConditionalOnProperty(
    prefix = "qiniuyun",
    name = {"access-key", "secret-key", "bucket"}
)
public class QiNiuYunAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(QiNiuYunAutoConfiguration.class);
    private final QiNiuYunProperties qiNiuYunProperties;

    @Autowired
    public QiNiuYunAutoConfiguration(QiNiuYunProperties qiNiuYunProperties) {
        this.qiNiuYunProperties = qiNiuYunProperties;
    }

    @Bean
    @ConditionalOnMissingBean({QiNiuYunServiceImpl.class})
    public QiNiuYunService qiNiuYunService() {
        return new QiNiuYunServiceImpl();
    }

    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        return new com.qiniu.storage.Configuration(Zone.autoZone());
    }

    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(this.qiniuConfig());
    }

    @Bean
    public Auth auth() {
        return Auth.create(this.qiNiuYunProperties.getAccessKey(), this.qiNiuYunProperties.getSecretKey());
    }

    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(this.auth(), this.qiniuConfig());
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}