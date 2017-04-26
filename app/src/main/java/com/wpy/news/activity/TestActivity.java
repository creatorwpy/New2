package com.wpy.news.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wpy.news.R;
import com.wpy.news.base.BaseActivity;

public class TestActivity extends BaseActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        webView = (WebView)findViewById(R.id.webview_test);

        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                //网页加载完成后回调
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl("http://www.baidu.com");
        webView.clearCache(true);
        webView.clearHistory();
    }

    @Override
    protected void onStop() {
        super.onStop();
        webView.destroy();
    }
}
