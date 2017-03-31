package com.fish.controller;

import com.fish.config.CommonData;
import com.fish.config.ErrorCode;
import com.fish.jpa.ad.AdRepository;
import com.fish.test.PartPara;
import com.fish.jpa.user.UserRepository;
import com.fish.model.response.BaseModel;
import com.fish.model.entity.user.UserInfo;
import com.fish.securety.AESHelper;
import com.fish.util.CodeGenetate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    //    value="/add_dev", method= RequestMethod.POST
    @RequestMapping("/api/login")
    public BaseModel<UserInfo> login(@RequestParam(value = "tel", defaultValue = "") String tel,
                                     @RequestParam(value = "passwd", defaultValue = "") String passwd,
                                     @RequestParam(value = "code", defaultValue = "") String code) {


        BaseModel<UserInfo> baseModel = new BaseModel<UserInfo>();
        if (tel.isEmpty()||passwd.isEmpty()){
            baseModel.setErrorCode(ErrorCode.EMPTY_INPUT);
            baseModel.setMsg("账号或密码不能为空");
            return baseModel;
        }
        UserInfo userInfo = null;

        if (isTel(tel)) {//判断是不是电话
            userInfo = repository.findByTel(tel);
        } else if (isEmail(tel)) {
            userInfo = repository.findByEmail(tel);
        } else {//用户名不能纯数字
            userInfo = repository.findByName(tel);
        }


        if (userInfo == null || !bCrypt.matches(passwd,userInfo.getPasswd())) {
            baseModel.setErrorCode(ErrorCode.PASSWD_ERROR);
            baseModel.setMsg("账号或密码错误");
            return baseModel;
        } else {
            baseModel.setErrorCode(ErrorCode.OK);
            baseModel.setMsg("");
            String key = userInfo.getId() + "session";
            String codeValue = CodeGenetate.getInstance().create();

            String authValue = AESHelper.encrypt(codeValue, CommonData.ENCRYPT_KEY);//对codeValue进行加密

            stringRedisTemplate.opsForValue().set(key, codeValue);
            stringRedisTemplate.expire("key", CommonData.EXPIRETIME, TimeUnit.SECONDS);

            userInfo.setAuth_key(authValue);

            baseModel.setData(userInfo);
            return baseModel;

        }



    }


    @RequestMapping("/api/register")
    public BaseModel register(@RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "tel", defaultValue = "") String tel,
                              @RequestParam(value = "passwd", defaultValue = "") String pass,
                              @RequestParam(value = "email", defaultValue = "") String email) {
//        HttpServletRequest request;
//        HttpServletResponse response;
//        HttpHeaders

//        验证唯一性约束,查看是否重复,多一次查询
        try {
            UserInfo user = new UserInfo();
            user.setName(name);
            user.setPasswd(bCrypt.encode(pass));//密码配合code进行MD5处理
            user.setTel(tel);
//            Date date = new Date();//获得系统时间.
//            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
//            user.setRegister_time(Timestamp.valueOf(nowTime));
            user.setRegister_time(new Timestamp(new java.util.Date().getTime()));
            user.setLogin_time(new Timestamp(new java.util.Date().getTime()));
            user.setEmail(email);
            repository.save(user);
            return new BaseModel();//跳转重新登录 添加过滤认证


        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new BaseModel(ErrorCode.DATABASEERROR,"", null);
//            e.getMessage()
        }


    }


    @RequestMapping("/api/forgotPass")
//    @Scheduled
    public String forgotPass() {
//        HttpServletRequest request;
//        HttpServletResponse response;
//        HttpHeaders
        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.expire("aaa", 180L, TimeUnit.SECONDS);
        return stringRedisTemplate.opsForValue().get("aaa");
    }


    @RequestMapping("/api/test")
    public List<PartPara> test() {
        return adRepository.findAFR(16);

//        return ""+repository.findByTel("18064486666").getRegister_time();
    }


    public boolean isEmail(String email) {

//        Pattern p = Pattern.compile("^(\\w+[-|\\.]?)+\\w@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Pattern p = Pattern.compile("^[\\.a-zA-Z0-9_-]+@([a-zA-Z0-9_-]+\\.)+[a-zA-Z]{2,3}$");
//        Pattern p = Pattern.compile("([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public boolean isTel(String tel) {

        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(tel);

        return m.matches();
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