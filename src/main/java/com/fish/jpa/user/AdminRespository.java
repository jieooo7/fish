package com.fish.jpa.user;

import com.fish.model.entity.user.AdminInfo;
import com.fish.model.entity.user.UserInfo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by thy on 16-12-19.
 */

public interface AdminRespository  extends CrudRepository<AdminInfo, Long> {


    AdminInfo findById(int id);
    AdminInfo findByTel(String tel);
    AdminInfo findByEmail(String email);
    AdminInfo findByName(String name);


    @Modifying
    @Query("update AdminInfo u set u.passwd = ?2 where u.id =?1")
    public int changePass(int uid,String pass);
}