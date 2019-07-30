package com.wd.health.adapter.homepageadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.wd.health.R;
import com.wd.health.activity.WzzxActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.dingtao.common.core.WDApplication.getContext;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/24 11:44
 * @Description：描述信息
 */
public class AAdapter extends RecyclerView.Adapter<AAdapter.ViewHolder> {
    private Context context;
    private List<WzzxBean> wzzxBeans = new ArrayList<>();

    public AAdapter(Context homePagerFragement) {
        this.context = homePagerFragement;
    }


    @NonNull
    @Override
    public AAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.wzzx_lb_item,parent,false);
        AAdapter.ViewHolder viewHolder = new AAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AAdapter.ViewHolder holder, int position) {
        holder.textView.setText(wzzxBeans.get(position).getDepartmentName());
        /*if (position == getthisPosition()) {
            holder.textView.setTextColor(Color.parseColor("#3087ea"));
        }else {
            holder.textView.setTextColor(Color.parseColor("#999999"));
        }*/
        //wzzxBeans.get(position).setTextcolor(0xff999999);
        holder.textView.setTextColor(wzzxBeans.get(position).getTextcolor());
       // holder.textView.setTextColor(0xff999999);
       // callBackj.callbackkj(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onClick(position);
                for (int i = 0; i < wzzxBeans.size(); i++) {
                    wzzxBeans.get(i).setTextcolor(0xff999999);
                }
                wzzxBeans.get(position).setTextcolor(0xff3087ea);
                callBackID.callback1(wzzxBeans.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return wzzxBeans.size();
    }

    public void setList(List<WzzxBean> data) {
        this.wzzxBeans = data;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.lb_text_name);
        }
    }
    //先声明一个int成员变量
    private int thisPosition;
    private OnItemClickListener onRecyclerViewItemClickListener;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition2(int thisPosition) {
        this.thisPosition = thisPosition;
    }
    public void setOnRecyclerViewItemClickListener12(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onClick(int position);
    }
    private CallBackID callBackID;

    public void getID1(CallBackID callBackID) {
        this.callBackID = callBackID;
    }

    public interface CallBackID{
        void callback1(int id);
    }
    private CallBackKj callBackj;

    public void getKj(CallBackKj callBackj) {
        this.callBackj = callBackj;
    }

    public interface CallBackKj{
        void callbackkj(int position);
    }
}
