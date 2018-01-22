package com.xiaowu.myself.app.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = this
        //初始化通用的SP&EDIT
        SP = getSharedPreferences("config", Context.MODE_PRIVATE)
        EDIT = SP?.edit()
    }

    companion object {
        var mContext: Context? = null
        /**
         * 初始化SP&EDIT
         */
        var SP: SharedPreferences? = null
        var EDIT: SharedPreferences.Editor? = null
    }
}
