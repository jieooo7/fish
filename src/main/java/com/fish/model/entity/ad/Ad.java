package com.fish.model.entity.ad;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thy on 16-11-3.
 */
@Entity
@Table( name = "ad")
@DynamicInsert
@DynamicUpdate
public class Ad {


    private int id;

    private String title;
    private String content;

    private BigDecimal money;

    private Timestamp publish_time;
    private Timestamp start_time;
    private Timestamp end_time;
    private int video_num;
    private int image_num;
    private int comment_num;


    public Ad() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(columnDefinition="Decimal(16,2) default '0.00'",nullable=false)
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable=false)
    public Timestamp getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Timestamp publish_time) {
        this.publish_time = publish_time;
    }
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable=false)
    public Timestamp getStart_time() {
        return start_time;
    }

    /**
     * 注解的数据类型为ddl对应的类型  Timestamp
     * @param start_time
     */
    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable=false)
    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }
    @Column(columnDefinition="int DEFAULT 1",nullable=false)
    public int getVideo_num() {
        return video_num;
    }

    public void setVideo_num(int video_num) {
        this.video_num = video_num;
    }
    @Column(columnDefinition="int DEFAULT 1",nullable=false)
    public int getImage_num() {
        return image_num;
    }

    public void setImage_num(int image_num) {
        this.image_num = image_num;
    }
    @Column(columnDefinition="int DEFAULT 1",nullable=false)
    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

//    columnDefinition="varchar(128) not null"
}
