package com.md.service.controller;

import com.md.service.model.BaseUser;
import com.md.service.utils.RequestContextUtil;

public class BaseController {

    public BaseUser getBaseUser() {
        return RequestContextUtil.getBaseUser();
    }
}
