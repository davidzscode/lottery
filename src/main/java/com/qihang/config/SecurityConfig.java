package com.qihang.config;

import cn.hutool.core.util.StrUtil;
import com.qihang.filter.JWTAuthenticationTokenFilter;
import com.qihang.hander.UserLogoutSuccessHandler;
import com.qihang.hander.UserNotLoginHandler;
import com.qihang.mapper.shop.ShopMapper;
import com.qihang.mapper.user.SysUserMapper;
import com.qihang.mapper.user.UserMapper;
import com.qihang.service.security.CustomUserServiceImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-13 11:53
 */
@Configuration
@EnableWebSecurity
/**
 * @author qihang
 * 开启权限注解,默认是关闭的 当@EnableGlobalMethodSecurity(prePostEnabled=true)的时候，@PreAuthorize可以使用
 * 这里主要@PreAuthorize, @PostAuthorize, @Secured这三个注解可以使用
 * 当@EnableGlobalMethodSecurity(securedEnabled=true)的时候，@Secured可以使用：
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Data
@ConfigurationProperties(prefix = "exclude")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private ShopMapper shopMapper;

    /**
     * 需要放行的路径
     */
    private String[] path;

    /**
     * 自定义未登录的处理器
     */
    final UserNotLoginHandler userNotLoginHandler;
    /**
     * 自定义登录逻辑验证器
     */
    final CustomUserServiceImpl customUserService;

    /**
     * 自定义注销成功处理器
     */
    final UserLogoutSuccessHandler userLogoutSuccessHandler;


    public SecurityConfig(
            UserNotLoginHandler userNotLoginHandler,
            UserLogoutSuccessHandler userLogoutSuccessHandler,
            CustomUserServiceImpl customUserService) {
        this.userNotLoginHandler = userNotLoginHandler;
        this.customUserService = customUserService;
        this.userLogoutSuccessHandler = userLogoutSuccessHandler;
    }

    /**
     * 能够抛出UsernameNotFoundException异常
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(customUserService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 从配置文件获取不用进行权限验证的请求或资源
                .antMatchers(path).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                .and()
                // 配置未登录自定义处理类
                .httpBasic().authenticationEntryPoint(userNotLoginHandler)
                .and()
                // 配置退出地址
                .logout()
                // 配置用户登出自定义处理类
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .logoutRequestMatcher(request -> // 匹配多种用户类型的登出
                        StrUtil.equalsAny(request.getRequestURI(), "/user/logout"))
                .and()
                // 开启跨域
                .cors()
                .and()
                // 取消跨站请求伪造防护
                .csrf().disable();
        // 基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT过滤器   用来解析token
        http.addFilter(new JWTAuthenticationTokenFilter(authenticationManager(), userMapper, sysUserMapper, shopMapper));
    }

    /**
     * 全局配置：跳过登录校验url
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(path);
    }
}
