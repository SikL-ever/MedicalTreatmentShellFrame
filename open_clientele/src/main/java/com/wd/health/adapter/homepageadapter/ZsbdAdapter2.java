package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.ZhuBean;
import com.dingtao.common.bean.homepage.ZhuBean1;
import com.wd.health.R;
import com.wd.health.activity.ZsbdActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/17 15:08
 * @Description：描述信息
 */
public class ZsbdAdapter2 extends RecyclerView.Adapter<ZsbdAdapter2.ViewHolder> {
    private Context context;
    private List<ZhuBean1> zhuBeans = new ArrayList<>();

    public ZsbdAdapter2(ZsbdActivity zsbdActivity) {
        this.context = zsbdActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.zhu_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.bl.setText(zhuBeans.get(0).getPathology());
        holder.zz.setText(zhuBeans.get(0).getSymptom());
        holder.yyj.setText(zhuBeans.get(0).getBenefitTaboo());
        holder.xyzl.setText(zhuBeans.get(0).getWesternMedicineTreatment());
        holder.zyzl.setText(zhuBeans.get(0).getChineseMedicineTreatment());
        Log.i("sssssss",zhuBeans.get(0).getPathology());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setList(List<ZhuBean1> data) {
        this.zhuBeans = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bl,zz,yyj,xyzl,zyzl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bl = itemView.findViewById(R.id.item_bl);
            zz = itemView.findViewById(R.id.item_zz);
            yyj = itemView.findViewById(R.id.item_yyj);
            xyzl = itemView.findViewById(R.id.item_xyzl);
            zyzl = itemView.findViewById(R.id.item_zyzl);
        }
    }
}
