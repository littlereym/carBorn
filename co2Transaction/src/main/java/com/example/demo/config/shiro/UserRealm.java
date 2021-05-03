package com.example.demo.config.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.BackstageAccountMapper;
import com.example.demo.model.BackstageAccount;



public  class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加授权字符串
        //info.addStringPermission("user:add");
        System.out.println("info==="+info.toString());

        //到数据库查询当前登入用户的授权字符串
        //获取当前登入用户
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        BackstageAccount employee = (BackstageAccount) subject.getPrincipal();
        System.out.println("name========"+employee.getAccountName());
        backstageAccountMapper.findByaccountName(employee.getAccountName());

        info.addStringPermission(employee.getAccountPermissionLevel());
        return info;
    }

    @Autowired
    private BackstageAccountMapper backstageAccountMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token1 = (UsernamePasswordToken)token;
        System.out.println("token1:"+"name="+token1.getUsername()+"|||"+"password="+token1.getPassword());

        BackstageAccount backstageAccount = backstageAccountMapper.findByaccountName(token1.getUsername());
        if (backstageAccount==null||backstageAccount.getAccountStatus()==2) {
            //用户名不存在
            return null;//shiro底层会抛出异常UnKnowAccountException
        }

        //2.判断密码
        //第一个参数是返回给登录的参数，第二个数据库密码，第三个shiro的名字
        return new SimpleAuthenticationInfo(backstageAccount,backstageAccount.getAccountPassword(),"");

    }
}


