package com.yexin.ftd.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.google.gson.Gson
import com.umeng.analytics.MobclickAgent
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscriber
import retrofit2.HttpException
import com.yexin.ftd.api.Api.APIException
import com.yexin.ftd.api.ApiWrapper
import com.yexin.ftd.api.SimpleMyCallBack
import com.yexin.ftd.reponse.HttpExceptionBean
import com.yexin.ftd.utils.ToastUtils
import com.yexin.ftd.view.dialog.DialogLoading
import org.reactivestreams.Subscription
import java.io.IOException

/**
 * Created by yexin on 2018/1/22.
 */
/**
 * Acivity  基类
 */
abstract class BaseActivity<T : BasePresenter> : AppCompatActivity() , View.OnClickListener {
    protected lateinit var mContext: AppCompatActivity

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected var mCompositeDisposable: CompositeDisposable? = null
        get() {
            if (field == null) {
                field = CompositeDisposable()
            }
            return field
        }
    /**
     * 加载对话框
     */
    protected var loading: DialogLoading? = null
    /**
     * 来自哪个 页面
     */
    lateinit var fromWhere: String
    /**
     * 页面布局的 根view
     */
    protected var mContentView: View? = null
    /**
     * Api类的包装 对象
     */
    protected var mApiWrapper: ApiWrapper? = null


    var presenter: T? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置不能横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        mContext = this
        //Activity管理
        ActivityPageChangeManager.getInstance().addActivity(this)
    }

    override fun setContentView(layoutResID: Int) {
        val view = LayoutInflater.from(this).inflate(layoutResID, null)
        setContentView(view)
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        mContentView = view
        //初始化页面
        init()
    }

    /**
     * 初始化页面
     */
    fun init() {
        initFromWhere()
        initView()
        bindEvent()
    }

    /**
     * 初始化 Api  更具需要初始化
     */
    fun initApi() {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeDisposable = CompositeDisposable()
        // 构建 ApiWrapper 对象
        mApiWrapper = ApiWrapper()
    }

    val apiWrapper: ApiWrapper
        get() {
            if (mApiWrapper == null) {
                mApiWrapper = ApiWrapper()
            }
            return mApiWrapper!!
        }

    /**
     * 初始化view
     */
    abstract fun initView()


    /**
     * 绑定事件
     */
    abstract fun bindEvent()


    /**
     * 创建相应的 presenter
     */
    fun createPresenter(presenter: T?) {
        if (presenter != null) {
            this.presenter = presenter
        }

    }

    protected fun initFromWhere() {
        if (null != intent.extras) {
            if (intent.extras!!.containsKey("fromWhere")) {
                fromWhere = intent.extras!!.getString("fromWhere")!!.toString()
            }
        }
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <T>
     * @return
    </T> */
    protected fun <T> newMySubscriber(onNext: SimpleMyCallBack<T>): Subscriber<T> {
        return object : Subscriber<T> {
            override fun onComplete() {
                onNext.onComplete()
            }

            override fun onSubscribe(s: Subscription?) {

            }

            override fun onError(e: Throwable) {
                if (e is APIException) {
//                    ToastUtils.showShort(e.getMessage())
                } else if (e is HttpException) {
                        val body = e.response().errorBody()
                        try {
                            val json = body!!.string()
                            val gson = Gson()
                            val mHttpExceptionBean = gson.fromJson<HttpExceptionBean>(json, HttpExceptionBean::class.java)
                            if (mHttpExceptionBean != null && mHttpExceptionBean.getMessage() != null) {
                                ToastUtils.showShort(mHttpExceptionBean.getMessage())
                                onNext.onError(mHttpExceptionBean)
                            }
                        } catch (IOe: IOException) {
                            IOe.printStackTrace()
                        }

                }
                //                e.printStackTrace();
                hideLoadingDialog()
            }

            override fun onNext(t: T) {
                if (!mCompositeDisposable!!.isDisposed) {
                    onNext.onNext(t)
                }
            }

        }
    }

    /**
     * 将 Fragment添加到Acitvtiy
     *
     * @param fragment
     * @param frameId
     */
    protected fun addFragmentToActivity(fragment: Fragment, frameId: Int) {
        checkNotNull(fragment)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }


    /**
     * 显示一个Toast信息
     */
    fun showToast(content: String?) {
        if (content != null) {
            ToastUtils.showShort(content)
        }
    }

    fun showLoadingDialog() {
        if (loading == null) {
            loading = DialogLoading(this)
        }
        loading!!.show()
    }

    fun hideLoadingDialog() {
        if (loading != null) {
            loading!!.dismiss()
        }

    }

    /**
     * 跳转页面
     *
     * @param clazz
     */
    fun skipAct(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        intent.putExtra("fromWhere", javaClass.simpleName)
        startActivity(intent)
    }

    fun skipAct(clazz: Class<*>, bundle: Bundle) {
        val intent = Intent(this, clazz)
        intent.putExtras(bundle)
        intent.putExtra("fromWhere", javaClass.simpleName)
        startActivity(intent)
    }

    fun skipAct(clazz: Class<*>, bundle: Bundle, flags: Int) {
        val intent = Intent(this, clazz)
        intent.putExtras(bundle)
        intent.putExtra("fromWhere", javaClass.simpleName)
        intent.flags = flags
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //Acitvity 释放子view资源
        ActivityPageChangeManager.unbindReferences(mContentView)
        ActivityPageChangeManager.getInstance().removeActivity(this)
        mContentView = null
        //一旦调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
        // 如果还想使用CompositeSubscription，就必须在创建一个新的对象了。
        mCompositeDisposable?.dispose()
        //解绑 presenter
        presenter?.unsubscribe()
    }
}
