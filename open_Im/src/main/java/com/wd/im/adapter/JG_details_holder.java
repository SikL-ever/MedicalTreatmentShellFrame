package com.wd.im.adapter;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wd.Im.R;

import androidx.annotation.RequiresApi;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.PromptContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;



public class JG_details_holder extends BaseViewHolder implements View.OnClickListener {
    private RoundedImageView MyImg;  //发送的图片
    private TextView MyTv_content, MyTV_Time, My_tc, My_tc1, My_Tv_state;
    private CircleImageView MyHead;
    private Context MyContext;
    private JG_details_Adapter.OnItemClickListener mOnItemClickLis = null;
    private View view;
    public JG_details_holder(View itemView, Context con, JG_details_Adapter.OnItemClickListener mOnItemClick) {
        super(itemView);
        MyContext = con;
        mOnItemClickLis = mOnItemClick;
    }
    @Override
    public void findView(View view) {
        this.view = view;
        MyImg = this.view.findViewById(R.id.item_jg_details_img);//图片
        MyHead = view.findViewById(R.id.item_jg_details_head);  //头像
        MyTv_content = view.findViewById(R.id.item_jg_details_content);//内容
        MyTV_Time = view.findViewById(R.id.item_jg_details_time); // 时间
        My_tc = view.findViewById(R.id.item_jg_details_tc);
        My_tc1 = view.findViewById(R.id.item_jg_details_tc1);
        My_Tv_state = view.findViewById(R.id.item_jg_details_state);
        MyImg.setOnClickListener(this);
        MyHead.setOnClickListener(this);
        MyTv_content.setOnClickListener(this);
        MyTV_Time.setOnClickListener(this);
        My_Tv_state.setOnClickListener(this);
    }
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setHolderData(Object o, int position) {
        if (o != null) {
            Message bean = (Message) o;
            if (bean.getFromUser() != null) {
                if (bean.getFromUser().getUserName().equals(JMessageClient.getMyInfo().getUserName())) {
                    //是自己的聊天
                    MyHead = view.findViewById(R.id.item_jg_details_head1);  //头像 右边
                    MyHead.setVisibility(View.VISIBLE);//头像显示隐藏
                    view.findViewById(R.id.item_jg_details_head).setVisibility(View.GONE);
                    //内容背景
                    MyTv_content.setBackground(MyContext.getDrawable(R.drawable.bbbb));
                    MyTv_content.setTextColor(MyContext.getColor(R.color.black));
                    My_tc.setVisibility(View.VISIBLE);//权重挤压
                    My_tc1.setVisibility(View.GONE);
                    //对方是否未读
                    My_Tv_state.setVisibility(View.VISIBLE);
                    if (bean.haveRead()) {
                        My_Tv_state.setText("已读");
                        My_Tv_state.setTextColor(MyContext.getColor(R.color.blue));
                    }
                    {
                        My_Tv_state.setText("未读");
                        My_Tv_state.setTextColor(MyContext.getColor(R.color.colorAccent));
                    }
                } else {
                    My_Tv_state.setVisibility(View.GONE);//对方是否未读
                    MyHead = view.findViewById(R.id.item_jg_details_head);  //头像
                    MyHead.setVisibility(View.VISIBLE);//头像显示隐藏
                    view.findViewById(R.id.item_jg_details_head1).setVisibility(View.GONE);
                    //内容背景
                    MyTv_content.setBackground(MyContext.getDrawable(R.drawable.cccc));
                    MyTv_content.setTextColor(MyContext.getColor(R.color.white));
                    My_tc.setVisibility(View.GONE);
                    My_tc1.setVisibility(View.VISIBLE);
                }
                MyHead.setOnClickListener(this); //刷新头像点击事件
                //头像
                bean.getFromUser().getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        if (bitmap != null) {
                            MyHead.setImageBitmap(bitmap);
                        } else {
                            Log.e("极光会话详情-用户头像赋值", "bitmap为空!");
                        }
                    }
                });
                switch (bean.getContentType()) {
                    case text:
                        MyTv_content.setVisibility(View.VISIBLE);
                        MyTV_Time.setVisibility(View.GONE);
                        MyImg.setVisibility(View.GONE);
                        //内容
                        TextContent textContent = (TextContent) bean.getContent();
                        String text = textContent.getText();
                        MyTv_content.setText(text);
                        break;
                    case image:
                        MyTv_content.setVisibility(View.GONE);
                        MyTV_Time.setVisibility(View.GONE);
                        MyImg.setVisibility(View.VISIBLE);
                        ImageContent imageContent = (ImageContent) bean.getContent();
                        if (imageContent.getLocalThumbnailPath() != null) {
                            Glide.with(MyContext).load(imageContent.getLocalThumbnailPath()).into(MyImg);
                        }
                        break;
                    case prompt: //提示
                        MyTv_content.setVisibility(View.GONE);
                        MyTV_Time.setVisibility(View.VISIBLE);
                        MyImg.setVisibility(View.GONE);
                        //内容
                        PromptContent promptContent = (PromptContent) bean.getContent();
                        String promptText = promptContent.getPromptText();
                        MyTV_Time.setText(promptText);
                        break;
                }
            }
        }
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickLis != null) {
            mOnItemClickLis.onItemClick(v, getPosition());
        }
    }
}

