package com.wd.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.wd.login.R;
import com.wd.login.R2;
import com.wd.login.presenter.AnewPassPresenter;
import com.wd.login.util.RsaCoder;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_LOGIN_MODIFICATIONPASS)
public class ModificationPassActivity extends WDActivity {


    @BindView(R2.id.modfinsh)
    ImageView modfinsh;
    @BindView(R2.id.modpasstextone)
    EditText modpasstextone;
    @BindView(R2.id.modpasstwohindshow)
    ImageView modpasstwohindshow;
    @BindView(R2.id.modpasstexttwo)
    EditText modpasstexttwo;
    @BindView(R2.id.modpassonehindshow)
    ImageView modpassonehindshow;
    @BindView(R2.id.modok)
    Button modok;
    private  boolean passone = false;//密码显示隐藏
    private  boolean passtwo = false;//密码显示隐藏
    private AnewPassPresenter anewPassPresenter;
    public  String id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_modification_pass;
    }

    @Override
    protected void initView() {
        //创建p层
        anewPassPresenter = new AnewPassPresenter(new getdata());
        //接受传过来的数据邮箱
        id = getIntent().getStringExtra("id");
        //返回点击
        modfinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //密码显示隐藏一
        Glide.with(ModificationPassActivity.this).load(R.drawable.login_icon_hide_password_n).into(modpassonehindshow);
        Glide.with(ModificationPassActivity.this).load(R.drawable.login_icon_hide_password_n).into(modpasstwohindshow);
        modpassonehindshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passone) {//密码显示，则隐藏
                    modpasstextone.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Glide.with(ModificationPassActivity.this).load(R.drawable.login_icon_hide_password_n).into(modpassonehindshow);
                    passone = false;
                } else {//密码隐藏则显示
                    modpasstextone.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Glide.with(ModificationPassActivity.this).load(R.drawable.login_icon_show_password).into(modpassonehindshow);
                    passone = true;
                }
            }
        });
        //第二个
        modpasstwohindshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passtwo) {//密码显示，则隐藏
                    modpasstexttwo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Glide.with(ModificationPassActivity.this).load(R.drawable.login_icon_hide_password_n).into(modpasstwohindshow);
                    passtwo = false;
                } else {//密码隐藏则显示
                    modpasstexttwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Glide.with(ModificationPassActivity.this).load(R.drawable.login_icon_show_password).into(modpasstwohindshow);
                    passtwo = true;
                }
            }
        });

        //完成点击
        modok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwdone=modpasstextone.getText().toString().trim();
                String pwdtwo=modpasstexttwo.getText().toString().trim();
                //进行判断
                if (pwdone.equals(pwdtwo)) {//如果两次密码一样进行邮箱判断
                    try {
                        String s = RsaCoder.encryptByPublicKey(pwdone);
                        anewPassPresenter.reqeust(id,s,s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(ModificationPassActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    //修改密码回来的数据
    class getdata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(ModificationPassActivity.this,"修改密码成功", Toast.LENGTH_SHORT).show();
            //清空栈
            //到登录页面
            //清空栈顶创建登录activity
            Intent in = new Intent(ModificationPassActivity.this,LoginActivity.class);
            in.setFlags(in.FLAG_ACTIVITY_CLEAR_TASK | in.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    @Override
    protected void destoryData() {

    }
}
