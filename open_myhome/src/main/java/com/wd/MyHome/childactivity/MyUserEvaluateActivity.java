package com.wd.MyHome.childactivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyUserEvaluateXingAdapter;
import com.wd.MyHome.adapter.MyUserEvaluateXingTwoAdapter;
import com.wd.MyHome.childthreeactivity.FinishActivity;
import com.wd.MyHome.presenter.MyUserevaluatedoctorPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MyUserEvaluateActivity extends WDActivity {


    @BindView(R2.id.myuser_evaluate_top)
    TopView myuserEvaluateTop;
    @BindView(R2.id.myuser_evaluate_text)
    EditText myuserEvaluateText;
    @BindView(R2.id.myuser_evaluate_recyclerone)
    RecyclerView myuserEvaluateRecyclerone;
    @BindView(R2.id.myuser_evaluate_recyclertwo)
    RecyclerView myuserEvaluateRecyclertwo;
    @BindView(R2.id.myuser_evaluate_finish)
    Button myuserEvaluateFinish;
    @BindView(R2.id.myuser_evaluate_ok)
    Button myuserEvaluateOk;
    private MyUserEvaluateXingAdapter myUserEvaluateXingAdapter;
    private MyUserEvaluateXingTwoAdapter myUserEvaluateXingtwoAdapter;
    private MyUserevaluatedoctorPresenter myUserevaluatedoctorPresenter;
    int a1,b;
    private int did;//医生id
    private int aid;//问诊id

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_evaluate;
    }

    @Override
    protected void initView() {
        //穿过来过去医生id问诊id.
        did = getIntent().getIntExtra("did", 0);//医生id
        aid = getIntent().getIntExtra("aid", 0);//问诊id
        //p层
        myUserevaluatedoctorPresenter = new MyUserevaluatedoctorPresenter(new getdta());
        myuserEvaluateTop.setTitle("评价问诊服务");
        //创建适配器
        myUserEvaluateXingAdapter = new MyUserEvaluateXingAdapter(MyUserEvaluateActivity.this);
        myUserEvaluateXingtwoAdapter = new MyUserEvaluateXingTwoAdapter(MyUserEvaluateActivity.this);
        //设置
        myuserEvaluateRecyclerone.setAdapter(myUserEvaluateXingAdapter);
        myuserEvaluateRecyclerone.setLayoutManager
                (new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myuserEvaluateRecyclertwo.setAdapter(myUserEvaluateXingtwoAdapter);
        myuserEvaluateRecyclertwo.setLayoutManager
                (new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myUserEvaluateXingAdapter.setClakBack(new MyUserEvaluateXingAdapter.ClakBack() {
            @Override
            public void getdta(int a) {
                a1=a;
            }
        });
        myUserEvaluateXingtwoAdapter.setClakBack(new MyUserEvaluateXingTwoAdapter.ClakBack() {
            @Override
            public void getdta(int a) {
                b=a;
            }
        });
        //退出
        myuserEvaluateFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //完成
        myuserEvaluateOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                String trim = myuserEvaluateText.getText().toString().trim();
                if (TextUtils.isEmpty(trim)){
                    Toast.makeText(MyUserEvaluateActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    if (a1 == 0 || b == 0) {
                        Toast.makeText(MyUserEvaluateActivity.this, "至少给一个星星啊", Toast.LENGTH_SHORT).show();
                    }else{
                        List<String> intt = new LoginDaoUtil().intt(MyUserEvaluateActivity.this);
                        Log.i("aaa", "onClick: "+did);
                        myUserevaluatedoctorPresenter.reqeust(intt.get(0),intt.get(1),aid,did,trim,a1,b);
                    }
                }
            }
        });
    }
    @Override
    protected void destoryData() {

    }
    //*********************************************
    //评论回来的数据
    class getdta implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            Log.i("kkk", "success: "+1111);
            //跳转页面
            Intent intent = new Intent(MyUserEvaluateActivity.this,FinishActivity.class);
            intent.putExtra("id",aid);
            startActivity(intent);
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //*********************************************

}
