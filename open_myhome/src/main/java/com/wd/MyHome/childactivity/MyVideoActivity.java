package com.wd.MyHome.childactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dingtao.common.bean.wardBean.MyVideo;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyVideoAdapater;
import com.wd.MyHome.presenter.MyVideoPresenter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class MyVideoActivity extends AppCompatActivity {

    @BindView(R2.id.video_back)
    ImageView video_back;
    @BindView(R2.id.viedo_recycler)
    RecyclerView viedoRecycler;
    private MyVideoPresenter myVideoPresenter;
    private MyVideoAdapater myVideoAdapater;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_video);
        ButterKnife.bind(this);
        //返回

        //视频列表


        //获取数据
        myVideoPresenter = new MyVideoPresenter(new video());
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MyVideoActivity.this);
        myVideoPresenter.reqeust(intt.get(0), intt.get(1), 1, 5);
        //设置方向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyVideoActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //创建适配器
        myVideoAdapater = new MyVideoAdapater(this);
        viedoRecycler.setLayoutManager(linearLayoutManager);
        //设置适配器
        viedoRecycler.setAdapter(myVideoAdapater);

        video_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class video implements DataCall<List<MyVideo>> {
        @Override
        public void success(List<MyVideo> data, Object... args) {
            Log.i("ff",data.toString());
            myVideoAdapater.setVideo(data);
            myVideoAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
