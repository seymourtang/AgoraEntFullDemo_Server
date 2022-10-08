package com.md.service.utils;

import com.md.service.common.ErrorCodeEnum;
import com.md.service.exception.BaseException;
import com.md.service.model.BaseUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Slf4j
@Component
public class RequestContextUtil {

    @Getter
    public static String secretKey;

    @Value("${jwt.token.secret}")
    public void getSecret(String key) {
        secretKey = key;
    }

    public static HttpServletRequest getRequestFromContextHolder() {
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            Assert.notNull(request, "The HttpServletRequest must not be null");
            return request;
        } catch (RuntimeException e) {
            throw new BaseException(ErrorCodeEnum.system_error);
        }
    }

    public static BaseUser getBaseUser() {
        HttpServletRequest request = getRequestFromContextHolder();
        BaseUser user = new BaseUser();
        user.setUserNo(getUser(request));
        return user;
    }

    /**
     * Token的解密
     *
     * @param token 加密后的token
     * @return
     */
    public static Claims parseJWT(String token) {
        String key = Base64.getEncoder().encodeToString(secretKey.getBytes());
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }

    public static String getUser(HttpServletRequest req) {
        String encodedToken = req.getHeader("Authorization");
//        log.info("【token】==========>" + encodedToken);
        try {
            Claims token = parseJWT(encodedToken);
            if (token.containsKey("user_no")) {
                return token.get("user_no").toString();
            }
            return null;
        } catch (Exception e) {
            log.error("jwt get token error",e);
        }
        return null;
    }
}