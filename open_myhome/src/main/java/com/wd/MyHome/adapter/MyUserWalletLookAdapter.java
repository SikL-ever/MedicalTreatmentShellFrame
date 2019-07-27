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

        holder.walletname.setText(list.get(position).remark);//备注

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
            Log.i("kkk", "onBindViewHolder: "+list);
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
