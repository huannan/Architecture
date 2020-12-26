package com.nan.day34_mvp.simple6.proxy;

import com.nan.day34_mvp.simple6.base.BaseView;

/**
 * Created by hcDarren on 2018/1/6.
 */

public class ActivityMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy{
    public ActivityMvpProxyImpl(V view) {
        super(view);
    }
    // 不同对待，一般可能不会


}
