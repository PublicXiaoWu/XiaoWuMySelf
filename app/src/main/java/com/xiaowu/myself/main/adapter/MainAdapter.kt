package com.xiaowu.myself.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.xiaowu.myself.app.base.BaseFragment


/**
 * Explanation：
 * @author LSX
 * Created on 2018/1/22.
 */

class MainAdapter (fm: FragmentManager, private val mFragments: List<BaseFragment>?) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

    override fun getCount(): Int {
        return mFragments?.size?:0
    }

    override fun getPageTitle(position: Int): CharSequence {
        return super.getPageTitle(position)
    }
}