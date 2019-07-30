package com.wd.health.activity.wardActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.wardBean.List_xiang_Bean;
import com.dingtao.common.bean.wardBean.Ping_lie_Bean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.activity.MainActivity;
import com.wd.health.activity.ZxxqActivity;
import com.wd.health.adapter.wardmateadapter.PingAdapater;
import com.wd.health.adapter.wardmateadapter.XiangAdapater;
import com.wd.health.presenter.wardmatepresenter.CollectPresenter;
import com.wd.health.presenter.wardmatepresenter.Lie_XiangPresenter;
import com.wd.health.presenter.wardmatepresenter.PingliePresenter;
import com.wd.health.presenter.wardmatepresenter.PinglunPresenter;
import com.wd.health.presenter.wardmatepresenter.ShoucangPresenter;
import com.wd.health.presenter.wardmatepresenter.ZanPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class List_detailsActivity extends AppCompatActivity {


    @BindView(R2.id.image_head)
    ImageView imageHead;
    @BindView(R2.id.text_content)
    TextView textContent;
    @BindView(R2.id.bull)
    ImageView bull;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.symptom)
    TextView symptom;
    @BindView(R2.id.workname)
    TextView workname;
    @BindView(R2.id.details)
    TextView details;
    @BindView(R2.id.hospital)
    TextView hospital;
    @BindView(R2.id.remedy)
    TextView remedy;
    @BindView(R2.id.collect)
    TextView collect;
    @BindView(R2.id.jainyi)
    ImageView jainyi;
    @BindView(R2.id.advise)
    TextView advise;
    @BindView(R2.id.correlation_image)
    ImageView correlationImage;
    @BindView(R2.id.touxiang)
    ImageView touxiang;
    @BindView(R2.id.name_ssss)
    TextView nameSsss;
    @BindView(R2.id.resetime)
    TextView resetime;
    @BindView(R2.id.neirong)
    TextView neirong;
    private Lie_XiangPresenter lie_xiangPresenter;
    private ImageView zhilaiotu;

    private XiangAdapater xiangAdapater;
    private RelativeLayout relativeLayout;
    private ImageView know;
    private PopupWindow popupWindow;
    private ImageView imageView;
    private PingAdapater pingAdapater;
    private RecyclerView recyclerView;
    private ImageView imageView1;
    private TextView ping_edit;
    private int id;
    private int type=1;
    private PingliePresenter pingliePresenter;
    private LoginDaoUtil loginDaoUtil;
    private PinglunPresenter pinglunPresenter;
    private CheckBox iamgev;
    private List<String> intt;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        ButterKnife.bind(this);
        know = findViewById(R.id.know);
        zhilaiotu = findViewById(R.id.zhiliaotu);
        iamgev = findViewById(R.id.star);
        Intent intent = getIntent();
        int jumpId = intent.getIntExtra("jumpID", 0);
        lie_xiangPresenter = new Lie_XiangPresenter(new lie_xiang());
        loginDaoUtil = new LoginDaoUtil();
        intt = loginDaoUtil.intt(List_detailsActivity.this);
        lie_xiangPresenter.reqeust(intt.get(0), intt.get(1), jumpId);
        String image = intt.get(2);
        Glide.with(List_detailsActivity.this).load(image).apply(RequestOptions.bitmapTransform(new RoundedCorners(80))).into(imageHead);
//        //弹出对话框
        jainyi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View view = View.inflate(List_detailsActivity.this, R.layout.recyclerviews, null);
                popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                //设置半透明
                setBackgroundAlpha(0.5f);
                //退出页面后还原页面透明度
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });
                //点击退出popupwindow
                imageView1 = view.findViewById(R.id.X);
                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                recyclerView = view.findViewById(R.id.pinglunrecycler);
                pingliePresenter = new PingliePresenter(new ping());
                final List<String> intt1 = loginDaoUtil.intt(List_detailsActivity.this);
                //请求数据
                pingliePresenter.reqeust(intt1.get(0),intt1.get(1),id,1,10);
                //布局管理器
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(List_detailsActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                //创建适配器
                pingAdapater = new PingAdapater(List_detailsActivity.this);

                recyclerView.setLayoutManager(linearLayoutManager);
                //设置适配器
                recyclerView.setAdapter(pingAdapater);

                //评论
                ping_edit = view.findViewById(R.id.ping_Edit);
                pinglunPresenter = new PinglunPresenter(new pinglun());
                LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(List_detailsActivity.this);
                linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                ping_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pinglunPresenter.reqeust(intt1.get(0),intt1.get(1),id,ping_edit.getText().toString().trim());
                        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(List_detailsActivity.this);
                        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                        ping_edit.setText("");
                        pingAdapater.notifyDataSetChanged();

                    }
                });

            }


            //半透明
            public void setBackgroundAlpha(float bgAlpha) {

                WindowManager.LayoutParams lp = (List_detailsActivity.this).getWindow()
                        .getAttributes();
                lp.alpha = bgAlpha;
                (List_detailsActivity.this).getWindow().setAttributes(lp);
            }
        });


    }
    //评论
    class pinglun implements DataCall{

        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(List_detailsActivity.this,"评论成功", Toast.LENGTH_SHORT).show();
//            String message = data.message;
            pingAdapater.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(List_detailsActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    //评论列表
    class ping implements DataCall<List<Ping_lie_Bean>> {

        @Override
        public void success(List<Ping_lie_Bean> data, Object... args) {

            pingAdapater.setpinglei(data);
            pingAdapater.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    class lie_xiang implements DataCall<List_xiang_Bean> {

        private int collectionFlag;

        @Override
        public void success(final List_xiang_Bean data, Object... args) {
            List<List_xiang_Bean> list_xiang_beans = new ArrayList<>();
            String title = data.getTitle();
            String detail = data.getDetail();
            String treatmentHospital = data.getTreatmentHospital();
            String treatmentProcess = data.getTreatmentProcess();
            String disease = data.getDisease();
            int commentNum = data.getCommentNum();
            int collectionNum = data.getCollectionNum();

            //采纳者
            String adoptHeadPic = data.getAdoptHeadPic();
            String adoptNickName = data.getAdoptNickName();

            //作者id
            int authorUserId = data.getAuthorUserId();
            //收藏id
            id = data.getSickCircleId();
            //收藏
            collectionFlag = data.getCollectionFlag();

            if(collectionFlag==1){
                iamgev.setChecked(true);
                iamgev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        data.setCollectionFlag(2);
                        ShoucangPresenter shoucangPresenter=new ShoucangPresenter(new shoucang());
                        shoucangPresenter.reqeust(intt.get(0),intt.get(1),id);
                    }
                });
            }else{
                iamgev.setChecked(false);
                iamgev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        data.setCollectionFlag(1);
                        ShoucangPresenter shoucangPresenter=new ShoucangPresenter(new shoucangs());
                        shoucangPresenter.reqeust(intt.get(0),intt.get(1),id);
                    }
                });
            }
            //用户头像
//            touxiang.setImageURI(Glide.with(List_detailsActivity.this).load(data.getAdoptHeadPic()).into(adoptHeadPic.split(","))
//            nameSsss.setText(adoptNickName+"");
            collect.setText(collectionNum+1+"");
            advise.setText(commentNum+1+"");
            String picture1 = data.picture;
            symptom.setText(disease);
            textContent.setText(title);
            details.setText(detail);
            hospital.setText(treatmentHospital);
            remedy.setText(treatmentProcess);
            String[] split = picture1.split(",");
//            Glide.with(zhilaiotu).load(picture).into(zhilaiotu);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //fail
    class shoucang implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            Toast.makeText(List_detailsActivity.this, "取消收藏！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //successful
    class shoucangs implements DataCall<Result>{

        @Override
        public void success(Result data, Object... args) {
            String message = data.getMessage();
            Toast.makeText(List_detailsActivity.this,message,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}
