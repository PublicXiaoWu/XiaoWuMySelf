package com.xiaowu.myself.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.xiaowu.myself.app.base.BaseFragment

/**
 */
class ApplyAdapter(fm: FragmentManager, private val mFragments: List<BaseFragment>?) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

    override fun getCount(): Int {
        return mFragments?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> {
                "首页"
            }
            1 -> {
                "优选"
            }
            2 -> {
                "订单"
            }
            3 -> {
                "收益"
            }
            else -> {
                "我的"
            }

        }
    }


}