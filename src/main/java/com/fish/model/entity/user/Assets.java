package com.fish.model.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thy on 16-11-22.
 */
@Entity
@Table(name = "assets")
public class Assets {


    private int user_id;
    private int money=0;
    private int card_num=0;
    private int bank_num=0;

    public Assets() {
    }
    @Id
    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCard_num() {
        return card_num;
    }

    public void setCard_num(int card_num) {
        this.card_num = card_num;
    }

    public int getBank_num() {
        return bank_num;
    }

    public void setBank_num(int bank_num) {
        this.bank_num = bank_num;
    }
}
