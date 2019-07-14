package com.wd.health.CustomImageView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/12 13:18
 * @Description：描述信息
 */

public class ScrollLevitateTabView extends ScrollView {
    private OnScrollLintener onScrollLintener;
    public void setOnScrollLintener(OnScrollLintener onScrollLintener) {
        this.onScrollLintener = onScrollLintener;
    }
    public ScrollLevitateTabView(Context context) {
        this(context,null);
    }

    public ScrollLevitateTabView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollLevitateTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        //增加视图监听 在整个视图树绘制完成后会回调
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScrollLintener.onScroll(getScrollY());
            }
        });
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollLintener != null) {
            onScrollLintener.onScroll(t);
        }
    }

    public interface OnScrollLintener{
        void onScroll(int scrollY);
    }
}
