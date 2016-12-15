package com.fish.jpa.ad;

import com.fish.model.entity.puzzle.PuzzleCard;
import com.fish.model.entity.puzzle.PuzzleUser;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-12-1.
 */

public interface PuzzleRepository extends CrudRepository<PuzzleCard, Long> {

    List<PuzzleCard> findByAdIdOrderBySeqAsc(int ad_id);
    List<PuzzleCard> findByAdIdOrderByLavaDesc(int ad_id);
    PuzzleCard findById(int id);

    @Modifying
    @Query("update PuzzleCard u set u.lava = ?2 where u.adId =?1")
    int changeLava(int ad_id,int lava);



}
