package com.fish.model.temple;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thy on 16-9-18 下午2:32.
 * E-mail : jieooo7@163.com
 */
@Entity
@Table( name = "dev_info")
public class Devloper {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="sex")
    private byte sex;

    @Column(name="skill")
    private String skill;

    @Column(name="qq")
    private String qq;

    @Column(name="tel")
    private String tel;

    @Column(name="weixin")
    private String weixin;

    @Column(name="company")
    private String company;

    @Column(name="job")
    private String job;

    @Column(name="other")
    private String other;

    @Column(name="weibo")
    private String weibo;

    @Column(name="city")
    private String city;

    @Column(name="detail")
    private String detail;

    @Column(name="email")
    private String email;

    @Column(name="experice")
    private int experice;


    public Devloper() {
    }

    public Devloper(Long id, String name, byte sex, String skill, String qq, String tel, String weixin, String company, String job, String other, String weibo, String city, String detail) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.skill = skill;
        this.qq = qq;
        this.tel = tel;
        this.weixin = weixin;
        this.company = company;
        this.job = job;
        this.other = other;
        this.weibo = weibo;
        this.city = city;
        this.detail = detail;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperice() {
        return experice;
    }

    public void setExperice(int experice) {
        this.experice = experice;
    }
}
