package com.xiaowu.myself.main.fragment;

import android.view.View;
import android.widget.TextView;

import com.xiaowu.myself.R;
import com.xiaowu.myself.app.base.BaseFragment;

import org.jetbrains.annotations.NotNull;


/**
 * Explanation: 用户信息
 *
 * @author LSX
 *         -----2018/1/23
 */
public class UserFragment extends BaseFragment {

    @Override
    public int provideContentViewId() {
        return R.layout.user_fragment;
    }

    @Override
    public void initView(@NotNull View rootView) {
        TextView topTitle = rootView.findViewById(R.id.top_title);
        topTitle.setText("个人中心");
    }

    @Override
    public void initData() {
    }
}
