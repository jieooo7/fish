package com.fish.jpa.ad;

import com.fish.model.entity.ad.AdNotice;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by thy on 16-11-27.
 */

public interface NoticeRepository extends CrudRepository<AdNotice, Long> {

    int countByUserIdAndAdId(int user_id,int ad_id);
}
