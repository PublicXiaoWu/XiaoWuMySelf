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
    private var userFragment: UserFragment? = null
    private var threefragment: ShoppingOnlineFragment? = null

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
    fun getTwoFragment(): UserFragment {
        if (userFragment == null) {
            synchronized(FirstFragment::class) {
                if (userFragment == null) {
                    userFragment = UserFragment()
                }
            }
        }
        return userFragment!!
    }  /**
     * 首页
     */
    fun getThreeFragment(): ShoppingOnlineFragment {
        if (threefragment == null) {
            synchronized(FirstFragment::class) {
                if (threefragment == null) {
                    threefragment = ShoppingOnlineFragment()
                }
            }
        }
        return threefragment!!
    }


}