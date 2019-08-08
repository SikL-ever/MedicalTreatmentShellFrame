package com.wd.MyHome.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.MyUser.CxxxBean;
import com.dingtao.common.bean.MyUser.YhBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.android.material.snackbar.Snackbar;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.childactivity.MyUserSetActivity;
import com.wd.MyHome.childthreeactivity.SichangyongMyShenfenActivity;
import com.wd.MyHome.childthreeactivity.XieqibangdingyinhangkaActivity;
import com.wd.MyHome.childthreeactivity.XieqiyinhangkaActivity;
import com.wd.MyHome.presenter.CxYhPresenter;
import com.wd.MyHome.presenter.CxyhxxPresenter;
import com.wd.MyHome.presenter.SctxPresenter;
import com.wd.MyHome.util.ABitMap;
import com.wd.MyHome.util.RealPathFromUriUtils;
import com.wd.MyHome.util.TopView;
import com.wd.health.activity.WzzxActivity;

import java.io.File;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;

public class WdxxActivity extends AppCompatActivity {

    @BindView(R2.id.myuserset_top_wdxx)
    TopView myusersetTopWdxx;
    @BindView(R2.id.ttx)
    ImageView ttx;
    @BindView(R2.id.orl)
    TextView orl;
    @BindView(R2.id.t_tp_nc)
    ImageView tTpNc;
    @BindView(R2.id.t_xb)
    ImageView tXb;
    @BindView(R2.id.t_tp_xb)
    ImageView tTpXb;
    @BindView(R2.id.t_nl)
    TextView tNl;
    @BindView(R2.id.t_nl_nl)
    TextView tNlNl;
    @BindView(R2.id.t_ti)
    TextView tTi;
    @BindView(R2.id.t_ti_ti)
    TextView tTiTi;
    @BindView(R2.id.asda)
    RelativeLayout asda;
    @BindView(R2.id.t_sg)
    TextView tSg;
    @BindView(R2.id.t_sg_sg)
    TextView tSgSg;
    @BindView(R2.id.qbd_wx)
    TextView qbdwx;
    @BindView(R2.id.qbdyhk)
    TextView qbdyhk;
    @BindView(R2.id.t_tp_tz)
    ImageView tTpTz;
    @BindView(R2.id.t_yx)
    TextView tYx;
    @BindView(R2.id.t_tp_qbd)
    ImageView tTpQbd;
    @BindView(R2.id.t_tp_qrz)
    ImageView tTpQrz;
    @BindView(R2.id.t_tp_qbdyhk)
    ImageView tTpQbdyhk;
    private File filea;
    private SctxPresenter sctxPresenter;
    private String file=Environment.getExternalStorageDirectory()+"/t.png";
    private PopupWindow window;
    private String userId;
    private String sessionId;
    private List<String> intt;
    private View inflate;
    private TextView paizhao,xiangce,updata_finish;
    private int sex;
    private CxyhxxPresenter cxyhxxPresenter;
    private int height;
    private int weight;
    private int age;
    private CxYhPresenter cxYhPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdxx);
        ButterKnife.bind(this);
        myusersetTopWdxx.setTitle("我的信息");
        intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);
        Glide.with(this).load(intt.get(2)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ttx);//设置头像
        orl.setText(intt.get(3));

        cxYhPresenter = new CxYhPresenter(new DataCall<YhBean>() {
            @Override
            public void success(final YhBean data, Object... args) {
                if (data!=null){
                    qbdyhk.setText("已绑定");
                    tTpQbdyhk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(WdxxActivity.this,XieqibangdingyinhangkaActivity.class));
                        }
                    });

                }else{
                    qbdyhk.setText("去绑定");
                    tTpQbdyhk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(WdxxActivity.this,XieqiyinhangkaActivity.class));
                        }
                    });

                }


            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        cxyhxxPresenter = new CxyhxxPresenter(new DataCall<CxxxBean>() {
            @Override
            public void success(CxxxBean data, Object... args) {
                tYx.setText(data.getEmail());
                sex = data.getSex();
                if (sex==1){
                    Glide.with(WdxxActivity.this).load(R.drawable.common_icon_boy_n).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(tXb);
                }else{
                    Glide.with(WdxxActivity.this).load(R.drawable.common_icon_girl_n).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(tXb);
                }
                height = data.getHeight();
                tSgSg.setText(height+"cm");
                weight = data.getWeight();
                tTiTi.setText(weight+"kg");
                age = data.getAge();
                tNlNl.setText(age+"");
                int chat = data.getWhetherBingWeChat();
                if (chat==1){
                    qbdwx.setText("已绑定");
                }else{
                    qbdwx.setText("去绑定");
                }
                cxYhPresenter.reqeust(userId,sessionId);

            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        cxyhxxPresenter.reqeust(userId,sessionId);


        tTpNc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdxxActivity.this,SzncActivity.class);
                startActivity(intent);
            }
        });
        tTpXb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdxxActivity.this,XbActivity.class);
                intent.putExtra("xb",sex);
                startActivity(intent);
            }
        });
        tTpTz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdxxActivity.this,TzActivity.class);
                intent.putExtra("height",height);
                intent.putExtra("weight",weight);
                intent.putExtra("age",age);
                startActivity(intent);
            }
        });
        tTpQbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WdxxActivity.this,SichangyongMyShenfenActivity.class));
            }
        });
        tTpQrz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WdxxActivity.this,SichangyongMyShenfenActivity.class);
                startActivity(intent);
            }
        });

        inflate = View.inflate(WdxxActivity.this, R.layout.updata_head, null);
        paizhao=inflate.findViewById(R.id.paizhao);
        xiangce=inflate.findViewById(R.id.xiangce);
        updata_finish=inflate.findViewById(R.id.updata_finish);
        sctxPresenter = new SctxPresenter(new SctxShow());
        ttx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //如果是在登录状态
                    //进行修改头像，跳转相册,创建popup
                    window = new PopupWindow(inflate,
                            WindowManager.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.WRAP_CONTENT
                            //(int) (MyUserActivity.this.findViewById(R.id.myuserlayout).getHeight()*0.4)
                    );
                    // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
                    window.setFocusable(true);
                    // 设置点击popupwindow外屏幕其它地方消失
                    window.setOutsideTouchable(true);
                    // 实例化一个ColorDrawable颜色为半透明
                    //ColorDrawable dw = new ColorDrawable(0xb0000000);
                    //ColorDrawable dw = new ColorDrawable(Color.WHITE);
                    //window.setBackgroundDrawable(dw);
                    // 设置popWindow的显示和消失动画
                    window.setAnimationStyle(R.style.anim_menu_bottombar);
                    // 在底部显示
                    window.showAtLocation(WdxxActivity.this.findViewById(R.id.lineee),
                            Gravity.BOTTOM, 0, 0);
                    //消失点击

            }
        });
        //头像popup点击
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//开启相机应用程序获取并返回图片（capture：俘获）
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                            "head.jpg")));//指明存储图片或视频的地址URI
                    startActivityForResult(intent2, 200);//采用ForResult打开
                } catch (Exception e) {
                    Toast.makeText(WdxxActivity.this, "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
                }

                window.dismiss();

            }
        });
        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳入相册拿取图片
                // 创建相册的意图对象
                Intent intent = new Intent(Intent.ACTION_PICK);
                // 设置支持的图片格式
                intent.setType("image/*");
                // 开启相册页面
                startActivityForResult(intent, 100);

            }
        });
        updata_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
    }



    private class SctxShow implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(WdxxActivity.this, " ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == 200 &&resultCode == RESULT_OK) {
            File temp = new File(Environment.getExternalStorageDirectory()
                    + "/head.jpg");
            crop(Uri.fromFile(temp));//裁剪图片

        }

        if (requestCode == 100 && resultCode == RESULT_OK) {
            //取出相册的图片
            Uri uri = data.getData();
            //裁剪
            crop(uri);
            /*Bitmap bitmap = data.getParcelableExtra("data");
            ABitMap aBitMap = new ABitMap();
            File file = aBitMap.compressImage(bitmap);
            myUserUpdataHead.reqeust(uid,sid,file);
            myuserheadportrait.setImageBitmap(bitmap);
            mLoadDialog.show();*/
        }
        if (requestCode == 99 && resultCode == RESULT_OK) {
            //取出裁剪的图片
            Bitmap bitmap = data.getParcelableExtra("data");
            //setPicToView(bitmap);//保存在SD卡中
            ABitMap aBitMap = new ABitMap();
            File file = aBitMap.compressImage(bitmap);
            sctxPresenter.reqeust(userId,sessionId,file);
            ttx.setImageBitmap(bitmap);
            //加载框
            //mLoadDialog.show();
            //进行一个底部土司

        }
    }
    //剪裁图片
    private void crop(Uri uri) {
        //通过隐式跳转进入到裁剪页面
        Intent  intent = new Intent("com.android.camera.action.CROP");
        //设置裁剪的数据
        intent.setDataAndType(uri, "image/*");
        //设置裁剪的属性
        intent.putExtra("crop", "true");//设置是否支持裁剪
        intent.putExtra("aspectX", 1);//设置裁剪框的比例
        intent.putExtra("aspectY", 1);//设置裁剪框的比例
        intent.putExtra("outputX", 250);//设置输出图片的大小
        intent.putExtra("outputY", 250);//设置输出图片的大小
        intent.putExtra("return-data", true);//设置是否返回数据
        //回传数据
        startActivityForResult(intent, 99);
    }
    @Override
    protected void onResume() {
        super.onResume();

        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(WdxxActivity.this);
        orl.setText(intt.get(3));
        cxyhxxPresenter.reqeust(userId,sessionId);

    }
}
