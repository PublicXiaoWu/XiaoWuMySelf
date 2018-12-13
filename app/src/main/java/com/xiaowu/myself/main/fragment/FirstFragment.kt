package com.xiaowu.myself.main.fragment

import android.view.View
import com.xiaowu.myself.R
import com.xiaowu.myself.app.base.BaseFragment
import com.xiaowu.myself.main.adapter.Adapter
import kotlinx.android.synthetic.main.frist_fragment.*

/**
 *
 * @author LSX
 */
class FirstFragment : BaseFragment() {

    override fun provideContentViewId(): Int {
        return R.layout.frist_fragment
    }

    override fun initView(rootView: View) {
    }

    override fun initData() {
        recy.adapter = Adapter(context)
    }


}
