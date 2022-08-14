package com.md.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RtmTokenBuilderSample {
    @Value("${rtm.java.appId}")
    private String appId;

    @Value("${rtm.java.customerSecret}")
    private String appCertificate;

    private static int expireTimestamp = 0;

    public String getToken(Integer userId) throws Exception {
        RtmTokenBuilder token = new RtmTokenBuilder();
        String result = token.buildToken(appId, appCertificate, userId.toString(), RtmTokenBuilder.Role.Rtm_User, expireTimestamp);
        return result;
    }
}