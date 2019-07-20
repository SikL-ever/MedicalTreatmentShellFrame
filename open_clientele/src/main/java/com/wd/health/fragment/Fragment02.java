package com.wd.health.fragment;

import android.content.Context;
import android.content.SharedPreferences;

import com.dingtao.common.bean.homepage.CjypBean;
import com.dingtao.common.bean.homepage.CjypBean1;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.CjypAdapter;
import com.wd.health.adapter.homepageadapter.CjypAdapter2;
import com.wd.health.presenter.homepagepresenter.Cjyp1Presenter;
import com.wd.health.presenter.homepagepresenter.CjypPresenter;
import com.wd.health.presenter.homepagepresenter.ZiPresenter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/18 9:09
 * @Description：描述信息
 */
public class Fragment02 extends WDFragment {
    @BindView(R2.id.left_recycler1)
    RecyclerView leftRecycler;
    @BindView(R2.id.right_recycler1)
    RecyclerView rightRecycler;
    private CjypPresenter cjypPresenter;
    private CjypAdapter cjypAdapter;
    private Cjyp1Presenter cjyp1Presenter;
    private CjypAdapter2 cjypAdapter2;
    private void startToFragment(Context context, int container, Fragment newFragment) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(container, newFragment);
        transaction.addToBackStack(context.getClass().getName());
        transaction.commit();
    }
    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment02;
    }

    @Override
    protected void initView() {
        leftRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        cjypAdapter = new CjypAdapter(getActivity());
        leftRecycler.setAdapter(cjypAdapter);
        cjypPresenter = new CjypPresenter(new CjypShow());
        cjypPresenter.reqeust();

        rightRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        cjypAdapter2 = new CjypAdapter2(getActivity());
        rightRecycler.setAdapter(cjypAdapter2);
    }

    private class CjypShow implements DataCall<List<CjypBean>> {
        @Override
        public void success(List<CjypBean> data, Object... args) {
            cjypAdapter.setList(data);
            cjypAdapter.setOnRecyclerViewItemClickListener1(new CjypAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                    cjypAdapter.setThisPosition(position);
                    //嫑忘记刷新适配器
                    cjypAdapter.notifyDataSetChanged();
                }
            });
            cjypAdapter.notifyDataSetChanged();
            cjyp1Presenter = new Cjyp1Presenter(new Fragment02.CypShow());
            cjyp1Presenter.reqeust(data.get(0).getId(),1,20);
            cjypAdapter.getID1(new CjypAdapter.CallBackID() {
                @Override
                public void callback1(int id) {
                    cjyp1Presenter = new Cjyp1Presenter(new Fragment02.CypShow());
                    cjyp1Presenter.reqeust(id,1,20);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class CypShow implements DataCall<List<CjypBean1>> {
        @Override
        public void success(List<CjypBean1> data, Object... args) {
            cjypAdapter2.setList(data);
            cjypAdapter2.getID2(new CjypAdapter2.CallBackID() {
                @Override
                public void callback2(int id) {
                    SharedPreferences id1 = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
                    id1.edit().putInt("idi",id).commit();
                    Fragment02_2 fragment02_2 = new Fragment02_2();
                    startToFragment(getActivity(),R.id.framelayout,fragment02_2);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
