package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.DuotiaomuBean;
import com.wd.health.R;

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
public class DuotiaomuAdapter extends RecyclerView.Adapter{
    private int a1 = 0;
    private int a2 = 1;
    private int a3 = 2;
    private Context context;
    private List<DuotiaomuBean> duotiaomuBeans = new ArrayList<>();
    public DuotiaomuAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getItemViewType(int position) {
        int a = duotiaomuBeans.get(position).getThumbnail().split(";").length;
        Log.i("ssssss",a+"");
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
            View view = View.inflate(context,R.layout.duotiaomu_item,null);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        }else if (viewType == a2){
            View view = View.inflate(context,R.layout.duotiaomu_item1,null);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }else{
            View view = View.inflate(context,R.layout.duotiaomu_item1,null);
            ViewHolder3 viewHolder3 = new ViewHolder3(view);
            return viewHolder3;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

      //  Log.i("aaaaaaaaaaa456",length+"");
        if (viewHolder instanceof ViewHolder1){
            String image = duotiaomuBeans.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split);
            ((ViewHolder1) viewHolder).recyclerView.setLayoutManager(((ViewHolder1) viewHolder).linearLayoutManager);
            ((ViewHolder1) viewHolder).name.setText(duotiaomuBeans.get(position).getTitle());
            ((ViewHolder1) viewHolder).yys.setText(duotiaomuBeans.get(position).getSource());
            ((ViewHolder1) viewHolder).time.setText(duotiaomuBeans.get(position).getReleaseTime()+"");
            ((ViewHolder1) viewHolder).recyclerView.setAdapter(gridAdapter);
        }else if(viewHolder instanceof ViewHolder2){
            String image = duotiaomuBeans.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            ((ViewHolder2) viewHolder).recyclerView1.setLayoutManager(gridLayoutManager);
            ((ViewHolder2) viewHolder).name.setText(duotiaomuBeans.get(position).getTitle());
            ((ViewHolder2) viewHolder).yys.setText(duotiaomuBeans.get(position).getSource());
            ((ViewHolder2) viewHolder).time.setText(duotiaomuBeans.get(position).getReleaseTime()+"");
            ((ViewHolder2) viewHolder).recyclerView1.setAdapter(gridAdapter);
        }else if (viewHolder instanceof ViewHolder3){
            String image = duotiaomuBeans.get(position).getThumbnail();
            String[] split = image.split(";");
            GridAdapter gridAdapter = new GridAdapter(context,split);
            ((ViewHolder3) viewHolder).recyclerView1.setLayoutManager(((ViewHolder3) viewHolder).gridLayoutManager1);
            ((ViewHolder3) viewHolder).name.setText(duotiaomuBeans.get(position).getTitle());
            ((ViewHolder3) viewHolder).yys.setText(duotiaomuBeans.get(position).getSource());
            ((ViewHolder3) viewHolder).time.setText(duotiaomuBeans.get(position).getReleaseTime()+"");
            ((ViewHolder3) viewHolder).recyclerView1.setAdapter(gridAdapter);

        }
        /*if (split.length > 0 ){


        }*/
    }

    @Override
    public int getItemCount() {
        return duotiaomuBeans.size();
    }

    public void setList(List<DuotiaomuBean> data) {
        this.duotiaomuBeans = data;
        notifyDataSetChanged();
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private final LinearLayoutManager linearLayoutManager;
        RecyclerView recyclerView;
        TextView name,yys,time;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.image_recyclerView);
            linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            name = itemView.findViewById(R.id.text_name);
            yys = itemView.findViewById(R.id.yys);
            time= itemView.findViewById(R.id.time);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        RecyclerView recyclerView1;
        TextView name,yys,time;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recyclerView1);

            name = itemView.findViewById(R.id.text_1);
            yys = itemView.findViewById(R.id.text_2);
            time = itemView.findViewById(R.id.text_3);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private final GridLayoutManager gridLayoutManager1;
        RecyclerView recyclerView1;
        TextView name,yys,time;
        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recyclerView1);
            gridLayoutManager1 = new GridLayoutManager(context, 3);
            name = itemView.findViewById(R.id.text_1);
            yys = itemView.findViewById(R.id.text_2);
            time = itemView.findViewById(R.id.text_3);
        }
    }
}
