package com.wd.MyHome.childactivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dingtao.common.bean.MyUser.MyCollectVideoBean;
import com.dingtao.common.bean.MyUser.MyConsultBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.bean.wardBean.WardLieBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyCollectBIngAdapter;
import com.wd.MyHome.adapter.MyCollectVideoAdapter;
import com.wd.MyHome.adapter.MyCollectconsultAdapter;
import com.wd.MyHome.presenter.MyCollectBingPresenter;
import com.wd.MyHome.presenter.MyCollectConsultPresenter;
import com.wd.MyHome.presenter.MyCollectVideoPresenter;
import com.wd.MyHome.util.TopView;
import com.wd.health.fragment.VideoFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;

public class MyUserCollectActivity extends WDActivity {

    @BindView(R2.id.mycollecttop)
    TopView mycollecttop;
    @BindView(R2.id.textrecycler)
    RecyclerView textrecycler;
    @BindView(R2.id.mybt1)
    RadioButton myt1;
    @BindView(R2.id.mybt2)
    RadioButton mybt2;
    @BindView(R2.id.mybt3)
    RadioButton mybt3;
    @BindView(R2.id.mycollectradio)
    RadioGroup mycollectradio;
    @BindView(R2.id.collectgone)
    RelativeLayout collectgone;
    private String uid = null;
    private String sid = null;
    private MyCollectConsultPresenter myCollectConsultPresenter;
    private MyCollectBingPresenter myCollectBingPresenter;
    private MyCollectVideoPresenter myCollectVideoPresenter;
    private int zhuangid;//状态值
    private int page = 1;
    private MyCollectVideoAdapter myCollectVideoAdapter;//视频的适配器
    private MyCollectconsultAdapter myCollectconsultAdapter;//咨询适配器
    private MyCollectBIngAdapter myCollectBIngAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_collect;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        //
        mycollecttop.setTitle("我的收藏");
        //创建p层
        myCollectConsultPresenter = new MyCollectConsultPresenter(new getconsult());
        myCollectBingPresenter = new MyCollectBingPresenter(new getbing());
        myCollectVideoPresenter = new MyCollectVideoPresenter(new getmyvideo());
        //创建适配器视频的适配器
        myCollectVideoAdapter = new MyCollectVideoAdapter(this);
        //创建咨询的适配器
        myCollectconsultAdapter = new MyCollectconsultAdapter(this);
        //创建病友圈的适配器
        myCollectBIngAdapter = new MyCollectBIngAdapter(this);


        myt1.setTextColor(R.color.top);
        mycollectradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.mybt1) {
                    myCollectConsultPresenter.reqeust(uid, sid, page, 10);
                    myt1.setTextColor(R.color.top);
                    mybt2.setTextColor(0xff000000);
                    mybt3.setTextColor(0xff000000);
                } else if (checkedId == R.id.mybt2) {
                    myCollectVideoPresenter.reqeust(uid, sid, page, 10);
                    myt1.setTextColor(0xff000000);
                    mybt2.setTextColor(R.color.top);
                    mybt3.setTextColor(0xff000000);
                } else if (checkedId == R.id.mybt3) {
                    myCollectBingPresenter.reqeust(uid, sid, page, 10);
                    myt1.setTextColor(0xff000000);
                    mybt2.setTextColor(0xff000000);
                    mybt3.setTextColor(R.color.top);

                }
            }
        });

        textrecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dx<0){
                    Log.i("ccc", "onScrolled: "+dx);
                }
                if (dx>0){
                    Log.i("ccc", "onScrolled: "+dx);
                }
            }
        });
    }
    //咨询
    class getconsult implements DataCall<List<MyConsultBean>> {
        @Override
        public void success(List<MyConsultBean> data, Object... args) {
            //创建 适配器//已经请求道接口，但是没有数据
            Log.i("aaa", "咨询: " + data);
            if (data.size()==0) {
                collectgone.setVisibility(View.VISIBLE);
                textrecycler.setVisibility(View.GONE);
            }else{
                collectgone.setVisibility(View.GONE);
                textrecycler.setVisibility(View.VISIBLE);
                //进行数据展示
                myCollectconsultAdapter.cleat();
                myCollectconsultAdapter.setList(data);
                textrecycler.setAdapter(myCollectconsultAdapter);
                textrecycler.setLayoutManager
                        (new LinearLayoutManager
                                (MyUserCollectActivity.this, LinearLayoutManager.VERTICAL, false));
            }
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //病友圈
    class getbing implements DataCall<List<WardLieBean>> {
        @Override
        public void success(List<WardLieBean> data, Object... args) {
            //创建适配器
            if (data.size()==0) {
                collectgone.setVisibility(View.VISIBLE);
                textrecycler.setVisibility(View.GONE);
            }else{
                collectgone.setVisibility(View.GONE);
                textrecycler.setVisibility(View.VISIBLE);
                //
                myCollectBIngAdapter.clear();
                myCollectBIngAdapter.add(data);
                //设置适配器
                textrecycler.setAdapter(myCollectBIngAdapter);
                textrecycler.setLayoutManager
                        (new LinearLayoutManager
                                (MyUserCollectActivity.this, LinearLayoutManager.VERTICAL, false));
            }
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //我的视频
    class getmyvideo implements DataCall<List<MyCollectVideoBean>> {
        @Override
        public void success(List<MyCollectVideoBean> data, Object... args) {
            if (data.size()==0) {
                collectgone.setVisibility(View.VISIBLE);
                textrecycler.setVisibility(View.GONE);
            }else{
                collectgone.setVisibility(View.GONE);
                textrecycler.setVisibility(View.VISIBLE);
                //创建适配器
                myCollectVideoAdapter.clear();
                myCollectVideoAdapter.add(data);
                //把UID，su穿进去
                myCollectVideoAdapter.adduid(uid);
                myCollectVideoAdapter.addsid(sid);
                textrecycler.setAdapter(myCollectVideoAdapter);
                textrecycler.setLayoutManager
                        (new LinearLayoutManager
                                (MyUserCollectActivity.this, LinearLayoutManager.VERTICAL, false));
            }
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MyUserCollectActivity.this);
        myCollectConsultPresenter.reqeust(intt.get(0), intt.get(1), page, 10);
        uid = intt.get(0);
        sid = intt.get(1);
    }

    @Override
    protected void destoryData() {

    }

}
