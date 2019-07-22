package com.wd.health.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dingtao.common.bean.wardBean.TabBean;
import com.dingtao.common.bean.wardBean.WardLieBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.activity.wardActivity.SeachActivity;
import com.wd.health.adapter.wardmateadapter.MyTabAdapater;
import com.wd.health.adapter.wardmateadapter.WardAdapater;
import com.wd.health.presenter.wardmatepresenter.TabPresenter;
import com.wd.health.presenter.wardmatepresenter.WardPresenter;

import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 15:18
 * 描述：
 */
public class WardmateFragment extends WDFragment {

    @BindView(R2.id.head)
    ImageView head;
    @BindView(R2.id.bull)
    ImageView bull;
    @BindView(R2.id.recycler)
    RecyclerView recycler;
    @BindView(R2.id.seach)
    ImageView seach;
    @BindView(R2.id.xrecyclerview)
    RecyclerView xrecyclerview;

    @BindView(R2.id.layout)
    RelativeLayout layout;
    @BindView(R2.id.scrollview)
    NestedScrollView scrollview;
    @BindView(R2.id.relativelayout)
    RelativeLayout relativelayout;
    private TabPresenter tabPresenter;
    private MyTabAdapater myTabAdapater;
    private WardPresenter wardPresenter;
    private WardAdapater wardAdapater;
    private int departmentId = 8;
    private int id1;



    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.wardmate;
    }

    @SuppressLint({"WrongConstant", "NewApi"})
    @Override
    protected void initView() {

        //科室
        tabPresenter = new TabPresenter(new tab());
        tabPresenter.reqeust();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //创建适配器
        myTabAdapater = new MyTabAdapater(getActivity());
        //设置布局
        recycler.setLayoutManager(linearLayoutManager);
        //设置适配器
        recycler.setAdapter(myTabAdapater);


        //列表详情
        wardPresenter = new WardPresenter(new ward());
        wardPresenter.reqeust(7, 1, 10);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        wardAdapater = new WardAdapater(getActivity());
        xrecyclerview.setLayoutManager(linearLayoutManager1);
        xrecyclerview.setAdapter(wardAdapater);
        myTabAdapater.setCall(new MyTabAdapater.Call() {

            @Override
            public void setCall(int id) {
                Log.i("aaa", "setCall: " + id);
                wardPresenter.reqeust(id, 1, 10);
            }
        });
        //搜索
        seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeachActivity.class);
                getContext().startActivity(intent);
            }
        });

        //滑动监听
        scrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    //  Log.e("=====", "下滑");
                    Rect scrollBounds = new Rect();
                    scrollview.getHitRect(scrollBounds);
                    if (xrecyclerview.getLocalVisibleRect(scrollBounds)) {
                        //       Log.e("but1", "显示");
                        layout.setVisibility(View.VISIBLE);
                        relativelayout.setVisibility(View.INVISIBLE);
                    } else {
                        //       Log.e("but1", "隐藏");
                        layout.setVisibility(View.INVISIBLE);
                        relativelayout.setVisibility(View.VISIBLE);
                    }
                }
                if (scrollY < oldScrollY) {
                    //    Log.e("=====", "上滑");
                    Rect scrollBounds = new Rect();
                    scrollview.getHitRect(scrollBounds);
                    if (xrecyclerview.getLocalVisibleRect(scrollBounds)) {
                        // Log.e("11111", "显示");
                        layout.setVisibility(View.INVISIBLE);
                        relativelayout.setVisibility(View.VISIBLE);
                    } else {
                        layout.setVisibility(View.VISIBLE);
                        relativelayout.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    //科室
    class tab implements DataCall<List<TabBean>> {
        @Override
        public void success(List<TabBean> data, Object... args) {
            //把数据给适配器丢过去
            for (int i = 0; i < data.size(); i++) {
                data.get(i).textColor = Color.BLACK;
            }
            data.get(0).textColor = Color.parseColor("#3087ea");
            myTabAdapater.setList(data);
            myTabAdapater.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //列表详情
    class ward implements DataCall<List<WardLieBean>> {

        @Override
        public void success(List<WardLieBean> datas, Object... args) {
            Log.i("aaa", "success: " + datas);
            wardAdapater.destroy();
            wardAdapater.setLIST(datas);
            wardAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
