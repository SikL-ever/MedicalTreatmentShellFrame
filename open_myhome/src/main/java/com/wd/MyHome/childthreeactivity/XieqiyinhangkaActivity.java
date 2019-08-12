package com.wd.MyHome.childthreeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.bean.BankCardBean;
import com.wd.MyHome.cardUtils.Base64Util;
import com.wd.MyHome.cardUtils.FileUtil;
import com.wd.MyHome.cardUtils.GsonUtils;
import com.wd.MyHome.cardUtils.HttpUtil;
import com.wd.MyHome.presenter.BdyhkPresenter;
import com.wd.MyHome.util.DataFileUtil;
import com.wd.MyHome.util.TopView;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

public class XieqiyinhangkaActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_GENERAL = 103;
    private TopView topView;
    private Button ghbd;
    private ImageView imageView,xj;
    private String token;
    int l = 0;
    private TextView ps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xieqiyinhangka);
        topView = findViewById(R.id.yh_top);
        topView.setTitle("绑定银行卡");
        ghbd = findViewById(R.id.ghbd);
        imageView = findViewById(R.id.yhk);
        xj = findViewById(R.id.pz);
        ps = findViewById(R.id.pzwz);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        imageView.setMaxWidth(width-60);

        xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 生成intent对象
                Intent intent = new Intent(XieqiyinhangkaActivity.this, CameraActivity.class);
                // 设置临时存储
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,DataFileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);

            }
        });
        // 初始化
        initAccessTokenWithAkSk();
    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                token = result.getAccessToken();
                /*Toast.makeText(XieqiyinhangkaActivity.this, "成功+++++++++"+token, Toast.LENGTH_SHORT).show();
                // 调用成功，返回BankCardResult对象*/
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("aaaaaaaaaaa",token);
                        Toast.makeText(XieqiyinhangkaActivity.this, "初始化认证成功", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError子类SDKError对象
                error.printStackTrace();
                Log.e("MainActivity", "onError: " + error.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(XieqiyinhangkaActivity.this, "初始化认证失败,请检查 key", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, getApplicationContext(), "HfKpBExeStIyAARHPvW9X6zt", "fCvzrVpNka4RgGvA335XsMZo9i2a8Iuu");


    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 获取调用参数




        // 判断拍摄类型（通用，身份证，银行卡等）
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            // 判断是否是银行卡
            if (data!=null){


            String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
            // 通过临时文件获取拍摄的图片
            final String filePath = DataFileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();

            if (CameraActivity.CONTENT_TYPE_BANK_CARD.equals(contentType)) {
               /* BankCardParams param = new BankCardParams();
                param.setImageFile(new File(filePath));
                Glide.with(XieqiyinhangkaActivity.this).load(filePath).into(imageView);
                Toast.makeText(XieqiyinhangkaActivity.this, "认出银行卡", Toast.LENGTH_SHORT).show();
                String bankcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";

                try {
                    byte[] imgData = FileUtil.readFileByBytes(filePath);
                    String imgStr = Base64Util.encode(imgData);
                    String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
                    *//*
                     * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
                     *//*
                    String accessToken = token;
                    String result = HttpUtil.post(bankcardIdentificate, accessToken, params);
                    BankCardBean bean = GsonUtils.fromJson(result, BankCardBean.class);
                    BankCardBean.ResultBean result1 = bean.getResult();
                    Log.i("aaaaaaaaaaaaaaaaaa: ","卡号："+result1.getBank_card_number()+"                "+"银行卡类型："+result1.getBank_card_type()+"              "+"银行卡名字："+result1.getBank_name()+"               "+"时间："+result1.getValid_date());

                } catch (Exception e) {
                    e.printStackTrace();
                }*/



                // 获取图片文件调用sdk数据接口，见数据接口说明
                BankCardParams param = new BankCardParams();
                param.setImageFile(new File(filePath));
                OCR.getInstance().recognizeBankCard(param, new OnResultListener<BankCardResult>() {

                    private Bitmap bm;

                    @Override
                    public void onResult(final BankCardResult result) {
                        BankCardResult.BankCardType bankCardType = result.getBankCardType();
                        if (result != null) {

                            String type;

                            if (result.getBankCardType() == BankCardResult.BankCardType.Credit) {
                                type = "信用卡";
                                l=2;
                            } else if (result.getBankCardType() == BankCardResult.BankCardType.Debit) {
                                type = "借记卡";
                                l=1;
                            } else {
                                type = "不能识别";
                            }
                        Log.i("aaaaaaaaaaaaaaaaaa: ","卡号："+result.getBankCardNumber()+"                "+"银行卡类型："+result.getBankCardType()+"              "+"银行卡名字："+result.getBankName());
                        Toast.makeText(XieqiyinhangkaActivity.this, "认出银行卡", Toast.LENGTH_SHORT).show();
                        final File file = new File(filePath);
                        if (file.exists()) {
                            xj.setVisibility(View.GONE);
                            ps.setVisibility(View.GONE);
                            bm = BitmapFactory.decodeFile(filePath);
                            imageView.setImageBitmap(bm);
                        }

                        ghbd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (file.exists()){
                                    Intent intent = new Intent(XieqiyinhangkaActivity.this,YzyhkActivity.class);
                                    intent.putExtra("number",result.getBankCardNumber());
                                    intent.putExtra("typpe",l);
                                    intent.putExtra("cardname",result.getBankName());
                                    intent.putExtra("bm",filePath);
                                    startActivity(intent);
                                    xj.setVisibility(View.VISIBLE);
                                    ps.setVisibility(View.VISIBLE);
                                    finish();
                                }else{
                                    Toast.makeText(XieqiyinhangkaActivity.this, "请上传银行卡", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        }
                    }
                        @Override
                        public void onError(OCRError error) {
                        // 调用失败，返回OCRError对象
                             Log.i("aaaaaaaaaaaaaaaaaa: ",error+"  ");

                         }
                    });

                }else{
                    Log.e("aaaaaaaa","不是银行卡");
                }
            }else{
                Log.e("aaaaaaaa","为空");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 释放内存资源
        OCR.getInstance().release();
    }


}
