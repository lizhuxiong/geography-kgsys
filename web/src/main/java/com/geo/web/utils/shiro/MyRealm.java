package com.geo.web.utils.shiro;



import com.geo.domain.po.UserPo;
import com.geo.service.UserService;
import com.geo.web.utils.jwt.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    //根据token判断此Authenticator是否使用该realm
    //必须重写不然shiro会报错
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如@RequiresRoles,@RequiresPermissions之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
////        PrincipalCollection包含了所有已认证的安全数据
//        //AuthorizationInfo：授权数据
//        System.out.println("授权~~~~~");
//        //获取安全数据
//        String token=principals.toString();
//        String userId = JWTUtil.getUserId(token);
//        String roleId = JWTUtil.getRoleId(token);
//
//        //构造返回
//        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        //查询数据库来获取用户的角色
//        //设置角色
//        info.addRole(roleId);
//        //查询数据库来获取用户的权限
//        List<String> roles = userBiz.queryRoleByRoleId(roleId);
//        System.out.println(roles);
//        //设置权限集合
//        info.addStringPermissions(roles);
////        for (String role: roles){
////            info.addStringPermission(role);
////        }
        return null;
    }


    /**
     * 默认使用此方法进行学号正确与否验证，错误抛出异常即可，在需要用户认证和鉴权的时候才会调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证~~~~~~~");
        String jwt= (String) token.getCredentials();
        String userId= null;
        //decode时候出错，可能是token的长度和规定好的不一样了
        try {
            userId= JWTUtil.getUserId(jwt);
        }catch (Exception e){
            throw new AuthenticationException("token非法，不是规范的token，可能被篡改了，或者过期了");
        }
        if (!JWTUtil.verify(jwt)||userId==null){
            throw new AuthenticationException("token认证失效，token错误或者过期，重新登陆");
        }
        UserPo user=userService.queryUserByUserId(userId);
        if (user==null){
            throw new AuthenticationException("该用户不存在");
        }

        return new SimpleAuthenticationInfo(jwt,jwt,"MyRealm");//1.安全数据，2.密码，3.当前reaml域名称
    }
}