package com.wd.health.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.wd.health.Helper.SearchHistoryDao;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.BzAdapter;
import com.wd.health.adapter.homepageadapter.SearchRecordsAdapter;
import com.wd.health.adapter.homepageadapter.YpAdapter;
import com.wd.health.adapter.homepageadapter.YsAdapter;
import com.wd.health.presenter.homepagepresenter.RmssPresenter;
import com.wd.health.presenter.homepagepresenter.SousuoPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
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
    @BindView(R2.id.ssshoew)
    RelativeLayout ssshoew;
    @BindView(R2.id.search_content_show_ll)
    RecyclerView searchRecordsLl;
    @BindView(R2.id.search_content_show_ll1)
    LinearLayout searchRecordsLl1;
    @BindView(R2.id.flow)
    FlowLayout flowLayout;
    @BindView(R2.id.yisheng_recycler)
    RecyclerView yishengRecycler;
    @BindView(R2.id.yisheng)
    LinearLayout yisheng;
    @BindView(R2.id.yaopin_recycler)
    RecyclerView yaopinRecycler;
    @BindView(R2.id.yaopin)
    LinearLayout yaopin;
    @BindView(R2.id.bingzheng_recycler)
    RecyclerView bingzhengRecycler;
    @BindView(R2.id.bingzheng)
    LinearLayout bingzheng;
    @BindView(R2.id.asdfghjkl)
    LinearLayout asdfghjkl;
    @BindView(R2.id.texxt)
    TextView texxt;

    private SearchRecordsAdapter recordsAdapter;
    private LinearLayout.LayoutParams layoutParams;
    private RmssPresenter rmssPresenter;
    private LsjlBeanDao lsjlBeanDao;
    private final int DELETEONE = 0X001;
    private final int DELETEALL = 0X002;
    private List<LsjlBean> list = new ArrayList<>();

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
        showKeyboard();
        Log.e("ttttttttttttttttt", list + "");
     /*   if (list.size()<1){
            searchRecordsLl1.setVisibility(View.GONE);
            ssshoew.setVisibility(View.GONE);
        }else{
            searchRecordsLl1.setVisibility(View.VISIBLE);
            ssshoew.setVisibility(View.VISIBLE);
        }*/
        //回车不换行
        edit_item.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
        edit_item.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER) {//修改回车键功能
// 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SousuoAcitivity.this.getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    if (edit_item.getText().toString().length() > 0) {

                        String record = edit_item.getText().toString();
                        SousuoPresenter sousuoPresenter = new SousuoPresenter(new sousuo());
                        sousuoPresenter.reqeust(record);
                        insertHistory();
                        //   recordsAdapter.notifyDataSetChanged();
                        Toast.makeText(SousuoAcitivity.this, "" + record, Toast.LENGTH_SHORT).show();
                        weizhaodao.setVisibility(View.GONE);
                        yizhaodao.setVisibility(View.GONE);
                        asdfghjkl.setVisibility(View.VISIBLE);
                        //根据关键词去搜索
                        //    searchRecordsLl1.setVisibility(View.VISIBLE);
                        //  ssshoew.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(SousuoAcitivity.this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                    hideSoftKeyboard(SousuoAcitivity.this);
                }
                return false;
            }
        });
        lsjlBeanDao = DaoMaster.newDevSession(this, LsjlBeanDao.TABLENAME).getLsjlBeanDao();
        rmssPresenter = new RmssPresenter(new RmssShow());
        rmssPresenter.reqeust();
//往容器内添加TextView数据
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (flowLayout != null) {
            flowLayout.removeAllViews();
        }
        searchRecordsLl.setLayoutManager(new LinearLayoutManager(this));
        recordsAdapter = new SearchRecordsAdapter(this, list);
        searchRecordsLl.setAdapter(recordsAdapter);
        recordsAdapter.setOnDeleteListener(position -> deleteHistory(DELETEONE, position));
        recordsAdapter.setOnItemClickListener(position -> {
            edit_item.setText(list.get(position).getName());
            insertHistory();
        });
        // btnClearall.setOnClickListener(view -> showDeleteDialog());
        queryHistory();
        edit_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weizhaodao.setVisibility(View.GONE);
                yizhaodao.setVisibility(View.VISIBLE);
                asdfghjkl.setVisibility(View.GONE);
            }
        });
        sousuo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_item.getText().toString().length() > 0) {

                    String record = edit_item.getText().toString();
                    SousuoPresenter sousuoPresenter = new SousuoPresenter(new sousuo());
                    sousuoPresenter.reqeust(record);
                    insertHistory();
                    //   recordsAdapter.notifyDataSetChanged();
                    Toast.makeText(SousuoAcitivity.this, "" + record, Toast.LENGTH_SHORT).show();
                    weizhaodao.setVisibility(View.GONE);
                    yizhaodao.setVisibility(View.GONE);
                    asdfghjkl.setVisibility(View.VISIBLE);
                    //根据关键词去搜索
                    //    searchRecordsLl1.setVisibility(View.VISIBLE);
                    //  ssshoew.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(SousuoAcitivity.this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                }
                hideSoftKeyboard(SousuoAcitivity.this);
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
    private void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(edit_item, 0);
    }
   /* /
    private void bindAdapter() {
        recordsAdapter = new SearchRecordsAdapter(this, tempList);
        recordsListLv.setAdapter(recordsAdapter);
    }*/

    private void showDeleteDialog() {
        new AlertDialog.Builder(this)
                .setMessage("确认清除所有搜索记录吗?")
                .setPositiveButton("确定", (dialogInterface, i) -> deleteHistory(DELETEALL, 1))
                .setNegativeButton("取消", null)
                .setCancelable(true)
                .create().show();
    }

    private void insertHistory() {
        LsjlBean searchHistory = new LsjlBean();
        searchHistory.setName(edit_item.getText().toString().trim());
        SearchHistoryDao.insertHistory(searchHistory);
        queryHistory();
    }

    private void deleteHistory(int TAG, int position) {
        if (TAG == DELETEONE)
            SearchHistoryDao.deleteHistory(list.get(position));
        else if (TAG == DELETEALL)
            SearchHistoryDao.clearAll();
        queryHistory();
    }

    private void queryHistory() {
        list.clear();
        list.addAll(SearchHistoryDao.queryAll());
        if (list.size()<1){
            searchRecordsLl1.setVisibility(View.GONE);
            ssshoew.setVisibility(View.GONE);
        }else{
            searchRecordsLl1.setVisibility(View.VISIBLE);
            ssshoew.setVisibility(View.VISIBLE);
        }
        Collections.reverse(list);
        recordsAdapter.notifyDataSetChanged();
    }

    private class sousuo implements DataCall<SousuoBean> {


        @Override
        public void success(SousuoBean data, Object... args) {
            List<SousuoBean.DiseaseSearchVoListBean> diseaseSearchVoList = data.getDiseaseSearchVoList();
            bingzhengRecycler.setLayoutManager(new LinearLayoutManager(SousuoAcitivity.this));
            List<SousuoBean.DoctorSearchVoListBean> doctorSearchVoList = data.getDoctorSearchVoList();
            yishengRecycler.setLayoutManager(new LinearLayoutManager(SousuoAcitivity.this));
            List<SousuoBean.DrugsSearchVoListBean> drugsSearchVoList = data.getDrugsSearchVoList();
            yaopinRecycler.setLayoutManager(new LinearLayoutManager(SousuoAcitivity.this));
            if (diseaseSearchVoList.size() > 0 || doctorSearchVoList.size() > 0 || drugsSearchVoList.size() > 0) {
                weizhaodao.setVisibility(View.GONE);
                yizhaodao.setVisibility(View.GONE);
                asdfghjkl.setVisibility(View.VISIBLE);
                if (diseaseSearchVoList.size() > 0) {
                    //有数据
                    bingzheng.setVisibility(View.VISIBLE);
                    BzAdapter bzAdapter = new BzAdapter(SousuoAcitivity.this,diseaseSearchVoList);
                    bingzhengRecycler.setAdapter(bzAdapter);
                    bzAdapter.notifyDataSetChanged();
                }
                if (diseaseSearchVoList.size()<1){//无数据
                    bingzheng.setVisibility(View.GONE);
                }
                if (doctorSearchVoList.size() > 0) {
                    //有数据
                    yisheng.setVisibility(View.VISIBLE);
                    YsAdapter ysAdapter = new YsAdapter(SousuoAcitivity.this,doctorSearchVoList);
                    yishengRecycler.setAdapter(ysAdapter);
                    ysAdapter.notifyDataSetChanged();
                }
                if (doctorSearchVoList.size()<1){//无数据
                    yisheng.setVisibility(View.GONE);
                }
                if (drugsSearchVoList.size() > 0) {
                    //有数据
                    yaopin.setVisibility(View.VISIBLE);
                    YpAdapter ysAdapter = new YpAdapter(SousuoAcitivity.this,drugsSearchVoList);
                    yaopinRecycler.setAdapter(ysAdapter);
                    ysAdapter.notifyDataSetChanged();
                }
                if (drugsSearchVoList.size()<1){//无数据
                    yaopin.setVisibility(View.GONE);
                }
            } else {
                //展示未找到图片
                weizhaodao.setVisibility(View.VISIBLE);
                yizhaodao.setVisibility(View.GONE);
                asdfghjkl.setVisibility(View.GONE);
                texxt.setText("抱歉！没有找到“"+edit_item.getText().toString().trim()+"”的相关信息");
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class RmssShow implements DataCall<List<RmssBean>> {
        @Override
        public void success(final List<RmssBean> data, Object... args) {
            Log.e("hhhhhh", data + "");
            for (int i = 0; i < data.size(); i++) {
                TextView tv = new TextView(SousuoAcitivity.this);
                tv.setPadding(28, 10, 28, 10);
                tv.setText(data.get(i).getName());
                tv.setMaxEms(10);
                tv.setSingleLine();
                tv.setBackgroundResource(R.drawable.bian);
                tv.setLayoutParams(layoutParams);
                final int finalI = i;
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SousuoAcitivity.this, "" + data.get(finalI).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                flowLayout.addView(tv, layoutParams);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("hhhhhh", data + "");
        }
    }
}
