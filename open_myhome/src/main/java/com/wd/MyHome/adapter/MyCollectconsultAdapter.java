package com.wd.MyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.MyUser.MyConsultBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.DateUtils;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.presenter.MyCollectConsultDeletePresenter;
import com.wd.health.activity.ZxxqActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/16 14:23
 * @Description：描述信息
 */
public class MyCollectconsultAdapter extends RecyclerView.Adapter{
    private int a1 = 0;
    private int a2 = 1;
    private int a3 = 2;
    private Context context;
    private List<MyConsultBean> duotiaomuBeans = new ArrayList<>();
    MyCollectConsultDeletePresenter myCollectConsultDeletePresenter=new MyCollectConsultDeletePresenter(new getdata());
    int begx,endx;
    public MyCollectconsultAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getItemViewType(int position) {
        int a = duotiaomuBeans.get(position).getThumbnail().split(";").length;
        if (a==1){
            return a1;
        }else if (a % 2 == 0){
            return a2;
        }else{
            return a3;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == a1){
            View view = View.inflate(context,R.layout.mycollectconsultduotiaomu_item,null);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        }else if (viewType == a2){
            View view = View.inflate(context,R.layout.mycollectconsultduotiaomu_item1,null);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }else{
            View view = View.inflate(context,R.layout.mycollectconsultduotiaomu_item1,null);
            ViewHolder3 viewHolder3 = new ViewHolder3(view);
            return viewHolder3;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof ViewHolder1){
            String image = duotiaomuBeans.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split);
            ((ViewHolder1) viewHolder).recyclerView.setLayoutManager(((ViewHolder1) viewHolder).linearLayoutManager);
            ((ViewHolder1) viewHolder).name.setText(duotiaomuBeans.get(position).getTitle());
            try {
                ((ViewHolder1) viewHolder).time.setText(DateUtils.dateTransformer(duotiaomuBeans.get(position).getCreateTime(),DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ((ViewHolder1) viewHolder).recyclerView.setAdapter(gridAdapter);
            //删除
            ((ViewHolder1) viewHolder).twoimagedelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> intt = new LoginDaoUtil().intt(context);
                    myCollectConsultDeletePresenter.reqeust(intt.get(0),intt.get(1),duotiaomuBeans.get(position).id);
                    duotiaomuBeans.remove(position);
                    ((ViewHolder1) viewHolder).twoimagedelete.setVisibility(View.GONE);
                }
            });
            ((ViewHolder1) viewHolder).itemView.setOnTouchListener(new View.OnTouchListener() {
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
                                ((ViewHolder1) viewHolder).twoimagedelete.setVisibility(View.VISIBLE);
                            }
                            break;
                    }
                    return true;
                }
            });
        }else if(viewHolder instanceof ViewHolder2){
            String image = duotiaomuBeans.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            ((ViewHolder2) viewHolder).recyclerView1.setLayoutManager(gridLayoutManager);
            ((ViewHolder2) viewHolder).name.setText(duotiaomuBeans.get(position).getTitle());
            try {
                ((ViewHolder2) viewHolder).time.setText(DateUtils.dateTransformer(duotiaomuBeans.get(position).getCreateTime(),DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ((ViewHolder2) viewHolder).recyclerView1.setAdapter(gridAdapter);
            //删除
            ((ViewHolder2) viewHolder).oneimagedelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> intt = new LoginDaoUtil().intt(context);
                    myCollectConsultDeletePresenter.reqeust(intt.get(0),intt.get(1),duotiaomuBeans.get(position).id);
                    duotiaomuBeans.remove(position);
                    ((ViewHolder2) viewHolder).oneimagedelete.setVisibility(View.GONE);
                }
            });

            ((ViewHolder2) viewHolder).itemView.setOnTouchListener(new View.OnTouchListener() {
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
                                ((ViewHolder2) viewHolder).oneimagedelete.setVisibility(View.VISIBLE);
                            }
                            break;
                    }
                    return true;
                }
            });

        }else if (viewHolder instanceof ViewHolder3){
            String image = duotiaomuBeans.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split);
            ((ViewHolder3) viewHolder).recyclerView1.setLayoutManager(((ViewHolder3) viewHolder).gridLayoutManager1);
            ((ViewHolder3) viewHolder).name.setText(duotiaomuBeans.get(position).getTitle());
            try {
                ((ViewHolder3) viewHolder).time.setText(DateUtils.dateTransformer(duotiaomuBeans.get(position).getCreateTime(),DateUtils.DATE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ((ViewHolder3) viewHolder).recyclerView1.setAdapter(gridAdapter);
            //删除
            ((ViewHolder3) viewHolder).oneimagedelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> intt = new LoginDaoUtil().intt(context);
                    myCollectConsultDeletePresenter.reqeust(intt.get(0),intt.get(1),duotiaomuBeans.get(position).id);
                    duotiaomuBeans.remove(position);
                    ((ViewHolder3) viewHolder).oneimagedelete.setVisibility(View.GONE);
                }
            });

            ((ViewHolder3) viewHolder).itemView.setOnTouchListener(new View.OnTouchListener() {
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
                                ((ViewHolder3) viewHolder).oneimagedelete.setVisibility(View.VISIBLE);
                            }
                            break;
                    }
                    return true;
                }
            });

        }
        //条目点击
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(context, ""+duotiaomuBeans.get(position).getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ZxxqActivity.class);
                intent.putExtra("idxq",duotiaomuBeans.get(position).id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return duotiaomuBeans.size();
    }

    public void setList(List<MyConsultBean> data) {
        this.duotiaomuBeans = data;
        notifyDataSetChanged();
    }

    public void cleat() {
        duotiaomuBeans.clear();
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private final LinearLayoutManager linearLayoutManager;
        RecyclerView recyclerView;
        TextView name,yys,time;
        ImageView twoimagedelete;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.image_recyclerView);
            linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            name = itemView.findViewById(R.id.text_name);
            yys = itemView.findViewById(R.id.yys);
            time= itemView.findViewById(R.id.time);
            twoimagedelete= itemView.findViewById(R.id.twoimagedelete);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        RecyclerView recyclerView1;
        TextView name,yys,time;
        ImageView oneimagedelete;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recyclerView1);
            oneimagedelete = itemView.findViewById(R.id.oneimagedelete);
            name = itemView.findViewById(R.id.text_1);
            yys = itemView.findViewById(R.id.text_2);
            time = itemView.findViewById(R.id.text_3);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private final GridLayoutManager gridLayoutManager1;
        RecyclerView recyclerView1;
        TextView name,yys,time;
        ImageView oneimagedelete;
        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recyclerView1);
            gridLayoutManager1 = new GridLayoutManager(context, 3);
            name = itemView.findViewById(R.id.text_1);
            yys = itemView.findViewById(R.id.text_2);
            time = itemView.findViewById(R.id.text_3);
            oneimagedelete = itemView.findViewById(R.id.oneimagedelete);
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
