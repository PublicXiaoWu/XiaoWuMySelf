package com.xiaowu.myself.main.fragment

import android.view.View
import com.xiaowu.myself.R
import com.xiaowu.myself.app.base.BaseFragment
import com.xiaowu.myself.app.network.ApiService
import com.xiaowu.myself.app.network.BaseResponseEntity
import com.xiaowu.myself.app.network.HttpObserver
import com.xiaowu.myself.app.network.RetrofitClient
import com.xiaowu.myself.main.bean.Login
import kotlinx.android.synthetic.main.two_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 *
 * @author LSX
 */
class TwoFragment : BaseFragment() {

    override fun provideContentViewId(): Int {
        return R.layout.two_fragment
    }

    override fun initView(rootView: View) {
    }

    override fun initData() {
        bt_one.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        var map = LinkedHashMap<String, String>()
        map["userName"] = "xian2"
        map["userPassword"] = "xian2"
        RetrofitClient.Companion.getInstance(context, ApiService.BASE_URL).mApi
                ?.login(map)
                ?.subscribeOn(Schedulers.io())
                ?.unsubscribeOn(AndroidSchedulers.mainThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : HttpObserver<BaseResponseEntity<Login>>(mContext!!) {
                    override fun success(t: BaseResponseEntity<Login>) {
                        if (t.status == 200) tv_show.text = t.toString()
                    }
                }
                )
    }

}
