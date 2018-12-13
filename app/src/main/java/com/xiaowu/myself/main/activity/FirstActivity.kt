package com.xiaowu.myself.main.activity

import android.app.Activity
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.Intent
import android.support.design.widget.TabLayout
import com.xiaowu.myself.R
import com.xiaowu.myself.app.base.BaseActivity
import com.xiaowu.myself.app.base.BaseFragment
import com.xiaowu.myself.main.adapter.ApplyAdapter
import com.xiaowu.myself.main.fragment.FirstFragment
import com.xiaowu.myself.main.fragment.ThreeFragment
import com.xiaowu.myself.main.fragment.TwoFragment
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : BaseActivity() {
    var tbList: MutableList<BaseFragment>? = null

    override fun initView() {
        tbList = ArrayList()
        //1.MODE_SCROLLABLE模式
        newapply_tab.tabMode = TabLayout.MODE_SCROLLABLE
    }

    override fun provideContentViewId(): Int = R.layout.activity_first


    override fun initData() {

        tbList?.add(FirstFragment())
        tbList?.add(TwoFragment())
        tbList?.add(ThreeFragment())
        newapply_vp.adapter = ApplyAdapter(supportFragmentManager, tbList)
        newapply_tab.setupWithViewPager(newapply_vp)
    }


    //Activity切换
    inline fun <reified T : Activity> Activity.newIntent() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}
