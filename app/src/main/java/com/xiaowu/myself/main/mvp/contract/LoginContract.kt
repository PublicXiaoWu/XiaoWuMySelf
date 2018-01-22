package com.xiaowu.myself.main.mvp.contract

import android.content.Context
import com.xiaowu.myself.app.base.BasePresenter
import com.xiaowu.myself.app.base.BaseView
import com.xiaowu.myself.app.network.BaseResponseEntity
import com.xiaowu.myself.main.bean.Login
import rx.Observable

/**
 * 作者：Administrator on 2017/9/7 16:06
 * 邮箱：zhanghuaiha@gmail.com
 */

interface LoginContract {
    interface Model{
        fun send(context: Context,map: Map<String,String>) : Observable<BaseResponseEntity<String>>?
        fun login(context: Context,map: Map<String,String>) : Observable<BaseResponseEntity<Login>>?
    }

    interface View : BaseView<Presenter> {
        fun getSms(responseEntity: BaseResponseEntity<String>)
        fun login(responseEntity: BaseResponseEntity<Login>)

    }

    interface Presenter : BasePresenter {
        fun requestData(mobile : String)
        fun login(code : String,icode:String)
    }
}