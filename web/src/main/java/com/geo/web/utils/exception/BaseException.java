package com.geo.web.utils.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.geo.domain.resp.Result;
import com.geo.domain.resp.Status;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.apache.ibatis.binding.BindingException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class BaseException {

    // token认证失败，异常处理
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public Result handle401(UnauthenticatedException e) {
        System.err.println(e);
        return new Result(Status.UNAUTHORISE);
    }

    // 捕捉shiro的异常,权限不足异常处理
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401(UnauthorizedException e) {
        System.err.println(e);
        return new Result(Status.UNAUTHORISE);
    }

    //sql注入异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindingException.class)
    public Result handler(BindingException e) throws IOException {
        System.err.println(e);
        return new Result(Status.NOTFOUND);
    }

    //空指针处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NullPointerException.class)
    public Result handler(NullPointerException e) throws IOException {
        System.err.println(e);
        return new Result(Status.FAIL);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AuthenticationException.class)
    public Result handler(AuthenticationException e) throws IOException {
        System.err.println(e);
        return new Result(Status.TOKEN_ERROR);
    }

    // 身份验证失败
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = TokenExpiredException.class)
    public Result handler(TokenExpiredException e) throws IOException {
        System.err.println(e);
        return new Result(Status.TOKEN_ERROR);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MySQLSyntaxErrorException.class)
    public Result handler(MySQLSyntaxErrorException e) throws IOException {
        System.err.println(e);
        return new Result(Status.FAIL);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IOException.class)
    public Result handle401(IOException e) {
        return new Result(Status.FAIL);
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        return new Result(Status.UNAUTHORISE);
    }


    //异常处理
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Exception.class)
    public Result handle401(Exception e) {
        System.err.println(e);
        return new Result(Status.FAIL);
    }
}
