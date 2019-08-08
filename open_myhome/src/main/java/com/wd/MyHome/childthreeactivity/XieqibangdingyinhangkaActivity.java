package com.wd.MyHome.childthreeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingtao.common.bean.MyUser.YhBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.DateUtils;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.activity.WdxxActivity;
import com.wd.MyHome.presenter.CxYhPresenter;
import com.wd.MyHome.util.TopView;

import org.jsoup.helper.DataUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class XieqibangdingyinhangkaActivity extends AppCompatActivity {

    @BindView(R2.id.yhk1)
    ImageView yhk1;
    @BindView(R2.id.yhmz)
    TextView yhmz;
    @BindView(R2.id.klx)
    TextView klx;
    @BindView(R2.id.kahao)
    TextView kahao;
    @BindView(R2.id.bktime)
    TextView bktime;
    @BindView(R2.id.qbd)
    Button qbd;
    private String userId;
    private String sessionId;
    private List<String> intt;
    private DateUtils dateUtils;
    private CxYhPresenter cxYhPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xieqibangdingyinhangka);
        ButterKnife.bind(this);
        TopView topView = findViewById(R.id.yh_top1);
        topView.setTitle("查看银行卡信息");
        intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);
        dateUtils = new DateUtils();

        //6212 8405 0010 0051 323
        cxYhPresenter = new CxYhPresenter(new DataCall<YhBean>() {
            @Override
            public void success(YhBean data, Object... args) {
                yhmz.setText(data.getBankName());
                if (data.getBankCardType()==1){
                    klx.setText("借记卡");
                }else{
                    klx.setText("信用卡");
                }

                String bankCardNumber = data.getBankCardNumber();
                Log.i("zzz", "success: "+bankCardNumber);
                String ban = bankCardNumber.replaceAll(" ", "");
                String s = ban.substring(4, 8);
                String s1 = ban.substring(12, 16);
                Log.i("zzz", "success: "+s);

                kahao.setText("**** "+s+" **** "+s1+" ***");
//6212 8405 0010 0051 323
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(data.getBindTime());
                bktime.setText("绑卡时间："+dateString);

            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        cxYhPresenter.reqeust(userId,sessionId);
        qbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XieqibangdingyinhangkaActivity.this,XieqiyinhangkaActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cxYhPresenter.reqeust(userId,sessionId);
    }
}
