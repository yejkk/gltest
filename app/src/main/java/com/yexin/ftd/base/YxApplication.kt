package com.yexin.ftd.base

import android.app.Application
import android.content.Context
import com.nostra13.universalimageloader.core.DisplayImageOptions
import vod.clearcrane.com.myapplication.utils.LogUtil

/**
 * Created by yexin on 2018/1/22.
 */
class YxApplication : Application() {
    private final val tag = YxApplication::class.java.getSimpleName()
    companion object {
        var Debug = true;//debug model
        lateinit var context:Context
        lateinit var instance:YxApplication
        lateinit var options: DisplayImageOptions
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = this
        init()
    }

    /**
     * 初始化
     */
    fun init(){
        LogUtil.setDebug(!Debug)
        LogUtil.e(tag, "isDebug: " + !Debug)

       // initImageLoader()
    }

}