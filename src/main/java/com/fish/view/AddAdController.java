package com.fish.view;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fish.config.CommonData;
import com.fish.controller.AdController;
import com.fish.jpa.ad.AdRepository;
import com.fish.jpa.ad.PuzzleRepository;
import com.fish.jpa.ad.QuestionRepository;
import com.fish.jpa.user.AdminRespository;
import com.fish.jpa.user.UserRepository;
import com.fish.model.entity.ad.Ad;
import com.fish.model.entity.ad.Images;
import com.fish.model.entity.ad.Videos;
import com.fish.model.entity.puzzle.PuzzleCard;
import com.fish.model.entity.question.Question;
import com.fish.model.entity.user.AdminInfo;
import com.fish.model.response.BaseModel;
import com.fish.model.response.model.PuzzleModel;
import com.fish.securety.AESHelper;
import com.fish.securety.MD5;
import com.fish.storage.FileSystemStorageService;
import com.fish.storage.StorageProperties;
import com.fish.storage.StorageService;
import com.fish.util.CodeGenetate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring4.view.ThymeleafView;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javassist.bytecode.BadBytecode;

/**
 * Created by thy on 16-12-9.
 */
@Controller
@RequestMapping("/")
public class AddAdController {

    private static final Logger log = LoggerFactory.getLogger(AddAdController.class);
    @Autowired
    private AdRepository repository;

    @Autowired
    private PuzzleRepository puzzleRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AdminRespository adminRespository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private StorageProperties properties;//用于设置存储文件目录,用两个store service 分别设置

    @Autowired
    private StorageService storageService;

    @Autowired
    private StorageService picStorageService;

//多线程环境,尽量少用成员变量,多用局部变量

    @RequestMapping("/admin/add/add_ads")
//    @PreAuthorize(“authenticated and hasPermission(‘hello’, ‘view’)”)表示只有当前已登录的并且拥有(“hello”, “view”)权限的用户才能访问此页面
    public String ok( Model model) {
        String codeValue = CodeGenetate.getInstance().create();

        String authValue = MD5.getMD5(codeValue);//对codeValue进行加密

        stringRedisTemplate.opsForValue().set("token", authValue);

        model.addAttribute("token",authValue);

        return "add_ads";
    }


    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "admin_login";
    }


    @RequestMapping("/")
    public String index() {

        return "user/index";
    }


    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String formLogin(@RequestParam(value = "username", defaultValue = "") String email,
                            @RequestParam(value = "password", defaultValue = "") String passwd,
                            RedirectAttributes redirectAttributes,
                            HttpServletResponse response) {//123456
        AdminInfo admin = adminRespository.findByEmail(email);
        if (admin == null || !bCrypt.matches(passwd, admin.getPasswd())) {
//            Flash attributes 为一个请求存储意图为另外一个请求所使用的属性提供了一条途径.
// Flash attributes 在对请求的重定向生效之前被临时存储（通常是在session)中，并且在重定向之后被立即移除.
            redirectAttributes.addFlashAttribute("message",
                    "邮箱或者密码错误");
            return "redirect:/admin/login";

        } else {

            response.addCookie(new Cookie("uid", AESHelper.encrypt(""+admin.getId(), CommonData.ENCRYPT_KEY)));
            return "redirect:/admin/add/add_ads";
        }


    }
    @RequestMapping(value = "/admin/add/add_ads", method = RequestMethod.POST)
    public String add_ok(  HttpServletRequest request,
                           @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId,
            @CookieValue(value = "uid", defaultValue = "-1") String uid,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "descripe", defaultValue = "") String descripe,
            @RequestParam(value = "total_money", defaultValue = "0") int total_money,
            @RequestParam(value = "top_money", defaultValue = "0") int top_money,
            @RequestParam(value = "man", defaultValue = "0") int man,
            @RequestParam(value = "catagory", defaultValue = "") byte catagory,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "video1", defaultValue = "") String video1,
            @RequestParam(value = "time1", defaultValue = "") String time1,
            @RequestParam(value = "time2", defaultValue = "") String time2,
            @RequestParam(value = "video", defaultValue = "") MultipartFile video,
            @RequestParam(value = "content", defaultValue = "") String content
                          ) {
//        BigDecimal a=new BigDecimal(12.88);
//        int b=a.intValue();

        AdminInfo admin = adminRespository.findById(Integer.parseInt(AESHelper.decrypt(uid, CommonData.ENCRYPT_KEY)));
        if(admin==null){
            return "redirect:/logout";
        }



        Ad ad=new Ad();
        List<Videos> videos=new ArrayList<Videos>();
        List<Images> images=new ArrayList<Images>();

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        ad.setTitle(title);
        ad.setDescripe(descripe);
        ad.setMoney(new BigDecimal(total_money));
        ad.setTop_bonus(top_money);
        ad.setTop_bonus_people(man);
        ad.setCatalog(catagory);
        ad.setKeyword(keyword);
        ad.setPublish_time(new Timestamp(new java.util.Date().getTime()));
        try {
            ad.setStart_time(new Timestamp(format.parse(time1).getTime()));
            ad.setEnd_time(new Timestamp(format.parse(time2).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ad.setContent(content);

        if(!video1.isEmpty()){
            Videos video_model=new Videos();
            video_model.setUrl(video1);
            video_model.setAd_id(ad);
            videos.add(video_model);
            ad.setVideo_num(videos.size());
        }
        if(!video.isEmpty()){
            storageService.setDir(properties.getVideo());
            storageService.store(video);
            Videos video_model=new Videos();
            video_model.setUrl("/videos/"+storageService.getName());
            video_model.setAd_id(ad);
            videos.add(video_model);
            ad.setVideo_num(videos.size());
            ad.setVideos(videos);
        }

        Pattern p = Pattern.compile("<img[\\s]+src[\\s]*=[\\s]*((['\"](?<src>[^'\"]*)[\\'\"])|(?<src1>[^\\s]*))");//只选择其中一条路走
        Matcher m = p.matcher(content);
        while (m.find()){

            Images image=new Images();
            image.setUrl(m.group("src"));
            image.setAd_id(ad);
            images.add(image);
            ad.setImages(images);

        }
        ad.setImage_num(images.size());
        ad.setAuthor_name(admin.getNick_name());
        ad.setAuthor_pic(admin.getHead_pic());
        repository.save(ad);

        HttpSession session =request.getSession();
        session.setAttribute(""+session.getAttribute(sessionId),ad.getId());

//        log.info("=================id"+session.getAttribute(""+session.getAttribute(sessionId)));


        return "redirect:/admin/add/puzzle_question";
    }


    @RequestMapping(value = "/admin/add/puzzle_question")
    public String add_puzzle(HttpServletRequest request,
                             @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId,
                             Model model){
        HttpSession session =request.getSession();
        model.addAttribute("ad_id",session.getAttribute(""+session.getAttribute(sessionId)));

        return "add_puzzle";
    }



//    Exception                                   HTTP Status Code
//    ConversionNotSupportedException             500 (Internal Server Error)
//    HttpMediaTypeNotAcceptableException         406 (Not Acceptable)
//    HttpMediaTypeNotSupportedException          415 (Unsupported Media Type)
//    HttpMessageNotReadableException             400 (Bad Request)
//    HttpMessageNotWritableException             500 (Internal Server Error)
//    HttpRequestMethodNotSupportedException      405 (Method Not Allowed)
//    MissingServletRequestParameterException     400 (Bad Request)
//    NoSuchRequestHandlingMethodException        404 (Not Found)
//    TypeMismatchException                       400 (Bad Request)

    @ResponseBody
    @RequestMapping(value = "/admin/add/puzzle", method = RequestMethod.POST)
    public String add_puzzle( @CookieValue(value = "uid", defaultValue = "-1") String uid,
                              @RequestParam(value = "ad_id", defaultValue = "") String ad_id,
                              @RequestParam("puzzle_name") String[] names,
                              @RequestParam("puzzle_desc") String[] puzzle_desc,
                              @RequestParam("puzzle_num") String[] num,
                              @RequestParam("puzzle_score") String[] score,
                              @RequestParam("puzzle_value") String[] puzzle_value,
                              @RequestParam("puzzle_file") MultipartFile file
                              ){

        if(uid.equals("-1")||ad_id.isEmpty()){
            throw new HttpMessageNotReadableException("");
        }

        String pic_url="";
        if(!file.isEmpty()){
            picStorageService.setDir(properties.getUpload());
            picStorageService.store(file);
            pic_url="/files/"+picStorageService.getName();
        }
        //use commit
        for(int i=0;i<names.length;i++){
            PuzzleCard puzzleCard=new PuzzleCard();
            puzzleCard.setAdId(Integer.parseInt(ad_id));
            puzzleCard.setPic_url(pic_url);
            if(!names[i].isEmpty()){

                puzzleCard.setCard_name(names[i]);
            }

            if(!puzzle_desc[i].isEmpty()){

                puzzleCard.setCard_desc(puzzle_desc[i]);
            }

            puzzleCard.setSeq((byte)i);

            if(!num[i].isEmpty()){

                puzzleCard.setPic_total(""+num[i]);
            }
            if(!score[i].isEmpty()){

                puzzleCard.setNumber(Integer.parseInt(score[i]));
            }
            if(!puzzle_value[i].isEmpty()){

                puzzleCard.setValuetation(Integer.parseInt(puzzle_value[i]));
            }
            puzzleCard.setName(repository.findById(Integer.parseInt(ad_id)).getTitle());
            puzzleCard.setCreate_time(new Timestamp(new java.util.Date().getTime()));

            puzzleRepository.save(puzzleCard);


        }

        BaseModel model=new BaseModel();



        ObjectMapper mapper = new ObjectMapper();
        String result="-1";
        try {
            result=mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return result;
    }



    @ResponseBody
    @RequestMapping(value = "/admin/add/question", method = RequestMethod.POST)
    public String add_question( @CookieValue(value = "uid", defaultValue = "-1") String uid,
                                @RequestParam(value = "ad_id", defaultValue = "") String ad_id,
                                @RequestParam("question_name") String[] question_names,
                                @RequestParam("answer") String[] answer,
                                @RequestParam("right_answer") String[] right_answer
                                ){

        if(uid.equals("-1")||ad_id.isEmpty()){
            throw new HttpMessageNotReadableException("");
        }

        for(int i=0;i<question_names.length;i++){

            Question question=new Question();
            question.setAdId(Integer.parseInt(ad_id));
            question.setAnswer(answer[i]);
            question.setRight_answer(right_answer[i]);
            question.setTitle(repository.findById(Integer.parseInt(ad_id)).getTitle());

            questionRepository.save(question);


        }



        BaseModel model=new BaseModel();



        ObjectMapper mapper = new ObjectMapper();
        String result="-1";
        try {
            result=mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

}


//    public @ResponseBody String handleFileUpload(MultipartHttpServletRequest request){
//        Iterator<String> iterator = request.getFileNames();
//        while (iterator.hasNext()) {
//            String fileName = iterator.next();
//            MultipartFile multipartFile = request.getFile(fileName);
//            byte[] file = multipartFile.getBytes();
//            ...
//        }
//        ...
//    }