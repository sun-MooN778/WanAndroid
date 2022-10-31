package com.stew.kotlinbox

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.stew.kb_common.util.ToastUtil
import com.stew.kb_home.di.homeModule
import com.stew.kb_me.di.meModule
import com.stew.kb_navigation.di.naviModule
import com.stew.kb_project.di.ProjectModule
import com.stew.kb_user.di.userModule
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

/**
 * Created by stew on 7/27/22.
 * mail: stewforani@gmail.com
 */
class MyApp : Application() {

    private val modules = mutableListOf(homeModule, ProjectModule, naviModule, meModule, userModule)

    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(this)
        initKoin()
        initARouter()
        initMMKV()
    }

    private fun initMMKV() {
        MMKV.initialize(this)//初始化图片加载器
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(modules)
        }
    }
}