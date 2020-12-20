package com.nan.day33_retrofit.retrofit.test;

import com.nan.day33_retrofit.retrofit.Call;
import com.nan.day33_retrofit.retrofit.http.GET;
import com.nan.day33_retrofit.retrofit.http.Query;
import com.nan.day33_retrofit.simple1.bean.Result;
import com.nan.day33_retrofit.simple1.bean.UserInfo;

public interface ServiceApi {

    @GET("login")
    Call<Result<UserInfo>> login(@Query("name") String userName, @Query("pwd") String userPwd);

}
