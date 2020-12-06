package com.nan.okhttpserver.response;

import com.alibaba.fastjson.JSON;

public class UserInfoEntity {

    private String name;
    private String pwd;
    private String sex;

    public UserInfoEntity() {
    }

    public UserInfoEntity(String name, String pwd, String sex) {
        this.name = name;
        this.pwd = pwd;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
