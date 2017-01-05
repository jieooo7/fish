package com.fish.model.entity.puzzle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

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
@Table( name = "card_category")
public class PuzzleCard {


    private int id;
    private int adId;
    private int lava;//剩余多少张
    @JsonIgnore
    private int number;//剩余多少张
    private int valuetation;//单张价值
    private byte seq;//序号
    private String name="";//广告名字
    private String card_desc="";//拼图的描述
    private String card_name="";//比如a1
    private String pic_url="";//大图的地址
    private String pic_total="";//总共数量

    private Timestamp create_time;

    public PuzzleCard() {


    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public int getValuetation() {
        return valuetation;
    }

    public void setValuetation(int valuetation) {
        this.valuetation = valuetation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public byte getSeq() {
        return seq;
    }

    public void setSeq(byte seq) {
        this.seq = seq;
    }

    public int getLava() {
        return lava;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public void setLava(int lava) {
        this.lava = lava;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "ad_id")
    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_desc() {
        return card_desc;
    }

    public void setCard_desc(String card_desc) {
        this.card_desc = card_desc;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPic_total() {
        return pic_total;
    }

    public void setPic_total(String pic_total) {
        this.pic_total = pic_total;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}

