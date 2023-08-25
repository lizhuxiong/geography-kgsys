package com.geo.web.controller;

import com.geo.domain.resp.Result;
import com.geo.domain.resp.Status;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin//解决跨域
@RestController
@RequestMapping("/unauthorized")
public class ShiroController {

    @GetMapping(value = "/token", produces = "application/json;charset=utf-8")
    public Result token() {
        return new Result(Status.TOKEN_ERROR);
    }
}