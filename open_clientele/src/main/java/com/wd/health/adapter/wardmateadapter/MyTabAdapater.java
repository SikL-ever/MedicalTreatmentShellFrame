package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.TabBean;
import com.wd.health.R;
import com.wd.health.activity.wardActivity.SeachActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 佀常勇
 *
 * @Data:2019/7/12 9:37
 * 描述：
 */
public class MyTabAdapater extends RecyclerView.Adapter<MyTabAdapater.MyViewHodler> {
    private Context context;
    private List<TabBean> list=new ArrayList<>();
    //1
    public MyTabAdapater(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public MyTabAdapater.MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item,null);
        MyViewHodler myViewHodler=new MyViewHodler(view);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyTabAdapater.MyViewHodler holder, final int position) {
        holder.textView.setText(list.get(position).departmentName);
        holder.textView.setTextColor(list.get(position).textColor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <list.size() ; i++) {
                       list.get(i).textColor=Color.BLACK;
                }
                list.get(position).textColor=Color.RED;
                notifyDataSetChanged();
            }
        });

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

    public void setList(List<TabBean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.texttab);
        }
    }
}
