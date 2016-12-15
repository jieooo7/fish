package com.fish.controller;

import com.fish.jpa.user.BankCardRepository;
import com.fish.model.entity.ad.Ad;
import com.fish.model.entity.user.CardBank;
import com.fish.model.response.BaseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by thy on 16-12-6.
 */
@RestController
public class UserInfoControl {

    private static final Logger log = LoggerFactory.getLogger(UserInfoControl.class);

    private BankCardRepository bankRepository;

    @RequestMapping("/api/get_bankcard")
    public BaseModel<List<CardBank>> getBankCard(@RequestHeader(value = "uid", defaultValue = "1") String uid) {

        BaseModel<List<CardBank>> model = new BaseModel<List<CardBank>>();

        model.setData(bankRepository.findByUserId(Integer.parseInt(uid)));

        return model;

    }



}
