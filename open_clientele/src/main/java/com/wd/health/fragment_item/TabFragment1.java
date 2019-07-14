package com.wd.health.fragment_item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.health.R;

import androidx.fragment.app.Fragment;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/12 14:21
 * @Description：描述信息
 */
public class TabFragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item1, container, false);
        return view;
    }
}
