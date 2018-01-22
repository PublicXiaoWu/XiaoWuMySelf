package com.xiaowu.myself.main.mvp.contract

import com.xiaowu.myself.app.base.BasePresenter
import com.xiaowu.myself.app.base.BaseView

/**
 * 作者：Administrator on 2017/9/7 16:06
 * 邮箱：zhanghuaiha@gmail.com
 */

interface MainContract {
    interface Model {
    }

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {
    }
}