package com.fish.service;

import com.fish.jpa.user.AdminRespository;
import com.fish.model.entity.user.AdminInfo;
import com.fish.securety.MD5;

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

    @Autowired
    private AdminRespository adminRespository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminInfo user = adminRespository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
               "e10adc3949ba59abbe56e057f20f883e", authorities);
    }

}
