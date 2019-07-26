package com.wd.health.adapter.wardmateadapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.wardBean.Ping_lie_Bean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.activity.wardActivity.TaWardActivity;
import com.wd.health.presenter.wardmatepresenter.ZanPresenter;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PingAdapater extends RecyclerView.Adapter<PingAdapater.MyViewHolder> {
    private Context context;
    private List<Ping_lie_Bean> list = new ArrayList<>();
    private ZanPresenter zanPresenter;

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
    public void onBindViewHolder(@NonNull final PingAdapater.MyViewHolder holder, final int position) {
        holder.commentCaiNum.setText(list.get(position).getOpposeNum() + "");
        holder.commentZan.setText(list.get(position).getSupportNum() + "");
        holder.commentName.setText(list.get(position).getNickName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        String time = simpleDateFormat.format(new Date(list.get(position).commentTime));
        holder.commentTime.setText(time + "");
        Glide.with(context).load(list.get(position).getHeadPic()).apply(RequestOptions.bitmapTransform(new RoundedCorners(80))).into(holder.head_ssss);
        holder.commentCoent.setText(list.get(position).getContent());

        holder.head_ssss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaWardActivity.class);
                context.startActivity(intent);

            }
        });
        //赞
        zanPresenter = new ZanPresenter(new zan());
        holder.zan.setTag(list.get(position));
        holder.zan.setOnClickListener(new View.OnClickListener() {

            private Ping_lie_Bean ping_lie_bean;
            private int commentId;

            @Override
            public void onClick(View v) {
                ping_lie_bean = (Ping_lie_Bean) v.getTag();
                commentId = list.get(position).getCommentId();
                CheckBox c = (CheckBox) v;
                if (c.isChecked()) {
                    holder.zan.setChecked(true);
                    LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                    List<String> intt = loginDaoUtil.intt(context);
                    zanPresenter.reqeust(intt.get(0), intt.get(1), commentId , 1);
                    holder.commentZan.setText(list.get(position).getSupportNum() + 1 + "");
                } else {
                    holder.zan.setChecked(false);
                    LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                    List<String> intt = loginDaoUtil.intt(context);
                    zanPresenter.reqeust(intt.get(0), intt.get(1), commentId + "", 2);
                    holder.commentZan.setText(list.get(position).getSupportNum() + "");
                }
            }
        });
        //踩
        zanPresenter = new ZanPresenter(new cai());
        holder.cai.setTag(list.get(position));
        holder.cai.setOnClickListener(new View.OnClickListener() {
            private Ping_lie_Bean ping_lie_bean;
            private int commentId;

            @Override
            public void onClick(View v) {
                commentId = list.get(position).getCommentId();
                CheckBox c = (CheckBox) v;
                if (c.isChecked()) {
                    holder.cai.setChecked(true);
                    LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                    List<String> intt = loginDaoUtil.intt(context);
                    zanPresenter.reqeust(intt.get(0), intt.get(1), commentId , 1);
                    holder.commentCaiNum.setText(list.get(position).getOpposeNum() + 1 + "");
                }else{
                    holder.cai.setChecked(false);
                    LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                    List<String> intt = loginDaoUtil.intt(context);
                    zanPresenter.reqeust(intt.get(0), intt.get(1), commentId + "", 2);
                    holder.commentCaiNum.setText(list.get(position).getOpposeNum() + "");
                }
            }
        });

    }

    //踩
    class cai implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {
            Toast.makeText(context,data.getMessage()+"",Toast.LENGTH_SHORT).show();

            notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //赞
    class zan implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(context,"点赞成功",Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setpinglei(List<Ping_lie_Bean> data) {
        if (data != null) {
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
