package com.wpy.news.base;

import android.app.Application;

import com.wpy.news.util.LogDebug;

/**
 * Created by X230 on 2017/4/25.
 */

public class App extends Application {
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
        System.exit(0);
    }
}
