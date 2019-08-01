package com.bw.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.message.presenter.MyUserHPresenter;
import com.dingtao.common.bean.MyUserMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import health.wd.com.open_mymessage.R;

public class MyUserMessageHAdapter extends RecyclerView.Adapter<MyUserMessageHAdapter.ViewHolder> {
    private Context context;

    public MyUserMessageHAdapter(Context context) {
        this.context = context;
    }

    List<MyUserMessage> list=new ArrayList<>();
    @NonNull
    @Override
    public MyUserMessageHAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = View.inflate(context, R.layout.mymessagehapater_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUserMessageHAdapter.ViewHolder holder, int position) {
        holder.myhadaptertext.setText(list.get(position).content);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(list.get(position).createTime);
        holder.myhadaptertime.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void add(List<MyUserMessage> data) {
        if (data != null) {
            list.addAll(data);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myhadaptertext,myhadaptertime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myhadaptertime=itemView.findViewById(R.id.myhadaptertime);
            myhadaptertext=itemView.findViewById(R.id.myhadaptertext);
        }
    }
}
