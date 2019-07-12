package com.wd.login.adapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 20:41
 * 描述：
 */
public class GuidanceAdapter  extends FragmentPagerAdapter {
    List<Fragment> list;

    public GuidanceAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
