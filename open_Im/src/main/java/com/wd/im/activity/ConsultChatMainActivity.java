package com.wd.im.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.Im.R;
import com.wd.Im.R2;
import com.wd.im.adapter.JG_details_Adapter;
import com.wd.im.util.GlobalEventListener;
import com.wd.im.util.RsaCoder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
public class ConsultChatMainActivity extends AppCompatActivity {

    @BindView(R2.id.charfinish)
    ImageView charfinish;
    @BindView(R2.id.chatnamee)
    TextView chatname;
    @BindView(R2.id.Chatrecycler)
    RecyclerView Chatrecycler;
    @BindView(R2.id.chat_endit)
    EditText chatEndit;
    @BindView(R2.id.chat_pice)
    ImageView chatPice;
    @BindView(R2.id.char_send)
    ImageView charSend;
    @BindView(R2.id.chatlayout)
    RelativeLayout chatlayout;
    private String appkey = "b5f102cc307091e167ce52e0";
    private Conversation singleConversation;
    private String ss;
    private boolean one;
    private JG_details_Adapter jg_details_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_chat_main);
        ButterKnife.bind(this);//绑定布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //设置消息接收监听
        //GlobalEventListener.setJG(this, false);
        JMessageClient.registerEventReceiver(this);//注册消息监听

        String id = getIntent().getStringExtra("id");//获取医生的username
        //进行解密
        try {
            ss = new RsaCoder().decryptByPublicKey(id);//rsa解密
            JMessageClient.enterSingleConversation(ss);// 进入会话状态,不接收通知栏
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = getIntent().getStringExtra("name");//获取名字
        chatname.setText(name);
        //获取类型
        int type = getIntent().getIntExtra("type", 0);
        singleConversation = Conversation.createSingleConversation(ss, appkey);
        if (type==1){//创建会话

        }else{//有会话，找之前会话
            initData();
        }
        //设置布局
        jg_details_adapter = new JG_details_Adapter(this);
        Chatrecycler.setAdapter(jg_details_adapter);
        Chatrecycler.setLayoutManager
                (new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //发送按钮点击
        charSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = chatEndit.getText().toString().trim();
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(ConsultChatMainActivity.this, "消息不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Message message = singleConversation.createSendMessage(new TextContent(text));
                    message.setOnSendCompleteCallback(new BasicCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseDesc) {
                            if (responseCode == 0) {
                                //消息发送成功
                                Toast.makeText(ConsultChatMainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                //添加一下
                                initData();
                            } else {
                                //消息发送失败
                                Toast.makeText(ConsultChatMainActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    JMessageClient.sendMessage(message);//使用默认控制参数发送消息
                    chatEndit.setText("");
                }
            }
        });
        //接受消息
    }
    public void onEvent(MessageEvent event) {
        //获取事件发生的会话对象
        Message newMessage = event.getMessage();//获取此次离线期间会话收到的新消息列表
        Log.i("aaa ", "onEvent: "+newMessage);
        initData();
    }
    @Override
    protected void onDestroy() {
        //退出会话界面 (开始接收通知栏)
        JMessageClient.exitConversation();
        //设置消息接收 监听
        GlobalEventListener.setJG(null, false);
        //销毁
        JMessageClient.unRegisterEventReceiver(this);
        super.onDestroy();
    }
    //--------------------------------------------------------------------------------------------------
    public void initData() {
        /*List<Conversation> msgList = JMessageClient.getConversationList();
        if (msgList != null){
            if (msgList.size() > 0) {
                if (msgList.get() != null) {
                    Conversation conversation = msgList.get(position);
                    //重置会话未读消息数
                    conversation.resetUnreadCount();
                }
            }
        }*/
        if (singleConversation != null) {
            //使列表滚动到底部
            if (singleConversation.getAllMessage() != null) {
                if (singleConversation.getAllMessage().size() > 0) {
                    jg_details_adapter.setData(singleConversation.getAllMessage());
                    //设置刷新不闪屏
                    ((SimpleItemAnimator) Chatrecycler.getItemAnimator()).setSupportsChangeAnimations(false);
                    if (one) {
                        jg_details_adapter.notifyDataSetChanged();
                    } else {
                        jg_details_adapter.notifyItemInserted(singleConversation.getAllMessage().size() - 1);
                    }
                    Chatrecycler.scrollToPosition(singleConversation.getAllMessage().size() - 1);
                }
            }
            /*mAdapter.setOnItemClickListener(new JG_details_Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (view.getId()) {
                        case R.id.item_jg_details_img:
                            ImageContent imageContent = (ImageContent) singleConversation.getAllMessage().get(position).getContent();
                            startActivity(new Intent(ConsultChatMainActivity.this, Activity_img.class)
                                    .putExtra("ImgUrl", imageContent.getLocalThumbnailPath()));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);//动画
                            break;
                    }
                }
            });*/
        }else{
            Toast.makeText(this, "没有消息对象", Toast.LENGTH_SHORT).show();
        }
        one=false; // 代表不是第一次initData
    }
}
