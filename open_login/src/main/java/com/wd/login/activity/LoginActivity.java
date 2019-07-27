package com.wd.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.dao.DaoMaster;
import com.dingtao.common.dao.LoginBeanDao;
import com.dingtao.common.util.Constant;
import com.wd.login.R;
import com.wd.login.R2;
import com.wd.login.presenter.LoginPresenter;
import com.wd.login.util.RsaCoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_LOGIN_LOGIN)
public class LoginActivity extends WDActivity {


    @BindView(R2.id.emialtext)
    EditText emialtext;
    @BindView(R2.id.passtext)
    EditText passtext;
    @BindView(R2.id.loginbt)
    Button loginbt;
    @BindView(R2.id.findpassbt)
    TextView findpassbt;
    @BindView(R2.id.registerbt)
    TextView registerbt;
    @BindView(R2.id.weixinbt)
    ImageView weixinbt;
    @BindView(R2.id.passhideshow)
    ImageView passhideshow;
    private  boolean pasVisibile = false;//密码显示隐藏
    private LoginPresenter loginPresenter;
    private LoginBeanDao dao;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //创建数据库
        dao=DaoMaster.newDevSession(LoginActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao();
        //p层
        loginPresenter = new LoginPresenter(new getlogindata());
        //登录点击
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取邮箱和密码
                String emila = emialtext.getText().toString().trim();
                String pass = passtext.getText().toString().trim();
                //进行判断
                if (TextUtils.isEmpty(emila) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    boolean email = isEmail(emila);
                    if (email) {//请求数据
                        try {
                            String s = RsaCoder.encryptByPublicKey(pass);
                            loginPresenter.reqeust(emila,s);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "邮箱格式不对", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //跳转注册
        registerbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_LOGIN_REGISTER);
            }
        });
        //点击找回密码
        findpassbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_LOGIN_FIND);
            }
        });
        //微信点击
        weixinbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "正在开发微信...", Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(LoginActivity.this).load(R.drawable.login_icon_hide_password_n).into(passhideshow);
        //密码的现实隐藏
        passhideshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pasVisibile) {//密码显示，则隐藏
                    passtext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Glide.with(LoginActivity.this).load(R.drawable.login_icon_hide_password_n).into(passhideshow);
                    pasVisibile = false;
                } else {//密码隐藏则显示
                    passtext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Glide.with(LoginActivity.this).load(R.drawable.login_icon_show_password).into(passhideshow);
                    pasVisibile = true;
                }
            }
        });
    }
    //登录成功返回回来的数值
    class getlogindata implements DataCall<LoginBean> {
        @Override
        public void success(LoginBean data, Object... args) {
            //先清空
            List<LoginBean> loginBeans = dao.loadAll();
            for (int i = 0; i < loginBeans.size(); i++) {
                LoginBean loginBean = loginBeans.get(i);
                if (loginBean.ttt==2){
                    loginBean.ttt=1;
                }
            }
            data.ttt=2;
            dao.insertOrReplaceInTx(loginBeans);
            dao.insertOrReplaceInTx(data);

            //跳转跳转
            //intentByRouter(Constant.ACTIVITY_LOGIN_MAIN);

            finish();
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
