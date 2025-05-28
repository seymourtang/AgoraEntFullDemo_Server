package com.md.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.model.entity.UserVerification;
import com.md.service.model.form.CreateUserVerificationForm;
import com.md.service.repository.UserVerificationMapper;
import com.md.service.service.UserVerificationService;
import org.springframework.stereotype.Service;

@Service
public class UserVerificationServiceImpl extends ServiceImpl<UserVerificationMapper, UserVerification> implements UserVerificationService {
    @Override
    public void createUserVerification(CreateUserVerificationForm form) {
        UserVerification userVerification = new UserVerification();

        userVerification.setUserNo(form.getUserNo());
        userVerification.setVerifyBatchId(form.getVerifyBatchId());
        userVerification.setRealNameCipher(form.getRealNameCipher());
        userVerification.setIdCardCipher(form.getIdCardCipher());
        userVerification.setVerifySuccessAt(form.getVerifySuccessAt());

        baseMapper.insert(userVerification);
    }
}
