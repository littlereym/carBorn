package com.example.demo.config.shiro;



import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;




@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
      
    	ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);      
        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器：
         *         anon: 无需认证（登录） 可以访问
         *         authc: 必须认证才能可以访问
         *         user：如果使用rememMe的功能可以直接访问
         *         perms：该资源必须得到资源权限才可以访问
         *         role: 该资源必须得到角色权限才可以访问
       */
        Map<String,String> filterMap = new LinkedHashMap<String, String>();

        filterMap.put("/back/testThymeleaf","anon");
        //放行login.html页面
        filterMap.put("/back/login","anon");

        filterMap.put("/back/firstRegistered","anon");
        
        filterMap.put("/back/modifyNickname","anon");
       
        filterMap.put("/back/modifyNickname","anon");
        
        filterMap.put("/back/swagger-ui.html*","anon");
        
        filterMap.put("/back/startbootstrap/index","anon");
        
        filterMap.put("/back/showWalletInformation","anon");
        
        filterMap.put("/back/findAllTransactionRecord","anon");
        //授权过滤器
       // filterMap.put("/back/add","perms[user:admin]");
        
        filterMap.put("/back/update","perms[user:editor,user:admin]");
        // filterMap.put("/look","perms[user:contributor]");
//         filterMap.put("/add","anon");
        
        filterMap.put("/*","anon");
        
        //修改调整的登入界面
        shiroFilterFactoryBean.setLoginUrl("/back/toLogin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/back/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        
        return shiroFilterFactoryBean;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    
    public UserRealm userRealm() {
    	
    	UserRealm userRealm = new UserRealm();
    	
    	return userRealm;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    
   
    /**
     * 创建DefaultWebSecurityManager安全管理器
     */
   
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager( @Qualifier("userRealm")UserRealm userRealm){
    	
  //   System.out.println("userRealm.getName()==========="+userRealm.getName());
     
    	DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    	
        defaultWebSecurityManager.setRealm(userRealm);
        
        return defaultWebSecurityManager;
    }



    /**
     * 配置ShiroDialect,用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
    	
        return new ShiroDialect();
        
    }
    @Bean 
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet) { 
    ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<DispatcherServlet>( 
    dispatcherServlet); 
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); 
    return registration; 
    } 
}

