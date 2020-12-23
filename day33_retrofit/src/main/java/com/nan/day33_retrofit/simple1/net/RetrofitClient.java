package com.nan.day33_retrofit.simple1.net;

import android.util.Log;

import com.google.gson.Gson;
import com.nan.day33_retrofit.simple1.bean.Result;
import com.nan.day33_retrofit.simple1.net.errorhandle.ErrorHandle;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final ServiceApi SERVICE_API;

    static {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("TAG", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        // 各种套路和招式 ，发现问题解决问题，基础，源码的理解
        // 1. 没打印？
        // 2. 数据格式不一致？成功 data 是个对象，不成功 data 是个 String
        // 3. 还有就是 baseUrl 问题？ (Retrofit 找不到任何入口可以修改)
        //        3.1 不同的 baseUrl 构建不同的 Retrofit 对象 （直不应该首选）
        //        3.2 自己想办法，取巧也行走漏洞
        Retrofit retrofit = new Retrofit.Builder()
                // 访问后台接口的主路径
                .baseUrl("http://172.16.47.80:8080/TestServer/")
                // 添加解析转换工厂,Gson 解析，Xml解析，等等
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 添加 OkHttpClient,不添加默认就是 光杆 OkHttpClient
                .client(okHttpClient)
                .build();

        // 创建一个 实例对象
        SERVICE_API = retrofit.create(ServiceApi.class);
    }

    public static ServiceApi getServiceApi() {
        return SERVICE_API;
    }

    public static <T> ObservableTransformer<Result<T>, T> getRetryTransformer() {
        return new ObservableTransformer<Result<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<Result<T>> upstream) {
                return upstream
                        .map(new Function<Result<T>, T>() {
                            @Override
                            public T apply(Result<T> result) throws Exception {
                                if (result.isOk()) {
                                    // 解析,获取类上面的泛型
                                    Class<T> dataClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                                    Gson gson = new Gson();
                                    T data = gson.fromJson(result.data.toString(), dataClass);
                                    return data;
                                } else {
                                    throw new ErrorHandle.ServiceError(result.getCode(), result.getMsg());
                                }
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
