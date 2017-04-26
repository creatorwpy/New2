package com.wpy.news.base;

import android.app.Activity;
import android.app.Application;

import com.wpy.news.util.LogDebug;

import java.util.Stack;

/**
 * Created by X230 on 2017/4/25.
 */

public class App extends Application {
    public static Stack<Activity> mActivitys;
    private static App myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
    public static App getApp(){
        return (App) myApp;
    }

    public void exitApp(){
        LogDebug.e("exitApp","退出App");
//        finishAllActivity();
        System.exit(0);
    }

    private void finishAllActivity() {
        while (!mActivitys.isEmpty()) {
            mActivitys.pop().finish();
        }
    }

    public void addActivity(Activity activity) {
        if (!mActivitys.contains(activity)) {
            mActivitys.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (mActivitys.contains(activity)) {
            mActivitys.remove(activity);
        }
    }
    public Stack<Activity> getActivityStack() {
        return mActivitys;
    }
}
