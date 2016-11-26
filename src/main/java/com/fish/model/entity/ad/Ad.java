package com.fish.model.entity.ad;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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

    private String title="";
    private String content="";

    private BigDecimal money=new BigDecimal(0.00);

    private Timestamp publish_time;
    private Timestamp start_time;
    private Timestamp end_time;
    private int video_num=0;
    private int image_num=0;
    private int comment_num=0;
    private List<Images> Images;
    private List<Videos> Videos;

//    @Transient 用于忽略某个属性，而不对该属性进行持久化操作

    private int read_num=0;
    private int bonus=0;
    private int top_bonus=0;
    private int top_bonus_people=0;
    private int cycle_time=0;
    private byte catalog=0;

    private String keyword="";


    public int getRead_num() {
        return read_num;
    }

    public void setRead_num(int read_num) {
        this.read_num = read_num;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getTop_bonus() {
        return top_bonus;
    }

    public void setTop_bonus(int top_bonus) {
        this.top_bonus = top_bonus;
    }

    public int getTop_bonus_people() {
        return top_bonus_people;
    }

    public void setTop_bonus_people(int top_bonus_people) {
        this.top_bonus_people = top_bonus_people;
    }

    public int getCycle_time() {
        return cycle_time;
    }

    public void setCycle_time(int cycle_time) {
        this.cycle_time = cycle_time;
    }

    public byte getCatalog() {
        return catalog;
    }

    public void setCatalog(byte catalog) {
        this.catalog = catalog;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

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
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public Timestamp getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Timestamp publish_time) {
        this.publish_time = publish_time;
    }
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
    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }
    public int getVideo_num() {
        return video_num;
    }

    public void setVideo_num(int video_num) {
        this.video_num = video_num;
    }
    public int getImage_num() {
        return image_num;
    }

    public void setImage_num(int image_num) {
        this.image_num = image_num;
    }
    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }


    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="ad_id")
//    @OrderBy(value= "id ASC")
    public List<Images> getImages() {
        return Images;
    }

    public void setImages(List<Images> images) {
        Images = images;
    }

//    有了mappedby不能也不该在此再定义@joincolumn）
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ad_id")
//    @OrderBy(value= "id ASC")
    public List<Videos> getVideos() {
        return Videos;
    }

    public void setVideos(List<Videos> videos) {
        Videos = videos;
    }


    //    columnDefinition="varchar(128) not null"
}

//@OneToMany(mapped=“由One的一方指向Many的一方，并且，这个属性应该等于Many的一方中含有One类的属性的属性名，否则会出错啦 ”)
//如：
//        在ClassPO中：
//
//@OneToMany(targetEntity=StudentPO.class,mappedBy="myclass",cascade=javax.persistence.CascadeType.ALL)
//@Cascade(CascadeType.ALL)
//private Set<StudentPO>studentSet;
//
//        在StudentPOst中：
//
//@ManyToOne(targetEntity=ClassPO.class,cascade={CascadeType.ALL})
//private ClassPO myclass;
//
//        其中ClassPO中的mappedBy=  StudentPOst中的 myclass;
//
//        2、如果我们没有填mapped这个属性，则会产生三张表，一张表是用来管理这两张表的