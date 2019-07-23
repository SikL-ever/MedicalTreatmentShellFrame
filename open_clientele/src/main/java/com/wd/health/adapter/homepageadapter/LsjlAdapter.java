package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.homepage.LsjlBean;
import com.wd.health.R;
import com.wd.health.activity.SousuoAcitivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/13 12:04
 * @Description：描述信息
 */
public class LsjlAdapter extends RecyclerView.Adapter<LsjlAdapter.ViewHolder>{
    private Context context;
    private List<LsjlBean> lsjlBeans = new ArrayList<>();
    public LsjlAdapter(SousuoAcitivity sousuoAcitivity) {
        this.context = sousuoAcitivity;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.sousuo_item1,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (lsjlBeans!=null){
            holder.textView.setText(lsjlBeans.get(position).getName());
            Glide.with(context).load(R.drawable.message_icon_close_n).into(holder.imageView);
        }else{
            Toast.makeText(context, "没有找到相关内容", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return lsjlBeans.size();
    }

    public void setlist(List<LsjlBean> lsjlBeans) {
        this.lsjlBeans = lsjlBeans;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.lsjl);
            imageView = itemView.findViewById(R.id.tp);
        }
    }
}
