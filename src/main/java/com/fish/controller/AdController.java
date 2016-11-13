package com.fish.controller;

import com.fish.jpa.ad.AdRepository;
import com.fish.jpa.ad.ImageRepository;
import com.fish.model.ad.Ad;
import com.fish.model.ad.Images;
import com.fish.model.temple.Greeting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by thy on 16-11-3.
 */
@RestController
public class AdController {


    private static final Logger log = LoggerFactory.getLogger(AdController.class);
    @Autowired
    private AdRepository repository;
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping("/api/ads")
    public String ad() {

        insertAds();
        return "ok";
    }


    public void insertAds(){


        Ad ad=new Ad();
        ad.setTitle("海飞丝");
        ad.setContent("啊呀呀");
//        ad.setEnd_time(new Timestamp(new Date().getTime()));

        Images images=new Images();
        images.setUrl("./url/jpg12.jpg");
        images.setAd_id(ad);
        repository.save(ad);
        imageRepository.save(images);


    }

}

//    spring secuirty在session里放了一个Authentication，判断用户是否登录。
/**
 *
 @Controller
 @RequestMapping("/users")
 public class UserController {

 @RequestMapping("/{id}")
 public String showUserForm(@PathVariable("id") User user, Model model) {

 model.addAttribute("user", user);
 return "userForm";
 }
 }
 */

/**
 * @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
response.sendError(HttpStatus.BAD_REQUEST.value(),
"Please try again and with a non empty string as 'name'");
}
 *
 */


/**
自定义异常+@ResponseStatus注解：

//定义一个自定义异常，抛出时返回状态码404
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    ...
}

//在Controller里面直接抛出这个异常
@Controller
public class SomeController {
    @RequestMapping(value="/video/{id}",method=RequestMethod.GET)
    public @ResponseBody Video getVidoeById(@PathVariable long id){
        if (isFound()) {
            // 做该做的逻辑
        }
        else {
            throw new ResourceNotFoundException();//把这个异常抛出
        }
    }
}
使用Spring的内置异常

        默认情况下，Spring 的DispatcherServlet注册了DefaultHandlerExceptionResolver,这个resolver会处理标准的Spring MVC异常来表示特定的状态码

        Exception                                   HTTP Status Code
        ConversionNotSupportedException             500 (Internal Server Error)
        HttpMediaTypeNotAcceptableException         406 (Not Acceptable)
        HttpMediaTypeNotSupportedException          415 (Unsupported Media Type)
        HttpMessageNotReadableException             400 (Bad Request)
        HttpMessageNotWritableException             500 (Internal Server Error)
        HttpRequestMethodNotSupportedException      405 (Method Not Allowed)
        MissingServletRequestParameterException     400 (Bad Request)
        NoSuchRequestHandlingMethodException        404 (Not Found)
        TypeMismatchException                       400 (Bad Request)
        在Controller方法中通过HttpServletResponse参数直接设值

//任何一个RequestMapping 的函数都可以接受一个HttpServletResponse类型的参数
@Controller
public class SomeController {
    @RequestMapping(value="/video/{id}",method=RequestMethod.GET)
    public @ResponseBody Video getVidoeById(@PathVariable long id ,HttpServletResponse response){
        if (isFound()) {
            // 做该做的逻辑
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
        }
        return ....
    }
}
*/



//    /**
//     * 自动32位UUID
//     * @return
//     */
//    @Id
//    @Column(name = "pid", unique = true, nullable = false, length = 32)
//    @GeneratedValue(generator = "generator")
//    @GenericGenerator(name = "generator", strategy = "uuid")
//    public String getPid() {
//        return pid;
//    }