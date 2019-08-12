package com.wd.MyHome.childthreeactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.BdyhkPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class YzyhkActivity extends AppCompatActivity {
    @BindView(R2.id.yz_top)
    TopView yzTop;
    @BindView(R2.id.img_yhk)
    ImageView imgYhk;
    @BindView(R2.id.xxx)
    ImageView xxx;
    @BindView(R2.id.zsxm)
    TextView zsxm;
    @BindView(R2.id.dzyh)
    TextView dzyh;
    @BindView(R2.id.yhzh)
    TextView yhzh;
    @BindView(R2.id.qrbd)
    Button qrbd;
    @BindView(R2.id.qd)
    Button qd;
    @BindView(R2.id.relat)
    RelativeLayout relat;
    private String userId;
    private String sessionId;
    private List<String> intt;
    private BdyhkPresenter bdyhkPresenter;
    private String number;
    private int typpe;
    private String cardname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzyhk);
        ButterKnife.bind(this);
        yzTop.setTitle("验证银行卡信息");

        intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);

        number = getIntent().getStringExtra("number");
        typpe = getIntent().getIntExtra("typpe",0);
        cardname = getIntent().getStringExtra("cardname");

        dzyh.setText(cardname);
        yhzh.setText(number);
        String bm = getIntent().getStringExtra("bm");
        Bitmap bma = BitmapFactory.decodeFile(bm);
        imgYhk.setImageBitmap(bma);
        //  Toast.makeText(XieqiyinhangkaActivity.this, "失败", Toast.LENGTH_SHORT).show();
        bdyhkPresenter = new BdyhkPresenter(new DataCall() {
            @Override
            public void success(Object data, Object... args) {
            Toast.makeText(YzyhkActivity.this, "成功", Toast.LENGTH_SHORT).show();
                 Log.i( "success: ","成功");
            }

            @Override
            public void fail(ApiException data, Object... args) {
                Toast.makeText(YzyhkActivity.this, "失败", Toast.LENGTH_SHORT).show();
                Log.i( "success: ","成功");
            }
        });

        xxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YzyhkActivity.this,XieqiyinhangkaActivity.class));
                finish();
            }
        });
        qrbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yzTop.setTitle("绑定银行卡");
                relat.setVisibility(View.GONE);
                qrbd.setVisibility(View.GONE);
                qd.setVisibility(View.VISIBLE);
            }
        });
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdyhkPresenter.reqeust(userId,sessionId, number, cardname, typpe);
                finish();
            }
        });

    }
}
