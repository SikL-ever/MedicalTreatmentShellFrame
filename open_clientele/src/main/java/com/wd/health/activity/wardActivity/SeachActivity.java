package com.wd.health.activity.wardActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.wardBean.SeachBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.wardmateadapter.SeachAdapater;
import com.wd.health.presenter.wardmatepresenter.SeachPresenter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SeachActivity extends AppCompatActivity {


    @BindView(R2.id.names)
    ImageView names;
    @BindView(R2.id.goner)
    RelativeLayout goner;
    @BindView(R2.id.seachrecyclerview)
    RecyclerView seachrecyclerview;
    @BindView(R2.id.seach_text)
    TextView seachText;
    @BindView(R2.id.seach_edit)
    EditText seachEdit;
    private SeachAdapater seachAdapater;
    private SeachPresenter seachPresenter;
    private String keyWord = "面神经炎";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        ButterKnife.bind(this);

        seachPresenter = new SeachPresenter(new seach());
        seachPresenter.reqeust(keyWord);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        seachAdapater = new SeachAdapater(this);
        seachrecyclerview.setLayoutManager(linearLayoutManager);
        seachrecyclerview.setAdapter(seachAdapater);
        seachText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = seachEdit.getText().toString();
                if(TextUtils.isEmpty(s)){
                    Toast.makeText(SeachActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    seachPresenter.reqeust(s);
                    seachAdapater.notifyDataSetChanged();
                }
            }
        });
        names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class seach implements DataCall<List<SeachBean>> {

        @Override
        public void success(List<SeachBean> data, Object... args) {
            seachAdapater.setseach(data);
            seachAdapater.notifyDataSetChanged();
            if(data.size()==0){
                goner.setVisibility(View.VISIBLE);
            }else{
                goner.setVisibility(View.GONE);
                seachAdapater.setseach(data);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
