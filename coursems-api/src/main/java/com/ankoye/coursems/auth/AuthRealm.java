package com.ankoye.coursems.auth;

import com.ankoye.coursems.entity.User;
import com.ankoye.coursems.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * 用户访问需要授权url时调用
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("用户授权");
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();

        // 获得用户的角色，及权限进行绑定
//        for(Role role : user.getRoleList()){
//            authorizationInfo.addRole(role.getRolename());
//            for(Permission p : role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        // 简单处理下角色
        roles.add(user.getRole());
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 用户登录时调用Subject.login()进入这里
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        log.info("登录账号密码： " + account + ", " + password);
        // 从数据库查询出User信息及用户关联的角色，权限信息，以备权限分配时使用
        User user = userServiceImpl.findUserByAccount(account);
        if(null == user) return null;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, // 账号
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }
}