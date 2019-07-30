package com.wd.health.activity;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.homepage.XqBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.presenter.homepagepresenter.QxscPresenter;
import com.wd.health.presenter.homepagepresenter.XqPresenter;
import com.wd.health.presenter.homepagepresenter.ZxscPresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class ZxxqActivity extends WDActivity {


    @BindView(R2.id.images_tx2)
    ImageView imagesTx2;
    @BindView(R2.id.edit)
    TextView edit;
    @BindView(R2.id.xiaoxi)
    CheckBox xiaoxi;
    @BindView(R2.id.aaaa)
    RelativeLayout aaaa;
    @BindView(R2.id.title)
    TextView title;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.date)
    TextView date1;
    @BindView(R2.id.time)
    TextView time;
    @BindView(R2.id.webView)
    WebView webView;
    @BindView(R2.id.fenxiang)
    ImageView fenxiang;
    @BindView(R2.id.shoucang)
    ImageView shoucang;
    private String userId = null;
    private String sessionId = null;
    private int id;
    private List<String> intt;
    private int whetherCollection;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxxq;
    }

    @Override
    protected void initView() {
        imagesTx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        intt = loginDaoUtil.intt(this);


        int idxq = getIntent().getIntExtra("idxq", 0);
        if (intt !=null){
            userId = intt.get(0);
            sessionId = intt.get(1);
            XqPresenter xqPresenter = new XqPresenter(new XqShow());
            xqPresenter.reqeust(idxq,userId,sessionId);
        }else{
            XqPresenter xqPresenter = new XqPresenter(new XqShow());
            xqPresenter.reqeust(idxq,userId,sessionId);
        }

        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt!=null){
                    if (whetherCollection==1){
                        QxscPresenter qxscPresenter = new QxscPresenter(new Zxscshow1());
                        qxscPresenter.reqeust(userId,sessionId,id);
                        whetherCollection=2;
                    }else{
                        ZxscPresenter zxscPresenter = new ZxscPresenter(new Zxscshow());
                        zxscPresenter.reqeust(userId,sessionId,id);
                        whetherCollection=1;
                    }
                }else{
                    intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                    Toast.makeText(ZxxqActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void destoryData() {

    }

    private class XqShow implements DataCall<XqBean> {
        @Override
        public void success(final XqBean data, Object... args) {
            title.setText(data.getTitle());
            name.setText(data.getSource());
            id = data.getId();
            Long time = Long.valueOf(data.getReleaseTime());
            String pattern = "yyyy-MM-dd    HH:mm:ss";
            Date date = new Date(time);
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date1.setText(format.format(date));
            Document parse = Jsoup.parse(data.getContent());
            Elements imgs = parse.getElementsByTag("img");
            if (!imgs.isEmpty()) {
                for (Element e : imgs) {
                    imgs.attr("width", "100%");
                    imgs.attr("height", "auto");
                }
            }
            String content = parse.toString();
            webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);

          /*  String js = "<script type=\"text/javascript\">" +
                    "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                    "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                    "imgs[i].style.width = '100%';" +  // 宽度改为100%
                    "imgs[i].style.height = 'auto';" +
                    "}" +
                    "</script>";
            webView.loadData(data.getContent() + js, "text/html; charset=UTF-8", null);*/
            whetherCollection = data.getWhetherCollection();
            if (whetherCollection ==1){
                Glide.with(ZxxqActivity.this).load(R.drawable.common_button_collection_small_s).into(shoucang);
            }else{
                Glide.with(ZxxqActivity.this).load(R.drawable.common_button_collection_small_n).into(shoucang);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }


    private class Zxscshow implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {
            Glide.with(ZxxqActivity.this).load(R.drawable.common_button_collection_small_s).into(shoucang);
           // Toast.makeText(ZxxqActivity.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void fail(ApiException data, Object... args) {
          //  Toast.makeText(ZxxqActivity.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private class Zxscshow1 implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {
            Glide.with(ZxxqActivity.this).load(R.drawable.common_button_collection_small_n).into(shoucang);
          //  Toast.makeText(ZxxqActivity.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {
           // Toast.makeText(ZxxqActivity.this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
