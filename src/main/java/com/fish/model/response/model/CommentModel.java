package com.fish.model.response.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by thy on 16-11-25.
 */

public class CommentModel {


    private String id;
    private String name;
    private String pic_url;
    private String content;
    private String time;
//    @JsonIgnore
    private String like_no;
    private String pic;
    private List<CommentModel> reply;


    public CommentModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike_no() {
        return like_no;
    }

    public void setLike_no(String like_no) {
        this.like_no = like_no;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<CommentModel> getReply() {
        return reply;
    }

    public void setReply(List<CommentModel> reply) {
        this.reply = reply;
    }
}
