package com.nan.daggerprimer.simple7.component;

import com.nan.daggerprimer.simple7.Friend;
import com.nan.daggerprimer.simple7.scope.FriendScope;

import dagger.Component;

/**
 * Component 的组织关系-依赖
 * 编译时生成的代码中 DaggerFriendComponent 的 Provider<Car>实现中会用到manComponent.car()来提供 car 实例
 * 即FriendComponent用了ManComponent提供 car 实例的方式
 */
@FriendScope
@Component(dependencies = {ManComponent.class})
public interface FriendComponent {

    void injectFriend(Friend friend);

}
