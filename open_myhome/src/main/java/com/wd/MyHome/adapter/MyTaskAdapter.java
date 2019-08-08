package com.wd.MyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dingtao.common.bean.wardBean.MyTaskBean;
import com.wd.MyHome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.MyHome.childactivity.MyHMoneyActivity;
import com.wd.health.activity.wardActivity.List_detailsActivity;
import com.wd.health.activity.wardActivity.PublishActivity;

/**
 * @Author：lenovo
 * @Date：2019/8/2 19:32
 * @Description：描述信息
 */
public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.MyViewHolder> {
    private Context context;
    private List<MyTaskBean> list=new ArrayList<>();

    public MyTaskAdapter(Context context, List<MyTaskBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item_taskliebiao,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        MyTaskBean bean = list.get(i);
        myViewHolder.renwu.setText(list.get(i).getTaskName());
        myViewHolder.hb.setText("+"+list.get(i).getReward()+"H币");
        int whetherReceive = list.get(i).getWhetherReceive();
        if (whetherReceive==1){
            myViewHolder.button01.setVisibility(View.GONE);
            myViewHolder.button02.setVisibility(View.VISIBLE);
            myViewHolder.button03.setVisibility(View.GONE);

        }else if (whetherReceive==2){
            myViewHolder.button01.setVisibility(View.GONE);
            myViewHolder.button02.setVisibility(View.GONE);
            myViewHolder.button03.setVisibility(View.VISIBLE);

        }else if (whetherReceive==3){
            myViewHolder.button01.setVisibility(View.VISIBLE);
            myViewHolder.button02.setVisibility(View.GONE);
            myViewHolder.button03.setVisibility(View.GONE);

        }
        myViewHolder.button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = list.get(i).getTaskName();
                if (taskName.equals("签到")){
                        call.onclick();
                }else if (taskName.equals("每日病友圈首评")){
//                    ARouter.getInstance().build(Constant.ACTIVITY_URL_MAIN)
//                            .navigation(context);
//                    Abb.id = 1;
                    Intent intent=new Intent(context,List_detailsActivity.class);
                    context.startActivity(intent);
                }else if (taskName.equals("每日首发病友圈")){
                    Intent intent=new Intent(context,PublishActivity.class);
                    context.startActivity(intent);
                }
            }

        });
        myViewHolder.button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String taskName = list.get(i).getTaskName();
                if (taskName.equals("签到")){
                    mHttpListener.onclick(1001);
                }else if (taskName.equals("每日病友圈首评")){
                    mHttpListener.onclick(1002);
                }else if (taskName.equals("每日首发病友圈")){
                    mHttpListener.onclick(1003);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView renwu,hb,button01,button02,button03;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            renwu=itemView.findViewById(R.id.renwu);
            hb=itemView.findViewById(R.id.hb);
            button01=itemView.findViewById(R.id.button01);
            button02=itemView.findViewById(R.id.button02);
            button03=itemView.findViewById(R.id.button03);
        }
    }
    private HttpListener mHttpListener;
    public void result(HttpListener mHttpListener){
        this.mHttpListener=mHttpListener;
    }
    public interface HttpListener{
        void onclick(int taskId);
    }
    private HttpListener2 call;
    public void result(HttpListener2 mHttpListener2){
        this.call=mHttpListener2;
    }
    public interface HttpListener2{
        void onclick();
    }


}
