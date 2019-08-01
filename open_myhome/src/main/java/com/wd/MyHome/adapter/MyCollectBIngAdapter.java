package com.wd.MyHome.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.wardBean.WardLieBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.DateUtils;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.presenter.MyCollectBingDeletePresenter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCollectBIngAdapter extends RecyclerView.Adapter<MyCollectBIngAdapter.ViewHolder> {
    private Context context;
    List<WardLieBean> list=new ArrayList<>();
    int begx,endx;
    public MyCollectBIngAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyCollectBIngAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.mycollectbing_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCollectBIngAdapter.ViewHolder holder, final int position) {

        if (list == null) {
            Toast.makeText(context, "1111", Toast.LENGTH_SHORT).show();
        }else{
            holder.mycollectbingtitle.setText(list.get(position).title);//标题
            holder.mycollecttexxt.setText(list.get(position).disease);//正文
            holder.mycollectbingshoucangnum.setText(list.get(position).collectionNum+"");//收藏书
            holder.mycollectbingsuggestnum.setText(list.get(position).commentNum+"");//建议的数量
            //设置时间
            try {
                holder.mycollectbingtime.setText(DateUtils.dateTransformer(list.get(position).createTime,DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //点击删除
        holder.mycollectdelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                List<String> intt = loginDaoUtil.intt(context);
                holder.myCollectBingDeletePresenter.reqeust(intt.get(0),intt.get(1),list.get(position).sickCircleId);
                list.remove(list.get(position));
                holder.mycollectdelect.setVisibility(View.GONE);
            }
        });
        //左滑删除
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下
                        begx= (int) event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起
                        //监听向左滑
                        endx= (int) event.getX();
                        if (begx - endx > 50){
                            holder.mycollectdelect.setVisibility(View.VISIBLE);
                        }
                        endx=0;
                        begx=0;
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void add(List<WardLieBean> data) {
        if (data != null) {
            list.addAll(data);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mycollectbingtitle,mycollecttexxt,mycollectbingshoucangnum,mycollectbingsuggestnum
                ,mycollectbingtime;
        private ImageView mycollectdelect;
        private MyCollectBingDeletePresenter myCollectBingDeletePresenter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mycollectdelect=itemView.findViewById(R.id.mycollectdelect);
            mycollectbingtime=itemView.findViewById(R.id.mycollectbingtime);
            mycollectbingsuggestnum=itemView.findViewById(R.id.mycollectbingsuggestnum);
            mycollectbingshoucangnum=itemView.findViewById(R.id.mycollectbingshoucangnum);
            mycollecttexxt=itemView.findViewById(R.id.mycollecttexxt);
            mycollectbingtitle=itemView.findViewById(R.id.mycollectbingtitle);
            myCollectBingDeletePresenter=new MyCollectBingDeletePresenter(new getdata());
        }
    }
    class getdata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            notifyDataSetChanged();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
}
