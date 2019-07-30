package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.LsjlBean;
import com.wd.health.R;
import com.wd.health.activity.SousuoAcitivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/15 10:33
 * @Description：描述信息
 */
public class SearchRecordsAdapter extends RecyclerView.Adapter<SearchRecordsAdapter.ViewHolder> {
    private Context context;
    private List<LsjlBean> list;

    public SearchRecordsAdapter(Context context, List<LsjlBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.sousuo_item1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvRecommandsearch.setText(list.get(position).getName());
        holder.imageView.setOnClickListener(view -> deleteListener.OnDelete(position));
        holder.itemView.setOnClickListener(view -> itemClickListener.OnItemClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
      //  @BindView(R.id.tv_recommandsearch)
        TextView tvRecommandsearch;
        ImageView imageView;
/*
        @BindView(R.id.btn_delete)
        ImageView btnDelete;
*/

        ViewHolder(View itemView) {
            super(itemView);
           tvRecommandsearch = itemView.findViewById(R.id.lsjl);
           imageView = itemView.findViewById(R.id.tp);
         //   ButterKnife.bind(this, itemView);
        }
    }

    private OnDeleteListener deleteListener;
    private OnItemClickListener itemClickListener;

    public interface OnDeleteListener {
        void OnDelete(int position);
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnDeleteListener(OnDeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
