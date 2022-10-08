package com.md.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RtmTokenBuilderSample {
    @Value("${rtm.java.appId}")
    private String appId;

    @Value("${rtm.java.customerSecret}")
    private String appCertificate;

    private static int expireTimestamp = 0;

    private static int expirationTimeInSeconds = 60 * 60 * 24;

    public String getToken(Integer userId,String roomNo) throws Exception {
        RtmTokenBuilder token = new RtmTokenBuilder();
        String result = token.buildToken(appId, appCertificate, userId.toString(), RtmTokenBuilder.Role.Rtm_User, expireTimestamp);
        return result;
    }

    public String getRtcToken(Integer userId,String roomNo) throws Exception {
        RtcTokenBuilder token = new RtcTokenBuilder();
        int timestamp = (int)(System.currentTimeMillis() / 1000 + expirationTimeInSeconds);
        String result = token.buildTokenWithUid(appId, appCertificate, roomNo,userId, RtcTokenBuilder.Role.Role_Publisher, timestamp);
        return result;
    }
}