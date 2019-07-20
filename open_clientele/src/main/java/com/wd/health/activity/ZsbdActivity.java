package com.wd.health.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.CjypBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.bean.homepage.WzzxBean1;
import com.dingtao.common.bean.homepage.ZhuBean;
import com.dingtao.common.bean.homepage.ZhuBean1;
import com.dingtao.common.bean.homepage.ZiBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.ZsbdAdapter;
import com.wd.health.adapter.homepageadapter.ZsbdAdapter1;
import com.wd.health.adapter.homepageadapter.ZsbdAdapter2;
import com.wd.health.fragment.Fragment01;
import com.wd.health.fragment.Fragment02;
import com.wd.health.presenter.homepagepresenter.CjypPresenter;
import com.wd.health.presenter.homepagepresenter.WzzxPresenter;
import com.wd.health.presenter.homepagepresenter.ZhuPresenter;
import com.wd.health.presenter.homepagepresenter.ZiPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ZsbdActivity extends AppCompatActivity {

    @BindView(R2.id.images)
    ImageView images;
    @BindView(R2.id.edit)
    TextView edit;
    @BindView(R2.id.xiaoxi)
    CheckBox xiaoxi;

    @BindView(R2.id.cjbz_title)
    RadioButton cjbzTitle;
    @BindView(R2.id.cyyp_title)
    RadioButton cyypTitle;
    @BindView(R2.id.group)
    RadioGroup group;


    @BindView(R2.id.framelayout)
    FrameLayout framelayout;
    private int type;
    private WzzxPresenter wzzxPresenter;
    private  ZsbdAdapter zsbdAdapter;
    private ZiPresenter ziPresenter;
    private ZsbdAdapter1 zsbdAdapter1;
//    private ZsbdAdapter2 zsbdAdapter2;
    private ZhuPresenter zhuPresenter;
    private Fragment01 fragment01;
    private Fragment02 fragment02;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zsbd);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 101);


/*
        zhuRecycler.setLayoutManager(new LinearLayoutManager(this));
        zsbdAdapter2 = new ZsbdAdapter2(ZsbdActivity.this);
        zhuRecycler.setAdapter(zsbdAdapter2);*/
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        if (type == 1) {
            fragment01 = new Fragment01();
            transaction.add(R.id.framelayout,fragment01);
            transaction.show(fragment01);
            transaction.commit();
            cjbzTitle.setChecked(true);
            cyypTitle.setChecked(false);
           /* wzzxPresenter = new WzzxPresenter(new Wzzx1());
            wzzxPresenter.reqeust();*/
        } else {
        //    CjypPresenter cjypPresenter = new CjypPresenter(new CjypShow());
            fragment02 = new Fragment02();
            transaction.add(R.id.framelayout,fragment02);
            transaction.show(fragment02);
            transaction.commit();
            cjbzTitle.setChecked(false);
            cyypTitle.setChecked(true);
        }
        list = new ArrayList<>();
        list.add(fragment01);
        list.add(fragment02);
        initListener();
    }

  /*  private class Wzzx1 implements DataCall<List<WzzxBean>> {
        @Override
        public void success(List<WzzxBean> data, Object... args) {
            zsbdAdapter.setList(data);
            zsbdAdapter.setOnRecyclerViewItemClickListener(new ZsbdAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                    zsbdAdapter.setThisPosition(position);
                    //嫑忘记刷新适配器
                    zsbdAdapter.notifyDataSetChanged();
                }
            });
            zsbdAdapter.notifyDataSetChanged();
            ziPresenter = new ZiPresenter(new ZiShow());
            ziPresenter.reqeust(data.get(0).getId());
            zsbdAdapter.getID(new ZsbdAdapter.CallBackID() {
                @Override
                public void callback(int id) {
                    ziPresenter = new ZiPresenter(new ZiShow());
                    ziPresenter.reqeust(id);
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("aaaaaaaaaaa443", data.getDisplayMessage());
        }
    }

    private class ZiShow implements DataCall<List<ZiBean>> {
        @Override
        public void success(List<ZiBean> data, Object... args) {
            zsbdAdapter1.setList(data);
            zsbdAdapter1.getID1(new ZsbdAdapter1.CallBackID() {
                @Override
                public void callback(int id) {
                    zhuPresenter = new ZhuPresenter(new zhushow());
                    zhuPresenter.reqeust(id);
                 //   start_layout.setVisibility(View.VISIBLE);
                }
            });
        }
        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class zhushow implements DataCall<ZhuBean> {
        @Override
        public void success(ZhuBean data, Object... args) {
            itembl.setText(data.getPathology());
            itemzz.setText(data.getSymptom());
            itemyyj.setText(data.getBenefitTaboo());
            itemxyzl.setText(data.getWesternMedicineTreatment());
            itemzyzl.setText(data.getChineseMedicineTreatment());
            *//*Intent intent = new Intent(ZsbdActivity.this,ZsbdActivity.class);
            intent.putExtra("id", 1);
            startActivity(intent);*//*
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }*/
   /* protected void onRestart() {
        Log.e("zz","onRestart");
        int id= getIntent().getIntExtra("id",0);
        if(id==1){
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            hideFragment(transaction);
            if (checkedId == R.id.cjbz_title) {
                if (fragment01 == null) {
                    fragment01 = new Fragment01();
                    transaction.add(R.id.framelayout, fragment01);
                } else {
                    transaction.show(fragment01);
                }
                //supportFragmentManager.beginTransaction().replace(R.id.framelayout,fragment01).commit();
            }
            transaction.commit();
        } super.onRestart();
    }*/
    private void initListener() {
      group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              FragmentManager supportFragmentManager = getSupportFragmentManager();
              FragmentTransaction transaction = supportFragmentManager.beginTransaction();
              hideFragment(transaction);
              if (checkedId == R.id.cjbz_title) {
                  if (fragment01 == null) {
                      fragment01 = new Fragment01();
                      transaction.add(R.id.framelayout, fragment01);
                  } else {
                      transaction.show(fragment01);
                  }
                  //supportFragmentManager.beginTransaction().replace(R.id.framelayout,fragment01).commit();

              } else if (checkedId == R.id.cyyp_title) {
                  if (fragment02 == null) {
                      fragment02 = new Fragment02();
                      transaction.add(R.id.framelayout, fragment02);
                  } else {
                      transaction.show(fragment02);
                  }

              }
              transaction.commit();
          }
      });

    }
    private void hideFragment(FragmentTransaction transaction) {
        if (fragment01 != null) {
            //隐藏
            transaction.hide(fragment01);
        }
        if (fragment02 != null) {
            //隐藏
            transaction.hide(fragment02);
        }
    }
   /* */


}
