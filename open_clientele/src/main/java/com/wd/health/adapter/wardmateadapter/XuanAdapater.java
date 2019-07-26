package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.TabBean;
import com.wd.health.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class XuanAdapater extends RecyclerView.Adapter<XuanAdapater.MyViewHolder> {
    private Context context;
    private List<TabBean> list=new ArrayList<>();
    public XuanAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public XuanAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.xuanze_popitem,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull XuanAdapater.MyViewHolder holder, final int position) {
        holder.xuan_text.setText(list.get(position).getDepartmentName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.setCall(list.get(position).getDepartmentName(),list.get(position).getId());
            }
        });
    }


    private Call call;

    public void setCall(Call call) {
        this.call=call;
    }
    public interface Call{
        void setCall(String departmentName,int id);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setxuan(List<TabBean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView xuan_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xuan_text = itemView.findViewById(R.id.xuanxe_text);
        }
    }
}
