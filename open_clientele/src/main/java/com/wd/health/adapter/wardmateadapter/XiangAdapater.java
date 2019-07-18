package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.wardBean.List_xiang_Bean;
import com.wd.health.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class XiangAdapater extends RecyclerView.Adapter<XiangAdapater.MyViewHolder> {
    private Context context;
    private List<List_xiang_Bean> list=new ArrayList<>();
    public XiangAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public XiangAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.itemlist,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull XiangAdapater.MyViewHolder holder, int position) {
        holder.details.setText(list.get(position).getDetail());
        holder.hospital.setText(list.get(position).getTreatmentHospital());
        holder.remedy.setText(list.get(position).getTreatmentProcess());
        holder.text_content.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getPicture()).into(holder.bull);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setllll(List<List_xiang_Bean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView details;
        TextView text_content;
        TextView symptom;
        TextView hospital;
        TextView remedy;
        ImageView bull;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.details);
            text_content = itemView.findViewById(R.id.text_content);
            symptom = itemView.findViewById(R.id.symptom);
            hospital = itemView.findViewById(R.id.hospital);
            remedy = itemView.findViewById(R.id.remedy);
            bull = itemView.findViewById(R.id.bull);
        }
    }
}
