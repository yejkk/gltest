package vod.clearcrane.com.myapplication.logic

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by yexin on 2018/1/4.
 */

class MainLogic{

    val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("http://ip.taobao.com/service/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val ipService = retrofit.create(IpService::class.java)
}