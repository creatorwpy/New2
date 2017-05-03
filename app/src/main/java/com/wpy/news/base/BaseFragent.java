package com.wpy.news.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/10/24.
 */

public class BaseFragent extends Fragment {
    private Toast mToast;
    public void showToastShort(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }
}
