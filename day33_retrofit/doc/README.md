# day33

### Retrofit

网络封装框架（解耦），是对 OkHttp 的封装。

### Retrofit常见问题

1. 打印
    HttpLoggingInterceptor
2. 数据格式不一致? 成功 data 是个对象，不成功 data 是个 String
    泛型+Object，封装一个HttpCallback，判断状态码，成功的时候才去解析
    
### 设计模式分析

https://www.jianshu.com/p/d96768b1d19f

具体看源码