package com.wd.MyHome.childthreeactivity;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dingtao.common.bean.MyUser.UserRecordBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.dingtao.common.util.StringUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.ImageAdapter;
import com.wd.MyHome.presenter.AddRecordPhotoPresenter;
import com.wd.MyHome.presenter.UpRecordPresenter;
import com.wd.MyHome.presenter.UserRecordPresenter;
import com.wd.MyHome.util.TopView;
import com.wd.health.PhotoXuan.GridViewAdapter;
import com.wd.health.PhotoXuan.MainConstant;
import com.wd.health.PhotoXuan.PictureSelectorConfig;
import com.wd.health.PhotoXuan.PlusImageActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UpdataRecordActivity extends WDActivity {

    @BindView(R2.id.uprecordone)
    TopView uprecordone;
    @BindView(R2.id.uprecordtext_disease)
    EditText uprecordtextDisease;
    @BindView(R2.id.uprecordtextdisease_new)
    EditText uprecordtextdiseaseNew;
    @BindView(R2.id.uprecordtextdisease_long)
    EditText uprecordtextdiseaseLong;
    @BindView(R2.id.uprecordtexthospital_name)
    EditText uprecordtexthospitalName;
    @BindView(R2.id.uprecord_begin)
    TextView uprecordBegin;
    @BindView(R2.id.uprecord_begin_bt)
    ImageView uprecordBeginBt;
    @BindView(R2.id.uprecord_end)
    TextView uprecordEnd;
    @BindView(R2.id.uprecord_end_be)
    ImageView uprecordEndBe;
    @BindView(R2.id.addrecord_course)
    EditText addrecordCourse;
    @BindView(R2.id.uprecord_recycler)
    GridView uprecordRecycler;
    @BindView(R2.id.uprecord_bt)
    Button uprecordBt;
    private TimePickerView pvCustomTime;
    private String uid=null;
    private String sid=null;
    private int cid;
    private UserRecordPresenter userRecordPresenter;
    private UpRecordPresenter upRecordPresenter;
    //-------------------------相机数据
    private ArrayList<String> mlist = new ArrayList<>(); //上传的图片凭证的数据源
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器
    private AddRecordPhotoPresenter addRecordPhotoPresenter;//图片的p层;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_updata_record;
    }

    @Override
    protected void initView() {
        uprecordone.setTitle("我的档案");
        //进去页面先进项查询 ， 然后进行赋值，之后进行修改请求接口
        //p
        userRecordPresenter = new UserRecordPresenter(new getuserrecord());//查看我的档案
        upRecordPresenter = new UpRecordPresenter(new getupdata());
        //添加图片
        addRecordPhotoPresenter = new AddRecordPhotoPresenter(new getphoto());
        //开始
        uprecordBeginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(uprecordBegin);
                pvCustomTime.show();
            }
        });
        //结束
        uprecordEndBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTime(uprecordEnd);
                pvCustomTime.show();
            }
        });
        //点击进行修改
        uprecordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                String adddisease=uprecordtextDisease.getText().toString().trim();//症状
                String disease_new=uprecordtextdiseaseNew.getText().toString().trim();//现在的症状
                String disease_long=uprecordtextdiseaseLong.getText().toString().trim();//之前的症状
                String hospital_name=uprecordtexthospitalName.getText().toString().trim();//医院名字
                String record_begin=uprecordBegin.getText().toString().trim();//就诊时间
                String record_end=uprecordEnd.getText().toString().trim();//结束时间
                String record_course=addrecordCourse.getText().toString().trim();//治疗过程
                //进行判断
                if (TextUtils.isEmpty(adddisease)||TextUtils.isEmpty(disease_new)||TextUtils.isEmpty(disease_long)
                        ||TextUtils.isEmpty(hospital_name)||TextUtils.isEmpty(record_begin)||TextUtils.isEmpty(record_end)
                        ||TextUtils.isEmpty(record_course)){
                    Toast.makeText(UpdataRecordActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }{
                    //请求接口
                    //调用数据库进行获取uid
                    String id= String.valueOf(cid);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("archivesId",id);
                    map.put("diseaseMain",adddisease);
                    map.put("diseaseNow",disease_new);
                    map.put("diseaseBefore",disease_long);
                    map.put("treatmentHospitalRecent",hospital_name);
                    map.put("treatmentProcess",record_course);
                    map.put("treatmentStartTime",record_begin);
                    map.put("treatmentEndTime",record_end);
                    Gson gson = new Gson();
                    String s = gson.toJson(map);
                    RequestBody body=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),s);
                    upRecordPresenter.reqeust(uid,sid,body);


                    /*MultipartBody.Builder builder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM);
                    builder.addFormDataPart("picture", String.valueOf(mlist));
                    List<String> list = (List<String>) mlist;
                    if (list.size()>1) {
                        for (int i = 1; i < list.size(); i++) {
                            File file = new File((String) list.get(i));
                            builder.addFormDataPart("picture", file.getName(),
                                    RequestBody.create(MediaType.parse("multipart/form-data"),
                                            file));
                        }
                    }*/
                    addRecordPhotoPresenter.reqeust(uid,sid,mlist);
                }
            }
        });
        initGridView();
    }
    class getupdata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            finish();
            Toast.makeText(UpdataRecordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //查询档案返回的数据
    class getuserrecord implements DataCall<UserRecordBean> {
        @Override
        public void success(UserRecordBean data, Object... args) {
            cid=data.id;
            recordnew(data);
            
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //修改图片
    class getphoto implements DataCall{
        @Override
        public void success(Object data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //-----------------------------------------------------------------------------


    //添加数据
    private void recordnew(UserRecordBean list) {
        uprecordtextDisease.setText(list.diseaseMain);//主要病症
        uprecordtextdiseaseNew.setText(list.diseaseNow);//先病史
        uprecordtextdiseaseLong.setText(list.diseaseBefore);//以前的病史
        uprecordtexthospitalName.setText(list.treatmentHospitalRecent);//最近治疗医院
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String begString = formatter.format(list.treatmentStartTime);
        String endString = formatter.format(list.treatmentEndTime);
        uprecordBegin.setText(begString);//开始
        uprecordEnd.setText(endString);//结束时间
        addrecordCourse.setText(list.treatmentProcess);//治疗过程
    }


    @Override
    protected void destoryData() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(UpdataRecordActivity.this);
        userRecordPresenter.reqeust(intt.get(0), intt.get(1));
        uid=intt.get(0);sid=intt.get(1);
    }
    //----------------------------------------------------------------------------选择时间
    private void initTime(final TextView view) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(UpdataRecordActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                view.setText(getTime(date));
            }
        }).setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(com.wd.health.R.layout.time_choose, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(com.wd.health.R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(com.wd.health.R.id.iv_cancel);
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
        mGridViewAddImgAdapter = new GridViewAdapter(UpdataRecordActivity.this, mlist);
        uprecordRecycler.setAdapter(mGridViewAddImgAdapter);
        uprecordRecycler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mlist.size() == MainConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加5张图片
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(MainConstant.MAX_SELECT_PIC_NUM - mlist.size());
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }
    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(UpdataRecordActivity.this, PlusImageActivity.class);
        intent.putStringArrayListExtra(MainConstant.IMG_LIST, mlist);
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
                mlist.add(compressPath); //把图片添加到将要上传的图片数组中
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
            mlist.clear();
            mlist.addAll(toDeletePicList);
            mGridViewAddImgAdapter.notifyDataSetChanged();
        }
    }
}
