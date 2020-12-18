package com.nan.day33_retrofit.simple1.bean;

public class BaseResult {
    String code;
    String msg;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public boolean isOk() {
        return "0000".equals(code);
    }
}