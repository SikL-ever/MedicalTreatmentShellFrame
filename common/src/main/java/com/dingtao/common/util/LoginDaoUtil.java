package com.dingtao.common.util;

import android.content.Context;
import android.util.Log;

import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.dao.DaoMaster;
import com.dingtao.common.dao.LoginBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 佀常勇
 *
 * @Data:2019/7/12 10:32
 * 描述：
 */
public class LoginDaoUtil {
    private LoginBeanDao dao;
    public List<String> intt(Context context){
        dao=DaoMaster.newDevSession(context,LoginBeanDao.TABLENAME).getLoginBeanDao();
        List<LoginBean> loginBeans = dao.loadAll();
        Log.i("aaa", "intt: "+loginBeans);
        for (int i = 0; i < loginBeans.size(); i++) {
            LoginBean loginBean = loginBeans.get(i);
            if (loginBean.ttt==2){
                List<String> list=new ArrayList<>();
                list.add(loginBean.id);
                list.add(loginBean.sessionId);
                list.add(loginBean.headPic);
                list.add(loginBean.nickName);
                return list;
            }
        }
        return null;
    }
}
