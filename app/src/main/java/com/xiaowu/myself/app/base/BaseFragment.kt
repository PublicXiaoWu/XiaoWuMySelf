package com.xiaowu.myself.app.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Administrator on 2018/1/17.
 */

abstract class BaseFragment : Fragment() {

    var mContext : Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(provideContentViewId(), container, false)
        initView(rootView!!)
        initSave(savedInstanceState)
        return rootView
    }


    fun initSave(savedInstanceState: Bundle?) {

    }
    abstract fun provideContentViewId(): Int

    abstract fun initView(rootView: View)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initListener()
    }

    open fun initListener() {}

    abstract fun initData()

}