package com.wd.MyHome.adapter;

import android.content.Context;
import android.hardware.SensorManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.wardBean.MyVideo;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.DateUtils;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.presenter.DeletePresenter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

import static android.content.Context.SENSOR_SERVICE;

public class MyVideoAdapater extends RecyclerView.Adapter<MyVideoAdapater.MYVIEWhOLDER> {
    private Context context;
    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener jzAutoFullscreenListener;
    private List<MyVideo> list=new ArrayList<>();
    public MyVideoAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyVideoAdapater.MYVIEWhOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.myvideoitem,null);
        MYVIEWhOLDER myvieWhOLDER=new MYVIEWhOLDER(view);
        return myvieWhOLDER;
    }

    @Override
    public void onBindViewHolder(@NonNull MyVideoAdapater.MYVIEWhOLDER holder, final int position) {


        holder.headline.setText(list.get(position).getTitle());
        //去除黑边
        holder.jiaozi.setVideoImageDisplayType(holder.jiaozi.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        //横竖屏切换
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        jzAutoFullscreenListener = new JZVideoPlayer.JZAutoFullscreenListener();

        holder.jiaozi.setUp(list.get(position).getOriginalUrl(),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
        holder.before_time.setText(list.get(position).getCreateTime()+"");
        try {
            holder.before_time.setText(DateUtils.dateTransformer(list.get(position).getCreateTime(),DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePresenter deletePresenter=new DeletePresenter(new delete());
                LoginDaoUtil loginDaoUtil=new LoginDaoUtil();
                List<String> intt = loginDaoUtil.intt(context);
                deletePresenter.reqeust(intt.get(0),intt.get(1),list.get(position).getVideoId());

            }
        });


    }
    class delete implements DataCall{
        @Override
        public void success(Object data, Object... args) {

            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setVideo(List<MyVideo> data) {
        if(data!=null){
            list.addAll(data);
        }
         notifyDataSetChanged();
    }

    public class MYVIEWhOLDER extends RecyclerView.ViewHolder {

        TextView delete;
        JZVideoPlayerStandard jiaozi;
        TextView before_time;
        TextView headline;

        public MYVIEWhOLDER(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.delete);
            jiaozi = itemView.findViewById(R.id.jiaozivideo);
            before_time = itemView.findViewById(R.id.before_time);
            headline = itemView.findViewById(R.id.headline);
        }
    }
}
