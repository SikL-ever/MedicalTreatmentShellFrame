package com.wd.MyHome.childthreeactivity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.gson.Gson;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.AddRecordPresenter;
import com.wd.MyHome.util.TopView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddRecordActivity extends WDActivity {

    @BindView(R2.id.addrecordone)
    TopView addrecordone;
    @BindView(R2.id.addrecordtext_disease)
    EditText addrecordtextDisease;
    @BindView(R2.id.addrecordtextdisease_new)
    EditText addrecordtextdiseaseNew;
    @BindView(R2.id.addrecordtextdisease_long)
    EditText addrecordtextdiseaseLong;
    @BindView(R2.id.addrecordtexthospital_name)
    EditText addrecordtexthospitalName;
    @BindView(R2.id.addrecord_begin)
    TextView addrecordBegin;
    @BindView(R2.id.addrecord_begin_bt)
    ImageView addrecordBeginBt;
    @BindView(R2.id.addrecord_end)
    TextView addrecordEnd;
    @BindView(R2.id.addrecord_end_be)
    ImageView addrecordEndBt;
    @BindView(R2.id.addrecord_course)
    EditText addrecordCourse;
    @BindView(R2.id.addrecord_recycler)
    GridView addrecordRecycler;
    @BindView(R2.id.addrecord_bt)
    Button addrecordBt;
    private AddRecordPresenter addRecordPresenter;//添加的p层
    private TimePickerView pvCustomTime;
    //-------------------------相机数据
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_record;
    }

    @Override
    protected void initView() {
        //创建p层
        addRecordPresenter = new AddRecordPresenter(new getadddata());
        addrecordone.setTitle("添加档案");
        //时间点击
        addrecordBeginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(addrecordBegin);
                pvCustomTime.show();
            }
        });
        //时间结束点解
        addrecordEndBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(addrecordEnd);
                pvCustomTime.show();
            }
        });
        //添加点击
        addrecordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                String adddisease=addrecordtextDisease.getText().toString().trim();//症状
                String disease_new=addrecordtextdiseaseNew.getText().toString().trim();//现在的症状
                String disease_long=addrecordtextdiseaseLong.getText().toString().trim();//之前的症状
                String hospital_name=addrecordtexthospitalName.getText().toString().trim();//医院名字
                String record_begin=addrecordBegin.getText().toString().trim();//就诊时间
                String record_end=addrecordEnd.getText().toString().trim();//结束时间
                String record_course=addrecordCourse.getText().toString().trim();//治疗过程
                //进行判断
                if (TextUtils.isEmpty(adddisease)||TextUtils.isEmpty(disease_new)||TextUtils.isEmpty(disease_long)
                        ||TextUtils.isEmpty(hospital_name)||TextUtils.isEmpty(record_begin)||TextUtils.isEmpty(record_end)
                        ||TextUtils.isEmpty(record_course)){
                    Toast.makeText(AddRecordActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }{
                    //请求接口
                    //调用数据库进行获取uid
                    LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                    List<String> intt = loginDaoUtil.intt(AddRecordActivity.this);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("diseaseMain",adddisease);
                    map.put("diseaseNow",disease_new);
                    map.put("diseaseBefore",disease_long);
                    map.put("treatmentHospitalRecent",hospital_name);
                    map.put("treatmentProcess",record_course);
                    map.put("treatmentStartTime",record_begin);
                    map.put("treatmentEndTime",record_end);
                    Gson gson = new Gson();
                    String s = gson.toJson(map);
                    RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),s);
                    addRecordPresenter.reqeust(intt.get(0),intt.get(1),body);
                }
            }
        });
        //----------------------------------------------------------------------------微信朋友圈

    }
    //返回来的数据
    class getadddata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            finish();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    @Override
    protected void destoryData() {

    }
    //----------------------------------------------------------------------------选择照片回来的数据




    //----------------------------------------------------------------------------选择时间
    private void initTime(final TextView view) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(AddRecordActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(getTime(date));
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(com.wd.health.R.layout.time_choose, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(com.wd.health.R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(com.wd.health.R.id.iv_cancel);
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
