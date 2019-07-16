package com.wd.health.adapter.videoadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.video.TopBean;
import com.wd.health.R;
import com.wd.health.R2;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 佀常勇
 *
 * @Data:2019/7/12 20:39
 * 描述：
 */
public class TopRecyclerAdapter extends RecyclerView.Adapter<TopRecyclerAdapter.ViewHolder> {
    private Context context;

    public TopRecyclerAdapter(Context context) {
        this.context = context;
    }
    List<TopBean> list=new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.toprecylcer_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.toprecyclertext.setText(list.get(position).name);
        holder.toprecyclertext.setTextColor(list.get(position).textColor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.toprecyclertext.setTextColor(Color.BLUE);
                if (callBack != null) {
                    callBack.data(list.get(position).id);
                }
                for (int i = 0; i <list.size() ; i++) {
                    list.get(i).textColor=Color.BLACK;
                }
                list.get(position).textColor=Color.BLUE;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(List<TopBean> data) {
        if (data!= null) {
            list.addAll(data);
        }
    }
    //接口回调
    public interface CallBack{
        void data(String da);
    }
    public CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView toprecyclertext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            toprecyclertext=itemView.findViewById(R.id.toprecyclertext);
        }
    }
}
