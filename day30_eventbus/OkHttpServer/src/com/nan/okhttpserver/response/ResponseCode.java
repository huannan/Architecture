package com.nan.okhttpserver.response;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ResponseCode {
    String OK = "0000";
    String SERVER_ERROR = "0001";
    String LOGIN_ERROR = "0002";
    String COMMENT_ERROR = "0003";
}
