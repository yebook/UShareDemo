package com.kermitye.usharedemo

import android.Manifest
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import org.jetbrains.anko.toast
import com.umeng.socialize.media.UMMin



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission()
        }
    }

    fun shareWx(view: View) {


        //分享小程序

        //兼容低版本的网页链接
        var umMin = UMMin("url")
        // 小程序消息封面图片
        umMin.setThumb(UMImage(this, R.mipmap.ic_launcher))
        // 小程序消息title
        umMin.setTitle("慧名小程序")
        // 小程序消息描述
        umMin.setDescription("小程序描述")
        //小程序页面路径
        umMin.setPath("pages/page10007/xxxxxx")
        // 小程序原始id,在微信平台查询
        umMin.setUserName("gh_xxxxxxxxxxxx")
        /*ShareAction(this).withMedia(umMin)
            .setPlatform(SHARE_MEDIA.WEIXIN)
            .setCallback(object : UMShareListener {
                override fun onResult(p0: SHARE_MEDIA?) {
                }

                override fun onCancel(p0: SHARE_MEDIA?) {
                }

                override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
                }

                override fun onStart(p0: SHARE_MEDIA?) {
                }
            }).share()*/


        ShareAction(this)
            .withText("分享说明")
            .setDisplayList(SHARE_MEDIA.WEIXIN)
            .setCallback(object : UMShareListener {
                override fun onResult(p0: SHARE_MEDIA?) {
                    toast("分享成功")
                }

                override fun onCancel(p0: SHARE_MEDIA?) {
                    toast("分享取消")
                }

                override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
                    toast("分享失败")
                }

                override fun onStart(p0: SHARE_MEDIA?) {

                }

            })
            .open()

    }

    fun checkPermission() = RxPermissions(this).request(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.READ_LOGS,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.SET_DEBUG_APP,
        Manifest.permission.SYSTEM_ALERT_WINDOW,
        Manifest.permission.GET_ACCOUNTS,
        Manifest.permission.WRITE_APN_SETTINGS
    )
        .subscribe({
            if (!it) {
                toast("请获取权限后使用")
            }
        })
}
