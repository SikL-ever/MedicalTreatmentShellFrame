package com.wd.health.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.ZhuBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.presenter.homepagepresenter.ZhuPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/18 11:48
 * @Description：描述信息
 */
public class Fragment01_1 extends WDFragment {
    @BindView(R2.id.item_bl)
    TextView itembl;
    @BindView(R2.id.item_zz)
    TextView itemzz;
    @BindView(R2.id.item_yyj)
    TextView itemyyj;
    @BindView(R2.id.item_xyzl)
    TextView itemxyzl;
    @BindView(R2.id.item_zyzl)
    TextView itemzyzl;
    @BindView(R2.id.b_name)
    TextView name;
    private ZhuPresenter zhuPresenter;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_zhu1;
    }

    @Override
    protected void initView() {
        SharedPreferences id1 = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        int idi = id1.getInt("idi", 0);
        String mz = id1.getString("name","");
        name.setText(mz);
        zhuPresenter = new ZhuPresenter(new ZhuShow());
        zhuPresenter.reqeust(idi);
    }

    private class ZhuShow implements DataCall<ZhuBean> {
        @Override
        public void success(ZhuBean data, Object... args) {
            itembl.setText(data.getPathology());
            itemzz.setText(data.getSymptom());
            itemyyj.setText(data.getBenefitTaboo());
            itemxyzl.setText(data.getWesternMedicineTreatment());
            itemzyzl.setText(data.getChineseMedicineTreatment());
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
