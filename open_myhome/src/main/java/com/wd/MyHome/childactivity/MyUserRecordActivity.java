package com.wd.MyHome.childactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.wd.MyHome.childthreeactivity.AddRecordActivity;
import com.wd.MyHome.childthreeactivity.UpdataRecordActivity;
import com.wd.MyHome.presenter.DeleteRecordPresenter;
import com.wd.MyHome.presenter.UserRecordPresenter;
import com.wd.MyHome.util.TopView;
import com.wd.health.util.MyDialog;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyUserRecordActivity extends WDActivity {

    @BindView(R2.id.myuserrecordtop)
    TopView myuserrecordtop;
    @BindView(R2.id.recordaddbt)
    Button recordaddbt;
    @BindView(R2.id.recordno)
    RelativeLayout recordno;
    @BindView(R2.id.recordtextone)
    TextView recordtextone;
    @BindView(R2.id.recordtextnew)
    TextView recordtextnew;
    @BindView(R2.id.recordtextlong)
    TextView recordtextlong;
    @BindView(R2.id.recordtextname)
    TextView recordtextname;
    @BindView(R2.id.recordtexttime)
    TextView recordtexttime;
    @BindView(R2.id.recordtext)
    TextView recordtext;
    @BindView(R2.id.recordtextrecycler)
    RecyclerView recordtextrecycler;
    @BindView(R2.id.recordtextdelete)
    Button recordtextdelete;
    @BindView(R2.id.recordtextupdata)
    Button recordtextupdata;
    @BindView(R2.id.recordyes)
    LinearLayout recordyes;
    private int recordid;
    private String uid=null;
    private String sid=null;
    private UserRecordPresenter userRecordPresenter;
    private DeleteRecordPresenter deleteRecordPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_record;
    }

    @Override
    protected void initView() {
        //设置标题
        myuserrecordtop.setTitle("我的档案");
        //p层
        userRecordPresenter = new UserRecordPresenter(new getuserrecord());//查看我的档案
        deleteRecordPresenter = new DeleteRecordPresenter(new deleterecord());


        //添加档案点击
        recordaddbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到添加档案的页面
                Intent intent = new Intent(MyUserRecordActivity.this,AddRecordActivity.class);
                startActivity(intent);
            }
        });
        //删除档案
        recordtextdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=View.inflate(MyUserRecordActivity.this, com.wd.health.R.layout.videodialong_item,null);
                final MyDialog dialog = new MyDialog(MyUserRecordActivity.this, 200, 100, view, com.wd.health.R.style.dialog);
                dialog.show();
                final TextView cancel =
                        (TextView) view.findViewById(com.wd.health.R.id.cancel);
                final TextView confirm =
                        (TextView)view.findViewById(com.wd.health.R.id.confirm);
                final TextView text =
                        (TextView)view.findViewById(com.wd.health.R.id.textView10);
                text.setText("是否确认删除");
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //去购买本个视频
                        //改变状态值
                        deleteRecordPresenter.reqeust(uid,sid,recordid);
                        dialog.dismiss();
                    }
                });
            }
        });
        //修改档案
        recordtextupdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyUserRecordActivity.this, UpdataRecordActivity.class);
                startActivity(intent);
            }
        });
    }
    //查询档案返回的数据
    class getuserrecord implements DataCall<UserRecordBean> {
        @Override
        public void success(UserRecordBean data, Object... args) {
            if (data== null) {
                recordno.setVisibility(View.VISIBLE);
                recordyes.setVisibility(View.GONE);
            } else {
                recordyes.setVisibility(View.VISIBLE);
                recordno.setVisibility(View.GONE);
                //添加数据
                recordnew(data);
                recordid=data.id;
            }
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //删除档案
    class deleterecord implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            userRecordPresenter.reqeust(uid, sid);
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    @Override
    protected void destoryData() {

    }
    //添加数据
    private void recordnew(UserRecordBean list) {
        recordtextone.setText(list.diseaseMain);//主要病症
        recordtextnew.setText(list.diseaseNow);//先病史
        recordtextlong.setText(list.diseaseBefore);//以前的病史
        recordtextname.setText(list.treatmentHospitalRecent);//最近治疗医院
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String begString = formatter.format(list.treatmentStartTime);
        String endString = formatter.format(list.treatmentEndTime);
        recordtexttime.setText(begString+"--"+endString);//开始结束时间
        recordtext.setText(list.treatmentProcess);//治疗过程
        //加载动态图片
        if (StringUtils.isEmpty(list.picture)) {
        } else {
            String[] listimage = list.picture.split(",");
            ImageAdapter adapter = new ImageAdapter(MyUserRecordActivity.this);
            adapter.clear();
            for (int i = 0; i < listimage.length; i++) {
                adapter.add(listimage[i]);
            }
            recordtextrecycler.setAdapter(adapter);
            if (listimage.length ==0) {
                recordtextrecycler.setLayoutManager(new GridLayoutManager(MyUserRecordActivity.this,1));
            }else if (listimage.length == 2 || listimage.length == 4) {
                recordtextrecycler.setLayoutManager(new GridLayoutManager(MyUserRecordActivity.this,2));
            } else {
                recordtextrecycler.setLayoutManager(new GridLayoutManager(MyUserRecordActivity.this,3));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MyUserRecordActivity.this);
        userRecordPresenter.reqeust(intt.get(0), intt.get(1));
        uid=intt.get(0);sid=intt.get(1);
    }
}

