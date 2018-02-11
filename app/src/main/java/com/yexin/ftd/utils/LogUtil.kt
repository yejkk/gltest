package vod.clearcrane.com.myapplication.utils

import android.util.Log

/**
 * Created by yexin on 2018/1/22.
 */


/**
 * Log 日志工具类
 * @author weijiang.Zeng
 */
object LogUtil {

    //当前Debug模式
    var yx_Debug = true

    fun e(tag: String, text: String) {
        if (yx_Debug) {
            Log.e(tag, text)
        }
    }

    fun d(tag: String, text: String) {
        if (yx_Debug) {
            Log.d(tag, text)
        }
    }

    fun i(tag: String, text: String) {
        if (yx_Debug) {
            Log.i(tag, text)
        }
    }

    fun w(tag: String, text: String) {
        if (yx_Debug) {
            Log.w(tag, text)
        }
    }

    fun v(tag: String, text: String) {
        if (yx_Debug) {
            Log.v(tag, text)
        }
    }

    private val LOG_FORMAT = "%1\$s\n%2\$s"

    fun d(tag: String, vararg args: Any) {
        log(Log.DEBUG, null, tag, *args)
    }

    fun i(tag: String, vararg args: Any) {
        log(Log.INFO, null, tag, *args)
    }

    fun w(tag: String, vararg args: Any) {
        log(Log.WARN, null, tag, *args)
    }

    fun e(ex: Throwable) {
        log(Log.ERROR, ex, null)
    }

    fun e(tag: String, vararg args: Any) {
        log(Log.ERROR, null, tag, *args)
    }

    fun e(ex: Throwable, tag: String, vararg args: Any) {
        log(Log.ERROR, ex, tag, *args)
    }

    private fun log(priority: Int, ex: Throwable?, tag: String?, vararg args: Any) {

        if (LogUtil.yx_Debug == false) return

        var log = ""
        if (ex == null) {
            if (args != null && args.size > 0) {
                for (obj in args) {
                    log += obj.toString()
                }
            }
        } else {
            val logMessage = ex.message
            val logBody = Log.getStackTraceString(ex)
            log = String.format(LOG_FORMAT, logMessage, logBody)
        }
        Log.println(priority, tag, log)
    }

    fun yx_Debug(): Boolean {
        return yx_Debug
    }

    fun setDebug(yx_Debug: Boolean) {
        LogUtil.yx_Debug = yx_Debug
    }
}

