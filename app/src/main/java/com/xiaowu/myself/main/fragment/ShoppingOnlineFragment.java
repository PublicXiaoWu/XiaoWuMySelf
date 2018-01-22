package com.xiaowu.myself.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiaowu.myself.R;
import com.xiaowu.myself.app.base.BaseFragment;
import com.xiaowu.myself.main.adapter.ShoppingOnlineAdapter;
import com.xiaowu.myself.main.mvp.contract.ShoppingOnlineContract;

import org.jetbrains.annotations.NotNull;


/**
 * 网上购物
 *
 * @author LSX
 */
public class ShoppingOnlineFragment extends BaseFragment implements ShoppingOnlineContract.View {

    @Override
    public int provideContentViewId() {
        return R.layout.shopping_online_fragment;
    }

    @Override
    public void initView(@NotNull View rootView) {
    }

    @Override
    public void initData() {
        initRecycler();
    }

    private void initRecycler() {
        RecyclerView shopping_online_recycler = getView().findViewById(R.id.shopping_online_recycler);


        shopping_online_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),1,false));
        shopping_online_recycler.setAdapter(new ShoppingOnlineAdapter());
    }
}
