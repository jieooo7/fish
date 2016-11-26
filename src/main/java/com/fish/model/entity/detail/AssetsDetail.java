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
    private UserInfo userInfo;
    private Ad ad;
    private int can_get_money=0;
    private Timestamp the_time;


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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    @ManyToOne
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
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
}
