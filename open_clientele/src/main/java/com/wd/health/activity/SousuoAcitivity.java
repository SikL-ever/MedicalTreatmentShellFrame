package com.wd.health.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.homepage.LsjlBean;
import com.dingtao.common.bean.homepage.RmssBean;
import com.dingtao.common.bean.homepage.SousuoBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.dao.DaoMaster;
import com.dingtao.common.dao.LsjlBeanDao;
import com.wd.health.CustomImageView.FlowLayout;
import com.wd.health.CustomImageView.MyListView;
import com.wd.health.Helper.RecordsDao;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.LsjlAdapter;
import com.wd.health.adapter.homepageadapter.SearchRecordsAdapter;
import com.wd.health.presenter.homepagepresenter.RmssPresenter;
import com.wd.health.presenter.homepagepresenter.SousuoPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SousuoAcitivity extends AppCompatActivity {

    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.edit_item)
    EditText edit_item;
    @BindView(R2.id.sousuo1)
    TextView sousuo1;
    @BindView(R2.id.tv_history)
    TextView tv_history;
    @BindView(R2.id.yizhaodao)
    LinearLayout yizhaodao;
    @BindView(R2.id.weizhaodao)
    LinearLayout weizhaodao;
    @BindView(R2.id.search_content_show_ll)
    LinearLayout searchRecordsLl;
    @BindView(R2.id.flow)
    FlowLayout flowLayout;
    private LsjlAdapter lsjlAdapter;
    private SearchRecordsAdapter recordsAdapter;
    private View recordsHistoryView;
    private ListView recordsListLv;
  //  private TextView clearAllRecordsTv;

    private List<String> searchRecordsList;
    private List<String> tempList;
    private RecordsDao recordsDao;
    private LinearLayout.LayoutParams layoutParams;
    private List<String> list = new ArrayList<>();
    private RmssPresenter rmssPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo_acitivity);
        ButterKnife.bind(this);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rmssPresenter = new RmssPresenter(new RmssShow());
        rmssPresenter.reqeust();
//往容器内添加TextView数据
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        /*for (int i = 0; i <10; i++) {
            list.add("Android");
            list.add("Java");
            list.add("IOS");
            list.add("python");
        }
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(28, 10, 28, 10);
            tv.setText(list.get(i));
            tv.setMaxEms(10);
            tv.setSingleLine();
          //  tv.setBackgroundResource(R.drawable.selector_playsearch);
            tv.setLayoutParams(layoutParams);
            flowLayout.addView(tv, layoutParams);
        }*/
        /*recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lsjlAdapter = new LsjlAdapter(this);
        recyclerView.setAdapter(lsjlAdapter);
        sousuo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = edit_item.getText().toString().trim();
                if (trim==null){
                    Toast.makeText(SousuoAcitivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }else{
                    LsjlBeanDao bean = DaoMaster.newDevSession(SousuoAcitivity.this,LsjlBeanDao.TABLENAME).getLsjlBeanDao();
                    List<LsjlBean> lsjlBeans = bean.loadAll();
                    lsjlAdapter.setlist(lsjlBeans);

                    *//*if (!lsjlBeans) {
                        insertData(editItem.getText().toString().trim());
                        //insertData(R.drawable.message_icon_close_n);
                        queryData("");
                    }*//*
                    *//*boolean hasData = hasData(editItem.getText().toString().trim());
                    if (!hasData) {
                        insertData(editItem.getText().toString().trim());
                        //insertData(R.drawable.message_icon_close_n);
                        queryData("");
                    }*//*
                }
            }
        });*/
        // 搜索框的文本变化实时监听
        /*edit_item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    tv_tip.setText("搜索历史");
                } else {
                    tv_tip.setText("搜索结果");
                }
                String tempName = edit_item.getText().toString();
                // 根据tempName去模糊查询数据库中有没有数据
               // queryData(tempName);

            }
        });*/

        initRecordsView();
        //添加搜索view
        searchRecordsLl.addView(recordsHistoryView);

        initData();
        bindAdapter();
        initListener();
        sousuo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_item.getText().toString().length() > 0) {

                    String record = edit_item.getText().toString();
                    SousuoPresenter sousuoPresenter = new SousuoPresenter(new sousuo());
                    //判断数据库中是否存在该记录
                    if (!recordsDao.isHasRecord(record)) {
                        tempList.add(record);
                    }
                    //将搜索记录保存至数据库中
                    recordsDao.addRecords(record);
                    reversedList();
                    checkRecordsSize();
                    recordsAdapter.notifyDataSetChanged();
                    Toast.makeText(SousuoAcitivity.this, "11",Toast.LENGTH_SHORT).show();
                    //根据关键词去搜索

                } else {
                    Toast.makeText(SousuoAcitivity.this, "搜索内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        recordsAdapter.getback(new SearchRecordsAdapter.CallBackOnClick() {
            @Override
            public void onclicket(int aid) {
                recordsDao.delete(aid);
                tempList.remove(aid);
              //  Toast.makeText(SousuoAcitivity.this, "删除"+aid,Toast.LENGTH_SHORT).show();
                recordsAdapter.notifyDataSetChanged();
                /*tempList.clear();
                reversedList();
                recordsDao.deleteAllRecords();
                recordsAdapter.notifyDataSetChanged();
                searchRecordsLl.setVisibility(View.GONE);
                edit_item.setHint("请输入你要搜索的内容");*/
            }
        });
    }

    //初始化搜索历史记录View
    private void initRecordsView() {
        recordsHistoryView = LayoutInflater.from(this).inflate(R.layout.search_lishi, null);
        //显示历史记录lv
        recordsListLv = (ListView) recordsHistoryView.findViewById(R.id.search_records_lv);
        //清除搜索历史记录
     //   clearAllRecordsTv = (TextView) recordsHistoryView.findViewById(R.id.clear_all_records_tv);
    }
    private void initData() {
        recordsDao = new RecordsDao(this);
        searchRecordsList = new ArrayList<>();
        tempList = new ArrayList<>();
        tempList.addAll(recordsDao.getRecordsList());

        reversedList();
        //第一次进入判断数据库中是否有历史记录，没有则不显示
        checkRecordsSize();
    }
    private void bindAdapter() {
        recordsAdapter = new SearchRecordsAdapter(this, tempList);
        recordsListLv.setAdapter(recordsAdapter);
    }

    private void initListener() {
       // clearAllRecordsTv.setOnClickListener(this);

        edit_item.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (edit_item.getText().toString().length() > 0) {

                        String record = edit_item.getText().toString();

                        //判断数据库中是否存在该记录
                        if (!recordsDao.isHasRecord(record)) {
                            tempList.add(record);
                        }
                        //将搜索记录保存至数据库中
                        recordsDao.addRecords(record);
                        reversedList();
                        checkRecordsSize();
                        recordsAdapter.notifyDataSetChanged();
                        Toast.makeText(SousuoAcitivity.this, "11",Toast.LENGTH_SHORT).show();
                        //根据关键词去搜索

                    } else {
                        Toast.makeText(SousuoAcitivity.this, "搜索内容不能为空",Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        //根据输入的信息去模糊搜索
        edit_item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    tv_history.setText("搜索相关");
                } else {
                    tv_history.setText("搜索结果");
                }
                String tempName = edit_item.getText().toString();
                tempList.clear();
                tempList.addAll(recordsDao.querySimlarRecord(tempName));
                reversedList();
                checkRecordsSize();
                recordsAdapter.notifyDataSetChanged();
            }
        });
        //历史记录点击事件
        /*recordsListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将获取到的字符串传到搜索结果界面
                //点击后搜索对应条目内容
//                searchContentEt.setText(searchRecordsList.get(position));
                Toast.makeText(SousuoAcitivity.this,searchRecordsList.get(position)+"",Toast.LENGTH_SHORT).show();
                edit_item.setSelection(edit_item.length());
            }
        });*/
    }

    //当没有匹配的搜索数据的时候不显示历史记录栏
    private void checkRecordsSize(){
        if(searchRecordsList.size() == 0){
            searchRecordsLl.setVisibility(View.GONE);
        }else{
            searchRecordsLl.setVisibility(View.VISIBLE);
        }
    }

  /*  @Override
    public void onClick(View v) {
        switch (v.getId()){
            //清空所有历史数据
            case R.id.clear_all_records_tv:
                tempList.clear();
                reversedList();
                recordsDao.deleteAllRecords();
                recordsAdapter.notifyDataSetChanged();
                searchRecordsLl.setVisibility(View.GONE);
                edit_item.setHint("请输入你要搜索的内容");
                break;
        }
    }*/

    //颠倒list顺序，用户输入的信息会从上依次往下显示
    private void reversedList(){
        searchRecordsList.clear();
        for(int i = tempList.size() - 1 ; i >= 0 ; i --) {
            searchRecordsList.add(tempList.get(i));
        }
    }


    private class sousuo implements DataCall<SousuoBean> {


        List<SousuoBean.DrugsSearchVoListBean> drugsSearchVoList;

        @Override
        public void success(SousuoBean data, Object... args) {
            List<SousuoBean.DiseaseSearchVoListBean> diseaseSearchVoList = data.getDiseaseSearchVoList();
            List<SousuoBean.DoctorSearchVoListBean> doctorSearchVoList = data.getDoctorSearchVoList();
            List<SousuoBean.DrugsSearchVoListBean> drugsSearchVoList = data.getDrugsSearchVoList();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class RmssShow implements DataCall<List<RmssBean>> {
        @Override
        public void success(List<RmssBean> data, Object... args) {
            Log.e("hhhhhh",data+"" );
            for (int i = 0; i < data.size(); i++) {
                TextView tv = new TextView(SousuoAcitivity.this);
                tv.setPadding(28, 10, 28, 10);
                tv.setText(data.get(i).getName());
                tv.setMaxEms(10);
                tv.setSingleLine();
                tv.setBackgroundResource(R.drawable.yuanjiao);
                tv.setLayoutParams(layoutParams);
                flowLayout.addView(tv, layoutParams);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("hhhhhh",data+"" );
        }
    }
}
