package com.wpy.news.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.wpy.news.R;
import com.wpy.news.base.BaseActivity;
import com.wpy.news.util.LogDebug;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/24.
 */

public class NewsDetailsActivity extends BaseActivity {
    WebView webText;
    LinearLayout loading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        webText = (WebView)findViewById(R.id.web_text);
        loading = (LinearLayout)findViewById(R.id.loading);

        getSupportActionBar().hide();

//        webText.setVisibility(View.GONE);
//        loading.setVisibility(View.VISIBLE);

        ButterKnife.bind(this);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        final ArrayList<String> data = bundle.getStringArrayList("data");
        LogDebug.d("url", data.get(0));

        webText.getSettings().setJavaScriptEnabled(true);
        /*关键的部分就是在这里设置Client*/

        webText.setWebChromeClient(new WebChromeClient());
        /*在这里设置是为了防止，点击链接的时候调到浏览器里。*/
        webText.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.setVisibility(View.GONE);
//                webText.setVisibility(View.VISIBLE);
            }
        });
        webText.loadUrl(data.get(1));

    }

    @Override
    /*当窗口关闭的时候也将浏览器关闭，否则视频的声音不停下来*/
    protected void onStop() {
        super.onStop();
        webText.destroy();
    }
}
