package com.wpy.news.util;

import android.util.Log;

import static android.R.attr.tag;


/**
 * 简单的Log工具,可以方便的关闭所有Log
 * Created by Harmy on 2015/8/10 0010.
 */
public class LogDebug {

    private static final String DEFAULT_TAG = "tag_DeBug";
    /**
     * 默认Debug模式
     */
    private static boolean mDebugMode = true;

    /**
     * 设置开启/关闭Debug模式
     *
     * @param debugMode
     */
    public static void setDebugMode(boolean debugMode) {
        mDebugMode = debugMode;
    }

    public static boolean getDebugMode() {
        return mDebugMode;
    }

    public static void d(String msg) {
        if (mDebugMode)
            Log.d(DEFAULT_TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (mDebugMode)
            Log.d((DEFAULT_TAG+":"+tag), msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (mDebugMode)
            Log.d((DEFAULT_TAG+":"+tag), msg, tr);
    }

    public static void e(String tag, String msg) {
        if (mDebugMode)
            Log.e((DEFAULT_TAG+":"+tag), msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (mDebugMode)
            Log.e((DEFAULT_TAG+":"+tag), msg, tr);
    }

    public static void e(Throwable tr) {
        if (mDebugMode)
            Log.e((DEFAULT_TAG+":"+tag), tr.toString(), tr);
    }

    public static void i(String tag, String msg) {
        if (mDebugMode)
            Log.i((DEFAULT_TAG+":"+tag), msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (mDebugMode)
            Log.i((DEFAULT_TAG+":"+tag), msg, tr);
    }

    public static void v(String tag, String msg) {
        if (mDebugMode)
            Log.v((DEFAULT_TAG+":"+tag), msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (mDebugMode)
            Log.v((DEFAULT_TAG+":"+tag), msg, tr);
    }

    public static void w(String tag, String msg) {
        if (mDebugMode)
            Log.w(DEFAULT_TAG+":"+tag, msg);
    }

    public static void w(String tag, Throwable tr) {
        if (mDebugMode)
            Log.w(DEFAULT_TAG+":"+tag, tr);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (mDebugMode)
            Log.w(DEFAULT_TAG+":"+tag, msg, tr);
    }

    public static void wtf(String tag, String msg) {
        if (mDebugMode)
            Log.wtf(DEFAULT_TAG+":"+tag, msg);
    }

    public static void wtf(String tag, Throwable tr) {
        if (mDebugMode)
            Log.wtf(DEFAULT_TAG+":"+tag, tr);
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (mDebugMode)
            Log.wtf(DEFAULT_TAG+":"+tag, msg, tr);
    }
}
