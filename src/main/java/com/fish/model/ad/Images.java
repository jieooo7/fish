package com.fish.model.ad;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
 * Created by thy on 16-11-3.
 */
@Entity
@Table( name = "images")
@DynamicInsert
@DynamicUpdate
public class Images {


    private int id;

    private String url;

    private Timestamp time;

    private Ad ad_id;

    public Images() {
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable=false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    @ManyToOne
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    public Ad getAd_id() {
        return ad_id;
    }

    public void setAd_id(Ad ad_id) {
        this.ad_id = ad_id;
    }
}
