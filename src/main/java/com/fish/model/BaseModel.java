package com.fish.model;

/**
 * Created by thy on 16-11-7.
 */

public class BaseModel<T> {

    private int errorCode=0;

    private String msg="";

    private T data;


    public BaseModel(int errorCode, String msg, T data) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    public BaseModel() {
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
