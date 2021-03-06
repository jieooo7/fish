package com.fish.jpa.ad;

import com.fish.model.ad.Ad;
import com.fish.model.temple.Customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-11-3.
 */

public interface AdRepository extends CrudRepository<Ad, Long> {

    List<Ad> findById(String lastName);
}