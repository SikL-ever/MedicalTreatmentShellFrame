package com.wd.health.adapter.homepageadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.WzysBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.util.Constant;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.activity.WzzxActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/25 9:52
 * @Description：描述信息
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;
    private  List<WzysBean> dataCall;
    public ImageAdapter(WzzxActivity wzzxActivity, List<WzysBean> dataCall) {
        this.context = wzzxActivity;
        this.dataCall = dataCall;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.image_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoundedCorners roundedCorners= new RoundedCorners(4);
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(240, 240);
        if(dataCall.get(position).getImagePic()==null){
            Glide.with(context).load(R.drawable.system_image7).apply(options).into(holder.imageView);
        }else{
            Glide.with(context).load(dataCall.get(position).getImagePic()).apply(options).into(holder.imageView);
        }
        holder.textView1.setText(dataCall.get(position).getDoctorName());
        holder.textView1.setBackgroundColor(dataCall.get(position).getTextcolor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < dataCall.size(); i++) {
                    dataCall.get(i).setTextcolor(0xff999999);
                    holder.textView1.setBackgroundColor(dataCall.get(i).getTextcolor());
                }
                dataCall.get(position).setTextcolor(0xff3087ea);
                holder.textView1.setBackgroundColor(dataCall.get(position).getTextcolor());
                huIdiaoId.huidiaoId(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataCall.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.yszp);
            textView1 = itemView.findViewById(R.id.ysname);
        }
    }

    private HUIdiaoId huIdiaoId;

    public void getKjens(HUIdiaoId callBackj) {
        this.huIdiaoId = callBackj;
    }

    public interface HUIdiaoId{
        void huidiaoId(int position);
    }

}
