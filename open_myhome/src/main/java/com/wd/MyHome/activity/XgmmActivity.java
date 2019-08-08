package com.wd.MyHome.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.XgmmPresenter;
import com.wd.MyHome.presenter.XgxbPresenter;
import com.wd.MyHome.util.TopView;
import com.wd.im.util.RsaCoder;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class XgmmActivity extends AppCompatActivity {

    @BindView(R2.id.xg_top)
    TopView xgTop;
    @BindView(R2.id.wdmm)
    EditText wdmm;
    @BindView(R2.id.xmm)
    EditText xmm;
    @BindView(R2.id.qrmm)
    EditText qrmm;
    @BindView(R2.id.qued)
    Button qued;

    private List<String> intt;
    private String userId;
    private String sessionId;
    private String lowpwd;
    private String newpwd;
    private String rqnewpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xgmm);
        ButterKnife.bind(this);
        xgTop.setTitle("修改密码");
        /*wdmm.setGravity(Gravity.RIGHT);
        xmm.setGravity(Gravity.RIGHT);
        qrmm.setGravity(Gravity.RIGHT);*/
        intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);



        qued.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowpwd = wdmm.getText().toString().trim();
                newpwd = xmm.getText().toString().trim();
                rqnewpwd = qrmm.getText().toString().trim();
                if (lowpwd.length()<=0|| newpwd.length()<=0|| rqnewpwd.length()<=0){
                    Toast.makeText(XgmmActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }else if(!newpwd.equals(rqnewpwd)){
                    Toast.makeText(XgmmActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }else if (lowpwd.equals(newpwd)){
                    Toast.makeText(XgmmActivity.this, "新旧密码不能一致", Toast.LENGTH_SHORT).show();
                }else if (newpwd.length()<6){
                    Toast.makeText(XgmmActivity.this, "密码最少6位", Toast.LENGTH_SHORT).show();
                }else{
                    XgmmPresenter xgxbPresenter = new XgmmPresenter(new DataCall<Result>() {
                        @Override
                        public void success(Result data, Object... args) {
//                            if (data.getMessage().equals("修改成功")){
//                                Toast.makeText(XgmmActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
                            Toast.makeText(XgmmActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            finish();
//                            Log.e("sssssssss",data.getMessage()+data.getStatus());
                        }

                        @Override
                        public void fail(ApiException data, Object... args) {

                        }
                    });
                    try {
                        String s = RsaCoder.encryptByPublicKey(lowpwd);
                        String s1 = RsaCoder.encryptByPublicKey(rqnewpwd);
                        xgxbPresenter.reqeust(userId,sessionId, s, s1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });




    }
}
