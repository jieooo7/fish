package com.fish.model.temple;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "user")  
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="tel")
	private String  tel;
	@Column(name="name")
	private String  name;
	@Column(name="sex")
	private String  sex;
	@Column(name="email")
	private String  email;
	@Column(name="nick_name")
	private String  nick_name;
	@Column(name="passwd")
	private String  passwd;
	@Column(name="card_id")
	private String  card_id;
	@Column(name="level")
	private String  level;
	@Column(name="head_url")
	private String  head_url;
	@Column(name="score")
	private int score;
	@Column(name="experence")
	private int experence;
	@Column(name="birth")
	private Date birth;
	@Column(name="register_time")
	private Date register_time;
	
	
	
	
	public User(int id, String tel, String name, String sex, String email, String nick_name, String passwd,
				String card_id, String level, String head_url, int score, int experence, Date birth, Date register_time) {
		super();
		this.id = id;
		this.tel = tel;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.nick_name = nick_name;
		this.passwd = passwd;
		this.card_id = card_id;
		this.level = level;
		this.head_url = head_url;
		this.score = score;
		this.experence = experence;
		this.birth = birth;
		this.register_time = register_time;
	}
	
	
	public User() {
		super();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getHead_url() {
		return head_url;
	}
	public void setHead_url(String head_url) {
		this.head_url = head_url;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getExperence() {
		return experence;
	}
	public void setExperence(int experence) {
		this.experence = experence;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", tel=" + tel + ", name=" + name + ", sex=" + sex + ", email=" + email
				+ ", nick_name=" + nick_name + ", passwd=" + passwd + ", card_id=" + card_id + ", level=" + level
				+ ", head_url=" + head_url + ", score=" + score + ", experence=" + experence + ", birth=" + birth
				+ ", register_time=" + register_time + "]";
	}
	
	
	
	

	
}
