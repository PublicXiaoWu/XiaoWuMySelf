package com.xiaowu.myself.main.activity

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.design.widget.TabLayout
import android.view.View

import com.xiaowu.myself.R
import com.xiaowu.myself.app.base.BaseActivity
import com.xiaowu.myself.app.base.BaseFragment
import com.xiaowu.myself.main.adapter.ApplyAdapter
import com.xiaowu.myself.main.fragment.FirstFragment
import kotlinx.android.synthetic.main.activity_first.*

import java.io.File

class FirstActivity : BaseActivity() {
    var tbList: MutableList<BaseFragment>? = null

    override fun initView() {
        tbList = ArrayList()
        //1.MODE_SCROLLABLE模式
        newapply_tab.tabMode = TabLayout.MODE_SCROLLABLE
    }

    override fun provideContentViewId(): Int =R.layout.activity_first


    override fun initData() {

        tbList?.add(FirstFragment())
        newapply_vp.adapter = ApplyAdapter(supportFragmentManager, tbList)
        newapply_tab.setupWithViewPager(newapply_vp)
    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


    fun go(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        //        intent.putExtra(Intent.EXTRA_STREAM, "www.baidu.com");  //传输图片或者文件 采用流的方式
        intent.putExtra(Intent.EXTRA_TEXT, "分享分享微博")   //附带的说明信息
        intent.putExtra(Intent.EXTRA_SUBJECT, "标题")
        intent.type = "image/*"   //分享图片
        startActivity(Intent.createChooser(intent, "分享"))
    }

    companion object {

        fun share(context: Context, title: String, type: String, file: File) {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = type
                intent.putExtra(Intent.EXTRA_SUBJECT, title)
                intent.putExtra(Intent.EXTRA_PHONE_NUMBER, title)
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(Intent.createChooser(intent, title))
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }

        }
    }
}
