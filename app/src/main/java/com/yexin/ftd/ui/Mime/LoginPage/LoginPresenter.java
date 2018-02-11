package com.yexin.ftd.ui.Mime.LoginPage;


import com.yexin.ftd.RequestParamete.LoginParams;
import com.yexin.ftd.api.SimpleMyCallBack;
import com.yexin.ftd.base.BaseCommonPresenter;
import com.yexin.ftd.reponse.HttpExceptionBean;
import com.yexin.ftd.reponse.Login;
import com.yexin.ftd.utils.ToastUtils;

import io.reactivex.disposables.Disposable;


/**
 * Created by baixiaokang on 16/4/29.
 */
public class LoginPresenter extends BaseCommonPresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login() {
        LoginParams mLoginParams = new LoginParams("18576400302", "123456a");
        mCompositeDisposable.add((Disposable) mApiWrapper.getUerInfo(mLoginParams).subscribeWith(newMySubscriber(new SimpleMyCallBack<Login>() {
            // 这个方法根据需要重写 之前已经toast了，如果toast了还要做其他的事情，就重写这个方法
            @Override
            public void onError(HttpExceptionBean mHttpExceptionBean) {
                super.onError(mHttpExceptionBean);
                ToastUtils.showShort("yoxin");
            }

            @Override
            public void onNext(Login mLogin) {
                ToastUtils.showShort("登录成功" + mLogin.toString());
                view.loginSuccess();
            }
        })));
    }
}
