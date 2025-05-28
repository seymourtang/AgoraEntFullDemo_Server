package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.entity.UserVerification;
import com.md.service.model.form.CreateUserVerificationForm;

public interface UserVerificationService extends IService<UserVerification> {
    void createUserVerification(CreateUserVerificationForm form);
}
