package com.wd.health.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.bean.homepage.ZiBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.activity.ZsbdActivity;
import com.wd.health.adapter.homepageadapter.ZsbdAdapter;
import com.wd.health.adapter.homepageadapter.ZsbdAdapter1;
import com.wd.health.presenter.homepagepresenter.WzzxPresenter;
import com.wd.health.presenter.homepagepresenter.ZhuPresenter;
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
public class Fragment01 extends WDFragment {
    @BindView(R2.id.left_recycler)
    RecyclerView leftRecycler;
    @BindView(R2.id.right_recycler)
    RecyclerView rightRecycler;
    private WzzxPresenter wzzxPresenter;
    private  ZsbdAdapter zsbdAdapter;
    private ZiPresenter ziPresenter;
    private ZsbdAdapter1 zsbdAdapter1;
    private FragmentManager fmanager;
    private FragmentTransaction ftransaction;
  //  private Fragment01_1 fragment01_1;
    /*public void gotoDownloadFragment() {    //去下载页面
        fmanager = getActivity().getSupportFragmentManager();
        ftransaction = fmanager.beginTransaction();
        fragment01_1 = new Fragment01_1();
        ftransaction.replace(R.id.framelayout, fragment01_1);
        ftransaction.commit();
    }*/
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
        return R.layout.fragment01;
    }

    @Override
    protected void initView() {
        leftRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        zsbdAdapter = new ZsbdAdapter(getActivity());
        leftRecycler.setAdapter(zsbdAdapter);

        rightRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        zsbdAdapter1 = new ZsbdAdapter1(getActivity());
        rightRecycler.setAdapter(zsbdAdapter1);

        wzzxPresenter = new WzzxPresenter(new Wzzx1());
        wzzxPresenter.reqeust();

    }



    private class Wzzx1 implements DataCall<List<WzzxBean>> {
        @Override
        public void success(List<WzzxBean> data, Object... args) {
            zsbdAdapter.setList(data);
            zsbdAdapter.setOnRecyclerViewItemClickListener(new ZsbdAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                    zsbdAdapter.setThisPosition(position);
                    //嫑忘记刷新适配器
                    zsbdAdapter.notifyDataSetChanged();
                }
            });
            zsbdAdapter.notifyDataSetChanged();
            ziPresenter = new ZiPresenter(new ZiShow());
            ziPresenter.reqeust(data.get(0).getId());
            zsbdAdapter.getID(new ZsbdAdapter.CallBackID() {
                @Override
                public void callback(int id) {
                    ziPresenter = new ZiPresenter(new ZiShow());
                    ziPresenter.reqeust(id);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("aaaaaaaaaaa443", data.getDisplayMessage());
        }
    }

    private class ZiShow implements DataCall<List<ZiBean>> {
        @Override
        public void success(List<ZiBean> data, Object... args) {
            zsbdAdapter1.setList(data);
            zsbdAdapter1.getID1(new ZsbdAdapter1.CallBackID() {
                @Override
                public void callback(int id,String name) {
                    //zhuPresenter = new ZhuPresenter(new zhushow());
                    //zhuPresenter.reqeust(id);
                    //   start_layout.setVisibility(View.VISIBLE);
                    /*FragmentManager fm = getActivity().getFragmentManager();
                    fm.beginTransaction()
                            //替换为TwoFragment
                            .replace(R.layout.fragment_container,new TwoFragment())
                            .commit();*/
                  //  gotoDownloadFragment();
                    SharedPreferences id1 = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
                    id1.edit().putInt("idi",id).putString("name",name).commit();
                    Fragment01_1 fragment01_1 = new Fragment01_1();
                    startToFragment(getActivity(),R.id.framelayout,fragment01_1);
                }
            });
        }
        @Override
        public void fail(ApiException data, Object... args) {

        }
    }



}
