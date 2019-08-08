package com.wd.health.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wd.health.R;
import com.wd.health.R2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @BindView(R2.id.webView_web)
    WebView webViewWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        String webUrl = getIntent().getStringExtra("webUrl");
        /*Document parse = Jsoup.parse(webUrl);
        Elements imgs = parse.getElementsByTag("img");
        if (!imgs.isEmpty()) {
            for (Element e : imgs) {
                imgs.attr("width", "100%");
                imgs.attr("height", "auto");
            }
        }
        String content = parse.toString();
        webViewWeb.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);*/

        WebSettings settings = webViewWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        // 开启 localStorage
        webViewWeb.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        webViewWeb.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        webViewWeb.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        webViewWeb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //使用自定义的WebViewClient
        webViewWeb.setWebViewClient(new WebViewClient()
        {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
        /*settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);*/
        webViewWeb.loadUrl(webUrl);
    }
    // 覆盖onKeydown 添加处理WebView 界面内返回事件处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && webViewWeb.canGoBack())
        {
            webViewWeb.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
