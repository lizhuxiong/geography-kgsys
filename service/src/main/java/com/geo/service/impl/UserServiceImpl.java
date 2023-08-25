package com.geo.service.impl;

import com.geo.dao.mapper.UserMapper;
import com.geo.domain.po.UserPo;
import com.geo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-22 14:45
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public UserPo queryUserByUserId(String id) {
        return userMapper.queryUserByUserId(id);
    }
}
