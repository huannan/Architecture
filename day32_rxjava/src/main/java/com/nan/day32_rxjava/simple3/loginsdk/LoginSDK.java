package com.nan.day32_rxjava.simple3.loginsdk;

import android.app.Activity;
import android.content.Intent;

/**
 * 模拟第三方登录SDK
 */
public class LoginSDK {

    public static void login(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, 0);
    }

}
