package com.itbaizhan.travel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//Security配置类
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义一个表单登录
        http.formLogin()
                .loginPage("/backstage/admin_login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/backstage/admin/login")
                .successForwardUrl("/backstage/index")
                .failureForwardUrl("/backstage/admin_fail");

        //权限拦截
        http.authorizeRequests()
                .antMatchers("/backstage/admin/login").permitAll()
                .antMatchers("/backstage/admin_fail").permitAll()
                .antMatchers("/backstage/admin_login").permitAll()
                .antMatchers("/**/*.css","/**/*.js").permitAll()
                .antMatchers("/backstage/**").authenticated();

        //退出登录
        http.logout()
                .logoutUrl("/backstage/admin/logout")
                .logoutSuccessUrl("/backstage/admin_login")
                .clearAuthentication(true)      //退出成功后清除认证信息
                .invalidateHttpSession(true); //清除session

        //异常处理
        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler());//权限不足处理器

        //关闭csrf防护
        http.csrf()
                .disable();
        //开启跨域访问
        http.cors();
    }

    //密码加密器
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
