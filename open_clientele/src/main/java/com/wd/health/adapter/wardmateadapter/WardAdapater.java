package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.WardLieBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.activity.wardActivity.List_detailsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WardAdapater extends XRecyclerView.Adapter<WardAdapater.MyHolder> {
    private Context context;
    private List<WardLieBean> list=new ArrayList<>();
    public WardAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WardAdapater.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.warditem,null);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WardAdapater.MyHolder holder, final int position) {
        holder.titles.setText(list.get(position).getTitle()+"");
        holder.times.setText(list.get(position).getReleaseTime()+"");
        holder.h_num.setText(list.get(position).getAmount()+"");
        holder.contents.setText(list.get(position).getDetail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,List_detailsActivity.class);
                intent.putExtra("jumpID",list.get(position).getSickCircleId());
                context.startActivity(intent);
            }
        });
        holder.jianyi.setText(list.get(position).getCollectionNum()+"");
        holder.advice.setText(list.get(position).getCommentNum()+"");

        int amount = list.get(position).getAmount();
        if(amount>0){
            holder.h.setVisibility(View.VISIBLE);
        }else{
            holder.h.setVisibility(View.GONE);
        }

    }

    private Call call;

    public void setCall(Call call) {
        this.call=call;
    }
    public interface Call{
        void setCall(int id);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setLIST(List<WardLieBean> datas) {
        if(datas!=null){
            list=datas;
        }
    }
    public void destroy(){
        list.clear();
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        TextView titles;
        TextView times;
        TextView contents;
        TextView h_num;
        RelativeLayout h;
        TextView jianyi;
        TextView advice;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            titles = itemView.findViewById(R.id.titles);
            times = itemView.findViewById(R.id.times);
            contents = itemView.findViewById(R.id.contents);
            h_num = itemView.findViewById(R.id.H_num);
            h = itemView.findViewById(R.id.H);
            jianyi = itemView.findViewById(R.id.jainyi);
            advice = itemView.findViewById(R.id.advise);
        }
    }
}
