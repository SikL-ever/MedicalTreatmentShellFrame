package com.wd.MyHome.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.dao.DaoMaster;
import com.dingtao.common.dao.LoginBeanDao;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.android.material.snackbar.Snackbar;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.childactivity.MyDiseaseActivity;
import com.wd.MyHome.childactivity.MyHMoneyActivity;
import com.wd.MyHome.childactivity.MyInterestActivity;
import com.wd.MyHome.childactivity.MyUserCollectActivity;
import com.wd.MyHome.childactivity.MyUserRecordActivity;
import com.wd.MyHome.childactivity.MyUserSetActivity;
import com.wd.MyHome.childactivity.MyUserSuggestActivity;
import com.wd.MyHome.childactivity.MyUserWalletActivity;
import com.wd.MyHome.childactivity.MyVideoActivity;
import com.wd.MyHome.presenter.MyUserUpdataHead;
import com.wd.MyHome.presenter.UserSignPresenter;
import com.wd.MyHome.util.ABitMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_LOGIN_MYUSERACTIVITY)
public class MyUserActivity extends WDActivity {
    @BindView(R2.id.myuserfinish)
    ImageView myuserfinish;
    @BindView(R2.id.myusermessage)
    ImageView myusermessage;
    @BindView(R2.id.myuserheadportrait)
    ImageView myuserheadportrait;
    @BindView(R2.id.myusername)
    TextView myusername;
    @BindView(R2.id.myuserQiandao)
    Button myuserQiandao;
    @BindView(R2.id.myusernew)
    RelativeLayout myusernew;
    @BindView(R2.id.myuserhistory)
    RelativeLayout myuserhistory;
    @BindView(R2.id.myuserrecord)
    LinearLayout myuserrecord;
    @BindView(R2.id.myuserwallet)
    LinearLayout myuserwallet;
    @BindView(R2.id.myusercollect)
    LinearLayout myusercollect;
    @BindView(R2.id.myusersuggest)
    LinearLayout myusersuggest;
    @BindView(R2.id.myuservideo)
    LinearLayout myuservideo;
    @BindView(R2.id.myuserfriend)
    LinearLayout myuserfriend;
    @BindView(R2.id.myusertask)
    LinearLayout myusertask;
    @BindView(R2.id.myuserset)
    LinearLayout myuserset;
    @BindView(R2.id.myuserattention)
    LinearLayout myuserattention;
    @BindView(R2.id.my_video)
    ImageView myVideo;
    @BindView(R2.id.my_disease)
    ImageView myDisease;
    @BindView(R2.id.my_interest)
    ImageView myInterest;
    @BindView(R2.id.my_h_money)
    ImageView myHMoney;
    @BindView(R2.id.myuserlayout)
    LinearLayout myuserlayout;
    private String uid = null;//userid
    private String sid = null;//senserid
    private UserSignPresenter userSignPresenter;
    private List<String> intt;
    private MyUserUpdataHead myUserUpdataHead;
    //-------popup
    private View inflate;
    private TextView paizhao,xiangce,updata_finish;
    private PopupWindow window;

    private LoginBeanDao dao;
    private static String path = "/sdcard/DemoHead/";//sd路径
    //布局
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user;
    }

    //数据
    @Override
    protected void initView() {
        //数据库
        dao=DaoMaster.newDevSession(MyUserActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao();
        //p
        userSignPresenter = new UserSignPresenter(new getsigndata());
        myUserUpdataHead = new MyUserUpdataHead(new gethead());
        //头像popup
        inflate = View.inflate(MyUserActivity.this, R.layout.updata_head, null);
        paizhao=inflate.findViewById(R.id.paizhao);
        xiangce=inflate.findViewById(R.id.xiangce);
        updata_finish=inflate.findViewById(R.id.updata_finish);
        //进行无线点击
        //返回点击
        myuserfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //签到点击
        myuserQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    userSignPresenter.reqeust(uid,sid);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //消息点击
        myusermessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    intentByRouter(Constant.ACTIVITY_MYUSERMESSAGE);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //当前问诊点击
        myusernew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //历史问诊点击
        myuserhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //我的档案点击
        myuserrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    Intent intent = new Intent(MyUserActivity.this, MyUserRecordActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //我的收藏点击
        myusercollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserCollectActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //我的钱包点击
        myuserwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserWalletActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //查询被采纳的建议
        myusersuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserSuggestActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //我的视频点击
        myuservideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        //设置跳转
        myuserset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserSetActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //我的视频
        myVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyUserActivity.this, MyVideoActivity.class);
                startActivity(intent);
            }
        });
        //我的病友圈
        myDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyUserActivity.this, MyDiseaseActivity.class);
                startActivity(intent);
            }
        });
        //我的关注
        myInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyUserActivity.this, MyInterestActivity.class);
                startActivity(intent);
            }
        });
        //我的任务
        myHMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyUserActivity.this, MyHMoneyActivity.class);
                startActivity(intent);
            }
        });
        //头像点击
        myuserheadportrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果是在登录状态
                if (intt != null) {
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
                    window.showAtLocation(MyUserActivity.this.findViewById(R.id.myuserlayout),
                            Gravity.BOTTOM, 0, 0);
                    //消失点击
                }else{
                    //跳转登录
                    intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                }
            }
        });
        //头像popup点击
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*try {
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//开启相机应用程序获取并返回图片（capture：俘获）
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                            "head.jpg")));//指明存储图片或视频的地址URI
                    startActivityForResult(intent2, 200);//采用ForResult打开
                } catch (Exception e) {
                    Toast.makeText(MyUserActivity.this, "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
                }*/
                window.dismiss();
                Snackbar.make(myuserlayout, "待开发", Snackbar.LENGTH_SHORT).show();
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
    @Override
    protected void destoryData() {

    }
    //头像回来的数据

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == 200 &&resultCode == RESULT_OK) {
            /*File temp = new File(Environment.getExternalStorageDirectory()
                    + "/head.jpg");
            crop(Uri.fromFile(temp));//裁剪图片*/

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
            myUserUpdataHead.reqeust(uid,sid,file);
            myuserheadportrait.setImageBitmap(bitmap);
            mLoadDialog.show();
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
    /*//保存到sd卡中
        private void setPicToView(Bitmap mBitmap) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd卡是否可用
                return;
            }
            FileOutputStream b = null;
            File file = new File(path);
            file.mkdirs();// 创建以此File对象为名（path）的文件夹
            String fileName = path + "head.jpg";//图片名字
            try {
                b = new FileOutputStream(fileName);
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件（compress：压缩）
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    //关闭流
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }*/

//回来的数据---------------------------------------------------------------
    class getsigndata implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            myuserQiandao.setText("签到成功");
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //修改头像
    class gethead implements DataCall<String>{
        @Override
        public void success(String data, Object... args) {
            //取出数据库的那一张图片，进行替换,然后谈吐seankbar
            List<LoginBean> loginBeans = dao.loadAll();
            LoginBean loginBean = loginBeans.get(0);
            loginBean.setHeadPic(data);
            dao.insertOrReplaceInTx(loginBean);
            Glide.with(MyUserActivity.this).load(data).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myuserheadportrait);//头像
            mLoadDialog.cancel();
            window.dismiss();
            Snackbar.make(myuserlayout, "更换成功", Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //---------------------------------------------------------------获取焦点
    @Override
    protected void onResume() {
        super.onResume();
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        intt = loginDaoUtil.intt(MyUserActivity.this);
        if (intt != null) {
            //设置用户头像
            uid=intt.get(0);
            sid=intt.get(1);
            Glide.with(MyUserActivity.this).load(intt.get(2)).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myuserheadportrait);//头像
            myusername.setText(intt.get(3));//昵称
        }else{
            myusername.setText("请先登录!");//设置昵称
            Glide.with(this).load(R.drawable.register_icon_invitatiion_code_n).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myuserheadportrait);//设置头像
        }
    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        userSignPresenter.unBind();//解绑
    }
}
