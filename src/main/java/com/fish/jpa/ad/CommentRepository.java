package com.fish.jpa.ad;

import com.fish.model.entity.ad.Ad;
import com.fish.model.entity.ad.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by thy on 16-11-28.
 */

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    Page<Comment> findByAdId(int ad_id,Pageable pageable);

    Comment findFirstByOrderByIdDesc();

}

//    User findFirstByOrderByLastnameAsc();
//
//    User findTopByOrderByAgeDesc();
//
//    Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);
//
//    Slice<User> findTop3ByLastname(String lastname, Pageable pageable);
//
//    List<User> findFirst10ByLastname(String lastname, Sort sort);
//
//    List<User> findTop10ByLastname(String lastname, Pageable pageable);