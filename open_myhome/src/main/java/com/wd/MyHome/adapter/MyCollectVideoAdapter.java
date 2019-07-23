package com.wd.MyHome.adapter;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.wd.MyHome.R;
import com.wd.MyHome.presenter.MyCollectVideoDeletePresenter;
import com.wd.MyHome.util.MyVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

import static android.content.Context.SENSOR_SERVICE;

/**
 * 佀常勇
 *
 * @Data:2019/7/20 16:38
 * 描述：
 */
public class MyCollectVideoAdapter extends RecyclerView.Adapter<MyCollectVideoAdapter.HolderView>{
    private Context context;
    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener jzAutoFullscreenListener;

    public MyCollectVideoAdapter(Context context) {
        this.context = context;
    }
    List<VideoBean> list=new ArrayList<>();
    private String uid=null;
    private String sid=null;
    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.mycolervideo_item, null);
        return new HolderView(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderView holder, final int position) {
        final VideoBean videoBean = list.get(position);
        //去除黑边
        holder.mycollectvideo.setVideoImageDisplayType(holder.mycollectvideo.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        holder.mycollectvideo.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片为全屏
        //横竖屏切换
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        jzAutoFullscreenListener = new JZVideoPlayer.JZAutoFullscreenListener();


        if (videoBean.whetherBuy == 2) {//没有购买的
            holder.mycollectvideo.setUp(videoBean.shearUrl,JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                    videoBean.title);
            //设置图片
            Glide.with(context).load(videoBean.shearUrl)
                    .into(holder.mycollectvideo.thumbImageView);
        }else{//购买的
            holder.mycollectvideo.setUp(videoBean.originalUrl,JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                    videoBean.title);
            //设置图片
            Glide.with(context).load(videoBean.originalUrl)
                    .into(holder.mycollectvideo.thumbImageView);
        }
        holder.mycollectbutnum.setText(videoBean.buyNum+"人以购买");//购买人数
        //取消收藏的
        holder.mycocmycollectdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂定视频
                holder.mycollectvideo.releaseAllVideos();
                //判断是否正在播放，如果正在播放，进行弹框。
                holder.myCollectVideoDeletePresenter.reqeust(uid,sid,videoBean.videoId);
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void add(List<VideoBean> data) {
        if (data != null){
            list.addAll(data);
        }
    }

    public void adduid(String uid) {
        if (uid != null) {
            this.uid=uid;
        }
    }

    public void addsid(String sid) {
        if (sid != null) {
            this.sid=sid;
        }
    }

    public void clear() {
        list.clear();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        private final MyCollectVideoDeletePresenter myCollectVideoDeletePresenter;
        MyVideoPlayer mycollectvideo;
        TextView mycocmycollectdelete,mycollecttime,mycollectbutnum;
        public HolderView(@NonNull View itemView) {
            super(itemView);
            mycollectvideo=itemView.findViewById(R.id.mycollectvideo);
            mycollectbutnum=itemView.findViewById(R.id.mycollectbutnum);
            mycollecttime=itemView.findViewById(R.id.mycollecttime);
            mycocmycollectdelete=itemView.findViewById(R.id.mycocmycollectdelete);
            myCollectVideoDeletePresenter = new MyCollectVideoDeletePresenter(new getdata());
        }
    }
    //删除视频返回回来的值
    class getdata implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
}
