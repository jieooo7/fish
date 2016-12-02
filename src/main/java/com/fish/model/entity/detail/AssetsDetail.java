package com.fish.model.entity.detail;

import com.fish.model.entity.ad.Ad;
import com.fish.model.entity.user.UserInfo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by thy on 16-11-22.
 */
@Entity
@Table(name = "assets_detail")
public class AssetsDetail {

    private int id;
    private int userInfo;
    private int ad;
    private int can_get_money=0;
    private Timestamp the_time;//周期
    private Timestamp start_time;//开始时间
    private Timestamp end_time;//结束时间
    private String name="";
    private String pic_url="";
    private int finish=0;//完成度
    private int valuation=0;//估值



    public AssetsDetail() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "user_id")
    public int getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(int userInfo) {
        this.userInfo = userInfo;
    }
    @Column(name = "ad_id")
    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }

    public int getCan_get_money() {
        return can_get_money;
    }

    public void setCan_get_money(int can_get_money) {
        this.can_get_money = can_get_money;
    }

    public Timestamp getThe_time() {
        return the_time;
    }

    public void setThe_time(Timestamp the_time) {
        this.the_time = the_time;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public int getValuation() {
        return valuation;
    }

    public void setValuation(int valuation) {
        this.valuation = valuation;
    }
}
