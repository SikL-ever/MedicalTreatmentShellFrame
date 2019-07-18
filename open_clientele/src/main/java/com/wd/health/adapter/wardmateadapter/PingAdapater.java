package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.wardBean.Ping_lie_Bean;
import com.wd.health.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PingAdapater extends RecyclerView.Adapter<PingAdapater.MyViewHolder> {
    private Context context;
    private List<Ping_lie_Bean> list=new ArrayList<>();
    public PingAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PingAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.popwin_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PingAdapater.MyViewHolder holder, int position) {
        holder.commentCaiNum.setText(list.get(position).getOpposeNum()+"");
        holder.commentZan.setText(list.get(position).getSupportNum()+"");
        holder.commentName.setText(list.get(position).getNickName());
        holder.commentTime.setText(list.get(position).getCommentTime()+"");
        Glide.with(context).load(list.get(position).getHeadPic()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(holder.head_ssss);
        holder.commentCoent.setText(list.get(position).getContent());
        boolean checked = holder.zan.isChecked();
        boolean checked1 = holder.cai.isChecked();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setpinglei(List<Ping_lie_Bean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView head_ssss;
        TextView commentName;
        TextView commentCoent;
        TextView commentTime;
        CheckBox zan;
        TextView commentZan;
        CheckBox cai;
        TextView commentCaiNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            head_ssss = itemView.findViewById(R.id.head_sssss);
            commentName = itemView.findViewById(R.id.CommentName);
            commentCoent = itemView.findViewById(R.id.CommentCoent);
            commentTime = itemView.findViewById(R.id.CommentTime);
            zan = itemView.findViewById(R.id.ImageViewZan);
            commentZan = itemView.findViewById(R.id.CommentZan);
            cai = itemView.findViewById(R.id.ImageViewCai);
            commentCaiNum = itemView.findViewById(R.id.CommentCaiNum);
        }
    }
}
