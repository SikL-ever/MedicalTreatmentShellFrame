package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.TaFaBean;
import com.wd.health.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaFaAdapater extends RecyclerView.Adapter<TaFaAdapater.MyViewHolder> {
    private Context context;
    private List<TaFaBean> list=new ArrayList<>();
    public TaFaAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TaFaAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.warditem,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaFaAdapater.MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-hh-dd");
        String format = simpleDateFormat.format(new Date(list.get(position).getReleaseTime()));
        holder.times.setText(format);
        holder.contents.setText(list.get(position).getDetail());
        holder.jianyi.setText(list.get(position).getCollectionNum()+"");
        holder.advise.setText(list.get(position).getCommentNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setta(List<TaFaBean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView times;
        TextView contents;
        TextView jianyi;
        TextView advise;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titles);
            times = itemView.findViewById(R.id.times);
            contents = itemView.findViewById(R.id.contents);
            jianyi = itemView.findViewById(R.id.jainyi);
            advise = itemView.findViewById(R.id.advise);
        }
    }
}
