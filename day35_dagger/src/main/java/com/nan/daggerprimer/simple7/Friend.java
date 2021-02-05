package com.nan.daggerprimer.simple7;

import com.nan.daggerprimer.simple7.component.DaggerFriendComponent;
import com.nan.daggerprimer.simple7.component.DaggerManComponent;
import com.nan.daggerprimer.simple7.component.FriendComponent;
import com.nan.daggerprimer.simple7.component.ManComponent;

import javax.inject.Inject;

public class Friend {

    @Inject
    Car car;

    public static void main(String[] args) {
        ManComponent manComponent = DaggerManComponent.builder()
                .build();
        FriendComponent friendComponent = DaggerFriendComponent.builder()
                .manComponent(manComponent)
                .build();

        Man man = new Man();
        Friend friend = new Friend();

        manComponent.injectMan(man);
        friendComponent.injectFriend(friend);

        man.car.run();
        friend.car.run();
    }

}
