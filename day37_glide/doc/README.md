# Glide

```java
Glide.with(this).load("").into(imageView);
```

1. Glide.with()

会去 创建一个 Fragment 绑定到 Activity 上面，Fragment 又会调用 setRequestManager() 方法
为什么要创建一个Fragment绑定到Activity上面？因为图片加载是比较耗时的，如果图片正在加装中，但是我们按了返回键，那这个时候就应该停止加载图片
Activity的生命周期会影响Fragment的生命周期，从而控制RequestManager的onStart()、onStop()、onDestroy()

自己写SDK、控件，需要生命周期的时候，也可以参考这种思路

2. RequestManager.load()

创建RequestBuilder，并且设置model

3. RequestBuilder.into()

处理ScaleType、构建Request（里面还会处理缩略图，最终通过享元设计模式从共享池里面取Request对象）、判断是否重复、最后交给TargetTracker去执行Request的begin()

Request的begin()
    判断状态
    获取图片宽高onSizeReady
    Engine.load()
        通过签名、宽高等信息buildKey（同一个url但是不同宽高也会认为不一样）
        根据key尝试从缓存里面取（内存缓存、磁盘缓存）
        取不到的情况下，构建加载和解码的Job，扔给线程池执行
        run()->runWrapped()->runGenerators()->SourceGenerator.startNext()->HttpUrlFetcher.loadData()->HttpUrlFetcher.loadDataWithRedirects()->SourceGenerator.onDataReady()
        DecodeJob.onDataFetcherReady()->DecodeJob.decodeFromRetrievedData()->decodeFromData()->decodeFromFetcher()->runLoadPath()->LoadPath.load()->loadWithExceptionList()->DecodePath.decode()->decodeResource()->decodeResourceWithList()->
        StreamBitmapDecoder.decode()->Downsampler.decode()->decodeFromWrappedStreams->一路返回DecodeJob.notifyEncodeAndRelease()->notifyComplete()-EngineJob.onResourceReady()->handleResultOnMainThread()->SingleRequest.onResourceReady()->ImageViewTarget.onResourceReady()->
        setResourceInternal()->setResource()
    
普通图片的解码：通过BitmapFactory.decodeStream()解析网络IO流，但是不能直接解析，需要看实际需要展示的尺寸做缩放、选择等操作
GIF支持：
    如何判断是否为gif：相关源码StreamGifDecoder、ByteBufferGifDecoder，GIF decoder should verify that the image contains the GIF header block，也就是需要看Stream里面是否有gif这个块信息
    gif如何解码：解析的时候，将stream转换成byte数组，然后只解码，创建GifDrawable（自定义Drawable），实现了Animatable，在start的时候通过frameLoader不断去拿下一帧的Bitmap（像素数据），然后绘制
缓存：内存缓存、磁盘缓存

# 看第三方源码的流程

1. 最简单的使用走时序图流程 (耐心，2-3天) 皮毛都没有，第一步
2. 会画类图的结构（汲取一些经验）
3. 抓单个细节（自己写图片加载框架会用到哪些知识？解析图 InputStream，怎么样去加载gif，怎么样缓存数据的，怎么获取控件宽高的，怎么样处理centerCorp 等等）
4. 如果自己看不太懂的情况下可以跟着别人的博客走 http://blog.csdn.net/guolin_blog/article/details/53759439
5. 尝试模仿写一些关键代码

# 博客

https://www.jianshu.com/p/223dc6205da2
http://blog.csdn.net/guolin_blog/article/details/53759439