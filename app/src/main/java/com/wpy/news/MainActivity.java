package com.wpy.news;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wpy.news.base.App;
import com.wpy.news.fragment.NewsFragment;
import com.wpy.news.model.AppUpDate;
import com.wpy.news.util.LogDebug;
import com.wpy.news.util.common.CommonUtil;

import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkUpDate();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //默认fragment
        gotoNewsList("guonei");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void gotoNewsList(String newtype){
        //"http://api.tianapi.com/social/?key=6cad4a694ce80d4c7596d738ec7c9c1c&num=10&page=1"
        newtype = Config.tiaji_site+newtype+"/?key="+Config.tiaji_key+"&num=10";
        NewsFragment fragment=new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("urltype", newtype);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_shehui) {
            gotoNewsList("social");
        } else if (id == R.id.nav_camera) {
            gotoNewsList("guonei");
        } else if (id == R.id.nav_gallery) {
            gotoNewsList("world");
        } else if (id == R.id.nav_gallery) {
            gotoNewsList("world");
        } else if (id == R.id.nav_slideshow) {
            gotoNewsList("huabian");
        } else if (id == R.id.nav_manage) {
            gotoNewsList("tiyu");
        } else if (id == R.id.nav_nba) {
            gotoNewsList("nba");
        } else if (id == R.id.nav_football) {
            gotoNewsList("football");
        } else if (id == R.id.nav_keji) {
            gotoNewsList("keji");
        } else if (id == R.id.nav_startup) {
            gotoNewsList("startup");
        } else if (id == R.id.nav_apple) {
            gotoNewsList("apple");
        } else if (id == R.id.nav_mobile) {
            gotoNewsList("mobile");
        } else if (id == R.id.nav_travel) {
            gotoNewsList("travel");
        } else if (id == R.id.nav_health) {
            gotoNewsList("health");
        } else if (id == R.id.nav_qiwen) {
            gotoNewsList("qiwen");
        } else if (id == R.id.nav_meinv) {
            gotoNewsList("meinv");
        } else if (id == R.id.nav_vr) {
            gotoNewsList("vr");
        } else if (id == R.id.nav_it) {
            gotoNewsList("it");
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String updateUrl = "";
    private Boolean isFroceUpdate = false;
    private void checkUpDate(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.apis_site+"/apis/news/?action=app_update&version="+ CommonUtil.getVersionName(this)+"&platform=android";
        LogDebug.i("apk更新请求",url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String body = new String(bytes);
                Gson gson = new Gson();
                TypeToken<AppUpDate> typeToken = new TypeToken<AppUpDate>(){};
                Type type = typeToken.getType();
                AppUpDate appUpDate = gson.fromJson(body,type);
                if(appUpDate.getCode().equals("200")){
                    updateUrl = appUpDate.getUrl();
                    if(appUpDate.getPlatform().equals("1")){
                        isFroceUpdate = true;
                    }
                    LogDebug.i("apk更新",updateUrl);
                    mHandler.sendEmptyMessage(0);
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }
    private void showUpDateTip(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("发现新版本，是否更新?");
        builder.setTitle("版本更新");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(updateUrl));
                startActivity(intent);
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(isFroceUpdate){
                    App.getApp().exitApp();
                }
            }
        });
        builder.create().show();
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    LogDebug.i("apk更新_主线程来了",updateUrl);
                    showUpDateTip();
                    break;
            }
        }
    };
}
