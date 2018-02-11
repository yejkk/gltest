package com.yexin.ftd

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by yexin on 2018/1/4.
 */

interface IIMSService{
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call


}