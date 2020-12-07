# day31

### 网络编程基础1

1.TCP/IP协议家族
《图解Http》《Http权威指南》《Java网络编程基础》
HTTP，FTP，DNS，TCP，UDP，IP等等
OSI七层协议：应用层，表示层，会话层，传输层，网络层，数据链路层，物理层
TCP三次握手（建立连接）和四次挥手（断开连接）
你能听到吗？
我能听到，你能听到吗？
我能听到，bilibili

我想挂了
好，我还有一句话没说完
挂了
挂了

2.Http请求：他们之间通信是以报文的形式
客户端请求（Request）：请求报文 = 请求头 + 空行 + 请求数据
Accept: 客户端可以处理的数据格式
Cookie：服务端的之前给我们的Cookie

服务端响应（Response）：响应报文 = 响应头 + 空行 + 响应数据
Status Code 响应状态码
Content-Type: 响应返回的类型 （文本，utf-8）图片等等
Set-Cookie：服务端给我们的Cookie，要保存起来，下次带过去

搭建服务器：
1.下载 Eclipse + Tomact 
2. Eclipse 配置 Tomcat
3. 新建一个 JavaWeb 工程
4.将新建的 JavaWeb 工程发布到 Tomcat
5.用浏览器测试项目工程
6.写个Servlet接口测试一下

3.Cookie 、Session、Token
3.1 都是用来做持久化处理的，目的就是让客户端和服务端相互认识。Http请求默认是不持久的没有状态的，谁也不认识谁
3.2 Cookie: 是存放在客户端的信息，这个信息是来自于服务器返回的信息，
下次请求带过去，如果用户离开网站后，如果Cookie已过期一般是会被清除的。如果Cookie没过期下次访问网站还是会带过去。（相对危险）
3.3 Session: 是存放在服务器上面的客户端临时信息，用户离开网站是会被清除的。（相对安全，耗资源）

3.4 Token（App）"令牌"：用户身份的验证，有点类似于 Cookie ，相对来说更安全，一般流程：
3.4.1 客户端像服务端申请 Token
3.4.2 服务端收到请求，会去验证用户信息,签发一个 Token 给客户端，自己保存 Token
3.4.3 客户端收到 Token 会保存起来，每次请求带上 Token 
3.4.4 服务器收到其他请求，会去验证客户端的 Token , 如果成功返回数据，不成功啥都不给

4. Http缓存
Cache-Control（缓存策略）：Public、private、no-cache、max-age 、no-store（不缓存）
Expires（缓存的过期策略）：指名了缓存数据有效的绝对时间，告诉客户端到了这个时间点（比照客户端时间点）后本地缓存就作废了，
在这个时间点内客户端可以认为缓存数据有效，可直接从缓存中加载展示。
https://mp.weixin.qq.com/s/qOMO0LIdA47j3RjhbCWUEQ

如果有缓存并且过期了那么发起请求，那么服务端会给我们数据？（不一定会给）服务器的数据没有变动就不会给，状态码会变为 304 ，自己拿之前过期的缓存

注意：
1.OkHttp帮我们处理了缓存，还处理了很多很多东西
2.一般服务端不会考虑这一块，都会返回200

### 网络编程基础2

1. HTTP状态码：

1xx:  Informational (信息状态码) ，接收的请求正在处理
2xx:  Success(成功)，请求正常处理完毕,如 200
3xx:  Redirection(重定向)，需要进行附加操作，一般是没有响应数据返回的，如 304（Not,modified）307 
4xx: Client Error (客户端的错误)，服务器无法处理请求，如 404
5xx: Server Error (服务端的错误)，服务器处理请求出错，如 500

2.Http 和 Https 的区别：
Https = Http + 加密 + 验证 + 完整

端口：Http (80)  Https (443)

Http 的缺点：
2.1 数据是没有加密传输，可能遭遇窃听
2.2 不验证通信方的身份，可能会遭遇伪装
2.3 无法验证报文的完整性，可能会遭遇篡改

TLS/SSL 协议：
加密：对称加密（AES，DES） + 非对称加密 (RSA，DSA)
证书：要钱（便宜），建立连接的速度会拖慢，TCP  3 次握手，8 次握手

MD5是编码

3. Http 1.x 和 Http 2.0 的区别
3.1 Http 2.0 采用二进制格式而非文本格式
3.2 Http 2.0 支持完全的多路复用
3.3 Http 2.0 使用报头压缩，降低开销
3.4 Http 2.0 让服务器将响应主动推送给客户端，（带内容推送，不带内容推送的通知）

4. 异步和同步
跟线程没什么关系，打电话
同步：打电话 -> 处理（没挂断） -> 反馈
异步：打电话 -> 处理（挂断）-> 打回来

5. 整体架构和源码分析

5.1 自己如果要写一个框架你要怎么处理

1. 网络是耗时，开线程，new Thread() ?  线程池
2. 处理网络，HttpUrlConnection(简单) 或者  输入流+Socket(麻烦)
3. 网络请求头信息处理，缓存的处理，文件格式上次的方式（表单提交，拼格式）
4. 路由的一些操作，Http 2.0 复用 等等

5.2 OkHttp 大致内容 Okio，Socket

okio：原生的JavaIO + 自定义封装 ，其实就是对于 io 的封装 
Socket 连接 
拦截器

5.3 走一下大致流程

原版博客：官方介绍文档 （基础）+ 项目实践验证 + 自己阅读源码 
根据如何使用的主线来阅读源码

主线（重点是第三步）：

```java
OkHttpClient client = new OkHttpClient();

// 1. 构建一个请求，url、端口、请求头等一些参数
// 内部添加处理了很多参数，例如表单提交，内部已经帮我们添加了content-type、content-length等
Request request = new Request.Builder()
        .url("https://www.baidu.com")
        .get()
        .build();

// 2. 把Request封装转成一个RealCall
Call call = client.newCall(request);

// 3. 加入到线程池里面执行（重点分析）
call.enqueue(new Callback() {
    @Override
    public void onFailure(Call call, IOException e) {
        Log.e(TAG, "请求失败");
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        ResponseBody body = response.body();
        if (null == body) {
            return;
        }
        Log.e(TAG, String.format("返回状态码：%d", response.code()));
        Log.e(TAG, String.format("返回结果：%s", body.string()));
    }
});
```

RealCall 里面的 enqueue
AsyncCall 是 RealCall 的内部类，给了 OKHttp 的 Dispatcher：

```java
  synchronized void enqueue(AsyncCall call) {
    // 判断当前正在执行的任务数量，最大是 64 ，正在执行的任务中的 host , 最大是 5
    if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost) {
      // 加入到正在执行
      runningAsyncCalls.add(call);
      // 扔到线程池，最终去了AsyncCall.execute()方法
      executorService().execute(call);
    } else {
      // 加入准备执行的集合，等待执行
      readyAsyncCalls.add(call);
    }
  }
```

需要重点分析getResponseWithInterceptorChain()方法，返回Response：
责任链设计模式：重试、重定向等等

```java
    @Override protected void execute() {
      boolean signalledCallback = false;
      try {
        // 这里面才是重点
        Response response = getResponseWithInterceptorChain();
        if (retryAndFollowUpInterceptor.isCanceled()) {
          signalledCallback = true;
          responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
        } else {
          signalledCallback = true;
          responseCallback.onResponse(RealCall.this, response);
        }
      } catch (IOException e) {
        if (signalledCallback) {
          // Do not signal the callback twice!
          Platform.get().log(INFO, "Callback failure for " + toLoggableString(), e);
        } else {
          eventListener.callFailed(RealCall.this, e);
          responseCallback.onFailure(RealCall.this, e);
        }
      } finally {
        client.dispatcher().finished(this);
      }
    }
  }
```

1. 多站在不同角度思考问题，想办法去赚钱
2. 多接触一些人