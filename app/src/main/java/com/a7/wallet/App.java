package com.a7.wallet;

import android.app.Application;
import android.content.Context;

/**
 * Created by viosonlee
 * on 2018/5/23.
 * for
 */
public class App extends Application {

    public static Context APP_CONTEXT;// TODO: 2018/5/23 不建议使用，会导致内存泄漏

    @Override
    public void onCreate() {
        super.onCreate();
        APP_CONTEXT = getApplicationContext();
    }
}
