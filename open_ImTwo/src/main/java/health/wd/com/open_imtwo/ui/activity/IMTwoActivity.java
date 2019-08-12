package health.wd.com.open_imtwo.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;

import androidx.recyclerview.widget.RecyclerView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import health.wd.com.open_imtwo.R;
import health.wd.com.open_imtwo.adapter.ChatAdapter;
import health.wd.com.open_imtwo.adapter.CommonFragmentPagerAdapter;
import health.wd.com.open_imtwo.enity.FullImageInfo;
import health.wd.com.open_imtwo.enity.MessageInfo;
import health.wd.com.open_imtwo.ui.fragment.ChatEmotionFragment;
import health.wd.com.open_imtwo.ui.fragment.ChatFunctionFragment;
import health.wd.com.open_imtwo.util.Constants;
import health.wd.com.open_imtwo.util.GlobalOnItemClickManagerUtils;
import health.wd.com.open_imtwo.util.MediaManager;
import health.wd.com.open_imtwo.widget.EmotionInputDetector;
import health.wd.com.open_imtwo.widget.NoScrollViewPager;
import health.wd.com.open_imtwo.widget.StateButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import health.wd.com.open_imtwo.R2;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class IMTwoActivity extends AppCompatActivity {

    EasyRecyclerView chatList;
    ImageView emotionVoice;
    EditText editText;
    TextView voiceText;
    ImageView emotionButton;
    ImageView emotionAdd;
    StateButton emotionSend;
    NoScrollViewPager viewpager;
    RelativeLayout emotionLayout;

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ChatFunctionFragment chatFunctionFragment;
    private CommonFragmentPagerAdapter adapter;

    private ChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;
    private List<MessageInfo> messageInfos;
    //录音相关
    int animationRes = 0;
    int res = 0;
    AnimationDrawable animationDrawable = null;
    private ImageView animView;


    public  Message sendVoiceMessage;

    private Conversation singleConversation;
    private String appkey = "b5f102cc307091e167ce52e0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        chatList=findViewById(R.id.chat_list);
        emotionVoice=findViewById(R.id.emotion_voice);
        editText=findViewById(R.id.edit_text);
        voiceText=findViewById(R.id.voice_text);
        emotionButton=findViewById(R.id.emotion_button);
        emotionAdd=findViewById(R.id.emotion_add);
        emotionSend=findViewById(R.id.emotion_send);
        viewpager=findViewById(R.id.viewpager);
        emotionLayout=findViewById(R.id.emotion_layout);
        String id = getIntent().getStringExtra("id");//获取医生的username
        singleConversation = Conversation.createSingleConversation(id, appkey);
        initWidget();
    }

    private void initWidget() {
        fragments = new ArrayList<>();
        chatEmotionFragment = new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        chatFunctionFragment = new ChatFunctionFragment();
        fragments.add(chatFunctionFragment);
        adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .setViewPager(viewpager)
                .BindViewToContent(chatList)
                .BindViewToEditText(editText)
                .BindViewToEmotionButton(emotionButton)
                .BindViewToAddButton(emotionAdd)
                .BindViewToSendButton(emotionSend)
                .BindViewToVoiceButton(emotionVoice)
                .BindViewToVoiceText(voiceText)
                .build();

        GlobalOnItemClickManagerUtils globalOnItemClickListener = GlobalOnItemClickManagerUtils.getInstance(this);
        globalOnItemClickListener.attachToEditText(editText);

        chatAdapter = new ChatAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        chatList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        chatAdapter.handler.removeCallbacksAndMessages(null);
                        mDetector.hideEmotionLayout(false);
                        mDetector.hideSoftInput();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        chatAdapter.addItemClickListener(itemClickListener);
        LoadData();
    }

    /**
     * item点击事件
     */
    private ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {
            Toast.makeText(IMTwoActivity.this, "onHeaderClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImageClick(View view, int position) {
            int location[] = new int[2];
            view.getLocationOnScreen(location);
            FullImageInfo fullImageInfo = new FullImageInfo();
            fullImageInfo.setLocationX(location[0]);
            fullImageInfo.setLocationY(location[1]);
            fullImageInfo.setWidth(view.getWidth());
            fullImageInfo.setHeight(view.getHeight());
            fullImageInfo.setImageUrl(messageInfos.get(position).getImageUrl());
            EventBus.getDefault().postSticky(fullImageInfo);
            startActivity(new Intent(IMTwoActivity.this, FullImageActivity.class));
            overridePendingTransition(0, 0);
        }

        @Override
        public void onVoiceClick(final ImageView imageView, final int position) {
            if (animView != null) {
                animView.setImageResource(res);
                animView = null;
            }
            switch (messageInfos.get(position).getType()) {
                case 1:
                    animationRes = R.drawable.voice_left;
                    res = R.mipmap.icon_voice_left3;
                    break;
                case 2:
                    animationRes = R.drawable.voice_right;
                    res = R.mipmap.icon_voice_right3;
                    break;
            }
            animView = imageView;
            animView.setImageResource(animationRes);
            animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            MediaManager.playSound(messageInfos.get(position).getFilepath(), new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    animView.setImageResource(res);
                }
            });
        }
    };

    /**
     * 构造聊天数据
     */
    private void LoadData() {
        messageInfos = new ArrayList<>();
        List<Message> allMessage = singleConversation.getAllMessage();

        for (int i = 0; i < allMessage.size(); i++) {
            final Message message = allMessage.get(i);
            if (message.getFromUser().getUserName().equals(JMessageClient.getMyInfo().getUserName())) {
                //你的消息
                final MessageInfo messageInfo = new MessageInfo();
                TextContent textContent = (TextContent) message.getContent();
                String text = textContent.getText();
                messageInfo.setContent(text);//文本
                messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
                //头像
                message.getFromUser().getBigAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        messageInfo.setHeader(bitmap);
                    }
                });
                messageInfos.add(messageInfo);
            } else{
                //对方消息
                final MessageInfo messageInfo = new MessageInfo();
                TextContent textContent = (TextContent) message.getContent();
                String text = textContent.getText();
                messageInfo.setContent(text);//文本
                messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                //头像
                message.getFromUser().getBigAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        messageInfo.setHeader(bitmap);
                    }
                });
                messageInfos.add(messageInfo);
            }
        }
        chatAdapter.addAll(messageInfos);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        //messageInfo.setHeader();
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        messageInfos.add(messageInfo);
        chatAdapter.add(messageInfo);

        if (messageInfo.getContent() != null) {
            sendVoiceMessage = singleConversation.createSendMessage(new TextContent(messageInfo.getContent()));
        } else if (messageInfo.getImageUrl() != null) {

        } else if (messageInfo.getFilepath() != null) {
            File aa= new File(messageInfo.getFilepath());
            int cc= (int) messageInfo.getVoiceTime();
            try {
                sendVoiceMessage = singleConversation.createSendVoiceMessage(aa,cc);//语音
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        chatList.scrollToPosition(chatAdapter.getCount() - 1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                fa(sendVoiceMessage);
                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                chatAdapter.notifyDataSetChanged();
            }
        }, 2000);
        /*new Handler().postDelayed(new Runnable() {
            public void run() {
                MessageInfo message = new MessageInfo();
                message.setContent("这是模拟消息回复");
                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                message.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
                messageInfos.add(message);
                chatAdapter.add(message);
                chatList.scrollToPosition(chatAdapter.getCount() - 1);
            }
        }, 3000);*/
    }

    //发送消息的类
    private void fa(Message me){
        me.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseDesc) {
                if (responseCode == 0) {
                    //消息发送成功
                    Toast.makeText(IMTwoActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    //添加一下
                    //添加到接口上
                    //sendp.reqeust();
                } else {
                    //消息发送失败
                    Toast.makeText(IMTwoActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        JMessageClient.sendMessage(me);//使用默认控制参数发送消息
    }
    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
    }
}
