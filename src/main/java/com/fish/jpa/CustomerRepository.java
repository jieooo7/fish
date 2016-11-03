package com.fish.jpa;


import com.fish.model.temple.Customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-9-5 下午4:04.
 * E-mail : jieooo7@163.com
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
