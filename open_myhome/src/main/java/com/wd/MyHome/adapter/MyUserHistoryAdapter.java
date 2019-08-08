package com.wd.MyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.MyUser.MyUserHistoryBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.MyHome.R;
import com.wd.MyHome.childactivity.MyUserEvaluateActivity;
import com.wd.MyHome.childactivity.MyUserNewInquiryActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyUserHistoryAdapter extends RecyclerView.Adapter<MyUserHistoryAdapter.ViewHolder> {
    private Context context;

    public MyUserHistoryAdapter(Context context) {
        this.context = context;
    }

    List<MyUserHistoryBean> list=new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.myuserhistory_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.myuserhistoryimage.setImageURI(list.get(position).imagePic);//形象照
        holder.myuserhistoryname.setText(list.get(position).doctorName);//医生名字
        holder.myuserhistoryzhiwei.setText(list.get(position).jobTitle);//职位
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(list.get(position).inquiryTime);
        holder.myuserhistorytime.setText(dateString);//时间
        //查看聊天记录
        holder.myuserhistorybtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //评论
        holder.myuserhistorybttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到评价页面
                Intent intent = new Intent(context,MyUserEvaluateActivity.class);
                intent.putExtra("aid",list.get(position).recordId);//问诊recordId
                intent.putExtra("did",list.get(position).doctorId);//医生doctorId
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(List<MyUserHistoryBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    public void clear() {
        list.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView myuserhistoryimage;
        TextView myuserhistoryname,myuserhistoryzhiwei,myuserhistorytime,myuserhistorybtone,myuserhistorybttwo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myuserhistoryimage=itemView.findViewById(R.id.myuserhistoryimage);
            myuserhistoryname=itemView.findViewById(R.id.myuserhistoryname);
            myuserhistoryzhiwei=itemView.findViewById(R.id.myuserhistoryzhiwei);
            myuserhistorytime=itemView.findViewById(R.id.myuserhistorytime);
            myuserhistorybtone=itemView.findViewById(R.id.myuserhistorybtone);
            myuserhistorybttwo=itemView.findViewById(R.id.myuserhistorybttwo);
        }
    }
}
