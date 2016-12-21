package com.fish.view;

import com.fish.controller.AdController;
import com.fish.jpa.ad.AdRepository;
import com.fish.jpa.user.AdminRespository;
import com.fish.jpa.user.UserRepository;
import com.fish.model.entity.user.AdminInfo;
import com.fish.securety.MD5;
import com.fish.storage.StorageProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private AdminRespository adminRespository;


    @Autowired
    private StorageProperties properties;//用于设置存储文件目录,用两个store service 分别设置


    @RequestMapping("/admin/add/add_ads")
//    @PreAuthorize(“authenticated and hasPermission(‘hello’, ‘view’)”)表示只有当前已登录的并且拥有(“hello”, “view”)权限的用户才能访问此页面
    public String ok() {
        return "add_ads";
    }


    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "admin_login";
    }


    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public String fornmLogin(@RequestParam(value = "username", defaultValue = "") String email,
                             @RequestParam(value = "password", defaultValue = "") String passwd,
                             RedirectAttributes redirectAttributes) {//123456
        AdminInfo admin=adminRespository.findByEmail(email);

        if(admin != null && admin.getPasswd().equals(MD5.getMD5(passwd + admin.getCode()))){
            return "redirect:/admin/add/add_ads";
        }else{
//            Flash attributes 为一个请求存储意图为另外一个请求所使用的属性提供了一条途径.
// Flash attributes 在对请求的重定向生效之前被临时存储（通常是在session)中，并且在重定向之后被立即移除.
            redirectAttributes.addFlashAttribute("message",
                    "邮箱或者密码错误");
            return "redirect:/admin/login";
        }



    }

    @ResponseBody
    @RequestMapping(value = "/admin/add/add_ad", method = RequestMethod.POST)
    public String add_ok(@RequestParam(value = "content", defaultValue = "") String content) {

        log.info("========" + content);
        return "添加完成了....";
    }

}
