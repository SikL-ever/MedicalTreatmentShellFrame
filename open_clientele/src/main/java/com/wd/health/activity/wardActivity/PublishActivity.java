package com.wd.health.activity.wardActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.suke.widget.SwitchButton;
import com.wd.health.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PublishActivity extends AppCompatActivity {

    private ImageView start_time;
    private ImageView stop_time;
    private TextView start_text;
    private TextView stop_text;
    private TimePickerView pvCustomTime;
    private SwitchButton switchButton;
    private LinearLayout linearLayout;
    private ImageView add_iamge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        start_time = findViewById(R.id.start_time);
        stop_time = findViewById(R.id.stop_time);
        start_text = findViewById(R.id.start_text);
        stop_text = findViewById(R.id.stop_text);
        linearLayout = findViewById(R.id.linear);
        add_iamge = findViewById(R.id.add_image);
        //switchbutton按钮
        switchButton = findViewById(R.id.switchbutton);
        switchButton.setChecked(false);
        switchButton.isChecked();
        switchButton.toggle();
        switchButton.toggle(true);
        switchButton.setShadowEffect(true);
        switchButton.setEnabled(true);
        switchButton.setEnableEffect(false);
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                boolean b = switchButton.isChecked() == false;
                if (isChecked) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(start_text);
                pvCustomTime.show();
            }
        });
        stop_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(PublishActivity.this, "kkk", Toast.LENGTH_SHORT).show();
                initTime(stop_text);
                pvCustomTime.show();
            }
        });


        add_iamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void initTime(final TextView view) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(PublishActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(getTime(date));
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.time_choose, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {//完成按钮
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTitleText("城市选择")//标题
                .setTitleColor(Color.BLUE)//标题文字颜

                .setContentTextSize(19)//滚轮文字大小
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .isDialog(true)
                .build();

    }

    //时间格式转换
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


}
