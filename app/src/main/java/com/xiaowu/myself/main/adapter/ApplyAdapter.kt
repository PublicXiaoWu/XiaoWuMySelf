package com.xiaowu.myself.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.xiaowu.myself.app.base.BaseFragment
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
                "测试位"
            }
            1 -> {
                "测试位"
            }
            else -> {
                "成品位"
            }

        }
    }


}