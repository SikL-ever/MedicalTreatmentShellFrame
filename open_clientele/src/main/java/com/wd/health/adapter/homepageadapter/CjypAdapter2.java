package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.homepage.CjypBean;
import com.dingtao.common.bean.homepage.CjypBean1;
import com.wd.health.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/18 10:24
 * @Description：描述信息
 */
public class CjypAdapter2 extends RecyclerView.Adapter<CjypAdapter2.ViewHolder> {
    private Context context;
    private List<CjypBean1> wzzxBeans = new ArrayList<>();

    public CjypAdapter2(FragmentActivity zsbdActivity) {
        this.context = zsbdActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_right1, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i("ssssssss1", wzzxBeans.get(position).getName());
        holder.textView.setText(wzzxBeans.get(position).getName());
        Glide.with(context).load(wzzxBeans.get(position).getPicture()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackID.callback2(wzzxBeans.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return wzzxBeans.size();
    }

    public void setList(List<CjypBean1> data) {
        this.wzzxBeans = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name_right1);
            imageView = itemView.findViewById(R.id.image_right);
        }
    }




    private CallBackID callBackID;

    public void getID2(CallBackID callBackID) {
        this.callBackID = callBackID;
    }

    public interface CallBackID{
        void callback2(int id);
    }
}
