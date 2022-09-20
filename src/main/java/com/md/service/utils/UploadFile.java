package com.md.service.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class UploadFile {

    @Value("${al.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${al.oss.endpoint}")
    private String endpoint;

    @Value("${al.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${al.oss.bucketName}")
    private String bucketName;

    @Value("${al.oss.objectName.url}")
    private String objectNameUrl;

    @Resource
    private YiTuUtils yiTuUtils;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;;

    public String uploadFile(MultipartFile file, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            objectName = objectNameUrl + objectName;
            PutObjectRequest putObjectRequest = null;
            try {
                putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(file.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            String url = "https://" + bucketName + "." + endpoint + "/" + objectName;
            yiTuUtils.checkImage(url);
            return url;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    public String getImages(String roomNo, String userNo) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String keyPrefix = objectNameUrl + roomNo + "/" + userNo;
        try {
            String mark = "";
            if(redisTemplate.hasKey(keyPrefix)){
                mark = redisTemplate.opsForValue().get(keyPrefix).toString();
            }
            // 列举包含指定前缀的文件。默认列举100个文件。
            ObjectListing objectListing = ossClient.listObjects(bucketName,keyPrefix);
            objectListing.setMarker(mark);
            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            log.info("keyPrefix：{}  sums size:{}",keyPrefix,sums.size());
            for (OSSObjectSummary s : sums) {
                Thread.sleep(1000);
                yiTuUtils.checkImage( "https://" + bucketName + "." + endpoint + "/" + s.getKey());
                mark = s.getKey();
            }
            redisTemplate.opsForValue().set(keyPrefix,mark);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException | InterruptedException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}                    