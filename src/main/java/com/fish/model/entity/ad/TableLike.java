package com.fish.model.entity.ad;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thy on 16-11-22.
 */
@Entity
@Table( name = "t_like")
public class TableLike {

    private int id;
    private int who_from;
    private int who_to;
    private Timestamp create_time;

    public TableLike() {
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

    public int getWho_from() {
        return who_from;
    }

    public void setWho_from(int who_from) {
        this.who_from = who_from;
    }

    public int getWho_to() {
        return who_to;
    }

    public void setWho_to(int who_to) {
        this.who_to = who_to;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
