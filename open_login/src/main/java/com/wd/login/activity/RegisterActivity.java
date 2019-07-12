package com.wd.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.wd.login.presenter.EmailPresenter;
import com.wd.login.presenter.RegisterPresenter;
import com.wd.login.util.RsaCoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_LOGIN_REGISTER)
public class RegisterActivity extends WDActivity {
    @BindView(R2.id.regemailtext)
    EditText regemailtext;
    @BindView(R2.id.verificationcodebt)
    Button verificationcodebt;
    @BindView(R2.id.verificationcodetext)
    EditText verificationcodetext;
    @BindView(R2.id.regpasstextone)
    EditText regpasstextone;
    @BindView(R2.id.regpassonehindshow)
    ImageView regpassonehindshow;
    @BindView(R2.id.regpasstexttwo)
    EditText regpasstexttwo;
    @BindView(R2.id.regpasstwohindshow)
    ImageView regpasstwohindshow;
    @BindView(R2.id.Invitationcodetext)
    EditText Invitationcodetext;
    @BindView(R2.id.register)
    Button register;
    private  boolean passone = false;//密码显示隐藏
    private  boolean passtwo = false;//密码显示隐藏
    private EmailPresenter emailPresenter;
    private RegisterPresenter registerPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        //创建所有p层
        registerPresenter = new RegisterPresenter(new getregister());
        emailPresenter = new EmailPresenter(new getverificationcodedata());
        //注册点击
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=regemailtext.getText().toString().trim();//邮箱
                String code=verificationcodetext.getText().toString().trim();//验证码
                String passone=regpasstextone.getText().toString().trim();//一次密码
                String passtwo=regpasstexttwo.getText().toString().trim();//二次密码
                String yaoqingcode=Invitationcodetext.getText().toString().trim();//邀请码
                if(TextUtils.isEmpty(em)||TextUtils.isEmpty(code)||TextUtils.isEmpty(passone)||TextUtils.isEmpty(passtwo)){
                    Toast.makeText(RegisterActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    if (passone.equals(passtwo)) {//如果两次密码一样进行邮箱判断
                        boolean email = isEmail(em);
                            if (email) {
                                //进行密码加密，发送请求
                                RsaCoder rsaCoder = new RsaCoder();
                                try {
                                    String a = rsaCoder.encryptByPublicKey(passone);
                                    String b = rsaCoder.encryptByPublicKey(passtwo);
                                    registerPresenter.reqeust(em,code,a,a,yaoqingcode);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else{
                                Toast.makeText(RegisterActivity.this, "邮箱格式不对", Toast.LENGTH_SHORT).show();
                            }
                    }else{
                        Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //获取验证码点击
        verificationcodebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext=regemailtext.getText().toString().trim();
                boolean email = isEmail(emailtext);
                if (TextUtils.isEmpty(emailtext)){
                    Toast.makeText(RegisterActivity.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                }else{
                    if (email) {
                        emailPresenter.reqeust(emailtext);
                    }else{
                        Toast.makeText(RegisterActivity.this, "邮箱格式不对", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Glide.with(RegisterActivity.this).load(R.drawable.login_icon_hide_password_n).into(regpassonehindshow);
        Glide.with(RegisterActivity.this).load(R.drawable.login_icon_hide_password_n).into(regpasstwohindshow);
        //密码的现实隐藏
        //第一个密码
        regpassonehindshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passone) {//密码显示，则隐藏
                    regpasstextone.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Glide.with(RegisterActivity.this).load(R.drawable.login_icon_hide_password_n).into(regpassonehindshow);
                    passone = false;
                } else {//密码隐藏则显示
                    regpasstextone.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Glide.with(RegisterActivity.this).load(R.drawable.login_icon_show_password).into(regpassonehindshow);
                    passone = true;
                }
            }
        });
        //第二个图
        regpasstwohindshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passtwo) {//密码显示，则隐藏
                    regpasstexttwo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Glide.with(RegisterActivity.this).load(R.drawable.login_icon_hide_password_n).into(regpasstwohindshow);
                    passtwo = false;
                } else {//密码隐藏则显示
                    regpasstexttwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Glide.with(RegisterActivity.this).load(R.drawable.login_icon_show_password).into(regpasstwohindshow);
                    passtwo = true;
                }
            }
        });
    }
    //返回回来的注册
    class getregister implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            finish();
            Toast.makeText(RegisterActivity.this, data.message,Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //返回回来的验证码
    class getverificationcodedata implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            Toast.makeText(RegisterActivity.this, data.message, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //判断邮箱
    /*** 验证邮箱输入是否合法* * @param strEmail* @return*/
    public static boolean isEmail(String strEmail) {//
        // String strPattern =// "^(
        // [a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
    @Override
    protected void destoryData() {

    }
}
