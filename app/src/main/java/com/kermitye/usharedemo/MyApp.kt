package com.kermitye.usharedemo

import android.app.Application
import android.support.multidex.MultiDex
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig

/**
 * Created by kermitye to 2018/12/2 16:37
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        UMConfigure.init(this, "5c038f70f1f556902100041a", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
        UMConfigure.setLogEnabled(true)
        PlatformConfig.setWeixin("wx61a1baf7d820425c", "89d62b92aee3a361345a1ffa546034d8") //微信APPID和AppSecret

    }
}