package com.yexin.ftd.api;



import android.content.Context;

import io.reactivex.Observable;
import com.yexin.ftd.RequestParamete.LoginParams;
import com.yexin.ftd.reponse.Login;

/**
 *  Api类的包装
 */
public class ApiWrapper extends Api {

    private static Context mContext;

    public ApiWrapper(){
    }

    public ApiWrapper(Context context){
        mContext = context;
    }

    public Observable<Login> getUerInfo(LoginParams mLoginParams) {
        return applySchedulers(getService().getPersonalInfo(mLoginParams));
    }

}
