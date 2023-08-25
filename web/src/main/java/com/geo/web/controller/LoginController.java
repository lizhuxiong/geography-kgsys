package com.geo.web.controller;

import com.geo.domain.po.UserPo;
import com.geo.domain.resp.Result;
import com.geo.domain.resp.Status;
import com.geo.domain.vo.LoginInfoVo;
import com.geo.domain.vo.LoginVo;
import com.geo.service.UserService;
import com.geo.web.utils.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-22 14:54
 */
@CrossOrigin//解决跨域
@RestController
public class LoginController {
   @Resource
   UserService userService;
   @PostMapping("/login")
   public Result login(@RequestBody LoginVo loginInfo) throws UnsupportedEncodingException {
      UserPo user = userService.queryUserByUserId(loginInfo.getUserId());
      if(!user.getUserPassword().equals(loginInfo.getPassword())){
         return new Result(Status.FAIL);
      }


      String token = JWTUtil.createToken(user);
      LoginInfoVo loginVo = new LoginInfoVo(user,token);
      Result result = new Result(Status.SUCCESS);
      result.setData(loginVo);
      System.out.println(loginVo);
      return result;
   }

}
