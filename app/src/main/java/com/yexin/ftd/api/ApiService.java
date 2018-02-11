package com.yexin.ftd.api;




import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.yexin.ftd.RequestParamete.LoginParams;
import com.yexin.ftd.reponse.Login;


/**
 * Created by Sunflower on 2015/11/4.
 */
public interface ApiService {


    /**
     * 获取个人信息
     */
    @POST("account/v1/login")
    Observable<Login> getPersonalInfo(@Body LoginParams mLoginParams);


}
