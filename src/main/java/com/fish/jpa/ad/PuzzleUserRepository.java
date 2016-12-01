package com.fish.jpa.ad;

import com.fish.model.entity.ad.AdNotice;
import com.fish.model.entity.puzzle.PuzzleUser;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-11-27.
 */

public interface PuzzleUserRepository extends CrudRepository<PuzzleUser, Long> {

    int  countByUserIdAndAdId(int user_id,int ad_id);

    int  countByUserIdAndCardId(int user_id,int card_id);

    List<PuzzleUser> findByUserIdAndAdId(int user_id, int ad_id);

    List<PuzzleUser> findByUserId(int user_id);


    @Query("select DISTINCT u.adId from PuzzleUser u where u.userId = ?1")
    List<Integer> findPuzzle(int user_id);//找出用户的相关ad,然后在组装card



}
