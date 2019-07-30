package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.YsxqBean;
import com.wd.health.R;
import com.wd.health.activity.YsxqActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/29 20:05
 * @Description：描述信息
 */
public class DsAdapter extends RecyclerView.Adapter<DsAdapter.ViewHolder> {
    private Context context;
  //  private List<YsxqBean.CommentListBean> list;
    public DsAdapter(YsxqActivity ysxqActivity, List<YsxqBean.CommentListBean> commentList) {
        this.context = ysxqActivity;
     //   this.list = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.ds_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.d_num.setText(list.get(position).);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView d_num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*imageView = itemView.findViewById(R.id.d_img);
            d_num = itemView.findViewById(R.id.d_num);
*/
        }
    }
}
