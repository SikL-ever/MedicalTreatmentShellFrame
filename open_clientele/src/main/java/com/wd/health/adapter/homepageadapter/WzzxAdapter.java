package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.activity.WzzxActivity;
import com.wd.health.fragment.HomePagerFragement;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.dingtao.common.core.WDApplication.getContext;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/16 8:42
 * @Description：描述信息
 */
public class WzzxAdapter extends RecyclerView.Adapter<WzzxAdapter.ViewHolder> {
    private Context context;
    private List<WzzxBean> wzzxBeans = new ArrayList<>();
    public WzzxAdapter(Context homePagerFragement, List<WzzxBean> data) {
        this.context = homePagerFragement;
        this.wzzxBeans = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.wzzx_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(wzzxBeans.get(position).getDepartmentName());
        Glide.with(getContext()).load(wzzxBeans.get(position).getPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> intt = new LoginDaoUtil().intt(context);
                if (intt!=null){
                    Intent intent = new Intent(context, WzzxActivity.class);
                    intent.putExtra("idid", wzzxBeans.get(position).getId());
                    context.startActivity(intent);
                }else{
                    ARouter.getInstance().build(Constant.ACTIVITY_LOGIN_LOGIN).navigation(context);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wzzxBeans.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.images1);
            textView = itemView.findViewById(R.id.text_name);
        }
    }
}
