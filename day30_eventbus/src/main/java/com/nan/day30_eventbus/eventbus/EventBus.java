package com.nan.day30_eventbus.eventbus;

import android.os.Looper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    static volatile EventBus defaultInstance;

    // subscriptionsByEventType 这个集合存放的是？
    // key 是 Event 参数的类
    // value 存放的是 Subscription 的集合列表
    // Subscription 包含两个属性，一个是 subscriber 订阅者（反射执行对象），一个是 SubscriberMethod 注解方法的所有属性参数值
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    // typesBySubscriber 这个集合存放的是？
    // key 是所有的订阅者
    // value 是所有订阅者里面方法的参数的class
    private final Map<Object, List<Class<?>>> typesBySubscriber;

    private EventBus() {
        subscriptionsByEventType = new HashMap<>();
        typesBySubscriber = new HashMap<>();
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    public void register(Object subscriber) {
        // 1. 解析所有方法封装成 SubscriberMethod 的集合
        List<SubscriberMethod> subscriberMethods = new ArrayList<>();
        Class<?> subscriberType = subscriber.getClass();
        Method[] methods = subscriberType.getDeclaredMethods();
        for (Method method : methods) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if (null == subscribe) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException("参数只能一个");
            }
            SubscriberMethod subscriberMethod = new SubscriberMethod(method, parameterTypes[0], subscribe.threadMode(), subscribe.priority(), subscribe.sticky());
            subscriberMethods.add(subscriberMethod);
        }

        // 2. 按照规则存放到 subscriptionsByEventType 里面去
        synchronized (this) {
            for (SubscriberMethod subscriberMethod : subscriberMethods) {
                subscriber(subscriber, subscriberMethod);
            }
        }
    }

    private void subscriber(Object subscriber, SubscriberMethod subscriberMethod) {
        Class<?> eventType = subscriberMethod.eventType;
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (null == subscriptions) {
            subscriptions = new CopyOnWriteArrayList<>();
            subscriptionsByEventType.put(eventType, subscriptions);
        }

        // 判断优先级 （不写）
        Subscription subscription = new Subscription(subscriber, subscriberMethod);
        subscriptions.add(subscription);

        // typesBySubscriber 要弄好是为了方便移除
        List<Class<?>> eventTypes = typesBySubscriber.get(subscriber);
        if (null == eventTypes) {
            eventTypes = new ArrayList<>();
            typesBySubscriber.put(subscriber, eventTypes);
        }
        if (!eventTypes.contains(eventType)) {
            eventTypes.add(eventType);
        }
    }

    public void unregister(Object subscriber) {
        List<Class<?>> eventTypes = typesBySubscriber.remove(subscriber);
        if (null == eventTypes) {
            return;
        }
        for (Class<?> eventType : eventTypes) {
            unSubscriber(subscriber, eventType);
        }
    }

    private void unSubscriber(Object subscriber, Class<?> eventType) {
        // 获取事件类的所有订阅信息列表，将订阅信息从订阅信息集合中移除，同时将订阅信息中的active属性置为FALSE
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (null == subscriptions) {
            return;
        }
        for (int i = subscriptions.size() - 1; i >= 0; i--) {
            Subscription subscription = subscriptions.get(i);
            if (subscriber == subscription.subscriber) {
                subscriptions.remove(i);
                subscription.active = false;
            }
        }
    }

    public void post(Object event) {
        Class<?> eventType = event.getClass();
        // 遍历 subscriptionsByEventType，找到符合的方法调用方法的 method.invoke() 执行。要注意线程切换
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (null == subscriptions) {
            return;
        }
        // 找到符合的方法调用方法的 method.invoke() 执行
        for (int i = 0; i < subscriptions.size(); i++) {
            Subscription subscription = subscriptions.get(i);
            invokeMethod(event, subscription);
        }
    }

    private void invokeMethod(Object event, Subscription subscription) {
        boolean isMainThread = Looper.myLooper() == Looper.getMainLooper();
        switch (subscription.subscriberMethod.threadMode) {
            case POSTING:
                invokeMethodInternal(event, subscription);
                break;
            case MAIN:
                if (isMainThread) {
                    invokeMethodInternal(event, subscription);
                } else {
                    EventPoster.enqueue(event, subscription);
                }
                break;
            case BACKGROUND:
                if (isMainThread) {
                    EventPoster.enqueue(event, subscription);
                } else {
                    invokeMethodInternal(event, subscription);
                }
                break;
            case ASYNC:
                EventPoster.enqueue(event, subscription);
                break;
            default:
                break;
        }
    }

    protected void invokeMethodInternal(Object event, Subscription subscription) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
