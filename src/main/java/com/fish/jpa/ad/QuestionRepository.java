package com.fish.jpa.ad;

import com.fish.model.entity.ad.AdNotice;
import com.fish.model.entity.question.Question;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by thy on 16-11-30.
 */

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByAdId(int id);
}
