package com.geo.web.utils.config;



import com.geo.web.utils.jwt.JWTFilter;
import com.geo.web.utils.shiro.MyRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    //创建安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        // 设置自定义 realm.
        securityManager.setRealm(myRealm);
        //关闭session
        DefaultSubjectDAO subjectDAO=new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator=new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 先走 filter ，然后 filter 如果检测到请求头存在 token，则用 token 去 login，走 Realm 去验证
     */
    //创建shrio的过滤工厂
    /*
    * 在web程序中，shiro进行权限控制全部是通过一组过滤器集合进行控制的
    *
    * 使用注解版本
    * @RequiresPermissions():访问此方法必须具备的权限
    * @RequiresRoles():访问此方法必须具备的角色有什么
    *
    *
    * 1.过滤器：如果权限信息不匹配走setUnauthorizedUrl
    * 2.注解：如果权限信息不匹配抛出异常
    *
    * */
    @Bean
    public ShiroFilterFactoryBean factory(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        //1.创建过滤器工厂
        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 添加自己的过滤器并且取名为jwt
        //要设置有顺序的map
        Map<String, Filter> filterMap=new LinkedHashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt",new JWTFilter());
        factoryBean.setFilters(filterMap);

        // 设置无权限时跳转的 url;
        factoryBean.setUnauthorizedUrl("/unauthorized/noRole");
        Map<String,String> filterRuleMap=new HashMap<>();
        //使用过滤器的形式配置请求地址的依赖权限
        filterRuleMap.put("/unauthorized/**","anon");
        filterRuleMap.put("/getRole","anon");
        //访问login，不通过JWTFilter
        filterRuleMap.put("/login","anon");
        //访问login，不通过JWTFilter
        filterRuleMap.put("/login/**","anon");
//        filterRuleMap.put("/result/download/**","anon");
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**","jwt");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 添加注解支持，如果不加的话很有可能注解失效
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){

        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}