package com.xiaowu.myself.main.mvp.presenter

import android.content.Context
import com.ht.personagelzgx.net.mvp.model.event.MainModel
import com.xiaowu.myself.app.base.MyApplication
import com.xiaowu.myself.main.mvp.contract.MainContract
import com.xiaowu.myself.main.mvp.contract.ShoppingOnlineContract
import rx.subscriptions.CompositeSubscription

/**
 * Explanation:
 * @author LSX
 *    -----2018/1/22
 */

class ShoppingOnliePresenter : ShoppingOnlineContract.Presenter {

    var mContext: Context? = null
    var mView: MainContract.View? = null
    var mModel: MainModel? = null
    var time: String? = null
    var udid: String? = null
    var aid: String? = null

    //构造方法
    init {
        mModel = MainModel()
        udid = MyApplication.SP?.getString("udid", "")
        aid = MyApplication.SP?.getString("aid", "")
    }

    fun initView(context: Context, view: MainContract.View) {
        mView = view
        mContext = context
        mCompositeSubscription= CompositeSubscription()
    }

    override fun start() {
    }

    private var mCompositeSubscription: CompositeSubscription? = null

    fun unSubscription() {
        if (mCompositeSubscription!!.hasSubscriptions()) {
            mCompositeSubscription?.unsubscribe()
        }
    }


}