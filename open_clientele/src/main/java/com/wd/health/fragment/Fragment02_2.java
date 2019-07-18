package com.wd.health.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.YpxqBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.presenter.homepagepresenter.YpxqPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/18 11:48
 * @Description：描述信息
 */
public class Fragment02_2 extends WDFragment {
    @BindView(R2.id.b_name1)
    TextView bName;
    @BindView(R2.id.ypcf)
    TextView ypcf;
    @BindView(R2.id.yyjj)
    TextView yyjj;
    @BindView(R2.id.zzgn)
    TextView zzgn;
    @BindView(R2.id.yfyl)
    TextView yfyl;
    @BindView(R2.id.ypxz)
    TextView ypxz;
    @BindView(R2.id.bzgg)
    TextView bzgg;
    @BindView(R2.id.blfy)
    TextView blfy;
    @BindView(R2.id.zctj)
    TextView zctj;
    @BindView(R2.id.zysx)
    TextView zysx;
    @BindView(R2.id.pzwh)
    TextView pzwh;
    @BindView(R2.id.start_layout)
    LinearLayout startLayout;
    private YpxqPresenter ypxqPresenter;

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_zhu2;
    }

    @Override
    protected void initView() {
        SharedPreferences id1 = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        int idi = id1.getInt("idi", 0);
        ypxqPresenter = new YpxqPresenter(new Ypxq());
        ypxqPresenter.reqeust(idi);
    }

    private class Ypxq implements DataCall<YpxqBean> {
        @Override
        public void success(YpxqBean data, Object... args) {
            bName.setText(data.getName());
            ypcf.setText(data.getComponent());
            yyjj.setText(data.getTaboo());
            zzgn.setText(data.getEffect());
            yfyl.setText(data.getUsage());
            ypxz.setText(data.getShape());
            bzgg.setText(data.getPacking());
            blfy.setText(data.getSideEffects());
            zctj.setText(data.getStorage());
            zysx.setText(data.getMindMatter());
            pzwh.setText(data.getApprovalNumber());
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
