package com.fish.view;

import com.fish.controller.AdController;
import com.fish.jpa.ad.AdRepository;
import com.fish.storage.StorageProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by thy on 16-12-9.
 */
@Controller
public class AddAdController {

    private static final Logger log = LoggerFactory.getLogger(AddAdController.class);
    @Autowired
    private AdRepository repository;


    @Autowired
    private StorageProperties properties;//用于设置存储文件目录,用两个store service 分别设置



//    @RequestMapping("/add/upload")
//    public void config(HttpServletRequest request,  HttpServletResponse response) {
//        // response.setContentType("application/json");
//        String rootPath = request.getSession().getServletContext().getRealPath("/");
//        response.setHeader("Content-Type" , "text/html");
//        try {
//            PrintWriter writer = response.getWriter();
//            writer.write("oh");
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    @RequestMapping("/admin/add/add_ads")
    public String ok(HttpServletResponse response) {
//        response.setContentType("text/html;charset=utf-8");
       return "add_ads";
    }

    @ResponseBody
    @RequestMapping(value="/admin/add/add_ad", method= RequestMethod.POST)
    public String add_ok(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value="content",defaultValue = "")String content) {

        log.info("========"+content);
        return "添加完成了....";
    }

}
