package com.wd.MyHome.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.MyUser.GiftBean;
import com.wd.MyHome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyUserGiftAdapter  extends RecyclerView.Adapter<MyUserGiftAdapter.ViewHolder> {
    public Context contet ;

    public MyUserGiftAdapter(Context contet) {
        this.contet = contet;
    }

    List<GiftBean> list=new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(contet, R.layout.giftadapter_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(contet).load(list.get(position).pic).into(holder.hua);//头像
        holder.huatext.setText(list.get(position).name);//礼物
        holder.huaprice.setText(list.get(position).worth+"");//价格
        holder.huabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clackBackData.getdata(list.get(position).id);
            }
        });
    }
    //清楚
    public void clear() {
        list.clear();
    }

    public void add(List<GiftBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    //接口回调
    public interface ClackBackData{
        void getdata(int id);
    }
    public ClackBackData clackBackData;

    public void setClackBackData(ClackBackData clackBackData) {
        this.clackBackData = clackBackData;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hua;
        TextView huatext,huaprice;
        Button huabt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hua=itemView.findViewById(R.id.hua);
            huatext=itemView.findViewById(R.id.huatext);
            huaprice=itemView.findViewById(R.id.huaprice);
            huabt=itemView.findViewById(R.id.huabt);
        }
    }
}
