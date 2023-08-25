package com.geo.domain.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Panda_Xiong
 * @Date 2023-08-20 9:42
 */
@Data
@NoArgsConstructor
public class Result {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String msg;//返回信息
    private Object data;// 返回数据

    public Result(Status status) {
        this.flag = status.flag;
        this.code = status.code;
        this.msg = status.msg;
    }
    public Result(Status status, Object data) {
        this.flag = status.flag;
        this.code = status.code;
        this.msg = status.msg;
        this.data = data;
    }

    public Result(Integer code,  String msg,boolean flag) {
        this.code = code;
        this.msg = msg;
        this.flag = flag;
    }

}
