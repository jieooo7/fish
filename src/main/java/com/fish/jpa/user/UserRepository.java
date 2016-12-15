package com.fish.jpa.user;

import com.fish.model.entity.user.UserInfo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by thy on 16-11-18.
 */

public interface UserRepository extends CrudRepository<UserInfo, Long> {


    UserInfo findById(int id);
    UserInfo findByTel(String tel);
    UserInfo findByEmail(String email);
    UserInfo findByName(String name);


    @Modifying
    @Query("update UserInfo u set u.passwd = ?2 where u.id =?1")
    public int changePass(int uid,String pass);
}
