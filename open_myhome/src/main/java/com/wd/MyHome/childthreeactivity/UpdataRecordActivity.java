package com.wd.MyHome.childthreeactivity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dingtao.common.bean.MyUser.UserRecordBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.dingtao.common.util.StringUtils;
import com.google.gson.Gson;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.ImageAdapter;
import com.wd.MyHome.presenter.UpRecordPresenter;
import com.wd.MyHome.presenter.UserRecordPresenter;
import com.wd.MyHome.util.TopView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    private TimePickerView pvCustomTime;
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
        //开始
        uprecordBeginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(uprecordBegin);
                pvCustomTime.show();
            }
        });
        //结束
        uprecordEndBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(uprecordEnd);
                pvCustomTime.show();
            }
        });
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
                    String id= String.valueOf(cid);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("archivesId",id);
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
                    upRecordPresenter.reqeust(uid,sid,body);
                }
            }
        });
    }
    class getupdata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            finish();
            Toast.makeText(UpdataRecordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //查询档案返回的数据
    class getuserrecord implements DataCall<UserRecordBean> {
        @Override
        public void success(UserRecordBean data, Object... args) {
            cid=data.id;
            recordnew(data);
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
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
    //----------------------------------------------------------------------------选择时间
    private void initTime(final TextView view) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(UpdataRecordActivity.this, new OnTimeSelectListener() {
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
