package com.yexin.ftd

import android.os.Bundle
import android.view.View
import com.yexin.ftd.base.BaseActivity
import com.yexin.ftd.ui.Mime.LoginPage.LoginFragment
import com.yexin.ftd.ui.Mime.LoginPage.LoginPresenter

/**
 * Created by yexin on 2018/1/31.
 */


//class  LoginActivity : BaseActivity(){
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        //采用mvp 模式 在这里添加fragment 到activity 另外 创建 Presenter 不需要初始化api  ------情况3
//        var mLoginFragment: LoginFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as LoginFragment
//        if (mLoginFragment == null) {
//            mLoginFragment = LoginFragment.newInstance()
//            addFragmentToActivity(mLoginFragment, R.id.contentFrame)
//        }
//        //创建 Presenter 如果不采用mvp 就不要创建
//        mLoginFragment!!.createPresenter(LoginPresenter(mLoginFragment))
//
//    }
//
//    override fun initView(){}
//
//    override fun bindEvent(){}
//
//    override fun onClick(view: View){}
//
//}