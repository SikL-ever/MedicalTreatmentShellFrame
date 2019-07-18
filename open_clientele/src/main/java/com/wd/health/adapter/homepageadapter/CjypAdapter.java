package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.CjypBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.wd.health.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/18 10:24
 * @Description：描述信息
 */
public class CjypAdapter  extends RecyclerView.Adapter<CjypAdapter.ViewHolder> {
    private Context context;
    private List<CjypBean> wzzxBeans = new ArrayList<>();

    public CjypAdapter(FragmentActivity zsbdActivity) {
        this.context = zsbdActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_left1, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i("ssssssss1", wzzxBeans.get(position).getName());
        holder.textView.setText(wzzxBeans.get(position).getName());
        if (position == getthisPosition()) {
            holder.textView.setBackgroundColor(Color.WHITE);
            holder.vi.setBackgroundColor(Color.parseColor("#3087ea"));

        } else {
            holder.textView.setBackgroundColor(Color.parseColor("#f2f2f2"));
            holder.vi.setBackgroundColor(Color.parseColor("#f2f2f2"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onClick(position);
                callBackID.callback1(wzzxBeans.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return wzzxBeans.size();
    }

    public void setList(List<CjypBean> data) {
        this.wzzxBeans = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View vi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name_left1);
            vi = itemView.findViewById(R.id.vi1);
        }
    }

    //先声明一个int成员变量
    private int thisPosition;
    private OnItemClickListener onRecyclerViewItemClickListener;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }
    public void setOnRecyclerViewItemClickListener1(OnItemClickListener onItemClickListener) {
        this.onRecyclerViewItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onClick(int position);
    }
    private CallBackID callBackID;

    public void getID1(CallBackID callBackID) {
        this.callBackID = callBackID;
    }

    public interface CallBackID{
        void callback1(int id);
    }
}
