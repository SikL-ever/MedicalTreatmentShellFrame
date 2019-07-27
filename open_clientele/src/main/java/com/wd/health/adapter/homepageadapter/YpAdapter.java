package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.SousuoBean;
import com.wd.health.R;
import com.wd.health.activity.SousuoAcitivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/23 20:33
 * @Description：描述信息
 */
public class YpAdapter extends RecyclerView.Adapter<YpAdapter.ViewHolder> {
    private Context context;
    private List<SousuoBean.DrugsSearchVoListBean> list;
    public YpAdapter(SousuoAcitivity sousuo, List<SousuoBean.DrugsSearchVoListBean> diseaseSearchVoList) {
        this.context = sousuo;
        this.list = diseaseSearchVoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yp_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        /*int parentHeight= parent.getHeight();
        parent.getWidth();
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        layoutParams.height =  (parentHeight/ 6);*/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getDrugsName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_yp);
        }
    }
}
