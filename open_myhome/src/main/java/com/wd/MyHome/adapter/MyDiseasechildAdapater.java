package com.wd.MyHome.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.wardBean.Ping_lie_Bean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyDiseasechildAdapater extends RecyclerView.Adapter<MyDiseasechildAdapater.MyViewHolder> {
    private Context context;
    List<Ping_lie_Bean> list=new ArrayList<>();

    public MyDiseasechildAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.child,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getHeadPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.head);
        holder.names.setText(list.get(position).getNickName());
        holder.fandui_nums.setText(list.get(position).getOpposeNum()+"");
        holder.zan_nums.setText(list.get(position).getSupportNum()+"");
//        holder.image_caina.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MychildDiseasePresenter mychildDiseasePresenter=new MychildDiseasePresenter(new childs());
//                LoginDaoUtil loginDaoUtil=new LoginDaoUtil();
//                List<String> intt = loginDaoUtil.intt(context);
//                mychildDiseasePresenter.reqeust(intt.get(0),intt.get(1),1,5);
//            }
//        });
    }
//    class childs implements DataCall<Result>{
//        @Override
//        public void success(Result data, Object... args) {
//            Toast.makeText(context,"采纳成功",Toast.LENGTH_SHORT).show();
//        }
//        @Override
//        public void fail(ApiException data, Object... args) {
//
//        }
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setchild(List<Ping_lie_Bean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView head;
        TextView names;
        ImageView zan;
        TextView zan_nums;
        ImageView fandui;
        TextView fandui_nums;
        TextView caina_nums;
        ImageView image_caina;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.head);
            names = itemView.findViewById(R.id.names);
            zan = itemView.findViewById(R.id.zan);
            zan_nums = itemView.findViewById(R.id.zannums);
            fandui = itemView.findViewById(R.id.fandui);
            fandui_nums = itemView.findViewById(R.id.fanduinums);
            caina_nums = itemView.findViewById(R.id.caina);
            image_caina = itemView.findViewById(R.id.image_caina);

        }
    }
}
