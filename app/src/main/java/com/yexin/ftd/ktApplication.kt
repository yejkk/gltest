package com.yexin.ftd

import android.app.Application

/**
 * Created by yexin on 2018/1/8.
 */

class ktApplication : Application() {

    var debug = false;

    override fun onCreate() {
        super.onCreate()
        minstance = this;
    }

    companion object {
        private var minstance: ktApplication? = null
        fun getInstance(): ktApplication {
            if (minstance == null) {
                synchronized(ktApplication::class.java) {
                    if (minstance == null) {
                        minstance = ktApplication()
                    }
                }
            }
            return minstance!!
        }
    }


}