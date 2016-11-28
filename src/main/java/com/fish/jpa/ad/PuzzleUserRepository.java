package com.fish.jpa.ad;

import com.fish.model.entity.ad.AdNotice;
import com.fish.model.entity.puzzle.PuzzleUser;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by thy on 16-11-27.
 */

public interface PuzzleUserRepository extends CrudRepository<PuzzleUser, Long> {

    long  countByUserIdAndCardId(int user_id,int card_id);

}
