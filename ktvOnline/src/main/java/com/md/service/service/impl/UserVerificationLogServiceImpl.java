package com.md.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.model.entity.UserVerificationLog;
import com.md.service.model.form.CreateUserVerificationLogForm;
import com.md.service.repository.UserVerificationLogMapper;
import com.md.service.service.UserVerificationLogService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class UserVerificationLogServiceImpl extends ServiceImpl<UserVerificationLogMapper, UserVerificationLog>
        implements UserVerificationLogService {
    @Override
    public void createUserVerificationLog(CreateUserVerificationLogForm form) {
        UserVerificationLog userVerificationLog = new UserVerificationLog();
        userVerificationLog.setVerifyBatchId(form.getVerifyBatchId());
        userVerificationLog.setUserNo(form.getUserNo());
        userVerificationLog.setVerifyResult(form.getVerifyResult());
        userVerificationLog.setRealNameCipher(form.getRealNameCipher());
        userVerificationLog.setIdCardCipher(form.getIdCardCipher());
        if (!Strings.isEmpty(form.getVerifyRemark())) {
            userVerificationLog.setVerifyRemark(form.getVerifyRemark());
        }

        baseMapper.insert(userVerificationLog);

    }
}
