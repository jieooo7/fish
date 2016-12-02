package com.fish.model.response.model;

import com.fish.model.entity.detail.AssetsDetail;

import java.util.List;

/**
 * Created by thy on 16-11-25.
 */

public class AssetsModel {

    private int total_money;


    private List<AssetsDetail> AssetsDetails;


    public int getTotal_money() {
        return total_money;
    }

    public void setTotal_money(int total_money) {
        this.total_money = total_money;
    }



    public List<AssetsDetail> getAssetsDetails() {
        return AssetsDetails;
    }

    public void setAssetsDetails(List<AssetsDetail> assetsDetails) {
        AssetsDetails = assetsDetails;
    }


}
