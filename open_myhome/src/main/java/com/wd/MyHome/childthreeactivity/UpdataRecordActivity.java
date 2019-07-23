package com.wd.MyHome.childthreeactivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.MyUser.UserRecordBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.dingtao.common.util.StringUtils;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.ImageAdapter;
import com.wd.MyHome.childactivity.MyUserRecordActivity;
import com.wd.MyHome.presenter.UpRecordPresenter;
import com.wd.MyHome.presenter.UserRecordPresenter;
import com.wd.MyHome.util.CustomDatePickerDialogFragment;
import com.wd.MyHome.util.TopView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdataRecordActivity extends WDActivity {

    @BindView(R2.id.uprecordone)
    TopView uprecordone;
    @BindView(R2.id.uprecordtext_disease)
    EditText uprecordtextDisease;
    @BindView(R2.id.uprecordtextdisease_new)
    EditText uprecordtextdiseaseNew;
    @BindView(R2.id.uprecordtextdisease_long)
    EditText uprecordtextdiseaseLong;
    @BindView(R2.id.uprecordtexthospital_name)
    EditText uprecordtexthospitalName;
    @BindView(R2.id.uprecord_begin)
    TextView uprecordBegin;
    @BindView(R2.id.uprecord_begin_bt)
    ImageView uprecordBeginBt;
    @BindView(R2.id.uprecord_end)
    TextView uprecordEnd;
    @BindView(R2.id.uprecord_end_be)
    ImageView uprecordEndBe;
    @BindView(R2.id.addrecord_course)
    EditText addrecordCourse;
    @BindView(R2.id.uprecord_recycler)
    RecyclerView uprecordRecycler;
    @BindView(R2.id.uprecord_bt)
    Button uprecordBt;
    private String uid=null;
    private String sid=null;
    private int cid;
    private UserRecordPresenter userRecordPresenter;
    private UpRecordPresenter upRecordPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_updata_record;
    }

    @Override
    protected void initView() {
        uprecordone.setTitle("我的档案");
        //进去页面先进项查询 ， 然后进行赋值，之后进行修改请求接口
        //p
        userRecordPresenter = new UserRecordPresenter(new getuserrecord());//查看我的档案
        upRecordPresenter = new UpRecordPresenter(new getupdata());
        //点击进行修改
        uprecordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                String adddisease=uprecordtextDisease.getText().toString().trim();//症状
                String disease_new=uprecordtextdiseaseNew.getText().toString().trim();//现在的症状
                String disease_long=uprecordtextdiseaseLong.getText().toString().trim();//之前的症状
                String hospital_name=uprecordtexthospitalName.getText().toString().trim();//医院名字
                String record_begin=uprecordBegin.getText().toString().trim();//就诊时间
                String record_end=uprecordEnd.getText().toString().trim();//结束时间
                String record_course=addrecordCourse.getText().toString().trim();//治疗过程
                //进行判断
                if (TextUtils.isEmpty(adddisease)||TextUtils.isEmpty(disease_new)||TextUtils.isEmpty(disease_long)
                        ||TextUtils.isEmpty(hospital_name)||TextUtils.isEmpty(record_begin)||TextUtils.isEmpty(record_end)
                        ||TextUtils.isEmpty(record_course)){
                    Toast.makeText(UpdataRecordActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }{
                    //请求接口
                    //调用数据库进行获取uid
                    LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                    List<String> intt = loginDaoUtil.intt(UpdataRecordActivity.this);
                    upRecordPresenter.reqeust(intt.get(0),intt.get(1),cid,adddisease,disease_new,disease_long,
                            hospital_name,record_course,record_begin,record_end);
                }
            }
        });
    }
    class getupdata implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {

        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //查询档案返回的数据
    class getuserrecord implements DataCall<Result<UserRecordBean>> {
        @Override
        public void success(Result<UserRecordBean> data, Object... args) {
            cid=data.result.id;
            recordnew(data.result);
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }


    //添加数据
    private void recordnew(UserRecordBean list) {
        uprecordtextDisease.setText(list.diseaseMain);//主要病症
        uprecordtextdiseaseNew.setText(list.diseaseNow);//先病史
        uprecordtextdiseaseLong.setText(list.diseaseBefore);//以前的病史
        uprecordtexthospitalName.setText(list.treatmentHospitalRecent);//最近治疗医院
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String begString = formatter.format(list.treatmentStartTime);
        String endString = formatter.format(list.treatmentEndTime);
        uprecordBegin.setText(begString);//开始
        uprecordEnd.setText(endString);//结束时间
        addrecordCourse.setText(list.treatmentProcess);//治疗过程
        //加载动态图片
        if (StringUtils.isEmpty(list.picture)) {
        } else {
            String[] listimage = list.picture.split(",");
            ImageAdapter adapter = new ImageAdapter(UpdataRecordActivity.this);
            adapter.clear();
            for (int i = 0; i < listimage.length; i++) {
                adapter.add(listimage[i]);
            }
            if (listimage.length ==1) {
                uprecordRecycler.setLayoutManager(new GridLayoutManager(UpdataRecordActivity.this,1));
            }else if (listimage.length == 2 || listimage.length == 4) {
                uprecordRecycler.setLayoutManager(new GridLayoutManager(UpdataRecordActivity.this,2));
            } else {
                uprecordRecycler.setLayoutManager(new GridLayoutManager(UpdataRecordActivity.this,3));
            }
        }
    }


    @Override
    protected void destoryData() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(UpdataRecordActivity.this);
        userRecordPresenter.reqeust(intt.get(0), intt.get(1));
        uid=intt.get(0);sid=intt.get(1);
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
                    uprecordBegin.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                }else if(a==2){
                    uprecordEnd.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
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
