package com.wd.MyHome.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.dao.DaoMaster;
import com.dingtao.common.dao.LoginBeanDao;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.XgncPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SzncActivity extends AppCompatActivity {

    @BindView(R2.id.sznc_top)
    TopView szncTop;
    @BindView(R2.id.wc)
    TextView wc;
    @BindView(R2.id.setname)
    TextView setname;
    @BindView(R2.id.setname_edit)
    EditText setnameEdit;
    @BindView(R2.id.x)
    ImageView x;
    private List<String> intt;
    private int a = 0;
    private int b = 0;
    private String userId;
    private String sessionId;
    private LoginBeanDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sznc);
        ButterKnife.bind(this);
        szncTop.setTitle("设置昵称");

        intt = new LoginDaoUtil().intt(this);
        setnameEdit.setText(intt.get(3));
        userId = intt.get(0);
        sessionId = intt.get(1);
        wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = intt.get(3);
                final String string1 = setnameEdit.getText().toString();
                if (string.equals(string1)){
                    wc.setBackgroundColor(Color.parseColor("#B0CFF2"));
                    Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_grey_n).into(x);
                    wc.setFocusableInTouchMode(true);
                    wc.setFocusable(true);
                    wc.requestFocus();
                }else {
                    XgncPresenter xgncPresenter = new XgncPresenter(new DataCall<Result>() {
                        @Override
                        public void success(Result data, Object... args) {


                        }

                        @Override
                        public void fail(ApiException data, Object... args) {

                        }
                    });
                    xgncPresenter.reqeust(userId,sessionId,string1);
                    dao = DaoMaster.newDevSession(SzncActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao();
                    List<LoginBean> loginBeans = dao.loadAll();
                    LoginBean loginBean = loginBeans.get(0);
                    loginBean.setNickName(string1);
                    dao.insertOrReplaceInTx(loginBean);
                    wc.setBackgroundColor(Color.parseColor("#B0CFF2"));
                    Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_grey_n).into(x);
                    wc.setFocusableInTouchMode(true);
                    wc.setFocusable(true);
                    wc.requestFocus();
                }
                hideSoftKeyboard(SzncActivity.this);
                finish();
            }
        });

       /* setname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setname.setVisibility(View.GONE);
                setnameEdit.setVisibility(View.VISIBLE);
                setnameEdit.setText(setname.getText());
                Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_n).into(x);
                a=1;
            }
        });
        setnameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_n).into(x);
                wc.setBackgroundColor(Color.parseColor("#619fe5"));
                b=1;


            }
        });*/
       setnameEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (hasFocus){
                   a=1;
                   Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_n).into(x);
                   wc.setBackgroundColor(Color.parseColor("#619fe5"));
               }else{

               }
           }
       });
        setnameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_n).into(x);
                wc.setBackgroundColor(Color.parseColor("#619fe5"));
                a=1;
                b=1;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                a=1;
                b=1;
                if (setnameEdit.getText().length()<1){
                    Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_grey_n).into(x);
                }else{
                    Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_n).into(x);
                    wc.setBackgroundColor(Color.parseColor("#619fe5"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a==1){
                    setnameEdit.setText("");
                    Glide.with(SzncActivity.this).load(R.drawable.common_icon_close_grey_n).into(x);
                    a=0;
                }else{

                }
            }
        });
    }
    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
