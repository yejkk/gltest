package vod.clearcrane.com.myapplication.logic

/**
 * Created by yexin on 2018/1/4.
 */

class NetHeartBeat{

    var isStart = false;

    fun startHeartBeat(){
         if(isStart) {
             return;
         }

        val heartbeat = Thread()
    }

    class heartbeatRunnable:Runnable{

        //heartbeat runnable
        override fun run() {

        }
    }
}