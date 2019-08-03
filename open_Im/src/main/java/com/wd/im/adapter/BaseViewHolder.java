package com.wd.im.adapter;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**

 * Created by Xinghai.Zhao on 18/03/29.

 */

/*

 *作者:赵星海

 *时间:18/03/29 16:57

 *用途:ViewHolder上层类

 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView) {
        super(itemView);
        findView(itemView);
    }
    public abstract void findView(View view);
    public abstract void setHolderData(Object o,int position);
}

