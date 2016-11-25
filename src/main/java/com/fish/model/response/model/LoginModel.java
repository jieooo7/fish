package com.fish.model.response.model;

/**
 * Created by thy on 16-11-25.
 */

public class LoginModel {
    private int uid;
    private String name;
    private String sex;
    private String auth_key;

    private String nick_name;

    private String money;

    private String puzzle_num;

    private String bankCard_num;
    private String head_url;


    public LoginModel() {
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPuzzle_num() {
        return puzzle_num;
    }

    public void setPuzzle_num(String puzzle_num) {
        this.puzzle_num = puzzle_num;
    }

    public String getBankCard_num() {
        return bankCard_num;
    }

    public void setBankCard_num(String bankCard_num) {
        this.bankCard_num = bankCard_num;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }
}
