package com.wd.MyHome.childthreeactivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.AddRecordPresenter;
import com.wd.MyHome.util.CustomDatePickerDialogFragment;
import com.wd.MyHome.util.TopView;

import java.util.Calendar;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

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
    RecyclerView addrecordRecycler;
    @BindView(R2.id.addrecord_bt)
    Button addrecordBt;
    private AddRecordPresenter addRecordPresenter;

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
                showDatePickDialog(1);
            }
        });
        //时间结束点解
        addrecordEndBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog(2);
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
                    addRecordPresenter.reqeust(intt.get(0),intt.get(1),adddisease,disease_new,disease_long,
                            hospital_name,record_course,record_begin,record_end);
                }
            }
        });
    }
    //返回来的数据
    class getadddata implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            finish();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    @Override
    protected void destoryData() {

    }
    //时间选择器
    long day = 24 * 60 * 60 * 1000;
    private void showDatePickDialog(final int a) {
        CustomDatePickerDialogFragment fragment = new CustomDatePickerDialogFragment();
        //fragment.setOnSelectedDateListener((CustomDatePickerDialogFragment.OnSelectedDateListener) AddRecordActivity.this);
        fragment.setOnSelectedDateListener(new CustomDatePickerDialogFragment.OnSelectedDateListener() {
            @Override
            public void onSelectedDate(int year, int monthOfYear, int dayOfMonth) {
                if (a == 1) {
                    addrecordBegin.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                }else if(a==2){
                    addrecordEnd.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                }
            }
        });
        Bundle bundle = new Bundle();
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTimeInMillis(System.currentTimeMillis());
        currentDate.set(Calendar.HOUR_OF_DAY,0);
        currentDate.set(Calendar.MINUTE,0);
        currentDate.set(Calendar.SECOND,0);
        currentDate.set(Calendar.MILLISECOND,0);
        bundle.putSerializable(CustomDatePickerDialogFragment.CURRENT_DATE,currentDate);
        long start = currentDate.getTimeInMillis() - day * 2;
        long end = currentDate.getTimeInMillis() - day;
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(start);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(end);
        bundle.putSerializable(CustomDatePickerDialogFragment.START_DATE,startDate);
        bundle.putSerializable(CustomDatePickerDialogFragment.END_DATE,currentDate);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(),CustomDatePickerDialogFragment.class.getSimpleName());
    }
}
