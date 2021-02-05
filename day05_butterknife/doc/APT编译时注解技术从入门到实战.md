### 概述

APT(Annotation Processing Tool)即注解处理器（通常也叫做编译时注解、编译时代码自动生成），是一种处理注解的工具，确切的说它是javac的一个工具，它用来在编译时扫描和处理注解。注解处理器以Java代码(或者编译过的字节码)作为输入，生成.java文件作为输出。
简单来说就是在编译期，通过注解生成.java文件。

得当的使用编译时注解，可以极大的提高开发效率，避免编写重复、易错的代码。大部分时候编译时注解都可以代替java反射，利用可以直接调用的代码代替反射，极大的提升运行效率。

那么问题来了：

- 什么是注解？
- 什么是运行时注解？
- 什么是编译时注解？APT？

![](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/57caceab5ee24e9593b2c02591453513~tplv-k3u1fbpfcp-watermark.image)

接下来本文将注解，文章将按照以下目录带大家了解编译时注解技术：

- 什么是注解
- 运行时注解的简单使用-实现ContentView自动注入
- 探秘运行时注解标志性框架Retrofit的核心原理
- 什么是编译时注解
- 编译时注解的项目实战-实现子类自动生成
- 探秘常用第三方框架核心原理

    - 探秘ButterKnife核心原理
    - 探秘Dagger核心原理
    - 探秘EventBus核心原理
    - 探秘ARouter核心原理
    - 探秘Room核心原理

- 总结

### 1 什么是注解

一般，我们评价某人会说，这是一个好人、坏人、男神、女神、大神、单身狗等等，这是我们人为贴得标签，这些标签有助于我们自己或者其他人去获取被评价的人的基本信息。

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e856ed9fbf644e85a438bd09c17512cb~tplv-k3u1fbpfcp-watermark.image)

而在软件开发中，我们也可以给某些类，某些字段贴上作用类似的标签，这种标签的名字就叫做注解，只不过这种标签是给代码看的。

标签只对特定的人起作用，比如小张被人贴了一个小气鬼的标签，所以小红认为小张是一个小气鬼，但是小张本人不会因为这个标签而改变自己变得不是小张，也许本质上小张是个大方的人。

所以，注解本身也不会影响代码本身的运行，它只会针对特定的代码起到一定的用处，用来处理注解的代码被称作 APT（Annotation Processing Tool）。

#### 1.1 注解示例

注解你一定不会陌生，这就是我们最常见的注解：

```java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
}
```

```java
@IntDef({
        AppBootType.COLD_BOOT,
        AppBootType.HOT_BOOT,
})
@Retention(RetentionPolicy.SOURCE)
public @interface AppBootType {
    int COLD_BOOT = 0;
    int HOT_BOOT = 1;
}
```

#### 1.2 注解分类

首先注解分为三类：

- 标准 Annotation

	包括 Override, Deprecated, SuppressWarnings，是java自带的几个注解，他们由编译器来识别，不会进行编译，不影响代码运行，至于他们的含义不是本次分享的重点，这里不再讲述。

- 元 Annotation

	@Retention, @Target, @Inherited, @Documented，它们是用来定义 Annotation 的 Annotation。也就是当我们要自定义注解时，需要使用它们。
	
- 自定义 Annotation
	
	根据需要，自定义的Annotation。而自定义的方式，下面我们会讲到。
	

同样，自定义的注解也分为三类，通过元Annotation - @Retention 定义（即注解保留到什么阶段）：

- @Retention(RetentionPolicy.SOURCE)

	源码时注解，一般用来作为编译器标记。如Override, Deprecated, SuppressWarnings。
	
- @Retention(RetentionPolicy.RUNTIME)

	运行时注解，在运行时通过反射去识别的注解。

- @Retention(RetentionPolicy.CLASS)

	编译时注解，在编译时被识别并处理的注解，这是本章重点。

### 2 运行时注解的简单使用-实现ContentView自动注入

运行时注解的实质是，在代码中通过注解进行标记，运行时通过反射寻找标记进行某种处理。而运行时注解一直以来被呕病的原因便是反射的低效。

#### 2.1 运行时注解展示Demo概述

下面仅仅作为一个展示Demo。我们先不讲这两种方式哪个好哪个坏，我们只谈技术不谈需求。这个Demo其功能是通过注解实现布局文件的设置。

之前我们是这样设置布局文件的：

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
}
```
    
如果使用注解，我们就可以这样设置布局了

```java
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity {
    。。。
}
```

那么这样的注解是怎么实现的呢？很简单，往下看。

#### 2.2 创建一个注解
	
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ContentView {
    int value();
}
```


**第一行:@Retention(RetentionPolicy.RUNTIME)**

@Retention用来修饰这是一个什么类型的注解（即注解保留到什么阶段）。这里表示该注解是一个运行时注解。这样APT就知道啥时候处理这个注解了。

**第二行：@Target({ElementType.TYPE})**

@Target用来表示这个注解可以使用在哪些地方。比如：类、方法、属性、接口等等。这里ElementType.TYPE 表示这个注解可以用来修饰：Class, interface or enum declaration。当你用ContentView修饰一个方法时，编译器会提示错误。

**第三行：public @interface ContentView**

这里的interface并不是说ContentView是一个接口。就像申明类用关键字class。申明枚举用enum。申明注解用的就是@interface。（值得注意的是：在ElementType的分类中，class、interface、Annotation、enum同属一类为Type，并且从官方注解来看，似乎interface是包含@interface的）

```java
/** Class, interface (including annotation type), or enum declaration */
    TYPE,
```

**第四行：int value();**

返回值表示这个注解里可以存放什么类型值。比如我们是这样使用的

```java
@ContentView(R.layout.activity_home)
```
	
R.layout.activity_home实质是一个int型id，如果这样用就会报错：

```java
@ContentView(“string”)
```

#### 2.3 注解解析

注解申明好了，但具体是怎么识别这个注解并使用的呢？

```java
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity {
    。。。
}
```

注解的解析就在BaseActivity中。我们看一下BaseActivity代码

```java
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //注解解析
    for (Class c = this.getClass(); c != Context.class; c = c.getSuperclass()) {
        ContentView annotation = (ContentView) c.getAnnotation(ContentView.class);
        if (annotation != null) {
            try {
                this.setContentView(annotation.value());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
```
	
第一步：遍历所有的子类

第二步：找到修饰了注解ContentView的类

第三步：获取ContentView的属性值。

第四步：为Activity设置布局。

#### 2.4 总结

相信你现在对运行时注解的使用一定有了一些理解了。也知道了运行时注解被人呕病的地方在哪了。

你可能会觉得*setContentView(R.layout.activity_home)*和*@ContentView(R.layout.activity_home)*没什么区别，用了注解反而还增加了性能问题。

但你要知道，这只是注解最简单的应用方式，好戏在后头。

### 3 探秘运行时注解标志性框架Retrofit的核心原理

#### 3.1 Retrofit用法简单回顾

这里不再赘述，直接参考Retrofit官方文档：（https://square.github.io/retrofit/）

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9571a65649b24cd8b30265fc039ebd27~tplv-k3u1fbpfcp-watermark.image)

核心步骤就是：

1. 创建网络接口
2. 创建Retrofit门面类对象，并且通过动态代理创建网络接口的实例
3. 调用网络接口的实例的方法，获取网络数据返回

#### 3.2 Retrofit核心原理分析

通过使用Retrofit可以很方便地进行网络访问，不需要关心底层实现，Retrofit帮你解耦了上层使用和底层框架，调用者不用关系框架的底层实现（OkHttp），将来更换底层网络引擎也很方便，其中运行时注解起到了很大的作用。

我们先从Retrofit的create方法入手分析：

```java
  @SuppressWarnings("unchecked") // Single-interface proxy creation guarded by parameter safety.
  public <T> T create(final Class<T> service) {
    Utils.validateServiceInterface(service);
    if (validateEagerly) {
      eagerlyValidateMethods(service);
    }
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
        new InvocationHandler() {
          private final Platform platform = Platform.get();

          @Override public Object invoke(Object proxy, Method method, @Nullable Object[] args)
              throws Throwable {
            // If the method is a method from Object then defer to normal invocation.
            if (method.getDeclaringClass() == Object.class) {
              return method.invoke(this, args);
            }
            if (platform.isDefaultMethod(method)) {
              return platform.invokeDefaultMethod(method, service, proxy, args);
            }
            ServiceMethod<Object, Object> serviceMethod =
                (ServiceMethod<Object, Object>) loadServiceMethod(method);
            OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);
            return serviceMethod.adapt(okHttpCall);
          }
        });
  }
```

可以看到通过动态代理的方式创建了网络接口的实例对象，我们调用网络请求方法的时候，会调用这个匿名InvocationHandler的invoke方法。

invoke方法通过loadServiceMethod把传进来的method解析成一个serviceMethod对象，然后先包装成okHttpCall对象，最终通过Adapter适配成不同的返回类型。

接下来重点看一下loadServiceMethod方法：

```java
  ServiceMethod<?, ?> loadServiceMethod(Method method) {
    ServiceMethod<?, ?> result = serviceMethodCache.get(method);
    if (result != null) return result;

    synchronized (serviceMethodCache) {
      result = serviceMethodCache.get(method);
      if (result == null) {
        result = new ServiceMethod.Builder<>(this, method).build();
        serviceMethodCache.put(method, result);
      }
    }
    return result;
  }
```
重点关注一下这个build方法：

```java
    public ServiceMethod build() {
      callAdapter = createCallAdapter();
      responseType = callAdapter.responseType();
      if (responseType == Response.class || responseType == okhttp3.Response.class) {
        throw methodError("'"
            + Utils.getRawType(responseType).getName()
            + "' is not a valid response body type. Did you mean ResponseBody?");
      }
      responseConverter = createResponseConverter();

      for (Annotation annotation : methodAnnotations) {
        parseMethodAnnotation(annotation);
      }

      if (httpMethod == null) {
        throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).");
      }

      if (!hasBody) {
        if (isMultipart) {
          throw methodError(
              "Multipart can only be specified on HTTP methods with request body (e.g., @POST).");
        }
        if (isFormEncoded) {
          throw methodError("FormUrlEncoded can only be specified on HTTP methods with "
              + "request body (e.g., @POST).");
        }
      }

      int parameterCount = parameterAnnotationsArray.length;
      parameterHandlers = new ParameterHandler<?>[parameterCount];
      for (int p = 0; p < parameterCount; p++) {
        Type parameterType = parameterTypes[p];
        if (Utils.hasUnresolvableType(parameterType)) {
          throw parameterError(p, "Parameter type must not include a type variable or wildcard: %s",
              parameterType);
        }

        Annotation[] parameterAnnotations = parameterAnnotationsArray[p];
        if (parameterAnnotations == null) {
          throw parameterError(p, "No Retrofit annotation found.");
        }

        parameterHandlers[p] = parseParameter(p, parameterType, parameterAnnotations);
      }

      if (relativeUrl == null && !gotUrl) {
        throw methodError("Missing either @%s URL or @Url parameter.", httpMethod);
      }
      if (!isFormEncoded && !isMultipart && !hasBody && gotBody) {
        throw methodError("Non-body HTTP method cannot contain @Body.");
      }
      if (isFormEncoded && !gotField) {
        throw methodError("Form-encoded method must contain at least one @Field.");
      }
      if (isMultipart && !gotPart) {
        throw methodError("Multipart method must contain at least one @Part.");
      }

      return new ServiceMethod<>(this);
    }
```

可以看到build方法里面对method上面的注解都做了处理，比如说解析POST还是GET请求。这就是为什么我们通过简单的注解，就可以实现复杂的网络请求功能。

### 4 什么是编译时注解

现在你对运行时注解有一个大概理解了，运行时注解很好理解，代码运行的时候通过反射技术就可以获取上面的注解信息。

根据前面注解分类，编译时注解是保留到编译阶段的，即.class文件，不会保留到dex里面，即运行时根本获取不到这个注解了，那么这种编译时注解又有什么用呢？

我们先来回顾一下编译时注解到底是什么：

前面说过，APT(Annotation Processing Tool)即注解处理器（通常也叫做编译时注解、编译时代码自动生成），是一种处理注解的工具，确切的说它是javac的一个工具，它用来在编译时扫描和处理注解。注解处理器以Java代码(或者编译过的字节码)作为输入，生成.java文件作为输出。
简单来说就是在编译期，通过注解生成.java文件。

通过上面的讲解，我们知道了：通常编译时注解要结合注解处理器一起使用的，通过解析注解，获取注解上面的信息，然后生成代码，从而生成一下辅助我们自己手写的代码。说得有点抽象，下面通过一个简单示例来演示一下。

### 5 编译时注解的项目实战-实现子类自动生成

#### 5.1 示例概述

微信分享需要我们在应用包下面建一个wxapi包，然后放一个WXEntryActivity，这种强制添加一个比较顶层的包对于强迫症一族来说真的不可接受。那么我们能不能通过APT技术，动态生成这个WXEntryActivity呢？答案当然是可以的。

示例最终效果如下：

```java
@SubTypeAutoGenerate("com.flyme.videoclips.wxapi.WXEntryActivity")
public class BaseWXEntryActivity extends Activity implements IWXAPIEventHandler {
    。。。
}
```

生成的代码如下：

```java
// 自动生成的代码,请不要改动
package com.flyme.videoclips.wxapi;

import com.flyme.videoclips.util.wxapi.BaseWXEntryActivity;

public class WXEntryActivity extends BaseWXEntryActivity {
}
```

我们通过一个@SubTypeAutoGenerate注解就可以很方便地生成WXEntryActivity啦，其中@SubTypeAutoGenerate注解的参数就是生成的全类名。

#### 5.2 项目架构

-- app Android主项目
-- lib-compiler Java项目，注解处理器
-- lib-annotations Java项目，存放注解

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/00ffe9fd37b2446eab781e2aec5d76e0~tplv-k3u1fbpfcp-watermark.image)

其中Android主项目通过下面这种方式引入注解项目和注解处理器：

```groovy
implementation project(path: ':lib-annotations')
kapt project(path: ':lib-compiler')
```

然后注解处理器里面也引入了注解项目，一般还需要引入AutoService和JavaPoet（用于代码生成）。

```groovy
    implementation project(path: ':lib-annotations')
    implementation "com.google.auto.service:auto-service:$versions.auto_service"
    kapt "com.google.auto.service:auto-service:$versions.auto_service"
    implementation "com.squareup:javapoet:$versions.javapoet"
```

#### 5.3 具体实现

从上面的需求我们知道，我们先要定义一个注解，供Android主项目和解处理器使用：

```java
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface SubTypeAutoGenerate {
    String value() default "";
}
```

这里我们定义了一个编译时注解，只能用于类型上面，这个注解的值就是我们需要生成的类的全类名。

下面我们需要实现注解处理器，这里我们先包装了一个基类，：

```java
public abstract class BaseProcessor extends AbstractProcessor {
    // 代码生成相关的工具类
    protected Filer mFiler;
    // 打印相关的工具类
    protected Messager mMessager;
    // Elements操作相关的工具类
    protected Elements mElementUtils;

    // 初始化方法，这里可以获取一些工具类
    @Override
    public final synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mMessager = processingEnvironment.getMessager();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    // 指定支持的源码版本
    @Override
    public final SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
    
    // 指定支持的注解类型
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    protected abstract Set<Class<? extends Annotation>> getSupportedAnnotations();

}
```

注解处理器的核心职责是扫描注解，然后根据扫描到的信息生成代码，具体示例如下：

```java
// 用这个@AutoService注解可以省去一些注解处理器的配置
@AutoService(Processor.class)
public class SubTypeAutoGenerateProcessor extends BaseProcessor {

    @Override
    protected Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(SubTypeAutoGenerate.class);
        return annotations;
    }
    
    // 注解处理器的核心方法
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 获取标记有注解的Element
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(SubTypeAutoGenerate.class);
        for (Element element : elements) {
            if (!(element instanceof TypeElement)) {
                continue;
            }
            // 强转为TypeElement
            TypeElement typeElement = (TypeElement) element;

            // 获取需要生成的全类名、包名、简单类名
            SubTypeAutoGenerate subTypeAutoGenerate = typeElement.getAnnotation(SubTypeAutoGenerate.class);
            String subTypeQualifiedName = subTypeAutoGenerate.value();
            String subTypePackage = Utils.getPackage(subTypeQualifiedName);
            String subTypeSimpleName = Utils.getSimpleName(subTypeQualifiedName);
    
            // 利用JavaPoet的API去生成代码。也可以直接用StringBuilder来拼接代码
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(subTypeSimpleName)
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(ClassName.get(typeElement));
            try {
                JavaFile.builder(subTypePackage, classBuilder.build())
                        .addFileComment(Constant.COMMON_COMMENT)
                        .build()
                        .writeTo(mFiler);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

}
```

附上工具类的代码：

```java
public class Utils {

    public static String getPackage(String qualifiedName) {
        return qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
    }

    public static String getSimpleName(String qualifiedName) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
    }

}
```

通过编译项目，就可以看到生成的WXEntryActivity了

### 6 探秘常用第三方框架核心原理

- 探秘ButterKnife
- 探秘Dagger
- 探秘EventBus
- 探秘ARouter
- 探秘Room

#### 6.1 探秘ButterKnife核心原理

ButterKnife是一个视图绑定框架，其核心原理是：

1. 在View成员上面添加BindView注解
2. 注解处理器扫描BindView注解，为每一个带有注解的类生成辅助类xxx_ViewBinding，这个类的构造方法里面会对xxx的属性进行注入
3. 调用ButterKnife.bind(this)，内部通过反射去构造xxx_ViewBinding，从而完成注入

#### 6.2 探秘Dagger核心原理

Dagger是一个通过依赖注入的方式实现IOC的框架，其核心原理是：

1. 通过@Inject注解标记需要注入的属性，对应生成注入类xxx_MembersInjector，帮助这个类的属性进行注入
2. 通过@Inject注解标记构造函数或者通过@Module注解标记一个依赖提供者，对应生成注入类xxx_Factory，帮助提供依赖
3. 通过@Component注解为需要注入的属性和依赖提供者搭建桥梁，对应生成桥梁类DaggerXxxComponent
4. 最后通过调用APT生成的Component的注入方法即可完成依赖注入

#### 6.3 探秘EventBus核心原理

EventBus是一个很好用的事件总线框架，其核心原理是：

早期版本（反射方案）：

1. 通过运行时注解@Subscribe定义事件接收方法
2. 通过register方法注册观察者，内部会通过反射来得到包含@Subscribe注解的所有方法，并且封装好添加到观察者Map集合里面去
3. 通过post方法发送事件，EventBus会遍历观察者Map集合，查找符合条件的观察者，进行回调
4. 通过unRegister移除观察者，即把这个对象从观察者Map集合中移除，防止内存泄漏

最新版本（APT方案）：

1. 在3.0版本中，EventBus通过注解处理器来在编译期通过扫描@Subscribe()注解并解析，处理其中所包含的信息，然后生成java类来保存所有订阅者关于订阅的信息，这样就比在运行时使用反射来获得这些订阅者的信息速度要快
2. 在EventBus的Builder里面可以通过addIndex来添加，在查找订阅者的时候就可以不用反射了
3. 其余的跟上面一样，不再赘述

参考：[EventBus 3.0 源码分析](https://www.jianshu.com/p/f057c460c77e)

#### 6.4 探秘ARouter核心原理

ARouter是一个很好用的路由框架，常用于组件化开发，其核心原理是：

1. 用路由注解标记Activity/Fragment等
2. 注解处理器扫描路由注解，生成路由表注入类
3. ARouter初始化的时候通过反射构造并调用路由表注入类的路由注入方法
4. 有了路由表，就可以很方便地完成路由功能啦

#### 6.5 探秘Room核心原理

Room是Google JetPack里面提供的数据库框架，其核心原理是：

1. 定义数据库操作接口或者抽象类，并用@Dao标记
2. 定义数据库门面，并用@Database标记
3. 注解处理器扫描这些注解，生成Dao和Database的实现类，完成数据库操作
4. 通过RoomDatabase.Builder的build方法，反射生成Database的实例

### 总结

通过这篇文章，我相信你已经了解了注解的大部分所需要掌握的知识了，由于本文的重点是APT，所以最后我们来总结一下APT

APT优点

- 对代码进行标记，在编译时收集信息，并做处理。
- 生成一套独立代码，辅助代码运行
- 生成代码位置的可控性（可以在任意包位置生成代码），与原有代码的关联性更为紧密方便
- 更为可靠的自动代码生成
- 自动生成的代码可以最大程度的简单粗暴，在不必考虑编写效率的情况下提高运行效率

APT缺点

- APT往往容易被误解可以实现代码插入，然而APT和代码插入是有本质区别的，APT是生成代码；代码插入是修改已有代码
- APT可以自动生成代码，但在运行时却需要主动调用
- APT代码生成于Build目录，只能在运行时通过接口（配合反射）等方式进行操作。这意味着生成的代码必须要有一套固定的模板

APT应用场景

- 某个场景需要写大量重复的代码的时候，可以考虑用APT来进行优化
- 某些用到反射的场景，可以考虑用APT来进行优化
- 例如大量的findViewById、数据库操作代码

参考文章

[Android编译时注解框架系列](https://lizhaoxuan.github.io/2016/07/17/apt-wathapt/)
[轻松学，听说你还没有搞懂 Dagger2](https://frank909.blog.csdn.net/article/details/75578459)
[参考源码地址](https://github.com/huannan/Architecture)
