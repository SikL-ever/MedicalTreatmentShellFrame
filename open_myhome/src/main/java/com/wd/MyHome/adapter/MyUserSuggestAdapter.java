package com.wd.MyHome.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.MyUser.MyUserSuggestBean;
import com.dingtao.common.bean.MyUser.MyUserWalletLookBean;
import com.dingtao.common.util.DateUtils;
import com.wd.MyHome.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class MyUserSuggestAdapter extends RecyclerView.Adapter<MyUserSuggestAdapter.ViewHolder> {
    List<MyUserSuggestBean> list=new ArrayList<>();
    private Context context;

    public MyUserSuggestAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.myusersuggest_item, null);
        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        MyUserSuggestBean ddd = list.get(position);
        Glide.with(context).load(ddd.releaseUserHeadPic).
                apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.suggestitem_image);//头像
        holder.suggestitem_name.setText(ddd.releaseUserNickName);//昵称
        holder.suggesttime_title.setText(ddd.title);//标题
        holder.suggestitem_bing.setText("主要病症:"+ddd.disease);//病症
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        String dateString = formatter.format(ddd.adoptTime);
        holder.suggestitem_time.setText(dateString);//设置时间
        holder.suggestitem_text.setText("采纳时间:"+ddd.content);//建议内容
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void addList(List<MyUserSuggestBean> strings) {
        if (strings != null) {
            list.addAll(strings);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView suggesttime_title,suggestitem_bing,suggestitem_time,suggestitem_name,suggestitem_text;
        ImageView suggestitem_image;
        public ViewHolder(View itemView) {
            super(itemView);
            suggesttime_title=itemView.findViewById(R.id.suggesttime_title);
            suggestitem_bing=itemView.findViewById(R.id.suggestitem_bing);
            suggestitem_time=itemView.findViewById(R.id.suggestitem_time);
            suggestitem_name=itemView.findViewById(R.id.suggestitem_name);
            suggestitem_text=itemView.findViewById(R.id.suggestitem_text);
            suggestitem_image=itemView.findViewById(R.id.suggestitem_image);
        }
    }
}
