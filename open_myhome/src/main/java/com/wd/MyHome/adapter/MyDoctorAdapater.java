package com.wd.MyHome.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dingtao.common.bean.wardBean.MyDoctorBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.MyHome.childactivity.SwipeItemLayout;
import com.wd.MyHome.presenter.DeletePresenter;
import com.wd.MyHome.presenter.DeleterPresenters;

public class MyDoctorAdapater extends RecyclerView.Adapter<MyDoctorAdapater.MyViewHolder> {
    private Context context;
    private List<MyDoctorBean> list=new ArrayList<>();
    public MyDoctorAdapater(Context context) {
        this.context = context;
    }

    private float moveX;
    private float moveY;
    private float pressX;
    private float pressY;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.interest_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final MyDoctorBean myDoctorBean = list.get(position);
        Glide.with(context).load(list.get(position).getImagePic()).into(holder.doctor_image);
        holder.name_title.setText(list.get(position).getName());
        holder.professional.setText(list.get(position).getJobTitle());
        holder.content_title.setText(list.get(position).getInauguralHospital());
        holder.good.setText(list.get(position).getParise());
        holder.patient_nums.setText(list.get(position).getNumber()+"");

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    //按下
                    case MotionEvent.ACTION_DOWN:
                        pressX = event.getX();
                        pressY = event.getY();
                        break;
                    //移动
                    case MotionEvent.ACTION_MOVE:
                        if (moveX-pressX>0&&Math.abs(moveY-pressY)<50){

                            holder.textView.setVisibility(View.GONE);
                        }else if (moveX - pressX < 0 && Math.abs(moveY - pressY) < 50){
                            holder.textView.setVisibility(View.VISIBLE);
                        }
                        moveX = event.getX();
                        moveY = event.getY();
                        break;
                    //松开
                    case MotionEvent.ACTION_UP:
                        if (moveX-pressX > 0 && Math.abs(moveY - pressY) < 50) {
                            //Log.i("message", "向右");
                        } else if (moveX - pressX < 0 && Math.abs(moveY - pressY) < 50) {
                            //Log.i("message", "向左");
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleterPresenters deleterPresenters=new DeleterPresenters(new DataCall() {
                    @Override
                    public void success(Object data, Object... args) {
                        Toast.makeText(context,"取消成功",Toast.LENGTH_LONG).show();
                        list.remove(myDoctorBean);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void fail(ApiException data, Object... args) {
                        Toast.makeText(context,data.getDisplayMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                LoginDaoUtil loginDaoUtil=new LoginDaoUtil();
                List<String> intt = loginDaoUtil.intt(context);
                deleterPresenters.reqeust(intt.get(0),intt.get(1),list.get(position).getDoctorId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setdoctor(List<MyDoctorBean> data) {
        if(data!=null){
            list.addAll(data);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patient_nums;
        TextView good;
        ImageView doctor_image;
        TextView name_title;
        TextView professional;
        TextView content_title;
        TextView textView;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            good = itemView.findViewById(R.id.good);

            doctor_image = itemView.findViewById(R.id.doctorimage);
            name_title = itemView.findViewById(R.id.name_title);
            professional = itemView.findViewById(R.id.professional);
            content_title = itemView.findViewById(R.id.content_title);
            patient_nums = itemView.findViewById(R.id.patient_nums);
            textView = itemView.findViewById(R.id.guanzhu_image_view_view);
            relativeLayout = itemView.findViewById(R.id.goness);
        }
    }
}
