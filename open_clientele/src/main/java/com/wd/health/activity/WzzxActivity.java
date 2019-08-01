package com.wd.health.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.WzysBean;
import com.dingtao.common.bean.homepage.WzysbtnBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.AAdapter;
import com.wd.health.adapter.homepageadapter.ImageAdapter;
import com.wd.health.presenter.homepagepresenter.QianBaoPresenter;
import com.wd.health.presenter.homepagepresenter.WzzxPresenter;
import com.wd.health.presenter.homepagepresenter.YslbPresenter;
import com.wd.health.presenter.videopresenter.VideoGetPricePresenter;
import com.wd.health.util.CommomDialog;
import com.wd.health.util.MyDialog;
import com.wd.im.activity.ConsultChatMainActivity;

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
   /* @BindView(R2.id.viewpager)
    RecyclerView viewPager;*/
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
    @BindView(R2.id.lv)
    GridView listView;
    @BindView(R2.id.ysxqjid)
    ImageView ysxqjid;

    private AAdapter aAdapter;
    private YslbPresenter yslbPresenter;
    private int tj=1;
    private int jgpx;
    private int idid=1;
    //private ImageAdapter imageAdapter;
    private ListViewAdapter adapter;
    private int qwer=1;
    int index=0;
    int pagerCount=3;
    private int clickTemp = -1;
    private boolean hasChecked = false;
    private int pos;
    private String userId;
    private String sesssionId;


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
        userId = intt.get(0);
        sesssionId = intt.get(1);
        String tx = intt.get(2);
        Glide.with(this).load(tx).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(aImages);
        bton1.setTextColor(Color.parseColor("#D92109"));
        yslbPresenter = new YslbPresenter(new DataCall<List<WzysBean>>() {
            @Override
            public void success(List<WzysBean> data, Object... args) {
                Log.e("aaaa",data+"+++++++");
                if (data.size()<1){
                    Toast.makeText(WzzxActivity.this, "该条目没有数据", Toast.LENGTH_SHORT).show();
                    q.setVisibility(View.GONE);
                    w.setVisibility(View.GONE);
                    weizhaodao1.setVisibility(View.VISIBLE);
                }else{
                    /*checkButton(data);*/
                    q.setVisibility(View.VISIBLE);
                    w.setVisibility(View.VISIBLE);
                    weizhaodao1.setVisibility(View.GONE);
                    GridLayoutManager layoutManager = new GridLayoutManager(WzzxActivity.this,3){
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    //shuzi.setText(index+1+"/"+(int) Math.ceil((double)data.size()/pagerCount));
                    you.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index++;
                            System.out.println(index+"nexPager");
                            shuzi.setText(index+1+"/"+(int) Math.ceil((double)data.size()/pagerCount));
                            zhanshi(data,index*3);
                            //隐藏上一个或下一个按钮
                            if (index<=0){
                                zuo.setVisibility(View.GONE);
                                you.setVisibility(View.VISIBLE);
                            }else if (data.size()-index*pagerCount<=pagerCount){    //数据总数减每页数当小于每页可显示的数字时既是最后一页
                                you.setVisibility(View.GONE);
                                zuo.setVisibility(View.VISIBLE);
                            }else{
                                you.setVisibility(View.VISIBLE);
                                zuo.setVisibility(View.VISIBLE);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    });
                    zuo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index--;
                            System.out.println(index+"prePager");
                            adapter.notifyDataSetChanged();
                            shuzi.setText(index+1+"/"+(int) Math.ceil((double)data.size()/pagerCount));
                            zhanshi(data,index*3);
                            //隐藏上一个或下一个按钮
                            if (index<=0){
                                zuo.setVisibility(View.GONE);
                                you.setVisibility(View.VISIBLE);
                            }else if (data.size()-index*pagerCount<=pagerCount){    //数据总数减每页数当小于每页可显示的数字时既是最后一页
                                you.setVisibility(View.GONE);
                                zuo.setVisibility(View.VISIBLE);
                            }else{
                                you.setVisibility(View.VISIBLE);
                                zuo.setVisibility(View.VISIBLE);
                            }
                            adapter .notifyDataSetChanged();

                        }
                    });
                    //禁止滑动
                    listView.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return MotionEvent.ACTION_MOVE == event.getAction() ? true
                                    : false;
                        }
                    });
                    //去掉点击效果
                    listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
                    adapter = new ListViewAdapter(data);
                    listView.setAdapter(adapter);
                    you.setVisibility(View.VISIBLE);
                    shuzi.setText(index+1+"/"+(int) Math.ceil((double)data.size()/pagerCount));
                    if (index<=0){
                        zuo.setVisibility(View.GONE);
                        you.setVisibility(View.VISIBLE);
                    }else if (data.size()-index*pagerCount<=pagerCount){    //数据总数减每页数当小于每页可显示的数字时既是最后一页
                        you.setVisibility(View.GONE);
                        zuo.setVisibility(View.VISIBLE);
                    }
                    if (index+1==(int) Math.ceil((double)data.size()/pagerCount)){
                        you.setVisibility(View.GONE);
                        zuo.setVisibility(View.GONE);
                    }
                    //设置方向为横向，不设置默认为纵向
                    // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    /*viewPager.setLayoutManager(layoutManager);
                    imageAdapter = new ImageAdapter(WzzxActivity.this,data);
                    //ImageAdapter1 imageAdapter = new ImageAdapter1(WzzxActivity.this,data);
                    viewPager.setAdapter(imageAdapter);*/

                    //int pos = position + index * pagerCount;
                    zhanshi(data,0);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            int pos = position + index * pagerCount;
                            WzysBean wzysBean = data.get(pos);
                            String img =wzysBean.getImagePic();
                            String name = wzysBean.getDoctorName();
                            String zrys = wzysBean.getJobTitle();
                            String yiyuan = wzysBean.getInauguralHospital();
                            String aho = wzysBean.getPraise();
                            int zhsu = wzysBean.getServerNum();
                            int jiage = wzysBean.getServicePrice();
                            int doctorId = wzysBean.getDoctorId();
                            setKognajina(img,name,zrys,yiyuan,aho,zhsu,jiage,doctorId);
                            TextView name1 = (TextView) view.findViewById(R.id.name_l);
                            for (int i = 0; i < data.size(); i++) {
                                data.get(i).setTextcolor(0xff999999);
                                name1.setBackgroundColor(data.get(i).getTextcolor());
                            }
                            data.get(pos).setTextcolor(0xff3087ea);
                            name1.setBackgroundColor(data.get(pos).getTextcolor());
                            adapter.notifyDataSetChanged();

                        }
                    });


                }
            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        yslbPresenter.reqeust(userId, sesssionId,idid,1,jgpx,1,10);

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
                        yslbPresenter.reqeust(userId, sesssionId,id,tj,jgpx,1,10);
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
                yslbPresenter.reqeust(userId, sesssionId,idid,tj,jgpx,1,10);
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
                yslbPresenter.reqeust(userId, sesssionId,idid,tj,jgpx,1,10);
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
                yslbPresenter.reqeust(userId, sesssionId,idid,tj,jgpx,1,10);
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
                if (qwer==1){
                    Glide.with(WzzxActivity.this).load(R.drawable.common_button_descending_s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageSx);
                    qwer=2;
                    yslbPresenter.reqeust(userId, sesssionId,idid,tj,jgpx,1,10);
                }else{
                    Glide.with(WzzxActivity.this).load(R.drawable.common_button_ascending_s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageSx);
                    qwer=1;
                    yslbPresenter.reqeust(userId, sesssionId,idid,tj,1,1,10);
                }
                //yslbPresenter.reqeust(userId,sesssionId,idid,tj,jgpx,1,10);

            }
        });


    }

    private void setKognajina(String img,String name,String zrys,String yiyuan,String aho,int zhsu,int jiage,int doctorId) {
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
        ljzx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QianBaoPresenter qianBaoPresenter = new QianBaoPresenter(new DataCall<Double>() {
                    @Override
                    public void success(Double data, Object... args) {
                        if (data>=jiage){
                        CommomDialog commomDialog = new CommomDialog(WzzxActivity.this, R.style.dialog_xz, "本次咨询将扣除"+jiage+"H币！", new CommomDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if (confirm==true){
                                    Intent intent = new Intent(WzzxActivity.this,ConsultChatMainActivity.class);
                                    //穿一个医生id和名字
                                    intent.putExtra("name",name);
                                    intent.putExtra("id",doctorId);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(WzzxActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        });
                        commomDialog.setPositiveButton("去咨询").show();
                        }else{
                            CommomDialog commomDialog = new CommomDialog(WzzxActivity.this, R.style.dialog_xz, "H币不足"+jiage+"，充值再来吧！", new CommomDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if (confirm==true){
                                        Toast.makeText(WzzxActivity.this, "跳转充值页面", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(WzzxActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                }
                            });
                            commomDialog.setPositiveButton("去充值").show();
                        }
                    }

                    @Override
                    public void fail(ApiException data, Object... args) {
                        Log.e("aaaaaaaaaaaaa++",data+"");
                    }
                });
                qianBaoPresenter.reqeust(userId,sesssionId);//请求用户余额
            }
        });
        ysxqjid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WzzxActivity.this,YsxqActivity.class);
                intent.putExtra("doctorId",doctorId);
                startActivity(intent);
            }
        });
    }

    class ListViewAdapter extends BaseAdapter {
        private Context context;
        private List<WzysBean> data ;


        public ListViewAdapter(List<WzysBean> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            //数据大于页数*每页个数,显示默认数字,小于时显示剩余的
            int current = index * pagerCount;
            return data.size()-current<pagerCount?data.size()-current:pagerCount;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.pager_item,viewGroup,false);
            TextView name = (TextView) convertView.findViewById(R.id.name_l);
            ImageView yszp = (ImageView) convertView.findViewById(R.id.yszp_l);
            pos = position+index*pagerCount;
            Log.e("qqqqqqqqqqqqqqqq",pos+"+ pos = position + index * pagerCount;+++++"+position);
            WzysBean wzysBean = data.get(pos);
            if(data.get(pos).getImagePic()==null){
                Glide.with(WzzxActivity.this).load(R.drawable.system_image7).into(yszp);
            }else{
                Glide.with(WzzxActivity.this).load(wzysBean.getImagePic()).into(yszp);
            }
            name.setText(wzysBean.getDoctorName());
            name.setBackgroundColor(data.get(pos).getTextcolor());
            int doctorId = wzysBean.getDoctorId();

            return convertView;
        }
    }
    private void zhanshi(List<WzysBean> data,int index) {
        WzysBean wzysBean = data.get(index);
        String img =wzysBean.getImagePic();
        String name = wzysBean.getDoctorName();
        String zrys = wzysBean.getJobTitle();
        String yiyuan = wzysBean.getInauguralHospital();
        String aho = wzysBean.getPraise();
        int zhsu = wzysBean.getServerNum();
        int jiage = wzysBean.getServicePrice();
        int doctorId = wzysBean.getDoctorId();
        setKognajina(img,name,zrys,yiyuan,aho,zhsu,jiage,doctorId);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setTextcolor(0xff999999);
        }
        data.get(index).setTextcolor(0xff3087ea);

    }



}
