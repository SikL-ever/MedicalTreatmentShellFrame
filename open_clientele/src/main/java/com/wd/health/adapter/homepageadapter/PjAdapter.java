package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.YsxqBean;
import com.dingtao.common.util.DateUtils;
import com.wd.health.R;
import com.wd.health.activity.YsxqActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/29 20:19
 * @Description：描述信息
 */
public class PjAdapter extends RecyclerView.Adapter<PjAdapter.ViewHolder> {
    private Context context;
    private List<YsxqBean.CommentListBean> list = new ArrayList<>();

    public PjAdapter(YsxqActivity ysxqActivity, List<YsxqBean.CommentListBean> commentList) {
        this.context = ysxqActivity;
        this.list = commentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PjAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.pj_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getHeadPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.imageView);
        holder.ename.setText(list.get(position).getNickName());
        holder.etext.setText(list.get(position).getContent());
        try {
            holder.etime.setText(DateUtils.dateTransformer(list.get(position).getCommentTime(),DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ename,etime,etext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.etx);
            ename = itemView.findViewById(R.id.ename);
            etime = itemView.findViewById(R.id.etime);
            etext = itemView.findViewById(R.id.etext);

        }
    }
}