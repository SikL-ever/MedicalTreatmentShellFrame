package com.wd.MyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.MyHome.R;

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
public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    List<Object> list=new ArrayList<>();
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    private int sign;//0:普通点击，1自定义
    public void setSign(int sign) {
        this.sign = sign;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.recyclerview_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        Glide.with(context).load(list.get(position)).into(holder.draweeview);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void addList(List<String> strings) {
        if (strings != null) {
            list.addAll(strings);
        }
    }

    public void add(Object image) {
        if (image != null) {
            list.add(image);
        }
    }

    public List<Object> getList() {
        return list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView draweeview;
        public ViewHolder(View itemView) {
            super(itemView);
            draweeview=itemView.findViewById(R.id.circle_image);
        }
    }
}
