package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.entity.UserVerificationLog;
import com.md.service.model.form.CreateUserVerificationLogForm;

public interface UserVerificationLogService extends IService<UserVerificationLog> {
    void createUserVerificationLog(CreateUserVerificationLogForm form);
}
