package com.wpy.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wpy.news.R;
import com.wpy.news.activity.NewsDetailsActivity;
import com.wpy.news.base.BaseFragent;
import com.wpy.news.model.News;
import com.wpy.news.model.NewsModel;
import com.wpy.news.util.LogDebug;
import com.wpy.news.util.PixUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2016/10/24.
 */

public class NewsFragment extends BaseFragent {

    private int page = 1;
    private NewsAdapter adapter;
    //"http://api.tianapi.com/social/?key=6cad4a694ce80d4c7596d738ec7c9c1c&num=10&page=1"
    private static String urltype="";

    //@BindView(R.id.recyclerView2)
    EasyRecyclerView recyclerView;
    LinearLayout loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogDebug.d("onCreateView");
        //getDataAsyncHttp();

        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            urltype = bundle.getString("urltype");
        }

        View view = inflater.inflate(R.layout.news_fragment_layout, container, false);
        recyclerView = (EasyRecyclerView)view.findViewById(R.id.recyclerView2);
        loading = (LinearLayout)view.findViewById(R.id.loading);

        ButterKnife.bind(this, view);
        LogDebug.d("adapter");
        adapter = new NewsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        LogDebug.d("setLayoutManager");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //添加边框
        SpaceDecoration itemDecoration = new SpaceDecoration((int) PixUtil.convertDpToPixel(8, getContext()));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);

        //更多加载
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                getDataAsyncHttp();
            }

            @Override
            public void onMoreClick() {

            }
        });
        //写刷新事件
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        page = 1;
                        getDataAsyncHttp();
                    }
                }, 1000);
            }
        });

        //点击事件
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                ArrayList<String> data = new ArrayList<String>();
                data.add(adapter.getAllData().get(position).getPicUrl());
                data.add(adapter.getAllData().get(position).getUrl());
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
//                Intent intent = new Intent(getActivity(), TestActivity.class);
                //用Bundle携带数据
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data", data);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataAsyncHttp();
    }
    private  void getDataAsyncHttp(){
        if(urltype.isEmpty()){
            LogDebug.e("getDataAsyncHttp","url为空");
            return;
        }
        String url = urltype+"&page="+page;
        LogDebug.v("getDataAsyncHttp","http 请求url:"+url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String body = new String(response);
                LogDebug.v("http结果：",body);
                loading.setVisibility(View.GONE);
                Gson gson = new Gson();
                TypeToken<NewsModel> typeToken = new TypeToken<NewsModel>(){};
                Type type = typeToken.getType();
                NewsModel newsModel = gson.fromJson(body,type);
                List<News> newsList = new ArrayList<News>();
                if(newsModel.getCode()==200){
                    for (NewsModel.NewslistBean newslistBean : newsModel.getNewslist()) {
                        News new1 = new News();
                        new1.setTitle(newslistBean.getTitle());
                        new1.setCtime(newslistBean.getCtime());
                        new1.setDescription(newslistBean.getDescription());
                        new1.setPicUrl(newslistBean.getPicUrl());
                        new1.setUrl(newslistBean.getUrl());
                        newsList.add(new1);
                    }
                    adapter.addAll(newsList);
                }
                page++;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                loading.setVisibility(View.GONE);
                showToastShort("出错了，请重新操作");
                LogDebug.v("http结果：","onFailure");
                page--;
            }
        });

    }
    public class NewsAdapter extends RecyclerArrayAdapter<News> {
        public NewsAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

            return new NewsViewHolder(parent);
        }
    }
    public class NewsViewHolder extends BaseViewHolder<News> {


        private TextView mTv_name;
        private ImageView mImg_face;
        private TextView mTv_sign;

        public NewsViewHolder(ViewGroup parent) {
            super(parent, R.layout.news_recycler_item2);
            mTv_name = $(R.id.person_name);
            mTv_sign = $(R.id.person_sign);
            mImg_face = $(R.id.person_face);    }

        @Override
        public void setData(final News data) {
            mTv_name.setText(data.getTitle());
            mTv_sign.setText(data.getCtime());
            Glide.with(getContext())
                    .load(data.getPicUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(mImg_face);
        }


    }
}
