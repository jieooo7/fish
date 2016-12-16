package com.fish.view;

import com.fish.controller.AdController;
import com.fish.jpa.ad.AdRepository;

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



    @RequestMapping("/add/upload")
    public void config(HttpServletRequest request,  HttpServletResponse response) {
        // response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        response.setHeader("Content-Type" , "text/html");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("oh");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/add/add_ad")
    public String ok() {
       return "add_ad";
    }

    @ResponseBody
    @RequestMapping(value="/add/add_ad", method= RequestMethod.POST)
    public String add_ok(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value="content",defaultValue = "")String content) {

        log.info("========"+content);
        return "添加完成了";
    }

}
