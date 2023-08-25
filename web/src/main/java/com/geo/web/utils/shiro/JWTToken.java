package com.geo.web.utils.shiro;

import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token){
        this.token=token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}