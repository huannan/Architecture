package com.nan.day08_pattern_singleton.manager;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

public class ActivityManager {

    private Stack<Activity> mActivities;

    private ActivityManager() {
        mActivities = new Stack<>();
    }

    private static final class Holder {
        private static final ActivityManager INSTANCE = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return Holder.INSTANCE;
    }

    public void attach(Activity activity) {
        mActivities.add(activity);
    }

    public void detach(Activity activity) {
        mActivities.remove(activity);
    }

    public void finish(Activity activity) {
        mActivities.remove(activity);
        activity.finish();
    }

    public void finish(Class<? extends Activity> clz) {
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass() == clz) {
                activity.finish();
                iterator.remove();
                break;
            }
        }
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        Iterator<Activity> iterator = mActivities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            activity.finish();
            iterator.remove();
        }
    }

    /**
     * 获取当前Activity
     * 例如单点登录的时候的被踢下线的弹窗需要获取当前Activity（不方便在BaseActivity添加判断逻辑）
     */
    public Activity getCurrentActivity() {
        return mActivities.lastElement();
    }
}
