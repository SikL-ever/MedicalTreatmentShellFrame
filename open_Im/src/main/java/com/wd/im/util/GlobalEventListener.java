package com.wd.im.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.wd.im.activity.ConsultChatMainActivity;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;

public class GlobalEventListener {
        private Context MainContext;
        private static ConsultChatMainActivity JG_details = null;// 会话详情对象
        public GlobalEventListener(Context context) {
            MainContext = context;
            JMessageClient.registerEventReceiver(this);
        }
        public static void setJG(Activity activity, boolean islist) {
            JG_details = (ConsultChatMainActivity) activity;
        }
        //通知点击 前往会话列表
        public void onEvent(NotificationClickEvent event) {
            MainContext.startActivity(new Intent(MainContext, ConsultChatMainActivity.class));
        }
        // 接收消息 (主线程)(刷新UI)
        public void onEventMainThread(MessageEvent event){
            if (JG_details != null) {
                JG_details.initData();
            }
        }
}
