package com.fish.service;

import com.fish.config.CommonData;
import com.fish.jpa.user.AdminRespository;
import com.fish.model.entity.user.AdminInfo;
import com.fish.securety.AESHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by thy on 16-12-27.
 */

public class SuccessHandle implements AuthenticationSuccessHandler {



    @Autowired
    private AdminRespository adminRespository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

            String email=request.getParameter("username");
            AdminInfo admin = adminRespository.findByEmail(email);
            if(admin!=null){
                Cookie cookie=new Cookie("uid", AESHelper.encrypt(""+admin.getId(), CommonData.ENCRYPT_KEY));
                cookie.setPath("/");
                cookie.setHttpOnly(true);

                response.addCookie(cookie);


                response.sendRedirect("/admin/add/add_ads");
            }



    }
}
