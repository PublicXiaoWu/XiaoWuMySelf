package com.xiaowu.myself.main.activity

import android.content.Context
import android.view.View
import com.xiaowu.myself.R
import com.xiaowu.myself.app.base.BaseActivity
import com.xiaowu.myself.app.base.BaseFragment
import com.xiaowu.myself.main.adapter.MainAdapter
import com.xiaowu.myself.main.fragment.FragmentFactory
import com.xiaowu.myself.main.mvp.contract.MainContract
import com.xiaowu.myself.main.mvp.presenter.MainPresenter
import kotlinx.android.synthetic.main.main_activty.*
import java.util.*


/**
 * Explanation：
 *
 * @author LSX
 * Created on 2018/1/22.
 */
class MainActivity : BaseActivity(), MainContract.View {

    var mContext: Context? = null
    var mPresenter: MainPresenter? = null
    var tbList: MutableList<BaseFragment>? = null


    override fun provideContentViewId(): Int {
        return R.layout.main_activty
    }


    override fun initView() {
        tbList = ArrayList()
        mPresenter = MainPresenter()
        mPresenter?.initView(this, this)
    }

    override fun initData() {
        tbList?.add(FragmentFactory.getInstance(this).getHomeFragment())
        tbList?.add(FragmentFactory.getInstance(this).getTwoFragment())
        tbList?.add(FragmentFactory.getInstance(this).getThreeFragment())
        main_vp.adapter = MainAdapter(supportFragmentManager, tbList)
    }

    override fun initListener() {
        main_icon.setOnClickListener({
            main_vp.currentItem = 0
        })

        main_shop.setOnClickListener({
            main_vp.currentItem = 1

        })

        main_text.setOnClickListener({
            main_vp.currentItem = 2
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.unSubscription()
    }
}
