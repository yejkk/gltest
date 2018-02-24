package com.yexin.ftd.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yexin.ftd.ktApplication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Sunflower on 2015/11/4.
 */
public class Api {

    /**
     * 服务器地址
     */
    // 请求公共部分
    private static final String BASE_URL = "https://api.baobaobooks.com/";

    // 消息头
    private static final String HEADER_X_HB_Client_Type = "X-HB-Client-Type";
    private static final String FROM_ANDROID = "ayb-android";

    private static ApiService service;
    private static Retrofit retrofit;
    /**
     * 拦截器  给所有的请求添加消息头
     */
    private static Interceptor mInterceptor ;

    public static ApiService getService() {
        if (service == null) {
            service = getRetrofit().create(ApiService.class);
        }
        return service;
    }


    public static Interceptor getInterceptor(){
        Map<String, String> headers = new HashMap<>();
        headers.put(HEADER_X_HB_Client_Type,FROM_ANDROID);
        mInterceptor = new BaseInterceptor(headers);
        return mInterceptor;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            // log拦截器  打印所有的log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 请求的缓存
            File cacheFile = new File(ktApplication.Companion.getInstance().getCacheDir(), "cache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb



            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .cookieJar(new NovateCookieManger(null))
                    .addInterceptor(interceptor)
                    .addInterceptor(getInterceptor())
                    .cache(cache)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //影响的就是第二部分以及我们的请求参数
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //影响的就是第一部分的Call或者Observable
                    .build();
        }
        return retrofit;
    }

    /**
     * 对 Observable<T> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     *
     * @param responseObservable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> applySchedulers(Observable<T> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<T, Observable<T>>() {
                    @Override
                    public Observable<T> apply(T response) throws Exception {
                        return flatResponse(response);
                    }
                });
    }

    /**
     * 对网络接口返回的Response进行分割操作 对于json 解析错误以及返回的 响应实体为空的情况
     *
     * @param response
     * @return
     */
    public <T> Observable<T> flatResponse(final T response) {
        return Observable.create(new ObservableOnSubscribe< T >() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                if (response != null) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(response);
                    }
                } else {
                    if (!emitter.isDisposed()) {
                        emitter.onError(new APIException("自定义异常类型", "解析json错误或者服务器返回空的json"));
                    }
                    return;
                }
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }

        });
    }

    /**
     *
     */
    public static class APIException extends Exception {
        public String code;
        private String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

    }


}
