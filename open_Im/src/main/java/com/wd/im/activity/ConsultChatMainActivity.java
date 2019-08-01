package com.wd.im.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.android.material.snackbar.Snackbar;
import com.wd.Im.R;
import com.wd.Im.R2;
import com.wd.im.presenter.ConsultADoctorPresenter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
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
    private ConsultADoctorPresenter consultADoctorPresenter;
    private int id;
    private String UserName;//医生的im账号
    private String appkey = "b5f102cc307091e167ce52e0";
    private Conversation mConv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_chat_main);
        ButterKnife.bind(this);//绑定布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //获取医生的id
        id = getIntent().getIntExtra("id", 0);
        //获取uid
        String name = getIntent().getStringExtra("name");
        chatname.setText(name);
        //创建p层
        consultADoctorPresenter = new ConsultADoctorPresenter(new getdatausername());
        //获取输入框的值i，如果说是没数据隐藏有数据进行现实发送按钮
        //发送按钮点击
        charSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String text = chatEndit.getText().toString().trim();
                Message message = mConv.createSendMessage(new TextContent("Hello jmessage."));
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseDesc) {
                        if (responseCode == 0) {
                            //消息发送成功
                            Toast.makeText(ConsultChatMainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                            //添加一下

                        } else {
                            //消息发送失败
                            Toast.makeText(ConsultChatMainActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                JMessageClient.sendMessage(message);//使用默认控制参数发送消息
                chatEndit.setText("");


            }
        });

    }

    //咨询医生请求到的数据;
    class getdatausername implements DataCall<String> {
        @Override
        public void success(String data, Object... args) {
            if (data == null) {
                Snackbar.make(chatlayout, "连接失败", Snackbar.LENGTH_SHORT).show();
            }else{
                Conversation.createSingleConversation(data, appkey);
            }
            //返回回来医生的username
            //创建会话
            Log.i("kkkk", "success: " + data);
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> intt = new LoginDaoUtil().intt(ConsultChatMainActivity.this);
        consultADoctorPresenter.reqeust(intt.get(0), intt.get(1), id);//请求咨询
    }
}
