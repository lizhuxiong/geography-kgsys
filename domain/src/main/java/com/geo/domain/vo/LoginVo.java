package com.geo.domain.vo;

import com.geo.domain.po.UserPo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    private String userId;
    private String password;
}
