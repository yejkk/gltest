package com.yexin.ftd.api;


import com.yexin.ftd.reponse.HttpExceptionBean;

/**
 * 发送请求后的回调接口
 */

interface MyCallBack<T>  {
   void onComplete();
   void onError(HttpExceptionBean mHttpExceptionBean);
   void onNext(T t);
}
