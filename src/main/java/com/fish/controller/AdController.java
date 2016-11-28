package com.fish.controller;

import com.fish.jpa.ad.AdRepository;
import com.fish.jpa.ad.CollectRepository;
import com.fish.jpa.ad.CommentRepository;
import com.fish.jpa.ad.ImageRepository;
import com.fish.jpa.ad.NoticeRepository;
import com.fish.jpa.ad.PuzzleUserRepository;
import com.fish.model.entity.ad.Ad;
import com.fish.model.entity.ad.Comment;
import com.fish.model.entity.ad.Images;
import com.fish.model.response.BaseModel;
import com.fish.model.response.model.AdModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private PuzzleUserRepository puzzleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/api/ads")
    public String ad() {

        insertAds();
        return "ok";
    }

    @RequestMapping("/api/get_ads")
    public BaseModel<Page<Ad>> getAd(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "15") int size,
                                     @RequestParam(value = "sort", defaultValue = "id") String sort_type) {
        Sort sort = new Sort(Sort.Direction.DESC, sort_type);
        Pageable pageable = new PageRequest(page, size, sort);

        BaseModel<Page<Ad>> model = new BaseModel<Page<Ad>>();

        model.setData(repository.findAll(pageable));


        return model;


//        List< Order> orders=new ArrayList< Order>();
//        orders.add( new Order(Direction. ASC, "c"));
//        orders.add( new Order(Direction. DESC, "d"));
//        Pageable pageable= new PageRequest(pageNumber, pageSize, new Sort(orders));
//        jpaRepo.findByAAndB(a,b,pageable);
    }

    @RequestMapping("/api/get_comments")
    public BaseModel<Page<Comment>> getComments(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "15") int size,
                                                @RequestParam(value = "ad_id") int ad_id) {

        List<Sort.Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "sequence"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "time"));
        Pageable pageable = new PageRequest(page, size, new Sort(orders));

        BaseModel<Page<Comment>> model = new BaseModel<Page<Comment>>();

        model.setData(commentRepository.findByAdId(ad_id,pageable));


        return model;

    }

    @RequestMapping("/api/post_comments")
    public BaseModel postComments(@RequestHeader(value="uid",defaultValue = "1") String uid,
                                 @RequestParam(value = "ad_id") int ad_id,
                                 @RequestParam(value = "parent_id" ,defaultValue ="-1") int parent_id,
                                 @RequestParam(value = "content" ,defaultValue ="") String content) {


        BaseModel model = new BaseModel();
        Comment comment=new Comment();
        comment.setTime(new Timestamp(new java.util.Date().getTime()));
        comment.setAdId(ad_id);
        comment.setParentId(parent_id);
        comment.setContent(content);
        if(-1!=parent_id){

            comment.setSequence(parent_id*10+1);
        }else{
            comment.setSequence((commentRepository.findFirstByOrderByIdDesc().getId()+1)*10);
        }
        commentRepository.save(comment);

//查询自己的头像,名字 和所回复的id的头像和名字 并存入数据库
        return model;

    }

    @RequestMapping("/api/get_ad_item")
    public BaseModel<AdModel> getAdDetail(@RequestHeader(value="uid",defaultValue = "1") String uid, @RequestParam(value = "ad_id", defaultValue = "0") int ad_id) {

        AdModel adModel = new AdModel();
        adModel.setFinish_percent("50%");
        adModel.setPuzzle_no(""+puzzleRepository.countByUserIdAndCardId(Integer.parseInt(uid),ad_id));
        if(collectRepository.countByUserIdAndAdId(Integer.parseInt(uid),ad_id)>0)
        {
            adModel.setCollect(true);
        }else{
            adModel.setCollect(false);
        }


        if(noticeRepository.countByUserIdAndAdId(Integer.parseInt(uid),ad_id)>0)
        {
            adModel.setNotice(true);
        }else{
            adModel.setNotice(false);
        }
        BaseModel<AdModel> model = new BaseModel<AdModel>();
        model.setData(adModel);
        return model;

    }


    public void insertAds() {


        Ad ad = new Ad();
        ad.setTitle("海飞丝");
        ad.setContent("啊呀呀");
//        ad.setEnd_time(new Timestamp(new Date().getTime()));
        ad.setStart_time(new Timestamp(new java.util.Date().getTime()));
        ad.setPublish_time(new Timestamp(new java.util.Date().getTime()));
        ad.setEnd_time(new Timestamp(new java.util.Date().getTime()));
        Images images = new Images();
        images.setUrl("./url/jpg12.jpg");
        images.setAd_id(ad);
        images.setTime(new Timestamp(new java.util.Date().getTime()));
        repository.save(ad);
        imageRepository.save(images);


    }

}

//    spring secuirty在session里放了一个Authentication，判断用户是否登录。
/**
 * @Controller
 * @RequestMapping("/users") public class UserController {
 * @RequestMapping("/{id}") public String showUserForm(@PathVariable("id") User user, Model model) {
 * <p>
 * model.addAttribute("user", user);
 * return "userForm";
 * }
 * }
 * @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
 * void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
 * response.sendError(HttpStatus.BAD_REQUEST.value(),
 * "Please try again and with a non empty string as 'name'");
 * }
 * <p>
 * <p>
 * 自定义异常+@ResponseStatus注解：
 * <p>
 * //定义一个自定义异常，抛出时返回状态码404
 * @ResponseStatus(value = HttpStatus.NOT_FOUND)
 * public class ResourceNotFoundException extends RuntimeException {
 * ...
 * }
 * <p>
 * //在Controller里面直接抛出这个异常
 * @Controller public class SomeController {
 * @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id){
 * if (isFound()) {
 * // 做该做的逻辑
 * }
 * else {
 * throw new ResourceNotFoundException();//把这个异常抛出
 * }
 * }
 * }
 * 使用Spring的内置异常
 * <p>
 * 默认情况下，Spring 的DispatcherServlet注册了DefaultHandlerExceptionResolver,这个resolver会处理标准的Spring MVC异常来表示特定的状态码
 * <p>
 * Exception                                   HTTP Status Code
 * ConversionNotSupportedException             500 (Internal Server Error)
 * HttpMediaTypeNotAcceptableException         406 (Not Acceptable)
 * HttpMediaTypeNotSupportedException          415 (Unsupported Media Type)
 * HttpMessageNotReadableException             400 (Bad Request)
 * HttpMessageNotWritableException             500 (Internal Server Error)
 * HttpRequestMethodNotSupportedException      405 (Method Not Allowed)
 * MissingServletRequestParameterException     400 (Bad Request)
 * NoSuchRequestHandlingMethodException        404 (Not Found)
 * TypeMismatchException                       400 (Bad Request)
 * 在Controller方法中通过HttpServletResponse参数直接设值
 * <p>
 * //任何一个RequestMapping 的函数都可以接受一个HttpServletResponse类型的参数
 * @Controller public class SomeController {
 * @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id ,HttpServletResponse response){
 * if (isFound()) {
 * // 做该做的逻辑
 * }
 * else {
 * response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
 * }
 * return ....
 * }
 * }
 * @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
 * void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
 * response.sendError(HttpStatus.BAD_REQUEST.value(),
 * "Please try again and with a non empty string as 'name'");
 * }
 * <p>
 * <p>
 * 自定义异常+@ResponseStatus注解：
 * <p>
 * //定义一个自定义异常，抛出时返回状态码404
 * @ResponseStatus(value = HttpStatus.NOT_FOUND)
 * public class ResourceNotFoundException extends RuntimeException {
 * ...
 * }
 * <p>
 * //在Controller里面直接抛出这个异常
 * @Controller public class SomeController {
 * @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id){
 * if (isFound()) {
 * // 做该做的逻辑
 * }
 * else {
 * throw new ResourceNotFoundException();//把这个异常抛出
 * }
 * }
 * }
 * 使用Spring的内置异常
 * <p>
 * 默认情况下，Spring 的DispatcherServlet注册了DefaultHandlerExceptionResolver,这个resolver会处理标准的Spring MVC异常来表示特定的状态码
 * <p>
 * Exception                                   HTTP Status Code
 * ConversionNotSupportedException             500 (Internal Server Error)
 * HttpMediaTypeNotAcceptableException         406 (Not Acceptable)
 * HttpMediaTypeNotSupportedException          415 (Unsupported Media Type)
 * HttpMessageNotReadableException             400 (Bad Request)
 * HttpMessageNotWritableException             500 (Internal Server Error)
 * HttpRequestMethodNotSupportedException      405 (Method Not Allowed)
 * MissingServletRequestParameterException     400 (Bad Request)
 * NoSuchRequestHandlingMethodException        404 (Not Found)
 * TypeMismatchException                       400 (Bad Request)
 * 在Controller方法中通过HttpServletResponse参数直接设值
 * <p>
 * //任何一个RequestMapping 的函数都可以接受一个HttpServletResponse类型的参数
 * @Controller public class SomeController {
 * @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id ,HttpServletResponse response){
 * if (isFound()) {
 * // 做该做的逻辑
 * }
 * else {
 * response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
 * }
 * return ....
 * }
 * }
 * @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
 * void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
 * response.sendError(HttpStatus.BAD_REQUEST.value(),
 * "Please try again and with a non empty string as 'name'");
 * }
 * <p>
 * <p>
 * 自定义异常+@ResponseStatus注解：
 * <p>
 * //定义一个自定义异常，抛出时返回状态码404
 * @ResponseStatus(value = HttpStatus.NOT_FOUND)
 * public class ResourceNotFoundException extends RuntimeException {
 * ...
 * }
 * <p>
 * //在Controller里面直接抛出这个异常
 * @Controller public class SomeController {
 * @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id){
 * if (isFound()) {
 * // 做该做的逻辑
 * }
 * else {
 * throw new ResourceNotFoundException();//把这个异常抛出
 * }
 * }
 * }
 * 使用Spring的内置异常
 * <p>
 * 默认情况下，Spring 的DispatcherServlet注册了DefaultHandlerExceptionResolver,这个resolver会处理标准的Spring MVC异常来表示特定的状态码
 * <p>
 * Exception                                   HTTP Status Code
 * ConversionNotSupportedException             500 (Internal Server Error)
 * HttpMediaTypeNotAcceptableException         406 (Not Acceptable)
 * HttpMediaTypeNotSupportedException          415 (Unsupported Media Type)
 * HttpMessageNotReadableException             400 (Bad Request)
 * HttpMessageNotWritableException             500 (Internal Server Error)
 * HttpRequestMethodNotSupportedException      405 (Method Not Allowed)
 * MissingServletRequestParameterException     400 (Bad Request)
 * NoSuchRequestHandlingMethodException        404 (Not Found)
 * TypeMismatchException                       400 (Bad Request)
 * 在Controller方法中通过HttpServletResponse参数直接设值
 * <p>
 * //任何一个RequestMapping 的函数都可以接受一个HttpServletResponse类型的参数
 * @Controller public class SomeController {
 * @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id ,HttpServletResponse response){
 * if (isFound()) {
 * // 做该做的逻辑
 * }
 * else {
 * response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
 * }
 * return ....
 * }
 * }
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
 @Controller public class SomeController {
 @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id){
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
 @Controller public class SomeController {
 @RequestMapping(value="/video/{id}",method=RequestMethod.GET) public @ResponseBody Video getVidoeById(@PathVariable long id ,HttpServletResponse response){
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