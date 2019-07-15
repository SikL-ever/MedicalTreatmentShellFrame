package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.SeachBean;
import com.wd.health.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeachAdapater extends RecyclerView.Adapter<SeachAdapater.MyViewHolder> {
    private Context context;

    private List<SeachBean> list = new ArrayList<>();

    public SeachAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SeachAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.seachitem, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeachAdapater.MyViewHolder holder, int position) {
        holder.content_name.setText(list.get(position).getTitle());
        holder.releasetime.setText(list.get(position).getReleaseTime()+"");
        holder.title_name.setText(list.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setseach(List<SeachBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_name;
        TextView releasetime;
        TextView content_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_name = itemView.findViewById(R.id.title_name);
            releasetime = itemView.findViewById(R.id.releasetime);
            content_name = itemView.findViewById(R.id.content_name);
        }
    }
}
