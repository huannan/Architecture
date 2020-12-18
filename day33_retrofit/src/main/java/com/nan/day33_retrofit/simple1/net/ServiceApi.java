package com.nan.day33_retrofit.simple1.net;

import com.nan.day33_retrofit.simple1.bean.Result;
import com.nan.day33_retrofit.simple1.bean.UserInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("login")
    Call<Result<UserInfo>> login(@Query("name") String userName, @Query("pwd") String userPwd);

}
