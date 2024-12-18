package com.md.service.service;

import com.md.service.model.dto.RealNameAuthDTO;

public interface RealNameAuthService {
    RealNameAuthDTO auth(String realName, String idCard)throws  Exception ;
}
