package com.yexin.ftd.base;

import com.google.gson.Gson;
import com.yexin.ftd.api.Api;
import com.yexin.ftd.api.ApiWrapper;
import com.yexin.ftd.api.SimpleMyCallBack;
import com.yexin.ftd.reponse.HttpExceptionBean;
import com.yexin.ftd.utils.ToastUtils;



import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by anzhuo002 on 2016/7/6.
 */

public  class BaseCommonPresenter<T extends BaseView> {
    /**
     * Api类的包装 对象
     */
    protected ApiWrapper mApiWrapper;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeDisposable mCompositeDisposable;

    public T view;

    public BaseCommonPresenter(T view) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeDisposable = new CompositeDisposable();
        // 构建 ApiWrapper 对象
        mApiWrapper = new ApiWrapper();
        this.view  = view;
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <E>
     * @return
     */
    protected <E> Observer newMySubscriber(final SimpleMyCallBack<E> onNext) {
        return new Observer<E>() {
            @Override
            public void onComplete() {
                if (view != null) {
                    view.hideLoading();
                }
                onNext.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof Api.APIException) {
                    Api.APIException exception = (Api.APIException) e;
                    if (view != null) {
                        ToastUtils.showShort(exception.getMessage());
                    }
                } else if (e instanceof HttpException) {
                    if (e instanceof HttpException) {
                        ResponseBody body = ((HttpException) e).response().errorBody();
                        try {
                            String json = body.string();
                            Gson gson = new Gson();
                            HttpExceptionBean mHttpExceptionBean = gson.fromJson(json, HttpExceptionBean.class);
                            if (mHttpExceptionBean != null && mHttpExceptionBean.getMessage() != null) {
                                ToastUtils.showShort(mHttpExceptionBean.getMessage());
                                onNext.onError(mHttpExceptionBean);
                            }
                        } catch (IOException IOe) {
                            IOe.printStackTrace();
                        }
                    }
                }
//                e.printStackTrace();
                if (view != null) {
                    view.hideLoading();
                }
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(E t) {
                if (!mCompositeDisposable.isDisposed()) {
                    onNext.onNext(t);
                }
            }

        };
    }


    /**
     * 解绑 CompositeSubscription
     */
    public void unsubscribe() {
        if(mCompositeDisposable != null){
            mCompositeDisposable.isDisposed();
        }
    }

}
