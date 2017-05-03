package com.wpy.news.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
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
    private Toast mToast;

    public void showToastShort(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }
}
