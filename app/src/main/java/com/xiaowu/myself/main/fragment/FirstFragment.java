package com.xiaowu.myself.main.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

import com.xiaowu.myself.R;
import com.xiaowu.myself.app.base.BaseFragment;
import com.xiaowu.myself.app.network.BaseResponseEntity;
import com.xiaowu.myself.app.network.HttpObserver;
import com.xiaowu.myself.app.network.NetWorkCodeInfo;
import com.xiaowu.myself.app.network.error_exception.RetrofitClientcheshi;
import com.xiaowu.myself.main.bean.ApplyPage;
import com.xiaowu.myself.utils.ObtainEncrys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author LSX
 */
public class FirstFragment extends BaseFragment {
    TextView TV_show;

    @Override
    public int provideContentViewId() {
        return R.layout.frist_fragment;
    }

    @Override
    public void initView(@NotNull View rootView) {
        TV_show = rootView.findViewById(R.id.textView);
    }

    @Override
    public void initData() {
        netTest();
    }

    private void netTest() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        String time = String.valueOf(System.currentTimeMillis());
        String versionName = getPackageInfo(getActivity()).versionName;
        String udid = "";
        String aid = "";
        map.put("rf", "2");
        map.put("ts", time);


        map.put("v", versionName);
        map.put("i", NetWorkCodeInfo.APPLY_PAGE_V2);
        map.put("udid", udid);

        map.put("aid", aid);

        map.put("do_status", "8");
        map.put("page_size", "20");
        map.put("page_index", "1");
        String encry = ObtainEncrys.Companion.getEncry(map);
        map.put("encry", encry);
        RetrofitClientcheshi.Companion.getInstance(getActivity(), "http://106.15.93.115")
                .getMApi()
                .getApplyPageV2(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResponseEntity<List<ApplyPage>>>(getActivity()) {
                    @Override
                    public void success(BaseResponseEntity<List<ApplyPage>> listBaseResponseEntity) {
                        List<ApplyPage> data = listBaseResponseEntity.data;
                        TV_show.setText("数据请求成功，数据个数为：" + data.size());
                    }

                    @Override
                    public void onError(@Nullable Throwable t) {
                        super.onError(t);
                        TV_show.setText("数据请求失败，原因为：" + t.toString());
                    }
                });

    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}
