package com.wd.MyHome.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.CxyqmPresenter;
import com.wd.MyHome.presenter.ScyqmPresenter;
import com.wd.MyHome.util.BarcodeEncoder;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class YqhyActivity extends AppCompatActivity {

    @BindView(R2.id.yqhy_top)
    TopView yqhyTop;
    @BindView(R2.id.yqm)
    TextView yqm;
    @BindView(R2.id.fzyqm)
    TextView fzyqm;
    @BindView(R2.id.ewm)
    ImageView ewm;
    @BindView(R2.id.yqhy)
    Button yqhy;
    private List<String> intt;
    private String userId;
    private String sessionId;
    private CxyqmPresenter cxyqmPresenter;
    private String contentEtString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yqhy);
        ButterKnife.bind(this);
        yqhyTop.setTitle("邀请好友");


        intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);
        fzyqm.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        fzyqm.getPaint().setAntiAlias(true);//抗锯齿
        fzyqm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(yqm.getText());
                Toast.makeText(YqhyActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        cxyqmPresenter = new CxyqmPresenter(new DataCall<String>() {
            @Override
            public void success(String data, Object... args) {
                yqm.setText(data);
                String contentEtString = yqm.getText().toString().trim();
                if (TextUtils.isEmpty(contentEtString)) {
                    Toast.makeText(YqhyActivity.this, "请输入要生成二维码图片的字符串", Toast.LENGTH_SHORT).show();
                    return;
                }

        /*Bitmap bitmap = CodeCreator.createQRCode(contentEtString, 400, 400, null);
        if (bitmap != null) {
            contentIv.setImageBitmap(bitmap);
        }*/
                Bitmap bitmap = encodeAs(contentEtString);
                ewm.setImageBitmap(bitmap);
            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        ScyqmPresenter scyqmPresenter = new ScyqmPresenter(new DataCall() {
            @Override
            public void success(Object data, Object... args) {
                cxyqmPresenter.reqeust(userId,sessionId);
            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        scyqmPresenter.reqeust(userId,sessionId);




/*//使用水波纹
        final WaveView viewById = (WaveView) findViewById(R.id.bezier);
        viewById.post(new Runnable() {
            @Override
            public void run() {
                viewById.start();
            }
        });*/


    }
    public Bitmap encodeAs(String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 300, 300);
            // 使用 ZXing Android Embedded 要写的代码
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(result);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            return null;
        }
        return bitmap;
    }
}
