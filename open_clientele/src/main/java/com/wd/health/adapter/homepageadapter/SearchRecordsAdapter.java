package com.wd.health.adapter.homepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.activity.SousuoAcitivity;

import java.util.List;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/15 10:33
 * @Description：描述信息
 */
public class SearchRecordsAdapter extends BaseAdapter {

    private Context context;
    private List<String> searchRecordsList;
    private LayoutInflater inflater;

    public SearchRecordsAdapter(Context context, List<String> searchRecordsList) {
        this.context = context;
        this.searchRecordsList = searchRecordsList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        //设置listView的显示条目数量为5
        return searchRecordsList.size() > 10 ? 10 : searchRecordsList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchRecordsList.size() == 0 ? null : searchRecordsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(null == convertView){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sousuo_item1,null);
            viewHolder.recordTv = (TextView) convertView.findViewById(R.id.lsjl);
            convertView.findViewById(R.id.tp).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBackOnClick.onclicket(position);
                }
            });

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String content = searchRecordsList.get(position);
        viewHolder.recordTv.setText(content);

        return convertView;
    }

    private class ViewHolder {
        TextView recordTv;
    }

    private CallBackOnClick callBackOnClick;

    public void getback(CallBackOnClick callBackOnClick) {
        this.callBackOnClick = callBackOnClick;
    }

    public interface CallBackOnClick{
        void onclicket(int aid);
    }
}
