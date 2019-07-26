package com.wd.MyHome.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.MyHome.R;

import androidx.annotation.Nullable;

/**
 * 佀常勇
 *
 * @Data:2019/7/17 20:34
 * 描述：
 */
public class TopView extends RelativeLayout {

    private ImageView topviewfinish;
    private TextView topviewtext;
    private RelativeLayout rela;
    public TopView(Context context) {
        super(context);
        initView(context);
    }
    public TopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public TopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(final Context context){
        //加载layout布局，第三个参数ViewGroup一定写成this
        View view = View.inflate(context,R.layout.topview,this);
        topviewfinish = view.findViewById(R.id.topviewfinish);
        topviewtext = view.findViewById(R.id.topviewtext);
        rela = view.findViewById(R.id.rela);
        //返回
        topviewfinish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
    }
    //设置标题
    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        topviewtext.setText(title);
    }
    //设置背景
    public void setback(int title) {
        rela.setBackgroundColor(title);
    }
}
