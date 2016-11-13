package com.fish.controller;

import com.fish.jpa.ad.AdRepository;
import com.fish.jpa.ad.ImageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by thy on 16-11-13.
 */
@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/api/login")
    public String login() {
//        HttpServletRequest request;
//        HttpServletResponse response;
//        HttpHeaders
        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.expire("aaa", 180L, TimeUnit.SECONDS);
        return stringRedisTemplate.opsForValue().get("aaa");
    }


    @RequestMapping("/api/register")
    public String register() {
//        HttpServletRequest request;
//        HttpServletResponse response;
//        HttpHeaders
        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.expire("aaa", 180L, TimeUnit.SECONDS);
        return stringRedisTemplate.opsForValue().get("aaa");
    }


    @RequestMapping("/api/forgotPass")
    public String forgotPass() {
//        HttpServletRequest request;
//        HttpServletResponse response;
//        HttpHeaders
        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.expire("aaa", 180L, TimeUnit.SECONDS);
        return stringRedisTemplate.opsForValue().get("aaa");
    }


}


//    @Autowired
//    private HttpServletRequest request;
//
//    @RequestMapping(value="/printname/{name}", method=RequestMethod.GET)
//    public String printName(@PathVariable String name,
//                            @RequestHeader HttpHeaders headers) {
//        System.out.println("from request:" + request.getHeader("code"));
//        System.out.println("from parameter:" + headers.getFirst("code"));
//
//        return "hello";
//    }
//@RequestHeader("Accept-Encoding") String encoding,
//    @RequestHeader("Keep-Alive") long keepAlive


//    @RequestHeader(value="User-Agent") String userAgent, @RequestParam(value = "ID", defaultValue = "") String id


//    @RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "data")
//    @ResponseBody
//    public ResponseEntity<Data> getData(HttpServletRequest request, @RequestParam(value = "ID", defaultValue = "") String id)
//    {
//        String userAgent = request.getHeader("user-agent");
//    }