package com.wd.MyHome.childactivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.activity.MyUserActivity;
import com.wd.MyHome.presenter.CotinuousPresenter;
import com.wd.MyHome.presenter.WhetherPresenter;
//import com.wd.MyHome.seekbarutils.*;
import com.wd.health.activity.WebActivity;
import com.wd.health.activity.wardActivity.List_detailsActivity;
import com.wd.health.activity.wardActivity.PublishActivity;

import java.util.List;

public class MyHMoneyActivity extends AppCompatActivity {

    @BindView(R2.id.desease_back)
    ImageView deseaseBack;
//    @BindView(R2.id.indcatorseekbar)
//    IndicatorSeekBar indcatorseekbar;
    @BindView(R2.id.ss)
    TextView ss;
    @BindView(R2.id.o)
    TextView o;
    @BindView(R2.id.a)
    TextView a;
    @BindView(R2.id.aa)
    TextView aa;
    private Button qiandao;
    private Button health;
    private Button record;
    private Button gower;
    private Button lingsss;
    private CotinuousPresenter cotinuousPresenter;
    private LoginBean loginBean;
//    private IndicatorStayLayout relat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_hmoney);
        lingsss = findViewById(R.id.lingsss);
        qiandao = findViewById(R.id.qiandao);
        health = findViewById(R.id.health);
        record = findViewById(R.id.record);
        gower = findViewById(R.id.go_war);
//        relat = findViewById(R.id.relat);

        cotinuousPresenter = new CotinuousPresenter(new cotinus());
        LoginDaoUtil loginDaoUtil=new LoginDaoUtil();
        final List<String> intt = loginDaoUtil.intt(MyHMoneyActivity.this);
        cotinuousPresenter.reqeust(intt.get(0),intt.get(1));

//        WhetherPresenter whetherPresenter=new WhetherPresenter(new whether());
//        whetherPresenter.reqeust(intt.get(0),intt.get(1));
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHMoneyActivity.this,MyUserActivity.class);
                startActivity(intent);
            }
        });
        //首发
        gower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHMoneyActivity.this,PublishActivity.class);
                startActivity(intent);
            }
        });
        //首屏
        lingsss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHMoneyActivity.this,List_detailsActivity.class);
                startActivity(intent);
            }
        });
        //健康评测
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHMoneyActivity.this,WebActivity.class);
                intent.putExtra("webUrl","https://www.wjx.cn/jq/33939807.aspx");
                startActivity(intent);

            }
        });
        //完成我的档案
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHMoneyActivity.this,MyUserRecordActivity.class);
                startActivity(intent);
            }
        });
    }
//    class whether implements DataCall{
//        @Override
//        public void success(Object data, Object... args) {
//            Toast.makeText(MyHMoneyActivity.this,"签到成功",Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void fail(ApiException data, Object... args) {
//            Toast.makeText(MyHMoneyActivity.this,"签到失败",Toast.LENGTH_SHORT).show();
////            Glide.with(this).load().into();
//
//        }
//    }
//    @NonNull
//    private TextView getTextView() {
//        TextView textView = new TextView(MyHMoneyActivity.this);
//        int padding = dp2px(MyHMoneyActivity.this, 16);
//        textView.setPadding(padding, padding, padding, 0);
//        return textView;
//    }
//    public int dp2px(Context context, float dpValue) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
//    }
    class cotinus implements DataCall<Double>{
        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void success(Double data, Object... args) {
//            int i = Double.valueOf(data).intValue();
//            //签到的进度条
//            TextView textView1 = getTextView();
//            relat.addView(textView1);
//            IndicatorSeekBar discrete_ticks_texts =IndicatorSeekBar
//                    .with(MyHMoneyActivity.this)
//                    .max(8)
//                    .max(7)
//                    .min(1)
//                    .progress(i) //把几个点连起来
//                    .tickCount(7)//把进度条分成几个点
//                    .showTickMarksType(TickMarkType.OVAL)
//                    .tickMarksColor(getResources().getColor(R.color.color_blue, null))
//                    .tickMarksSize(13)//dp
//                    .showTickTexts(true)
//                    .tickTextsColor(getResources().getColor(R.color.color_blue, null))
//                    .tickTextsSize(13)//sp
//                    .tickTextsTypeFace(Typeface.MONOSPACE)
//                    .showIndicatorType(com.wd.MyHome.seekbarutils.IndicatorType.ROUNDED_RECTANGLE)
//                    .indicatorColor(getResources().getColor(R.color.color_blue, null))
//                    .indicatorTextColor(Color.parseColor("#ffffff"))
//                    .indicatorTextSize(13)//sp
//                            .thumbColor(getResources().getColor(R.color.color_blue, null))
//                    .thumbSize(14)//dp
//                    .trackProgressColor(getResources().getColor(R.color.color_blue, null))
//                    .trackProgressSize(4)//dp
//                    .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
//                    .trackBackgroundSize(2)//dp
//                    .build();
//            relat.addView(discrete_ticks_texts);

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
