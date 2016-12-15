package com.fish.jpa.user;

import com.fish.model.entity.user.CardBank;
import com.fish.model.entity.user.UserInfo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-12-6.
 */

public interface BankCardRepository extends CrudRepository<CardBank, Long> {

    List<CardBank> findByUserId(int uid);
}
