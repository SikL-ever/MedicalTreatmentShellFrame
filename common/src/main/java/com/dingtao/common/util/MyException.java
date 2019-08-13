package com.dingtao.common.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class MyException implements Thread.UncaughtExceptionHandler {
    private Context context;

    public MyException(Context context){
        this.context=context;
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.i("zlh",e.getMessage());
        Log.i("zlh","线程名字"+t.getName());
        //把日志保存到sd卡
        File file=new File(Environment.getExternalStorageDirectory(),"BT.log");
        try {
            //文件写入
            FileWriter fileWriter=new FileWriter(file);
            if (!file.exists()){
                file.createNewFile();
            }
            fileWriter.write(e.getMessage());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
