package com.wd.MyHome.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.SctxPresenter;
import com.wd.MyHome.util.RealPathFromUriUtils;
import com.wd.MyHome.util.TopView;

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
    private String userId;
    private String sessionId;
    private SctxPresenter sctxPresenter;
    private String file=Environment.getExternalStorageDirectory()+"/t.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdxx);
        ButterKnife.bind(this);
        myusersetTopWdxx.setTitle("我的信息");
        List<String> intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);
        Glide.with(this).load(intt.get(2)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ttx);//设置头像
        ttx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Portss();
                sctxPresenter = new SctxPresenter(new SctxShow());
            }
        });
    }
    public void Portss() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();//创建对话框
        dialog.setTitle("上传头像");//设置对话框标题
        //分别设置三个button
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(file)));
                startActivityForResult(intent,100);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent1=new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                startActivityForResult(intent1,200);
            }
        });
        dialog.show();//显示对话框
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
//    获取相机传过来的照片
            Uri uri=Uri.fromFile(new File(file));
            if (uri!=null) {
                File filea = new File(file);
                if (!filea.exists()) {
                    filea.mkdirs();
                }

                sctxPresenter.reqeust(userId, sessionId, filea);

            }
        }else if (requestCode==200&&resultCode==RESULT_OK){
            //    获取相册传过来的照片
            if (data.getData()!=null) {
                String realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                filea = new File(realPathFromUri);
                if (!filea.exists()){
                    filea.mkdirs();
                }
                sctxPresenter.reqeust(userId,sessionId, filea);
               /* Bitmap bitmap=BitmapFactory.decodeFile(realPathFromUri);
                saveBitmapFile(bitmap);*/


                Log.e("eee",realPathFromUri+"++++++++++++++++"+userId+"++++++++++++++"+sessionId+"++++++++++++++++"+ filea);
            }
        }
    }


    private class SctxShow implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(WdxxActivity.this, "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
