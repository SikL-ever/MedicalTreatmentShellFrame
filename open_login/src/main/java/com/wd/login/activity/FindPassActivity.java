package com.wd.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.wd.login.R;
import com.wd.login.R2;
import com.wd.login.presenter.EmailPresenter;
import com.wd.login.util.RsaCoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_LOGIN_FIND)
public class FindPassActivity extends WDActivity {

    @BindView(R2.id.findfinish)
    ImageView findfinish;
    @BindView(R2.id.findemailtext)
    EditText findemailtext;
    @BindView(R2.id.findbt)
    Button findbt;
    @BindView(R2.id.findverificationcodetext)
    EditText findverificationcodetext;
    @BindView(R2.id.findnext)
    Button findnext;
    private EmailPresenter emailPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_pass;
    }

    @Override
    protected void initView() {
        //p层
        emailPresenter = new EmailPresenter(new emaildata());
        //取消页面
        findfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取验证码
        findbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emila=findemailtext.getText().toString().trim();
                boolean email = isEmail(emila);
                if (email) {//请求数据
                    emailPresenter.reqeust(emila);
                }else{
                    Toast.makeText(FindPassActivity.this, "邮箱格式不对", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //下一步
        findnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emila=findemailtext.getText().toString().trim();
                String code=findverificationcodetext.getText().toString().trim();
                //进行判断
                if (TextUtils.isEmpty(emila) || TextUtils.isEmpty(code)) {
                    Toast.makeText(FindPassActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    boolean email = isEmail(emila);
                    if (email) {//请求数据
                        Intent intent = new Intent(FindPassActivity.this, ModificationPassActivity.class);
                        intent.putExtra("id",emila);
                        startActivity(intent);
                    }else{
                        Toast.makeText(FindPassActivity.this, "邮箱格式不对", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    //验证码返回的数据
    class emaildata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(FindPassActivity.this,"发送成功", Toast.LENGTH_SHORT).show();
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
