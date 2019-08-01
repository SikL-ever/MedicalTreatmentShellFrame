package com.wd.MyHome.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.MyUser.MyUserWalletLookBean;
import com.dingtao.common.util.DateUtils;
import com.wd.MyHome.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 佀常勇
 *
 * @Data:2019/5/28 16:02
 * 描述：
 */
public class MyUserWalletLookAdapter extends RecyclerView.Adapter<MyUserWalletLookAdapter.ViewHolder> {
    List<MyUserWalletLookBean> list=new ArrayList<>();
    private Context context;

    public MyUserWalletLookAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.myuserwallet_look, null);
        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        //进行判断
        if (list.get(position).type==1){
            holder.walletname.setText("签到");//备注
        }else if (list.get(position).type==2){
            holder.walletname.setText("病友圈首评");//备注
        }else if (list.get(position).type==3){
            holder.walletname.setText("首发病友圈");//备注
        }else if (list.get(position).type==4){
            holder.walletname.setText("完善档案");//备注
        }else if (list.get(position).type==5){
            holder.walletname.setText("健康测评");//备注
        }else if (list.get(position).type==6){
            holder.walletname.setText("悬赏消费");//备注
        }else if (list.get(position).type==7){
            holder.walletname.setText("悬赏奖励");//备注
        }else if (list.get(position).type==8){
            holder.walletname.setText("邀请奖励");//备注
        }else if (list.get(position).type==9){
            holder.walletname.setText("问诊消费");//备注
        }else if (list.get(position).type==10){
            holder.walletname.setText("问诊收入");//备注
        }else if (list.get(position).type==11){
            holder.walletname.setText("观看咨询");//备注
        }else if (list.get(position).type==12){
            holder.walletname.setText("送礼物");//备注
        }else if (list.get(position).type==13){
            holder.walletname.setText("绑定身份证");//备注
        }else if (list.get(position).type==14){
            holder.walletname.setText("绑定银行卡");//备注
        }else if (list.get(position).type==15){
            holder.walletname.setText("充值");//备注
        }else if (list.get(position).type==16){
            holder.walletname.setText("提现");//备注
        }
        holder.walletprice.setText(Integer.parseInt(list.get(position).changeNum)+"H币");//金钱

        if (list.get(position).direction==1){//1就是收入，2就是支出
            holder.walletprice.setTextColor(Color.RED);
        }else{
            holder.walletprice.setTextColor(Color.BLUE);
        }
        try {
            holder.wallettime.setText(DateUtils.dateTransformer(list.get(position).createTime,DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void addList(List<MyUserWalletLookBean> strings) {
            list.addAll(strings);
            notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView walletname,wallettime,walletprice;
        public ViewHolder(View itemView) {
            super(itemView);
            walletname=itemView.findViewById(R.id.walletname);
            wallettime=itemView.findViewById(R.id.wallettime);
            walletprice=itemView.findViewById(R.id.walletprice);
        }
    }
}
