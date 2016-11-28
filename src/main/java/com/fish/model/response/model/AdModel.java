package com.fish.model.response.model;

/**
 * Created by thy on 16-11-25.
 */

public class AdModel {


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

}
