package com.fish.model.response.model;

import java.util.List;

/**
 * Created by thy on 16-11-25.
 */

public class PuzzleModel {

    private String title;
    private String name;
    private String pic;
    private String total;
    private String tips;
    private String time;
    private String total_money;
    private String top_money;

    private List<Puzzle> puzzles;


    public PuzzleModel() {
    }


    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
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

    public class Puzzle{
        private String name;
        private String no;

        public Puzzle() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }
    }
}
