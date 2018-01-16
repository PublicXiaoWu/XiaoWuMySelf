package com.xiaowu.myself.app.base;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication instance;
    private static Context context;

    public static MyApplication newInstance() {
        return instance;
    }

    /* 应用全局Context, 使用单例模式的业务类操作统一使用这个全局资源，避免传入Activity context,造成内存泄露 */
    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }
}
