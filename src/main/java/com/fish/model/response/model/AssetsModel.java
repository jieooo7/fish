package com.fish.model.response.model;

import java.util.List;

/**
 * Created by thy on 16-11-25.
 */

public class AssetsModel {

    private String total_money;

    private List<AssetScale> assetScales;

    private List<AssetsDetailModel> AssetsDetails;


    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public List<AssetScale> getAssetScales() {
        return assetScales;
    }

    public void setAssetScales(List<AssetScale> assetScales) {
        this.assetScales = assetScales;
    }

    public List<AssetsDetailModel> getAssetsDetails() {
        return AssetsDetails;
    }

    public void setAssetsDetails(List<AssetsDetailModel> assetsDetails) {
        AssetsDetails = assetsDetails;
    }

    public class AssetScale{
        private String name;

        private String money;


        public AssetScale() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

}
