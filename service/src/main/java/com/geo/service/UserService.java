package com.geo.service;

import com.geo.domain.po.UserPo;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-22 14:45
 */
public interface UserService {
    UserPo queryUserByUserId(String id);
}
