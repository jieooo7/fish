package com.fish.jpa.ad;

import com.fish.model.entity.detail.AssetsDetail;
import com.fish.model.entity.puzzle.PuzzleUser;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-12-1.
 */

public interface AssetsRepository extends CrudRepository<AssetsDetail, Long> {

    List<AssetsDetail> findByUserUserInfo(int uid);

}
