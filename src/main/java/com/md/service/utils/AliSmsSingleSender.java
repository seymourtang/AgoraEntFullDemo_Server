package com.md.service.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.md.service.common.ErrorCodeEnum;
import com.md.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class AliSmsSingleSender {

    @Value("${al.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${al.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${al.sms.singName}")
    private String singName;

    @Value("${al.sms.temPlateCode}")
    private String temPlateCode;

    @Value("${al.sms.url}")
    private String url;

    @Resource
    private RedissonClient redisson;

    public void sendCode(String phone, String param) {
        RLock rlock = redisson.getLock(phone);
        if(!rlock.tryLock()){
            return;
        }
        try {
            Config config = new Config()
                    .setAccessKeyId(accessKeyId)
                    .setAccessKeySecret(accessKeySecret);
            config.endpoint = url;
            Client client = null;
            client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName(singName)
                    .setTemplateCode(temPlateCode)
                    .setPhoneNumbers(phone)
                    .setTemplateParam(param);
            RuntimeOptions runtime = new RuntimeOptions();
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            log.info("sendCode result:{}", sendSmsResponse.body.getMessage());
            log.info("sendCode code :{},result :{}",sendSmsResponse.getBody().getCode(),sendSmsResponse.getBody().toString());
            if (!sendSmsResponse.getBody().getCode().equals("OK")) {
                throw new BaseException(ErrorCodeEnum.verification_code_sent_failure, ErrorCodeEnum.verification_code_sent_failure.getMessage());
            }
        } catch (Exception e) {
            log.error("send code error",e);
            throw new BaseException(ErrorCodeEnum.verification_code_sent_failure, ErrorCodeEnum.verification_code_sent_failure.getMessage());
        }finally {
            rlock.unlock();
        }
    }
}
