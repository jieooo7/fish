package com.fish.jpa.ad;

import com.fish.model.entity.ad.Videos;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-11-3.
 */

public interface VideoRepository extends CrudRepository<Videos, Long> {

    List<Videos> findById(int id);
}