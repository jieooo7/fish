package com.fish.controller;

import com.fish.config.ErrorCode;
import com.fish.jpa.user.UserRepository;
import com.fish.model.response.BaseModel;
import com.fish.model.entity.user.UserInfo;
import com.fish.securety.MD5;
import com.fish.util.CodeGenetate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by thy on 16-11-13.
 */
@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserRepository repository;

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
    public BaseModel register(@RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "tel", defaultValue = "") String tel,
                              @RequestParam(value = "pass", defaultValue = "") String pass,
                              @RequestParam(value = "email", defaultValue = "") String email) {
//        HttpServletRequest request;
//        HttpServletResponse response;
//        HttpHeaders
        try {
            UserInfo user=new UserInfo();
            String code=CodeGenetate.create();
            user.setCode(code);
            user.setName(name);
            user.setPasswd(MD5.getMD5(pass+code));//密码配合code进行MD5处理
            user.setTel(tel);
            Date date = new Date();//获得系统时间.
            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
            user.setRegister_time(Timestamp.valueOf(nowTime));
            user.setLogin_time(new Timestamp(new java.util.Date().getTime()));
            user.setEmail(email);
            repository.save(user);
            log.debug("时间戳:"+new Date().getTime());
            return new BaseModel();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new BaseModel(ErrorCode.DATABASEERROR,e.getMessage(),null);
        }


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