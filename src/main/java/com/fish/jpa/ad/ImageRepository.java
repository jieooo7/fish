package com.fish.jpa.ad;

import com.fish.model.ad.Ad;
import com.fish.model.ad.Images;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-11-3.
 */

public interface ImageRepository extends CrudRepository<Images, Long> {

    List<Images> findById(int id);
}
