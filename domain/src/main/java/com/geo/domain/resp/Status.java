package com.geo.domain.resp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-20 9:44
 */
@NoArgsConstructor
@AllArgsConstructor
public enum Status {
   SUCCESS(true,200,"请求成功"),
   CREATED(true,201,"创建成功"),
   FAIL(false,202,"操作失败"),
   UNAUTHTICATION(false,400,"您还未登陆"),
   UNAUTHORISE(false,401,"权限不足"),
   TOKEN_ERROR(false,405,"token验证失败"),
   NOTFOUND(false,404,"请求的资源不存在"),
   SERVER_ERROR(false,500,"系统繁忙，请稍后再试");
   boolean flag;//是否成功
   int code;// 返回码
   String msg;//返回信息


   public boolean isFlag() {
      return flag;
   }

   public void setFlag(boolean flag) {
      this.flag = flag;
   }

   public int getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getMsg() {
      return msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }
}
