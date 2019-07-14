package com.wd.health.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.homepage.LsjlBean;
import com.dingtao.common.dao.DaoMaster;
import com.dingtao.common.dao.LsjlBeanDao;
import com.wd.health.CustomImageView.MyListView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.LsjlAdapter;

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
    EditText editItem;
    @BindView(R2.id.sousuo)
    TextView sousuo;
    @BindView(R2.id.tv_tip)
    TextView tv_tip;
    @BindView(R2.id.listView)
    RecyclerView recyclerView;
    @BindView(R2.id.tv_clear)
    TextView tvClear;
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
        /*// 搜索框的键盘搜索键点击回调
        editItem.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(editItem.getText().toString().trim());
                    if (!hasData) {
                        insertData(editItem.getText().toString().trim());
                        queryData("");
                    }
                    // TODO 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现
                    Toast.makeText(SousuoAcitivity.this, "clicked!", Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LsjlAdapter lsjlAdapter = new LsjlAdapter(this);
        recyclerView.setAdapter(lsjlAdapter);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = editItem.getText().toString().trim();
                if (trim==null){
                    Toast.makeText(SousuoAcitivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }else{
                    LsjlBeanDao bean = DaoMaster.newDevSession(SousuoAcitivity.this,LsjlBeanDao.TABLENAME).getLsjlBeanDao();
                    List<LsjlBean> lsjlBeans = bean.loadAll();

                    /*if (!lsjlBeans) {
                        insertData(editItem.getText().toString().trim());
                        //insertData(R.drawable.message_icon_close_n);
                        queryData("");
                    }*/
                    /*boolean hasData = hasData(editItem.getText().toString().trim());
                    if (!hasData) {
                        insertData(editItem.getText().toString().trim());
                        //insertData(R.drawable.message_icon_close_n);
                        queryData("");
                    }*/
                }
            }
        });
        // 搜索框的文本变化实时监听
        editItem.addTextChangedListener(new TextWatcher() {
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
                String tempName = editItem.getText().toString();
                // 根据tempName去模糊查询数据库中有没有数据
               // queryData(tempName);

            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                editItem.setText(name);
                Toast.makeText(SousuoAcitivity.this, name, Toast.LENGTH_SHORT).show();
                // TODO 获取到item上面的文字，根据该关键字跳转到另一个页面查询，由你自己去实现
            }
        });*/

      /*  // 插入数据，便于测试，否则第一次进入没有数据怎么测试呀？
        Date date = new Date();
        long time = date.getTime();
        insertData("Leo" + time);*/

        // 第一次进入查询所有的历史记录
       // queryData("");

    }

    /**
     * 插入数据
     */
/*
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
*/

    /**
     * 模糊查询数据
     */
  /*  private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);

        // 创建adapter适配器对象
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }*/




}
