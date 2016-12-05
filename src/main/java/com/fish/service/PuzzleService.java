package com.fish.service;

import com.fish.jpa.ad.PuzzleRepository;
import com.fish.jpa.ad.PuzzleUserRepository;
import com.fish.model.entity.puzzle.PuzzleCard;
import com.fish.model.entity.puzzle.PuzzleUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * Created by thy on 16-12-5.
 */

@Component
public class PuzzleService {

    private final static Logger logger = LoggerFactory.getLogger(PuzzleService.class);

    @Autowired
    private PuzzleUserRepository puzzleRepository;


    @Autowired
    private PuzzleRepository cardRepository;

    @Autowired
    public PuzzleService() {

    }

    @Transactional
    public PuzzleCard getPuzzle(int uid, int ad_id) {

        List<PuzzleCard> cardList = cardRepository.findByAdIdOrderByLavaDesc(ad_id);
        if (cardList.size() > 2) {
//添加幸运数字 和概率
            Random random = new Random();
            int num = random.nextInt(cardList.size() - 1);
            if (random.nextInt(1000000) == 453657) {
                num = cardList.size() - 1;
            }
            PuzzleCard card = cardList.get(num);
            if (card.getLava() > 0) {
                cardRepository.changeLava(ad_id, card.getLava() - 1);

                PuzzleUser puzzleUser=new PuzzleUser();
                puzzleUser.setAdId(ad_id);
                puzzleUser.setCardId(card.getId());
                puzzleUser.setUserId(uid);
                puzzleUser.setCreate_time(new Timestamp(new java.util.Date().getTime()));
                puzzleRepository.save(puzzleUser);
                return card;
            }

            return null;
        }else{
            return null;
        }
    }



}
