package com.xiaowu.myself.main.mvp.contract

import com.xiaowu.myself.app.base.BasePresenter
import com.xiaowu.myself.app.base.BaseView


 /**
 * Explanation: 
 * @author LSX
 *    -----2018/1/22
 */
interface ShoppingOnlineContract {
    interface Model {
    }

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {
    }
}