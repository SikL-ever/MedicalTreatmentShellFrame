package com.wd.health.Helper;

import com.dingtao.common.bean.homepage.LsjlBean;
import com.dingtao.common.core.WDApplication;

import java.util.List;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/23 16:13
 * @Description：描述信息
 */
public class SearchHistoryDao {
    /**
     * 添加数据
     *
     * @param searchHistory
     */
    public static void insertHistory(LsjlBean searchHistory) {
        WDApplication.getDaoInstant().getLsjlBeanDao().insertOrReplace(searchHistory);
    }

    /**
     * 删除数据
     *
     * @param searchHistory
     */
    public static void deleteHistory(LsjlBean searchHistory) {
        WDApplication.getDaoInstant().getLsjlBeanDao().delete(searchHistory);
    }

    /**
     * 查询全部数据
     *
     * @return List<SearchHistory>
     */
    public static List<LsjlBean> queryAll() {
        return WDApplication.getDaoInstant().getLsjlBeanDao().loadAll();
    }

    /**
     * 清空数据
     */
    public static void clearAll() {
        WDApplication.getDaoInstant().getLsjlBeanDao().deleteAll();
    }
}
