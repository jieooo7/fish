package com.fish.model.entity.ad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fish.model.response.model.CommentModel;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by thy on 16-11-22.
 */
@Entity
@Table( name = "comment")
public class Comment {

    private int id;
    private int adId=-1;
    private int parentId=-1;
    private int like_num=0;
    private int author_id=-1;
    @JsonIgnore
    private int sequence=0;
    @JsonIgnore
    private String url="";
    private String content="";
    private String authorName="";
    private String parentName="";
    private String headUrl="";
    private Timestamp time;


    public Comment() {
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
    @Column(name="ad_id")
    public int getAdId() {
        return adId;
    }
    @Column(name="parent_name")
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }
    @Column(name="parent_id")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    @Column(name="author_name")
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    @Column(name="head_url")
    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
