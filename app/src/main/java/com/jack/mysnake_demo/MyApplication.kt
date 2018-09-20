package com.jack.mysnake_demo

import android.app.Application
import android.util.Log
import anet.channel.util.ALog
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import kotlin.math.log

/**
 * Class for:
 * Created by   jack.马
 * Date: 2018/8/27
 * Time: 9:45
 */
class MyApplication : Application() {

    //单例化 伴生对象（Companion Object） 生成一个唯一实例
    companion object {
        private var instance: Application? = null
        fun instance() = instance!!
    }


    override fun onCreate() {
        super.onCreate()

        UMConfigure.init(this, "5b8356e2f43e48260300000e", "垃圾渠道", UMConfigure.DEVICE_TYPE_PHONE, "62af4902d9454e8ac6db101d4f65051a");
        val mPushAgent = PushAgent.getInstance(this)
        mPushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(token: String?) {
                Log.i("友盟token", token)
            }

            override fun onFailure(s1: String?, s2: String?) {
                Log.i("友盟token获取失败", s1 + ":" + s2)
            }

        })

    }
}