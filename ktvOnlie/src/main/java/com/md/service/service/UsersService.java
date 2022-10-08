package com.md.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.BaseResult;
import com.md.service.model.dto.UserInfo;
import com.md.service.model.entity.Users;
import com.md.service.model.form.UpdateUserInfoForm;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 */
public interface UsersService extends IService<Users> {

    BaseResult<String> verificationCode(String phone);

    BaseResult<UserInfo> login(String phone, String code);

    BaseResult<UserInfo> updateInfo(UpdateUserInfoForm form);

    BaseResult<?> cancellation(String no);

    BaseResult<UserInfo> getUserInfo(String no);

    UserInfo getUser(String no);

    Users getUserByNo(String no);

    String getUserNoById(Integer id);

    IPage<Users> userList(Page form);
}
