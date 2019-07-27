package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.BingzhengBean;
import com.wd.health.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BingzhengAdapater extends RecyclerView.Adapter<BingzhengAdapater.MyViewHolder> {
    private Context context;
    private List<BingzhengBean> list=new ArrayList<>();
    public BingzhengAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BingzhengAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.xuanze_popitem,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BingzhengAdapater.MyViewHolder holder, final int position) {
        holder.xuan.setText(list.get(position).getName()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.setCall(list.get(position).getName());
            }
        });
    }
    private Calll call;

    public void setCall(Calll call) {
        this.call=call;
    }
    public interface Calll{
        void setCall(String name);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setbingzheng(List<BingzhengBean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView xuan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            xuan = itemView.findViewById(R.id.xuanxe_text);
        }
    }
}
