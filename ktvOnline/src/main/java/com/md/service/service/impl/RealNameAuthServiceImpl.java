package com.md.service.service.impl;

import com.aliyun.cloudauth20190307.Client;
import com.aliyun.cloudauth20190307.models.Id2MetaVerifyRequest;
import com.aliyun.cloudauth20190307.models.Id2MetaVerifyResponse;
import com.aliyun.credentials.models.Config;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.md.service.common.ErrorCodeEnum;
import com.md.service.exception.BaseException;
import com.md.service.model.dto.RealNameAuthDTO;
import com.md.service.service.RealNameAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RealNameAuthServiceImpl implements RealNameAuthService {
    @Value("${realNameAuth.accessKey}")
    private String accessKey;

    @Value("${realNameAuth.accessSecret}")
    private String accessSecret;

    @Override
    public RealNameAuthDTO auth(String realName, String idCard) throws Exception {
        Id2MetaVerifyRequest request = new Id2MetaVerifyRequest();
        request.setParamType("normal");
        request.setUserName(realName);
        request.setIdentifyNum(idCard);

        Id2MetaVerifyResponse response = id2MetaVerifyAutoRoute(request);
        if (response == null || response.getBody() == null) {
            log.error("实名认证失败，返回结果为空");
            throw new BaseException(ErrorCodeEnum.real_name_auth_unknown_err,
                    ErrorCodeEnum.real_name_auth_unknown_err.getMessage());
        }

        RealNameAuthDTO realNameAuthDTO = new RealNameAuthDTO();
        realNameAuthDTO.setCode(response.getBody().getCode());
        realNameAuthDTO.setMessage(response.getBody().getMessage());
        realNameAuthDTO.setRequestId(response.getBody().getRequestId());

        if (response.getBody().getResultObject() != null) {
            realNameAuthDTO.setResult(response.getBody().getResultObject().getBizCode());
        }

        return realNameAuthDTO;

    }

    public Id2MetaVerifyResponse id2MetaVerifyAutoRoute(Id2MetaVerifyRequest request) throws Exception {
        List<String> endpoints = java.util.Arrays.asList(
                "cloudauth.cn-shanghai.aliyuncs.com",
                "cloudauth.cn-beijing.aliyuncs.com");
        Id2MetaVerifyResponse lastResponse = null;
        for (String endpoint : endpoints) {
            try {
                // 调用服务。
                Id2MetaVerifyResponse response = id2MetaVerify(endpoint, request);
                // 节点调用结果
                String ret = com.aliyun.teautil.Common.toJSONString(com.aliyun.teautil.Common.toMap(response));
                log.info("节点 {} 结果：{} ", endpoint, ret);
                // 有一个服务调用成功即返回。
                if (!com.aliyun.teautil.Common.isUnset(response)
                        && com.aliyun.teautil.Common.equalNumber(response.statusCode, 200)) {
                    if (!com.aliyun.teautil.Common.isUnset(response.body)) {
                        lastResponse = response;
                        break;
                    }

                }

            } catch (TeaException error) {
                log.error("节点 {} 调用异常：{}", endpoint, error.message);
            } catch (Exception _error) {
                TeaException error = new TeaException(_error.getMessage(), _error);
                log.error("节点 {} 调用异常：{}", endpoint, error.message);
            }
        }
        return lastResponse;
    }

    public Id2MetaVerifyResponse id2MetaVerify(String endpoint, Id2MetaVerifyRequest request) throws Exception {
        RuntimeOptions runtime = new RuntimeOptions();
        runtime.setReadTimeout(5000);
        runtime.setConnectTimeout(5000);
        // 连接
        return createClient(endpoint).id2MetaVerifyWithOptions(request, runtime);
    }

    public Client createClient(String endpoint) throws Exception {
        Config credentialConfig = new com.aliyun.credentials.models.Config();

        credentialConfig.setType("access_key");
        credentialConfig.setAccessKeyId(accessKey);
        credentialConfig.setAccessKeySecret(accessSecret);

        com.aliyun.credentials.Client credential = new com.aliyun.credentials.Client(credentialConfig);
        com.aliyun.teaopenapi.models.Config apiConfig = new com.aliyun.teaopenapi.models.Config();

        apiConfig.credential = credential;
        apiConfig.endpoint = endpoint;

        return new Client(apiConfig);
    }
}
