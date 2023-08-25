package com.geo.dao.mapper;

import com.geo.domain.po.UserPo;
import org.springframework.stereotype.Repository;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-22 14:52
 */
@Repository
public interface UserMapper {
   UserPo queryUserByUserId(String id);
}
