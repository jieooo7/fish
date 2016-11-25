package com.fish.model.response.model;

/**
 * Created by thy on 16-11-25.
 */

public class BankCardModel {

    private String card_no;

    private String bank_name;

    public BankCardModel() {
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
