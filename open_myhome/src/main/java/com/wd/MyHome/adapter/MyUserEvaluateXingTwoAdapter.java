package com.wd.MyHome.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wd.MyHome.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyUserEvaluateXingTwoAdapter extends RecyclerView.Adapter<MyUserEvaluateXingTwoAdapter.ViewHolder> {

    private Context context;

    public MyUserEvaluateXingTwoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyUserEvaluateXingTwoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.myuserevaluate_adapter, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyUserEvaluateXingTwoAdapter.ViewHolder holder, int position) {

        holder.xingone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingone);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingtwo);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingthree);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingfive);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingfour);
                Glide.with(context).load(R.drawable.evaluation_icon_angry_n).into(holder.xingsix);
                holder.xingsix.setVisibility(View.VISIBLE);
                if (clakBack != null) {
                    clakBack.getdta(1);
                }
            }
        });
        holder.xingtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingone);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingtwo);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingthree);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingfive);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingfour);
                Glide.with(context).load(R.drawable.evaluation_icon_angry_n).into(holder.xingsix);
                holder.xingsix.setVisibility(View.VISIBLE);
                if (clakBack != null) {
                    clakBack.getdta(2);
                }
            }
        });
        holder.xingthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingone);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingtwo);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingthree);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingfive);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingfour);
                Glide.with(context).load(R.drawable.evaluation_icon_smile_n).into(holder.xingsix);
                holder.xingsix.setVisibility(View.VISIBLE);
                if (clakBack != null) {
                    clakBack.getdta(3);
                }
            }
        });
        holder.xingfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingone);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingtwo);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingthree);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingfour);
                Glide.with(context).load(R.drawable.evaluation_star_n).into(holder.xingfive);
                Glide.with(context).load(R.drawable.evaluation_icon_smile_n).into(holder.xingsix);
                holder.xingsix.setVisibility(View.VISIBLE);
                if (clakBack != null) {
                    clakBack.getdta(4);
                }
            }
        });
        holder.xingfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingone);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingtwo);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingthree);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingfour);
                Glide.with(context).load(R.drawable.evaluation_star_s).into(holder.xingfive);
                Glide.with(context).load(R.drawable.evaluation_icon_laugh_n).into(holder.xingsix);
                holder.xingsix.setVisibility(View.VISIBLE);
                if (clakBack != null) {
                    clakBack.getdta(5);
                }
            }
        });

    }
    //接口回调
    public interface ClakBack{
        void getdta(int a);
    }
    public ClakBack clakBack;

    public void setClakBack(ClakBack clakBack) {
        this.clakBack = clakBack;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView xingone,xingtwo,xingthree,xingfour,xingfive,xingsix;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xingone=itemView.findViewById(R.id.xingone);
            xingtwo=itemView.findViewById(R.id.xingtwo);
            xingthree=itemView.findViewById(R.id.xingthree);
            xingfour=itemView.findViewById(R.id.xingfour);
            xingfive=itemView.findViewById(R.id.xingfive);
            xingsix=itemView.findViewById(R.id.xingsix);
        }
    }
}
