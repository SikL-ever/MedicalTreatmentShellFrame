package com.wd.health.activity.wardActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.wardBean.BingzhengBean;
import com.dingtao.common.bean.wardBean.TabBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.suke.widget.SwitchButton;
import com.wd.health.PhotoXuan.GridViewAdapter;
import com.wd.health.PhotoXuan.MainConstant;
import com.wd.health.PhotoXuan.PictureSelectorConfig;
import com.wd.health.PhotoXuan.PlusImageActivity;
import com.wd.health.R;
import com.wd.health.adapter.wardmateadapter.BingzhengAdapater;
import com.wd.health.adapter.wardmateadapter.XuanAdapater;
import com.wd.health.presenter.wardmatepresenter.BingzhengPresenter;
import com.wd.health.presenter.wardmatepresenter.FabuPresenter;
import com.wd.health.presenter.wardmatepresenter.ImagePresenter;
import com.wd.health.presenter.wardmatepresenter.TabPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PublishActivity extends AppCompatActivity {

    private ImageView start_time;
    private ImageView stop_time;
    private TextView start_text;
    private TextView stop_text;
    private TimePickerView pvCustomTime;
    private SwitchButton switchButton;
    private LinearLayout linearLayout;
    private Button publish;
    private ImageView bz_pop;
    private ImageView xuanze_pop;
    private PopupWindow popupWindow;
    private TabPresenter tabPresenter;
    private XuanAdapater xuanAdapater;
    private RecyclerView xuanze_recycler;
    private RelativeLayout layouta;
    private BingzhengAdapater bingzhengAdapater;
    private BingzhengPresenter bingzhengPresenter;
    private RelativeLayout two_ress;
    private TextView keshi;
    private TextView x_bingzheng;
    private ImageView one_img;
    private EditText title;
    private TextView treatmentdetails;
    private TextView hospital_name;
    private TextView therapeutic;
    private TextView chongzhi;
    private FabuPresenter fabuPresenter;
    private ArrayList<String> mPicList = new ArrayList<>(); //上传的图片凭证的数据源
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器
    private GridView addrecord_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        //标题
        title = findViewById(R.id.title);
        //治疗详情
        treatmentdetails = findViewById(R.id.Treatmentdetails);
        //医院名字
        hospital_name = findViewById(R.id.hospital_name);
        //治疗过程
        therapeutic = findViewById(R.id.therapeutic);
        //充值
        chongzhi = findViewById(R.id.chongzhi);
        //开始时间
        start_time = findViewById(R.id.start_time);
        //结束时间
        stop_time = findViewById(R.id.stop_time);
        //添加图片addrecord_recycler
        addrecord_recycler = findViewById(R.id.addrecord_recycler);
        start_text = findViewById(R.id.start_text);
        stop_text = findViewById(R.id.stop_text);
        linearLayout = findViewById(R.id.linear);
        publish = findViewById(R.id.publish);
        bz_pop = findViewById(R.id.bz_pop);
        layouta = findViewById(R.id.layouta);
        two_ress = findViewById(R.id.two_ress);
        //科室
        keshi = findViewById(R.id.x_ks);
        //病症
        x_bingzheng = findViewById(R.id.x_bz);
        xuanze_pop = findViewById(R.id.xuanze_pop);
        one_img = findViewById(R.id.one_img);
        final LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        final List<String> intt = loginDaoUtil.intt(PublishActivity.this);
        String userid = intt.get(0);
        String sessonid = intt.get(1);
        String image = intt.get(2);
        Glide.with(PublishActivity.this).load(image).into(one_img);
        //switchbutton按钮
        switchButton = findViewById(R.id.switchbutton);
        switchButton.setChecked(false);
        switchButton.isChecked();
        switchButton.toggle();
        switchButton.toggle(true);
        switchButton.setShadowEffect(true);
        switchButton.setEnabled(true);
        switchButton.setEnableEffect(false);
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                boolean b = switchButton.isChecked() == false;
                if (isChecked) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
        //开始时间
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(start_text);
                pvCustomTime.show();
            }
        });
        //结束时间
        stop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PublishActivity.this, "kkk", Toast.LENGTH_SHORT).show();
                initTime(stop_text);
                pvCustomTime.show();
            }
        });

//        //添加图片
//        add_iamge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //选择科室
        xuanze_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(PublishActivity.this, R.layout.xuanze_pop, null);
                popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow.setFocusable(true);
//                popupWindow.setOutsideTouchable(false);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(layouta,0,0);


                tabPresenter = new TabPresenter(new xuan());
                tabPresenter.reqeust();
                xuanze_recycler = view.findViewById(R.id.xuanze_recycler);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(PublishActivity.this,4);
                xuanze_recycler.setLayoutManager(gridLayoutManager);
                xuanAdapater = new XuanAdapater(PublishActivity.this);
                xuanAdapater.setCall(new XuanAdapater.Call() {
                    @Override
                    public void setCall(String departmentName, int id) {
                        keshi.setText(departmentName);

                        popupWindow.dismiss();

                    }
                });
                xuanze_recycler.setAdapter(xuanAdapater);




            }

        });
        //选择病症
        bz_pop.setOnClickListener(new View.OnClickListener() {


            private RecyclerView xuanze;

            @Override
            public void onClick(View v) {
                View view = View.inflate(PublishActivity.this, R.layout.xuanze_pop, null);
                popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setTouchable(true);
                popupWindow.showAsDropDown(two_ress,0,0);
                popupWindow.setOutsideTouchable(true);

                bingzhengPresenter = new BingzhengPresenter(new bingzheng());
                bingzhengPresenter.reqeust(2);
                xuanze = view.findViewById(R.id.xuanze_recycler);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(PublishActivity.this,4);
                xuanze.setLayoutManager(gridLayoutManager);
                bingzhengAdapater = new BingzhengAdapater(PublishActivity.this);
                xuanze.setAdapter(bingzhengAdapater);
                bingzhengAdapater.setCall(new BingzhengAdapater.Calll() {
                    @Override
                    public void setCall(String name) {
                        x_bingzheng.setText(name);
                        popupWindow.dismiss();
                    }
                });


            }
        });

        fabuPresenter = new FabuPresenter(new fabu());
        //发表病友圈
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titl = title.getText().toString().trim();
                String kesh = keshi.getText().toString().trim();
                String bingzhen = x_bingzheng.getText().toString().trim();
                String treatmentdetail = treatmentdetails.getText().toString().trim();
                String hospital_names = hospital_name.getText().toString().trim();
                String text_start = start_text.getText().toString().trim();
                String text_stop = stop_text.getText().toString().trim();
                String therapeutics = therapeutic.getText().toString().trim();
                if(titl.length()>=20){
                    Toast.makeText(PublishActivity.this,"不能超过20个字",Toast.LENGTH_SHORT).show();
                }
                if(treatmentdetail.length()>=300){
                    Toast.makeText(PublishActivity.this,"不能超过300个字",Toast.LENGTH_SHORT).show();
                }
                HashMap<String,String> map=new HashMap<>();
                map.put("titl",titl);
                map.put("kesh",kesh);
                map.put("bingzhen",bingzhen);
                map.put("treatmentdetail",treatmentdetail);
                map.put("hospital_names",hospital_names);
                map.put("text_start",text_start);
                map.put("text_stop",text_stop);
                map.put("therapeutics",therapeutics);
                Gson gson=new Gson();
                String ma = gson.toJson(map);
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf=8"), ma);
                LoginDaoUtil loginDaoUtil1=new LoginDaoUtil();
                List<String> intt1 = loginDaoUtil.intt(PublishActivity.this);
                fabuPresenter.reqeust(intt1.get(0),intt1.get(1),body);

//                ImagePresenter imagePresenter=new ImagePresenter(new sengimage());
//                imagePresenter.reqeust(intt.get(0),intt.get(1),);
            }
        });
        initGridView();
    }
    //发表图片
    class sengimage implements DataCall{

        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //发布病友圈
    class fabu implements DataCall<Double>{
        @Override
        public void success(Double data, Object... args) {

//            Toast.makeText(PublishActivity.this,data.getMessage(),Toast.LENGTH_SHORT).show();

            Toast.makeText(PublishActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //选择病症
    class bingzheng implements DataCall<List<BingzhengBean>>{
        @Override
        public void success(List<BingzhengBean> data, Object... args) {

            bingzhengAdapater.setbingzheng(data);
            bingzhengAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //选择科室
    class xuan implements DataCall<List<TabBean>> {

        @Override
        public void success(List<TabBean> data, Object... args) {
            xuanAdapater.setxuan(data);
            xuanAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //时间
    private void initTime(final TextView view) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2090, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(PublishActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(getTime(date));
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.time_choose, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {//完成按钮
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTitleText("城市选择")//标题
                .setTitleColor(Color.BLUE)//标题文字颜

                .setContentTextSize(19)//滚轮文字大小
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .isDialog(true)
                .build();

    }
    //时间格式转换
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    //--------------------------------------------------------------------------------------相机
    //初始化展示上传图片的GridView
    private void initGridView() {
        mGridViewAddImgAdapter = new GridViewAdapter(PublishActivity.this, mPicList);
        addrecord_recycler.setAdapter(mGridViewAddImgAdapter);
        addrecord_recycler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == MainConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加5张图片
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(MainConstant.MAX_SELECT_PIC_NUM - mPicList.size());
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }
    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(PublishActivity.this, PlusImageActivity.class);
        intent.putStringArrayListExtra(MainConstant.IMG_LIST, mPicList);
        intent.putExtra(MainConstant.POSITION, position);
        startActivityForResult(intent, MainConstant.REQUEST_CODE_MAIN);
    }
    /**
     * 打开相册或者照相机选择凭证图片，最多5张
     *
     * @param maxTotal 最多选择的图片的数量
     */
    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
    }

    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                mPicList.add(compressPath); //把图片添加到将要上传的图片数组中
                mGridViewAddImgAdapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
        if (requestCode == MainConstant.REQUEST_CODE_MAIN && resultCode == MainConstant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(MainConstant.IMG_LIST); //要删除的图片的集合
            mPicList.clear();
            mPicList.addAll(toDeletePicList);
            mGridViewAddImgAdapter.notifyDataSetChanged();
        }
    }

}
