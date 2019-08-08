package com.wd.MyHome.childthreeactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.dingtao.common.core.WDActivity;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.util.DataFileUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SichangyongMyShenfenActivity extends WDActivity {

    private static final int REQUEST_CODE_CAMERA = 102;
    @BindView(R2.id.oneimage)
    ImageView oneimage;
    @BindView(R2.id.twoimage)
    ImageView twoimage;
    @BindView(R2.id.mybangbe)
    Button mybangbe;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sichangyong_my_shenfen;
    }

    @Override
    protected void initView() {
        //头像
      oneimage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(SichangyongMyShenfenActivity.this, CameraActivity.class);
              intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                      DataFileUtil.getSaveFile(getApplication()).getAbsolutePath());
              intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
              startActivityForResult(intent, REQUEST_CODE_CAMERA);
          }
      });
      //国会
        twoimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SichangyongMyShenfenActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        DataFileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        //初始化
        initAccessTokenWithAkSk();
    }

    @Override
    protected void destoryData() {

    }
    public void initAccessTokenWithAkSk(){
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {

                                                      @Override
                                                      public void onResult(AccessToken accessToken) {
                                                          Log.d("MainActivity", "onResult: " + accessToken.toString());
                                                          runOnUiThread(new Runnable() {
                                                              @Override
                                                              public void run() {
                                                                  Toast.makeText(SichangyongMyShenfenActivity.this, "初始化认证成功", Toast.LENGTH_SHORT).show();
                                                              }
                                                          });
                                                      }

                                                      @Override
                                                      public void onError(OCRError ocrError) {
                                                          ocrError.printStackTrace();
                                                          Log.e("MainActivity", "onError: " + ocrError.getMessage());
                                                          runOnUiThread(new Runnable() {
                                                              @Override
                                                              public void run() {
                                                                  Toast.makeText(SichangyongMyShenfenActivity.this, "初始化认证失败,请检查 key", Toast.LENGTH_SHORT).show();
                                                              }
                                                          });
                                                      }
                                                  }, getApplicationContext(),
                // 根据自己的包名，去百度云自行配置
                "HfKpBExeStIyAARHPvW9X6zt",
                // 根据自己的包名，去百度云自行配置
                "fCvzrVpNka4RgGvA335XsMZo9i2a8Iuu");


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = DataFileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                Toast.makeText(this, contentType, Toast.LENGTH_SHORT).show();
                Glide.with(this).load(filePath).into(oneimage);
            }
        }


    }

}
