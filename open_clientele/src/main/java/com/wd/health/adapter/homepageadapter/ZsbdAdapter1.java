package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.ZiBean;
import com.wd.health.R;
import com.wd.health.activity.ZsbdActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/17 14:30
 * @Description：描述信息
 */
public class ZsbdAdapter1 extends RecyclerView.Adapter<ZsbdAdapter1.ViewHolder> {
    private Context context;
    private List<ZiBean> ziBeans = new ArrayList<>();
    public ZsbdAdapter1(FragmentActivity zsbdActivity) {
        this.context = zsbdActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_right,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(ziBeans.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackID.callback(ziBeans.get(position).getId(),ziBeans.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ziBeans.size();
    }

    public void setList(List<ZiBean> data) {
        this.ziBeans = data;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name_right);
        }
    }
    private CallBackID callBackID;

    public void getID1(CallBackID callBackID) {
        this.callBackID = callBackID;
    }

    public interface CallBackID{
        void callback(int id,String name);
    }
}
