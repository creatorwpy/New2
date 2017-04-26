package com.wpy.news.base;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wpy.news.Config;
import com.wpy.news.util.LogDebug;
import com.wpy.news.util.common.CommonUtil;

import cz.msebera.android.httpclient.Header;

/**
 * Created by X230 on 2017/4/25.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;
    private Context context;
    public  static CrashHandler getInstance(){
        if(instance==null){
            instance = new CrashHandler();
        }
        return instance;
    }
    public void init(Context c){
        context=c;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        LogDebug.e("崩溃了:",e.getMessage());

        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.apis_site+"/apis/news/?action=debug&version="+ CommonUtil.getVersionName(context)+"&platform=android&msg="+e.getMessage();
        RequestParams requestParams = new RequestParams();
        requestParams.add("msg",e.getMessage());
        LogDebug.i("崩溃了,发送错误日志",url);
        client.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

    }
}
