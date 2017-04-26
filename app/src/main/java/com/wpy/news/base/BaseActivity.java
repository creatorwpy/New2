package com.wpy.news.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.wpy.news.util.LogDebug;

/**
 * Created by xinghongfei on 16/8/12.
 */
public class BaseActivity extends AppCompatActivity {
    String TAG=getClass().getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        // TODO: 16/9/1  add the third service. eg.umeng ...
//        App.getApp().addActivity(this);
        LogDebug.e(TAG,TAG+"创建");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        App.getApp().removeActivity(this);
        LogDebug.e(TAG,TAG+"销毁");
    }
}
