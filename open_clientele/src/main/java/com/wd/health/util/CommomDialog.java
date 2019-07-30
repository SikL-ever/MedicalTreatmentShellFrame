package com.wd.health.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.health.R2;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/29 11:33
 * @Description：描述信息
 */
public class CommomDialog extends Dialog implements View.OnClickListener{
    private TextView contentTxt;
    private TextView submitTxt;
    private TextView cancelTxt;


    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;


    public CommomDialog(Context context) {
        super(context);
        this.mContext = context;
    }


    public CommomDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }


    public CommomDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }


    protected CommomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }





    public CommomDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }


    public CommomDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_layout);
        setCanceledOnTouchOutside(false);
        initView();
    }


    private void initView(){
        contentTxt = (TextView)findViewById(R.id.content_ts);
        submitTxt = (TextView)findViewById(R.id.submit_qd);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView)findViewById(R.id.cancel_qs);
        cancelTxt.setOnClickListener(this);


        contentTxt.setText(content);
        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }


        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }




    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cancel_qs) {
            if (listener != null) {
                listener.onClick(this, false);
            }
            this.dismiss();

        } else if (i == R.id.submit_qd) {
            if (listener != null) {
                listener.onClick(this, true);
            }

        }
    }


    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }

}
