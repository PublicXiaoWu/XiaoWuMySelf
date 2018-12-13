package com.xiaowu.myself.main.fragment

import android.content.Context

/**
 * Explanation：
 * @author LSX
 * Created on 2018/1/22.
 */

class FragmentFactory private constructor(context: Context) {

    companion object {
        var instance: FragmentFactory? = null
        fun getInstance(context: Context): FragmentFactory {
            if (instance == null) {
                synchronized(FragmentFactory::class) {
                    if (instance == null) {
                        instance = FragmentFactory(context)
                    }
                }
            }
            return instance!!
        }
    }

    private var firstFragment: FirstFragment? = null
    private var twoFragment: TwoFragment? = null
    private var mThreefragment: ThreeFragment? = null

    /**
     * 首页
     */
    fun getHomeFragment(): FirstFragment {
        if (firstFragment == null) {
            synchronized(FirstFragment::class) {
                if (firstFragment == null) {
                    firstFragment = FirstFragment()
                }
            }
        }
        return firstFragment!!
    }  /**
     * 首页
     */
    fun getTwoFragment(): TwoFragment {
        if (twoFragment == null) {
            synchronized(FirstFragment::class) {
                if (twoFragment == null) {
                    twoFragment = TwoFragment()
                }
            }
        }
        return twoFragment!!
    }  /**
     * 首页
     */
    fun getThreeFragment(): ThreeFragment {
        if (mThreefragment == null) {
            synchronized(FirstFragment::class) {
                if (mThreefragment == null) {
                    mThreefragment = ThreeFragment()
                }
            }
        }
        return mThreefragment!!
    }


}