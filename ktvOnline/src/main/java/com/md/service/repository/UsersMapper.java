package com.md.service.repository;

import com.md.service.model.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 */
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 获取用户no 不看用户是否注销
     * @param id
     * @return
     */
    String getUserNo(@Param("id") Integer id);
}
