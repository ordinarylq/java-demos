package com.lq.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * @ClassName: WebAppSecurityConfig
 * @Author: LiQi
 * @Date: 2022-05-31 16:34
 * @Version: V1.0
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService detailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        http.authorizeRequests() // 认证请求
                .antMatchers("/layui/**", "/index.jsp") // 使用ant风格设置要授权的请求地址
                .permitAll() // 允许上面设置的ant风格的所有请求地址
                .antMatchers("/level1/**") //对请求划分角色以便进行访问控制
                .hasRole("外门弟子")
                .antMatchers("/level2/**")
                .hasRole("真传弟子")
                .antMatchers("/level3/**")
                .hasRole("十大弟子")
                .anyRequest() // 对于其他未设置的全部请求
                .authenticated() // 设置为需要认证
                .and()
                .formLogin() // 设置未授权的请求跳转到登录页
                .loginPage("/index.jsp") // 指定登录页，若不指定，会跳转到SpringSecurity的默认登录页
                .permitAll() // 为登录页设置为可以访问
                .loginProcessingUrl("/user/login.html") // 登录提交表单的地址
                .usernameParameter("loginAcct") // 登录账号名
                .passwordParameter("loginPswd") // 登录密码名
                .defaultSuccessUrl("/main.html") // 登录验证账号密码成功后跳转的地址
                .and()
                .logout() // 设置登出，没有关闭csrf时需要带上_csrf token，登出请求需要是post请求
                .logoutUrl("/user/logout.html") // 设置登出地址
                .logoutSuccessUrl("/index.jsp") // 设置登出成功跳转地址
                .and()
                .exceptionHandling() // 异常处理
                .accessDeniedPage("/to/no/auth/page.html") // 403 Access Denied异常处理
                .and()
                .rememberMe()
                .tokenRepository(jdbcTokenRepository);
    }

    // 设置登录系统的账号、密码
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("Tom").password(new BCryptPasswordEncoder().encode("123123"))
//                .roles("ADMIN", "外门弟子")
//                .and()
//                .withUser("XieJun").password(new BCryptPasswordEncoder().encode("123123"))
//                .roles("外门弟子", "真传弟子")
//                .and()
//                .withUser("ZhangYan").password(new BCryptPasswordEncoder().encode("123123"))
//                .roles("外门弟子", "真传弟子", "十大弟子");
        auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
