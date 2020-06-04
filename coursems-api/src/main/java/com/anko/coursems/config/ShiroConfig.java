package com.anko.coursems.config;

import com.anko.coursems.auth.AuthRealm;
import com.anko.coursems.auth.ShiroLoginFilter;
import com.anko.coursems.auth.ShiroLogoutFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 自定义认证失败处理
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new ShiroLoginFilter());
        filters.put("logout", new ShiroLogoutFilter());

        /**
         * 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
         * authc: 所有url都必须认证通过才可以访问
         * anon:所有url都都可以匿名访问
         * perms[user,admin] 表示需要某个或某些权限才能通过
         * roles[admin] 表示是某个或某些角色才能通过
         * 还有logout authcBasic user ssl port[8081] 等
         */
        Map<String,String> filterChainDefinitionMap = new  LinkedHashMap<>();

        // 权限配置
        // filterChainDefinitionMap.put("/stu/addStu", "perms[student:aaaa]");

        // 配置不会被拦截的链接 顺序判断  相关静态资源
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/products/**", "anon");
        filterChainDefinitionMap.put("/Widget/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        // 配置退出 过滤器
        filterChainDefinitionMap.put("/api/logout", "logout");

        // 配置不需要验证的请求
        filterChainDefinitionMap.put("/api/v1/login", "anon");
        filterChainDefinitionMap.put("/api/v1/login/visitor", "anon");
        filterChainDefinitionMap.put("/api/v1/register", "anon");
        filterChainDefinitionMap.put("/api/v1/users/current", "anon");
        filterChainDefinitionMap.put("/api/v1/reset-password", "anon");
        filterChainDefinitionMap.put("/api/v1/email/*/*", "anon");
        // test
        filterChainDefinitionMap.put("/api/**", "anon");
        // druid
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 其他全部需要验证
        filterChainDefinitionMap.put("/**", "authc");

        // 登录接口，返回json数据，由前端跳转
        shiroFilterFactoryBean.setLoginUrl("/api/v1/login");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    // 加密方式配置
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");   //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);          //散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    // 认证器配置
    @Bean
    public AuthRealm authRealm(){
        AuthRealm authRealm = new AuthRealm();
        // 密码加密
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return authRealm;
    }

    // 安全管理器配置
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm());
        // 自定义缓存实现 使用redis
        // securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 开启@RequirePermission注解的配置，
     * 要结合DefaultAdvisorAutoProxyCreator一起使用，或者导入aop的依赖
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
          DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
          advisorAutoProxyCreator.setProxyTargetClass(true);
          return advisorAutoProxyCreator;
    }

    // 定义Spring MVC的异常处理器
    @Bean
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","403");//处理shiro的认证未通过异常
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("ex");     // Default is "exception"
        return r;
    }

}

