package com.wd.im.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.wd.Im.R;
import com.wd.Im.R2;
import com.wd.im.adapter.JG_details_Adapter;
import com.wd.im.presenter.SendMessagePresenter;
import com.wd.im.util.AudioRecoderUtils;
import com.wd.im.util.GlobalEventListener;
import com.wd.im.util.PopupWindowFactory;
import com.wd.im.util.RsaCoder;

import java.io.File;
import java.io.FileNotFoundException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import health.wd.com.open_imtwo.util.Utils;

@Route(path = Constant.ACTIVITY_MESSAGEACTIVITY)
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
    @BindView(R2.id.chat_text)
    TextView chat_text;
    @BindView(R2.id.imag_IM_yuyin)
    ImageView imag_IM_yuyin;
    @BindView(R2.id.chatlayout)
    RelativeLayout chatlayout;
    private String appkey = "b5f102cc307091e167ce52e0";
    private Conversation singleConversation;
    private boolean one;
    private JG_details_Adapter jg_details_adapter;
    private SendMessagePresenter sendp;
    private String ss;//对方极光找好


    private boolean yuyin=true;
    private PopupWindowFactory mVoicePop;
    private TextView mPopVoiceText;
    private AudioRecoderUtils mAudioRecoderUtils=new AudioRecoderUtils();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_chat_main);
        ButterKnife.bind(this);//绑定布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //创建发送消息的p层
        sendp = new SendMessagePresenter(new getdata());
        //设置消息接收监听
        //GlobalEventListener.setJG(this, false);
        JMessageClient.registerEventReceiver(this);//注册消息监听

        String name = getIntent().getStringExtra("name");//获取名字
        chatname.setText(name);

        //获取类型
        int type = getIntent().getIntExtra("type", 0);
        if (type==1){
            String id = getIntent().getStringExtra("id");//获取医生的username
            //进行解密
            try {
                ss = new RsaCoder().decryptByPublicKey(id);//rsa解密
                JMessageClient.enterSingleConversation(ss);// 进入会话状态,不接收通知栏
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            ss = getIntent().getStringExtra("id");//获取医生的username
        }
        singleConversation = Conversation.createSingleConversation(ss, appkey);
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
                    Message message = ConsultChatMainActivity.this.singleConversation.createSendMessage(new TextContent(text));
                    fa(message);
                    chatEndit.setText("");
                }
            }
        });
        initData();
        //接受消息
        btinit();
    }
    //处理点击时间
    private void btinit() {
        //语音点击
        imag_IM_yuyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (yuyin){
                   chatEndit.setVisibility(View.GONE);
                   chat_text.setVisibility(View.VISIBLE);
                   yuyin=false;
               }else{
                    chatEndit.setVisibility(View.VISIBLE);
                    chat_text.setVisibility(View.GONE);
                    yuyin=true;
                }
            }
        });
        //发送语音
        chat_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 获得x轴坐标
                int x = (int) event.getX();
                // 获得y轴坐标
                int y = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mVoicePop.showAtLocation(v, Gravity.CENTER, 0, 0);
                        chat_text.setText("松开结束");
                        mPopVoiceText.setText("手指上滑，取消发送");
                        chat_text.setTag("1");
                        mAudioRecoderUtils.startRecord(ConsultChatMainActivity.this);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (wantToCancle(x, y)) {
                            chat_text.setText("松开结束");
                            mPopVoiceText.setText("松开手指，取消发送");
                            chat_text.setTag("2");
                        } else {
                            chat_text.setText("松开结束");
                            mPopVoiceText.setText("手指上滑，取消发送");
                            chat_text.setTag("1");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mVoicePop.dismiss();
                        if (chat_text.getTag().equals("2")) {
                            //取消录音（删除录音文件）
                            mAudioRecoderUtils.cancelRecord();
                       } else {
                            //结束录音（保存录音文件）
                            mAudioRecoderUtils.stopRecord();
                        }
                        chat_text.setText("按住说话");
                        chat_text.setTag("3");
                        break;
                }
                return true;
            }
        });
        //*********************语音里面的popup布局
        View view = View.inflate(this, R.layout.layout_microphone, null);
        mVoicePop = new PopupWindowFactory(this, view);

        //PopupWindow布局文件里面的控件
        final ImageView mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        final TextView mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
        mPopVoiceText = (TextView) view.findViewById(R.id.tv_recording_text);
        //录音回调
        mAudioRecoderUtils.setAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(Utils.long2String(time));
            }
            //录音结束，filePath为保存路径
            @Override
            public void onStop(long time, String filePath) {
                mTextView.setText(Utils.long2String(0));
                int timeid= (int) time;
                File aa= new File(filePath);
                try {
                    Message singleVoiceMessage = singleConversation.createSendVoiceMessage(aa, timeid);
                    fa(singleVoiceMessage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError() {
                chatEndit.setVisibility(View.GONE);
                chat_text.setVisibility(View.VISIBLE);
            }
        });
    }
    //发送消息的类
    private void fa(Message me){
        me.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseDesc) {
                if (responseCode == 0) {
                    //消息发送成功
                    Toast.makeText(ConsultChatMainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    //添加一下
                    initData();
                    //添加到接口上
                    //sendp.reqeust();
                } else {
                    //消息发送失败
                    Toast.makeText(ConsultChatMainActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        JMessageClient.sendMessage(me);//使用默认控制参数发送消息
    }
    private boolean wantToCancle(int x, int y) {
        // 超过按钮的宽度
        if (x < 0 || x > chat_text.getWidth()) {
            return true;
        }
        // 超过按钮的高度
        if (y < -50 || y > chat_text.getHeight() + 50) {
            return true;
        }
        return false;
    }
    public void onEvent(MessageEvent event) {
        //获取事件发生的会话对象
        Message newMessage = event.getMessage();//获取此次离线期间会话收到的新消息列表
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
        singleConversation.getAllMessage().clear();
        super.onDestroy();
    }
    //**********************************************************************************
    //发送消息的返回值
    class getdata implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //**********************************************************************************
    //--------------------------------------------------------------------------------------------------
    public void initData() {
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
        }else{
            Toast.makeText(this, "没有消息对象", Toast.LENGTH_SHORT).show();
        }
        one=false; // 代表不是第一次initData
    }
}
