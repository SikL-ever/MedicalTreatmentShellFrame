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
import com.dingtao.common.util.DateUtils;
import com.wd.MyHome.R;
import com.wd.MyHome.presenter.MyCollectVideoDeletePresenter;
import com.wd.health.presenter.videopresenter.BuyVideoPresenter;
import com.wd.health.util.MyDialog;

import java.text.ParseException;
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
            holder.mycollectvideobuy.setVisibility(View.VISIBLE);//没有购买就显示图标
            holder.ifbuy.setVisibility(View.GONE);//是否一购买的图标
        }else{//购买的
            holder.mycollectvideo.setUp(videoBean.originalUrl,JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                    videoBean.title);
            //设置图片
            Glide.with(context).load(videoBean.originalUrl)
                    .into(holder.mycollectvideo.thumbImageView);
            holder.mycollectvideobuy.setVisibility(View.GONE);//没有购买就显示图标
            holder.ifbuy.setVisibility(View.VISIBLE);//显示出来
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
            }
        });
        //设置时间
        try {
            holder.mycollecttime.setText(DateUtils.dateTransformer(videoBean.createTime,DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //点击购买
        holder.mycollectvideobuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view=View.inflate(context, com.wd.health.R.layout.videodialong_item,null);
                final MyDialog dialog = new MyDialog(context, 200, 100, view, com.wd.health.R.style.dialog);
                dialog.show();
                final TextView cancel =
                        (TextView) view.findViewById(com.wd.health.R.id.cancel);
                final TextView confirm =
                        (TextView)view.findViewById(com.wd.health.R.id.confirm);
                final TextView text =
                        (TextView)view.findViewById(com.wd.health.R.id.textView10);
                text.setText("购买本视频将扣除"+videoBean.price+"H币");
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "购买取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //去购买本个视频
                        //请求接口
                        //改变状态值
                        videoBean.whetherBuy=1;
                        holder.buyVideoPresenter.reqeust(uid,sid,videoBean.videoId,videoBean.price);
                        dialog.dismiss();
                    }
                });

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public void add(List<VideoBean> data) {
        list.addAll(data);
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
        JZVideoPlayerStandard mycollectvideo;
        TextView mycocmycollectdelete,mycollecttime,mycollectbutnum;
        RelativeLayout collectgno,collectgyes;
        ImageView mycollectvideobuy,ifbuy;
        private BuyVideoPresenter buyVideoPresenter;
        public HolderView(@NonNull View itemView) {
            super(itemView);
            mycollectvideo=itemView.findViewById(R.id.mycollectvideo);
            mycollectbutnum=itemView.findViewById(R.id.mycollectbutnum);
            mycollecttime=itemView.findViewById(R.id.mycollecttime);
            mycocmycollectdelete=itemView.findViewById(R.id.mycocmycollectdelete);
            collectgno=itemView.findViewById(R.id.collectgno);
            collectgyes=itemView.findViewById(R.id.collectgyes);
            mycollectvideobuy=itemView.findViewById(R.id.mycollectvideobuy);
            ifbuy=itemView.findViewById(R.id.ifbuy);
            myCollectVideoDeletePresenter = new MyCollectVideoDeletePresenter(new getdata());
            buyVideoPresenter = new BuyVideoPresenter(new getbuyvideo());//购买的接口
        }
    }
    //删除视频返回回来的值
    class getdata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(context,"取消成功", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //购买返回回来的数据
    class getbuyvideo implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            notifyDataSetChanged();
            Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
}

