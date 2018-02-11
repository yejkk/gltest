package com.yexin.ftd.ui.Mime.LoginPage;

import com.yexin.ftd.base.BasePresenter;
import com.yexin.ftd.base.BaseView;

/**
 * Created by baixiaokang on 16/4/29.
 */
public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess();
    }

    interface Presenter extends BasePresenter {
        void login();
    }
}
