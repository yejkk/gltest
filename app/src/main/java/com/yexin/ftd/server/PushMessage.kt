package vod.clearcrane.com.myapplication.server

import com.yexin.ftd.server.NanoHTTPD

/**
 * Created by yexin on 2018/1/12.
 */

class PushMessage(port: Int) : NanoHTTPD(port){
    val TAG = "PushMessage"
    var port = 7777

    fun startWork(){
        start()

    }

}