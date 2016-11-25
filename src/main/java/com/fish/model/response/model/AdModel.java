package com.fish.model.response.model;

/**
 * Created by thy on 16-11-25.
 */

public class AdModel {


    private String adId;
    private String describe;
    private String time;
    private String total_money;
    private String top_money;
    private String mark;
    private String type;
    private String author_name;
    private String author_pic;
    private String pic_url;
    private String hot;
    private String comment_no;
    private String finish_percent;
    private String puzzle_no;

    private boolean isCollect;
    private boolean isNotice;

    public AdModel() {
    }

    public String getPuzzle_no() {
        return puzzle_no;
    }

    public void setPuzzle_no(String puzzle_no) {
        this.puzzle_no = puzzle_no;
    }

    public String getFinish_percent() {
        return finish_percent;
    }

    public void setFinish_percent(String finish_percent) {
        this.finish_percent = finish_percent;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean isNotice() {
        return isNotice;
    }

    public void setNotice(boolean notice) {
        isNotice = notice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getTop_money() {
        return top_money;
    }

    public void setTop_money(String top_money) {
        this.top_money = top_money;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_pic() {
        return author_pic;
    }

    public void setAuthor_pic(String author_pic) {
        this.author_pic = author_pic;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getComment_no() {
        return comment_no;
    }

    public void setComment_no(String comment_no) {
        this.comment_no = comment_no;
    }
}
