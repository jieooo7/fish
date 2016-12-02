package com.fish.config;

import com.fish.controller.AdController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by thy on 16-11-8.
 */

public class UserSecurityInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AdController.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(">>>MyInterceptor2>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        log.debug(">>>当前的url>>>>>>>"+requestUri);
        log.debug(">>>当前的contextPath>>>>>>>"+contextPath);
        log.debug(">>>当前的url+++>>>>>>>"+url);
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        request.getHeader()
//        System.out.println(">>>MyInterceptor2>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");


    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

//        System.out.println(">>>MyInterceptor2>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");

    }

}
