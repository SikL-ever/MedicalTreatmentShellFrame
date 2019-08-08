package com.wd.MyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.MyDiseaseBean;
import com.wd.MyHome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.MyHome.childactivity.MyChildDiseaseActivity;

public class MyDiseaseAdapater extends RecyclerView.Adapter<MyDiseaseAdapater.MyViewHolder> {
    private Context context;
    List<MyDiseaseBean> list=new ArrayList<>();
    public MyDiseaseAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.mydesease_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.texttitle.setText(list.get(position).getTitle());
        holder.text_context.setText(list.get(position).getDisease());
        holder.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MyChildDiseaseActivity.class);
                intent.putExtra("id",list.get(position).getSickCircleId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setdisease(List<MyDiseaseBean> data) {
        if(data!=null){
            list.addAll(data);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView texttitle;
       TextView text_context;
       TextView look;
       TextView timec;
       TextView timemonth;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            texttitle = itemView.findViewById(R.id.texttitle);
            text_context = itemView.findViewById(R.id.text_content);
            look = itemView.findViewById(R.id.look);
            timec = itemView.findViewById(R.id.time_month);
            timemonth = itemView.findViewById(R.id.timemonth);
        }
    }
}
