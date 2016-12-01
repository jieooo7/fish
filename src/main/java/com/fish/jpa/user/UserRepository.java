package com.fish.jpa.user;

import com.fish.model.entity.user.UserInfo;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by thy on 16-11-18.
 */

public interface UserRepository extends CrudRepository<UserInfo, Long> {


    UserInfo findById(int id);
    UserInfo findByTel(String tel);
}
