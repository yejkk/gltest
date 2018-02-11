package com.yexin.ftd.api;



import io.reactivex.Observable;
import com.yexin.ftd.RequestParamete.LoginParams;
import com.yexin.ftd.reponse.Login;

/**
 *  Api类的包装
 */
public class ApiWrapper extends Api {

    public Observable<Login> getUerInfo(LoginParams mLoginParams) {
        return applySchedulers(getService().getPersonalInfo(mLoginParams));
    }

}
