package com.yexin.ftd.ui.Mime;


import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.yexin.ftd.R;
import com.yexin.ftd.RequestParamete.LoginParams;
import com.yexin.ftd.api.SimpleMyCallBack;
import com.yexin.ftd.base.BaseActivity;
import com.yexin.ftd.reponse.HttpExceptionBean;
import com.yexin.ftd.reponse.Login;
import com.yexin.ftd.ui.Mime.LoginPage.LoginActivity;
import com.yexin.ftd.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


;

//public class WelcomeActivity extends BaseActivity implements OnRefreshListener,OnLoadMoreListener {
//
//    private SwipeToLoadLayout swipeToLoadLayout;
//    private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_welcome);
//
//        initApi();
//
//        /**
//         * 网络请求
//         */
//        LoginParams mLoginParams = new LoginParams("18576400302", "123456");
//        Subscription subscription =  mApiWrapper.getUerInfo(mLoginParams)
//                .subscribe(newMySubscriber(new SimpleMyCallBack<Login>() {
//                    // 这个方法根据需要重写 之前已经toast了，如果toast了还要做其他的事情，就重写这个方法
//                    @Override
//                    public void onError(HttpExceptionBean mHttpExceptionBean) {
//                        super.onError(mHttpExceptionBean);
//                        showToast("yoxin");
//                    }
//                    @Override
//                    public void onNext(Login mLogin) {
//                        showToast("登录成功"+mLogin.toString());
//                    }
//                }));
//        mCompositeSubscription.add(subscription);
//
//    }
//
//    @Override
//    public void initView() {
//
//        /**
//         * 刷新 recyclerView 点击事件
//         */
//        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
//        recyclerView = (RecyclerView)findViewById(R.id.swipe_target);
//        RecyclerView.LayoutManager layoutManager = null;
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        List<String> list = new ArrayList<>();
//        for(int i= 0;i<50; i++){
//            list.add("ad");
//        }
//        MyTextAdapter adapter = new MyTextAdapter(this, list);
//        adapter.setOnItemClickLitener(new BaseRecylerAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ToastUtils.showShort("点击了第--"+position+"--个");
//                skipAct(LoginActivity.class);
//            }
//        });
//        recyclerView.setAdapter(adapter);
//
//        swipeToLoadLayout.setOnRefreshListener(this);
//        swipeToLoadLayout.setOnLoadMoreListener(this);
//
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
//                    if (!ViewCompat.canScrollVertically(recyclerView, 1)){
//                        swipeToLoadLayout.setLoadingMore(true);
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onRefresh() {
//        swipeToLoadLayout.setRefreshing(false);
//    }
//
//    @Override
//    public void onLoadMore() {
//        swipeToLoadLayout.setLoadingMore(false);
//    }
//
//    @Override
//    public void bindEvent() {
//
//    }
//
//
//    @Override
//    public void onClick(View view) {
//
//    }
//
//
//}
