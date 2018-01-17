package com.xiaowu.myself.app.network

import android.app.Dialog
import android.content.Context
import com.xiaowu.myself.app.network.error_exception.ApiErrorHelper
import com.xiaowu.myself.utils.loadDialog

/**
 * 作者：Administrator on 2017/8/28 16:22
 * 邮箱：zhanghuaiha@gmail.com
 */
abstract class HttpObserver<T>(context: Context) : MySubscriber<T>(), OnRequestListener<T> {
    var loadDialog: Dialog? = null
    var contexts : Context?  = null

    init {
        contexts = context
        loadDialog = context.loadDialog("加载中", false)
        loadDialog?.show()
    }

    override fun onCompleted() {
        loadDialog?.dismiss()
    }

    override fun onNext(t: T) {
        success(t)
    }

    override fun onError(t: Throwable?) {
        loadDialog?.dismiss()
        ApiErrorHelper.handleCommonError(contexts!!,t!!)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun unsubscribe() {
        super.unsubscribe()
    }
}