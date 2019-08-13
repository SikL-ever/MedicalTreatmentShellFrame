package com.wd.MyHome.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.MyHome.R;
import com.wd.MyHome.util.TopView;

public class VersionsActivity extends AppCompatActivity {

    TextView version_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_versions);
        TopView topView = findViewById(R.id.bbgx_top);
        topView.setTitle("版本检测");

        version_code = findViewById(R.id.version_code);
        version_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新
                try {
                    if (getVersionCode()==1) {
                        version_code.setText("已是最新版本");


                    }else if (getVersionCode()==2){
                        showDialogUpdate();//弹出提示版本更新的对话框

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
     * 获取当前程序的版本号
     */
    private int getVersionCode() throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager = this.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
        Log.e("TAG","版本号"+packInfo.versionCode);
        Log.e("TAG","版本名"+packInfo.versionName);
        return packInfo.versionCode;
    }


    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.drawable.group).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        try {
                            //newVersionPresenter.getData(userId,sessionId,getVersionCode());
                            loadNewVersionProgress();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }

    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final ProgressDialog pd;    //进度条对话框
        pd = new  ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);//设置最大值
        pd.setMessage("正在下载更新");
        pd.incrementProgressBy(0);//设置初始值为0，其实可以不用设置，默认就是0
        pd.setIndeterminate(false);//是否精确显示对话框，flase为是，反之为否
        //是否可以通过返回按钮退出对话框
        pd.setCancelable(true);
        pd.show();
        //启动子线程下载任务
        new Thread(){
            @Override
            public void run() {
                int initial = 0;//初始下载进度
                for (int i = 0; i <pd.getMax() ; i++) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //下载apk失败
                        Toast.makeText(VersionsActivity.this, "下载新版本失败", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    pd.incrementProgressBy(i);

                }
                pd.dismiss();
            }}.start();

    }


}
