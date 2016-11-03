package com.fish.jpa;


import com.fish.model.temple.Devloper;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-9-18 下午2:31.
 * E-mail : jieooo7@163.com
 */
public interface DevloperRepository extends CrudRepository<Devloper, Long> {

    List<Devloper> findByName(String name);
    List<Devloper> findBySkill(String skill);
    List<Devloper> findByTel(String Tel);
    List<Devloper> findByCity(String city);

}
