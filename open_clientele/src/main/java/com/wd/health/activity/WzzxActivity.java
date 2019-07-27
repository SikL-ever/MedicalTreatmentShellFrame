package com.wd.health.activity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.WzysBean;
import com.dingtao.common.bean.homepage.WzysbtnBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.AAdapter;
import com.wd.health.adapter.homepageadapter.ImageAdapter;
import com.wd.health.presenter.homepagepresenter.WzzxPresenter;
import com.wd.health.presenter.homepagepresenter.YslbPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

public class WzzxActivity extends AppCompatActivity {

    @BindView(R2.id.a_recycler)
    RecyclerView aRecycler;
    @BindView(R2.id.a_images)
    ImageView aImages;
    @BindView(R2.id.edit12)
    TextView edit12;
    @BindView(R2.id.xiaoxi_121)
    CheckBox xiaoxi121;
    @BindView(R2.id.aaaa)
    RelativeLayout aaaa;
    @BindView(R2.id.bton1)
    TextView bton1;
    @BindView(R2.id.bton2)
    TextView bton2;
    @BindView(R2.id.bton3)
    TextView bton3;
    @BindView(R2.id.bton4)
    TextView bton4;
    @BindView(R2.id.image_sx)
    ImageView imageSx;
    @BindView(R2.id.image_big)
    ImageView imageBig;
    @BindView(R2.id.name_ys)
    TextView nameYs;
    @BindView(R2.id.guan)
    TextView guan;
    @BindView(R2.id.yy)
    TextView yy;
    @BindView(R2.id.hp)
    TextView hp;
    @BindView(R2.id.hzs)
    TextView hzs;
    @BindView(R2.id.jg)
    TextView jg;
    @BindView(R2.id.ljzx)
    TextView ljzx;
    @BindView(R2.id.viewpager)
    RecyclerView viewPager;
    @BindView(R2.id.container)
    RelativeLayout container;
    @BindView(R2.id.weizhaodao1)
    LinearLayout weizhaodao1;
    @BindView(R2.id.q)
    RelativeLayout q;
    @BindView(R2.id.w)
    RelativeLayout w;
    @BindView(R2.id.shuzi)
    TextView shuzi;
    @BindView(R2.id.zuo)
    TextView zuo;
    @BindView(R2.id.you)
    TextView you;

    private AAdapter aAdapter;
    private YslbPresenter yslbPresenter;
    private int tj=1;
    private int jgpx;
    private int idid=1;
    private ImageAdapter imageAdapter;
    //private int xb;
    int index=0;
    int pagerCount=3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wzzx);
        idid = getIntent().getIntExtra("idid",0);
      //  xb = getIntent().getIntExtra("xb",0);
        ButterKnife.bind(this);
        /*// 1.设置幕后item的缓存数目
        viewPager.setOffscreenPageLimit(3);
// 2.设置页与页之间的间距
        viewPager.setPageMargin(10);
// 3.将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });*/

        aRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        aAdapter = new AAdapter(this);
        aRecycler.setAdapter(aAdapter);
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(this);
        String userId = intt.get(0);
        String sesssionId = intt.get(1);
        String tx = intt.get(2);
        Glide.with(this).load(tx).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(aImages);
        bton1.setTextColor(Color.parseColor("#D92109"));
        yslbPresenter = new YslbPresenter(new DataCall<List<WzysBean>>() {

           /* class ImageAdapter1 extends RecyclerView.Adapter<ImageAdapter1.ViewHolder> {
                private Context context;
                private  List<WzysBean> dataCall;
                public ImageAdapter1(WzzxActivity wzzxActivity, List<WzysBean> dataCall) {
                    this.context = wzzxActivity;
                    this.dataCall = dataCall;
                }

                @androidx.annotation.NonNull
                @Override
                public ImageAdapter1.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
                    View view = View.inflate(context,R.layout.image_item,null);
                    ImageAdapter1.ViewHolder viewHolder = new ImageAdapter1.ViewHolder(view);
                    return viewHolder;
                }

                @Override
                public void onBindViewHolder(@androidx.annotation.NonNull ImageAdapter1.ViewHolder holder, int position) {
                    RoundedCorners roundedCorners= new RoundedCorners(10);
                    RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(240, 240);
                    if(dataCall.get(position).getImagePic()==null){
                        Glide.with(context).load(R.drawable.system_image7).apply(options).into(holder.imageView);
                    }else{
                        Glide.with(context).load(dataCall.get(position).getImagePic()).apply(options).into(holder.imageView);
                    }
                    holder.textView1.setText(dataCall.get(position).getDoctorName());
                    holder.textView1.setBackgroundColor(dataCall.get(position).getTextcolor());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < dataCall.size(); i++) {
                                dataCall.get(i).setTextcolor(0xff999999);
                                holder.textView1.setBackgroundColor(dataCall.get(i).getTextcolor());
                            }
                            dataCall.get(position).setTextcolor(0xff3087ea);
                            holder.textView1.setBackgroundColor(dataCall.get(position).getTextcolor());
                            // huIdiaoId.huidiaoId(position);
                            notifyDataSetChanged();
                        }
                    });
                }

                @Override
                public int getItemCount() {
                    *//*int current = index * pagerCount;
                    return dataCall.size()-current<pagerCount?dataCall.size()-current:pagerCount;*//*
                    return dataCall.size();
                }

                class ViewHolder extends RecyclerView.ViewHolder {
                    ImageView imageView;
                    TextView textView1;
                    public ViewHolder(@androidx.annotation.NonNull View itemView) {
                        super(itemView);
                        imageView = itemView.findViewById(R.id.yszp);
                        textView1 = itemView.findViewById(R.id.ysname);
                    }
                }

                private ImageAdapter.HUIdiaoId huIdiaoId;

                public void getKjens(ImageAdapter.HUIdiaoId callBackj) {
                    this.huIdiaoId = callBackj;
                }

                abstract class HUIdiaoId{
                    public abstract void huidiaoId(int position);
                }
            }*/

            @Override
            public void success(List<WzysBean> data, Object... args) {
                Log.e("aaaa",data+"+++++++");
                if (data.size()<1){
                    Toast.makeText(WzzxActivity.this, "该条目没有数据", Toast.LENGTH_SHORT).show();
                    q.setVisibility(View.GONE);
                    w.setVisibility(View.GONE);
                    weizhaodao1.setVisibility(View.VISIBLE);
                }/*else if(){

                }*/else{
                    checkButton(data);
                    q.setVisibility(View.VISIBLE);
                    w.setVisibility(View.VISIBLE);
                    weizhaodao1.setVisibility(View.GONE);
                    GridLayoutManager layoutManager = new GridLayoutManager(WzzxActivity.this,3){
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    shuzi.setText(index+1+"/"+(int) Math.ceil((double)data.size()/pagerCount));
                    you.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(WzzxActivity.this, "+++", Toast.LENGTH_SHORT).show();
                            index++;
                            System.out.println(index+"nexPager");
                            shuzi.setText(index+1+"/"+(int) Math.ceil((double)data.size()/pagerCount));
                            imageAdapter .notifyDataSetChanged();
                            //隐藏上一个或下一个按钮
                            if (index<=0){
                                zuo.setVisibility(View.GONE);
                                you.setVisibility(View.VISIBLE);
                            }else if (data.size()-index*pagerCount<=pagerCount){    //数据总数减每页数当小于每页可显示的数字时既是最后一页
                                you.setVisibility(View.GONE);
                                zuo.setVisibility(View.VISIBLE);
                            }
                        }
                    });


                    zuo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index--;
                            System.out.println(index+"prePager");
                            imageAdapter.notifyDataSetChanged();
                            shuzi.setText(index+1+"/"+(int) Math.ceil((double)data.size()/pagerCount));
                            //隐藏上一个或下一个按钮
                            if (index<=0){
                                zuo.setVisibility(View.GONE);
                                you.setVisibility(View.VISIBLE);
                            }else if (data.size()-index*pagerCount<=pagerCount){    //数据总数减每页数当小于每页可显示的数字时既是最后一页
                                you.setVisibility(View.GONE);
                                zuo.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    //设置方向为横向，不设置默认为纵向
                    // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    viewPager.setLayoutManager(layoutManager);
                    imageAdapter = new ImageAdapter(WzzxActivity.this,data);
                    //ImageAdapter1 imageAdapter = new ImageAdapter1(WzzxActivity.this,data);
                    viewPager.setAdapter(imageAdapter);

                    String img = data.get(0).getImagePic();
                    String name = data.get(0).getDoctorName();
                    String zrys = data.get(0).getJobTitle();
                    String yiyuan = data.get(0).getInauguralHospital();
                    String aho = data.get(0).getPraise();
                    int zhsu = data.get(0).getServerNum();
                    int jiage = data.get(0).getServicePrice();
                    setKognajina(img,name,zrys,yiyuan,aho,zhsu,jiage);
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setTextcolor(0xff999999);
                    }
                    data.get(0).setTextcolor(0xff3087ea);
                    imageAdapter.getKjens(new ImageAdapter.HUIdiaoId() {
                        @Override
                        public void huidiaoId(int position) {
                            String img = data.get(position).getImagePic();
                            String name = data.get(position).getDoctorName();
                            String zrys = data.get(position).getJobTitle();
                            String yiyuan = data.get(position).getInauguralHospital();
                            String aho = data.get(position).getPraise();
                            int zhsu = data.get(position).getServerNum();
                            int jiage = data.get(position).getServicePrice();
                            setKognajina(img,name,zrys,yiyuan,aho,zhsu,jiage);
                        }
                    });
                    imageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        yslbPresenter.reqeust(userId,sesssionId,idid,1,jgpx,1,10);

        WzzxPresenter wzzxPresenter = new WzzxPresenter(new DataCall<List<WzzxBean>>() {
            @Override
            public void success(List<WzzxBean> data, Object... args) {
                aAdapter.setList(data);
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setTextcolor(0xff999999);
                    if (data.get(i).getId()==idid){
                        data.get(i).setTextcolor(0xff3087ea);
                    }
                }
                aAdapter.notifyDataSetChanged();
                aAdapter.setOnRecyclerViewItemClickListener12(new AAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        //拿适配器调用适配器内部自定义好的setThisPosition方法（参数写点击事件的参数的position）
                        Log.e("aaaaaaaaaaa",position+"+++");

                            aAdapter.setThisPosition2(position);
                            //嫑忘记刷新适配器
                            aAdapter.notifyDataSetChanged();

                    }
                });
                aAdapter.getID1(new AAdapter.CallBackID() {
                    @Override
                    public void callback1(int id) {
                        Log.e("iiiiiiiiiiiiiii123",id+"");
                        yslbPresenter.reqeust(userId,sesssionId,id,tj,jgpx,1,10);
                        idid = id;
                    }
                });
            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        wzzxPresenter.reqeust();

        Log.e("iiiiiiiiiiiiiii",idid+"");
        /*aAdapter.setThisPosition2(idid);
        aAdapter.notifyDataSetChanged();*/

        bton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tj=1;
                bton1.setTextColor(Color.parseColor("#D92109"));
                bton2.setTextColor(Color.parseColor("#3B3B3B"));
                bton3.setTextColor(Color.parseColor("#3B3B3B"));
                bton4.setTextColor(Color.parseColor("#3B3B3B"));
                yslbPresenter.reqeust(userId,sesssionId,idid,tj,jgpx,1,10);
            }
        });
        bton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tj=2;
                bton2.setTextColor(Color.parseColor("#D92109"));
                bton1.setTextColor(Color.parseColor("#3B3B3B"));
                bton3.setTextColor(Color.parseColor("#3B3B3B"));
                bton4.setTextColor(Color.parseColor("#3B3B3B"));
                yslbPresenter.reqeust(userId,sesssionId,idid,tj,jgpx,1,10);
            }
        });
        bton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tj=3;
                bton3.setTextColor(Color.parseColor("#D92109"));
                bton1.setTextColor(Color.parseColor("#3B3B3B"));
                bton2.setTextColor(Color.parseColor("#3B3B3B"));
                bton4.setTextColor(Color.parseColor("#3B3B3B"));
                yslbPresenter.reqeust(userId,sesssionId,idid,tj,jgpx,1,10);
            }
        });
        bton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tj=4;
                bton4.setTextColor(Color.parseColor("#D92109"));
                bton1.setTextColor(Color.parseColor("#3B3B3B"));
                bton2.setTextColor(Color.parseColor("#3B3B3B"));
                bton3.setTextColor(Color.parseColor("#3B3B3B"));
               /* if (qwer==1){
                    Glide.with(WzzxActivity.this).load(R.drawable.common_button_sequence_n).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageSx);
                    qwer=2;
                    yslbPresenter.reqeust(userId,sesssionId,idi,tj,jgpx,1,10);
                }else{
                    Glide.with(WzzxActivity.this).load(R.drawable.common_button_ascending_s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageSx);
                    qwer=1;
                    yslbPresenter.reqeust(userId,sesssionId,idi,tj,1,1,10);
                }*/
                yslbPresenter.reqeust(userId,sesssionId,idid,tj,jgpx,1,10);

            }
        });


    }

    private void checkButton(List<WzysBean> data) {
        if (index<=0){
            zuo.setVisibility(View.GONE);
            you.setVisibility(View.VISIBLE);
        }else if (data.size()-index*pagerCount<=pagerCount){    //数据总数减每页数当小于每页可显示的数字时既是最后一页
            you.setVisibility(View.GONE);
            zuo.setVisibility(View.VISIBLE);
        }
    }

    private void checkButton() {

    }

    private void setKognajina(String img,String name,String zrys,String yiyuan,String aho,int zhsu,int jiage) {
        RoundedCorners roundedCorners= new RoundedCorners(4);
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners);
        if(img==null){
            Glide.with(WzzxActivity.this).load(R.drawable.system_image7).apply(options).into(imageBig);
        }else{
            Glide.with(WzzxActivity.this).load(img).apply(options).into(imageBig);
        }
        nameYs.setText(name);
        guan.setText(zrys);
        yy.setText(yiyuan);
        hp.setText("好评率"+aho);
        hzs.setText("服务患者数  "+zhsu);
        jg.setText(jiage+"H币/次");
    }

}
