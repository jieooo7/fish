package com.fish.config;


import com.fish.service.CustomUserDetailsService;
import com.fish.service.SuccessHandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler success() {
        return new SuccessHandle();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
//        auth.userDetailsService(userDetailsService());
        auth.authenticationProvider(authenticationProvider());

//        auth.userDetailsService(userDetailService)
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"))

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
//        StandardPasswordEncoder

    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("thy@163.com").password("e10adc3949ba59abbe56e057f20f883e").roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/api/**","/files/**","/test/**").permitAll() //antMatchers(HttpMethod method, String... antPatterns)
                .anyRequest().authenticated()//authenticated()表示允许用户访问
                .and()
                .formLogin()
                .usernameParameter("username") // default is username
                .passwordParameter("password") // default is password
//                .loginProcessingUrl("/admin/login")//登录请求拦截的url,也就是form表单提交时指定的action,默认拦截的URL是/login,如果不使用默认的弹出框而使用自己的页面，表单的action必须和loginProcessingUrl()指定的一样，当然也需要是post方式
                .loginPage("/admin/login")
                .successHandler(success())//只能有一个,需要defaultSuccessUrl失效,不能和default-target-url还有always-use-default-target同时使用
//                .defaultSuccessUrl("/admin/add/add_ads")
                .permitAll()//对于路径不进行拦截
                .and()
                .csrf()
                .ignoringAntMatchers("/test/**")
                .and()////启用防跨站伪请求攻击，默认启用  HttpSessionCsrfTokenRepository
                .logout()
//        private String logoutSuccessUrl = "/login?logout";
//        private String logoutUrl = "/logout";
                .permitAll()
                .and()
                .rememberMe()
//                .rememberMeParameter("remeber-me")//默认为remeber-me ,可自定义
                .tokenValiditySeconds(1209600);;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/404.html","/500.html","/401.html","/fonts/**","/img/**","/dist/**","/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }

//    @Bean
//    public TokenBasedRememberMeServices tokenRepository() {
//        TokenBasedRememberMeServices token = new TokenBasedRememberMeServices();
//        db.setDataSource(dataSource);
//        return db;
//    }

}

//第一行是说访问/和匹配/assets/**模式的url都可以直接访问，下面说其他的需要认证。
// 使用form表单登录，为什么要指定这个呢？因为spring会从请求中查找username和password域参数。
// 从哪个请求中呢？默认是/login，可以通过login(String s)自定义。
// 表单提交的参数也可以通过usernameParameter()和passwordParamter()自定义。
// 如果不使用默认的弹出框而使用自己的页面，表单的action必须和loginProcessingUrl()指定的一样，当然也需要是post方式。
// 再往下是允许spring控制登出。默认访问/logout会执行登出，spring会使session无效，并清理rememberMe生成的cookie。
// logoutUrl()可以自定义登出的url，成功登出后的跳转url由logoutSuccessUrl()指定，默认是/login?logout，你可以这个页面判断有Logout参数就提示用户登出成功。

//(using an @Override of a method in the configurer) then the AuthenticationManagerBuilder is
// only used to build a "local" AuthenticationManager, which is a child of the global one.
// In a Spring Boot application you can @Autowired the global one into another bean,
// but you can’t do that with the local one unless you explicitly expose it yourself.