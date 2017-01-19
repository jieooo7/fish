package com.fish.service;

import com.fish.jpa.user.AdminRespository;
import com.fish.model.entity.user.AdminInfo;
import com.fish.securety.AESHelper;
import com.fish.securety.MD5;
import com.fish.view.AddAdController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thy on 16-12-19.
 */

public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AdminRespository adminRespository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminInfo user = adminRespository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("not found");
        }
//        GrantedAuthority，表示在应用范围内授予principal的权利许可。
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPasswd(), authorities);
    }

}
//SecurityContextHolder，提供对 SecurityContext的访问
//
//        SecurityContext，维护了 Authentication 和可能的特定请求的安全信息
//
//        Authentication，以Spring Security的方式描述principal。
//
//        GrantedAuthority，表示在应用范围内授予principal的权利许可。
//
//
//        UserDetailsService，用来根据传递的字符串形式的用户名(或者验证id等类似信息)来创建 UserDetails 对象。