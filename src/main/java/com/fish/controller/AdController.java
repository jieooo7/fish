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
    public String greeting() {

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