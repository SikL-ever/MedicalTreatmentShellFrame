package com.wd.health.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.video.DanBean;
import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.videoadapter.TopRecyclerAdapter;
import com.wd.health.presenter.videopresenter.BulletscreenPresenter;
import com.wd.health.presenter.videopresenter.BuyVideoPresenter;
import com.wd.health.presenter.videopresenter.TopPresenter;
import com.wd.health.presenter.videopresenter.VideoCollectPresenter;
import com.wd.health.presenter.videopresenter.VideoGetPricePresenter;
import com.wd.health.presenter.videopresenter.VideoPresenter;
import com.wd.health.presenter.videopresenter.VideoSendBulletscreen;
import com.wd.health.util.Barrage;
import com.wd.health.util.BarrageView;
import com.wd.health.util.BaseRecAdapter;
import com.wd.health.util.BaseRecViewHolder;
import com.wd.health.util.MyVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

import static android.content.Context.MODE_PRIVATE;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 15:19
 * 描述：
 */

public class VideoFragment extends WDFragment {


    @BindView(R2.id.videotoprecycler)
    RecyclerView videotoprecycler;
    @BindView(R2.id.videoimage)
    ImageView videoimage;
    @BindView(R2.id.videorecycler)
    RecyclerView videorecycler;
    @BindView(R2.id.videosupernatantimagetwo)
    ImageView videosupernatantimagetwo;
    @BindView(R2.id.videolayout)
    RelativeLayout videolayout;
    @BindView(R2.id.vidoebarr)
    BarrageView vidoebarr;
    private TopPresenter topPresenter;
    private VideoPresenter videoPresenter;
    private TopRecyclerAdapter topRecyclerAdapter;
    private List<VideoBean> urlList = new ArrayList<>();
    private PagerSnapHelper snapHelper;
    private ListVideoAdapter videoAdapter;
    private LinearLayoutManager layoutManager;
    private String uid=null;
    private String sid=null;
    int page = 3;//选择模式
    //创建hind
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                page--;
                handler.sendEmptyMessageDelayed(1, 1000);
                if (page == 0) {
                    videotoprecycler.setVisibility(View.GONE);
                    page = 3;
                    handler.removeMessages(1);
                }
            }
        }
    };
    private SharedPreferences sp;//存值
    private VideoGetPricePresenter videoGetPricePresenter;//我的余额
    private double myprice;
    private BuyVideoPresenter buyVideoPresenter;
    private VideoCollectPresenter videoCollectPresenter;//收藏
    private VideoSendBulletscreen videoSendBulletscreen;//发送弹幕;
    private PopupWindow commentpopuyvideo;
    private BulletscreenPresenter bulletscreenPresenter;//弹幕查询

    @Override
    public String getPageName() {
        return null;
    }
    //视图
    @Override
    protected int getLayoutId() {
        return R.layout.video;
    }
    //数据
    @Override
    protected void initView() {
        //创建p层
        videoGetPricePresenter = new VideoGetPricePresenter(new videogetprice());
        topPresenter = new TopPresenter(new gettopdata());//请求顶部栏的数据
        topPresenter.reqeust();//顶部栏数据请求
        videoPresenter = new VideoPresenter(new getvideodata());
        buyVideoPresenter = new BuyVideoPresenter(new getbuyvideo());//购买的接口
        videoCollectPresenter = new VideoCollectPresenter(new videocollectdata());//收藏
        videoSendBulletscreen = new VideoSendBulletscreen(new videosendbulletscreen());//发送弹幕评论
        bulletscreenPresenter = new BulletscreenPresenter(new getdan());
        //播放视频的方法
        inittView();
        addListener();
        //浮层显示
        supernatant();
        //顶部栏显示
        //创建适配器
        topRecyclerAdapter = new TopRecyclerAdapter(getContext());
        videotoprecycler.setAdapter(topRecyclerAdapter);
        videotoprecycler.setLayoutManager
                (new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //顶部栏点击事件
        topRecyclerAdapter.setCallBack(new TopRecyclerAdapter.CallBack() {
            @Override
            public void data(String da) {
                int id = Integer.parseInt(da);
                videoPresenter.reqeust(uid, sid, id, 1, 10);
                page = 3;
            }
        });
        //点击下拉
        videoimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videotoprecycler.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        });
        //知道到了点击
        videosupernatantimagetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //JZVideoPlayer.goOnPlayOnResume();
                videolayout.setVisibility(View.GONE);//浮层小时
                //进行存值
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("videoflay", true);
                edit.commit();
            }
        });
    }
    //------------------------------------------------------------------------------请求回来的数据
    //顶部栏的数据
    class gettopdata implements DataCall<List<TopBean>> {
        @Override
        public void success(List<TopBean> data, Object... args) {
            topRecyclerAdapter.add(data);
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //请求下来的视频
    class getvideodata implements DataCall<List<VideoBean>> {
        @Override
        public void success(List<VideoBean> data, Object... args) {
            urlList.clear();
            urlList.addAll(data);
            videoAdapter.notifyDataSetChanged();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //请求下来的我的余额
    class videogetprice implements DataCall<Double>{
        @Override
        public void success(Double data, Object... args) {
            myprice=data;
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //收藏回来的数据
    class videocollectdata implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            Toast.makeText(getActivity(), data.message, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //发送弹幕成功的数据
    class videosendbulletscreen implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            commentpopuyvideo.dismiss();
            //进行重新查询弹幕列表进行添加。
            bulletscreenPresenter.reqeust(args[2]);//进行请求弹幕
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //弹幕请求
    class getdan implements DataCall<List<DanBean>> {
        @Override
        public void success(List<DanBean> data, Object... args) {
                List<Barrage> mBarrages = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    mBarrages.add(new Barrage(data.get(i).content, R.color.cf0f2f7, Color.BLACK));
                }
                vidoebarr.setBarrages(mBarrages);
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //购买返回回来的数据
    class getbuyvideo implements DataCall<Result>{
        @Override
        public void success(Result data, Object... args) {
            int mid=(int) args[4];
            videoPresenter.reqeust(uid, sid, mid, 1, 10);
            videoGetPricePresenter.reqeust(uid,sid);
            Toast.makeText(getActivity(), data.message, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //------------------------------------------------------------------------------浮层
    //浮层
    private void supernatant() {
        //创建sp
        sp = getActivity().getSharedPreferences("videosupernatant", MODE_PRIVATE);
        boolean flay = sp.getBoolean("videoflay", false);
        if (flay) {
            videolayout.setVisibility(View.GONE);//浮层消失
        } else {
            videolayout.setVisibility(View.VISIBLE);//浮层显示

        }
    }
    //------------------------------------------------------------------------------视频
    //视频
    private void inittView() {
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(videorecycler);
        videoAdapter = new ListVideoAdapter(urlList);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        videorecycler.setLayoutManager(layoutManager);
        videorecycler.setAdapter(videoAdapter);
    }
    private void addListener() {
        videorecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.i("bbb", "onScrollStateChanged: " + newState + "");
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = snapHelper.findSnapView(layoutManager);
                        JZVideoPlayer.releaseAllVideos();
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                        if (viewHolder != null && viewHolder instanceof VideoViewHolder) {
                            ((VideoViewHolder) viewHolder).mp_video.startVideo();
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }
            }
        });
    }
    //------------------------------------------------------------------------------内部适配器
    class ListVideoAdapter extends BaseRecAdapter<VideoBean, VideoViewHolder> {
        private PopupWindow buypopuyvideo;
        public ListVideoAdapter(List<VideoBean> list) {
            super(list);
        }
        @Override
        public void onHolder(final VideoViewHolder holder, final VideoBean bean, final int position) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (bean.whetherBuy == 2) {//2是没有购买的
                holder.mp_video.setUp(bean.shearUrl, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
                //判断如果视频已经购买，不显示已购买人数,
                holder.videobuytext.setVisibility(View.VISIBLE);
                holder.videobuytext.setText(bean.buyNum + "" + "已购买");//已购买人数
                holder.videoitemifbuy.setText("试看15s购买完整版视频");
                holder.videoitemifbuy.setVisibility(View.VISIBLE);
                //没有购买显示购买按钮
                Glide.with(getContext()).load(R.drawable.common_icon_toll_n).into(holder.videobtbuy);
            } else {
                holder.mp_video.setUp(bean.originalUrl, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
                holder.videobuytext.setVisibility(View.GONE);
                holder.videoitemifbuy.setVisibility(View.GONE);
                Glide.with(getContext()).load(R.drawable.common_icon_comment_samll_n).into(holder.videobtbuy);
            }
            if (position == 0) {
                holder.mp_video.startVideo();
            }
            Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
            holder.videotitle.setText(bean.title);//视频标题
            holder.videotext.setText(bean.abstracts);//视频摘要
            //判断视频是否收藏whetherCollection==2没有收藏
            if (bean.whetherCollection == 2) {
                holder.videobtcollect.setChecked(false);
            } else {
                holder.videobtcollect.setChecked(true);
            }
            //视频收藏点击
            holder.videobtcollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (uid == null) {
                        holder.videobtcollect.setChecked(false);
                       //跳转登录
                        JZVideoPlayer.goOnPlayOnPause();//视频暂停
                        intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                    }else{
                        //进行收藏视频2是没有收藏
                        if (bean.whetherCollection==2){
                            //--------------------------------------------------------------视频收藏
                            //进行收藏请求
                            videoCollectPresenter.reqeust(uid,sid,bean.id);
                            holder.videobtcollect.setChecked(true);
                        }else{
                            Toast.makeText(context, "视频已经收藏过了.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            //设置弹幕进入一个item进行判断，如果是显示弹幕进行请求显示，否则的话就是不显示弹幕+
            bulletscreenPresenter.reqeust(bean.id);//进行请求弹幕
            //进行判断是否显示弹幕
            if (holder.videobtbulletscreen.isChecked()) {
                vidoebarr.setVisibility(View.GONE);
            } else {
                vidoebarr.setVisibility(View.VISIBLE);
            }
            //弹幕点击事件
            holder.videobtbulletscreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.videobtbulletscreen.isChecked()) {
                        vidoebarr.setVisibility(View.GONE);
                    } else {
                        vidoebarr.setVisibility(View.VISIBLE);
                    }
                }
            });
            //这个是进行购买视频
            //购买按钮点击
            holder.videobtbuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进行判断是否登录状态
                    if (uid == null) {
                        JZVideoPlayer.goOnPlayOnPause();//视频暂停
                        intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                    }else{
                        //进行一个判断如果是购买的进行评论，如果不是购买的进行购买处理
                        if (bean.whetherBuy == 2) {
                            //2是没有购买1是购买了
                            //购买
                            //创建popup
                            buypopuyvideo = new PopupWindow(holder.popupone,
                                    WindowManager.LayoutParams.MATCH_PARENT,
                                    /* WindowManager.LayoutParams.WRAP_CONTENT*/
                                    (int) (getActivity().findViewById(R.id.layout_main).getHeight() * 0.5));
                            // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
                            buypopuyvideo.setFocusable(true);
                            // 设置点击popupwindow外屏幕其它地方消失
                            buypopuyvideo.setOutsideTouchable(true);
                            // 实例化一个ColorDrawable颜色为半透明
                            //ColorDrawable dw = new ColorDrawable(0xb0000000);
                            ColorDrawable dw = new ColorDrawable(Color.WHITE);
                            buypopuyvideo.setBackgroundDrawable(dw);
                            // 设置popWindow的显示和消失动画
                            buypopuyvideo.setAnimationStyle(R.style.anim_menu_bottombar);
                            // 在底部显示
                            buypopuyvideo.showAtLocation(getActivity().findViewById(R.id.layout_main),
                                    Gravity.BOTTOM, 0, 0);
                            //设置值
                            //图片
                            holder.videopopupmyprice.setText(bean.price + "");//价格
                        } else if (bean.whetherBuy == 1) {
                            //评论
                            //弹出输入框
                            commentpopuyvideo = new PopupWindow(holder.popuptwo,
                                    WindowManager.LayoutParams.MATCH_PARENT,
                                     WindowManager.LayoutParams.WRAP_CONTENT);
                            // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
                            commentpopuyvideo.setFocusable(true);
                            // 设置点击popupwindow外屏幕其它地方消失
                            commentpopuyvideo.setOutsideTouchable(true);
                            ColorDrawable dw = new ColorDrawable(Color.BLACK);
                            commentpopuyvideo.setBackgroundDrawable(dw);
                            // 设置popWindow的显示和消失动画
                            commentpopuyvideo.setAnimationStyle(R.style.anim_menu_bottombar);
                            // 在打字版上边
                            commentpopuyvideo.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                            // 在底部显示
                            commentpopuyvideo.showAtLocation(getActivity().findViewById(R.id.layout_main),
                                    Gravity.BOTTOM, 0, 0);
                        }
                    }
                }
            });
            //popupwindo的点击事件
            //设置值
            holder.videopopuptextone.setText("我的H币，"+myprice+"，H币不足？");
            //设置购买
            holder.videopopupbuybt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入购买页面
                    //创建弹框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    //    设置Title的内容
                    builder.setTitle("提示");
                    //    设置Content来显示一个信息
                    builder.setMessage("购买本视频将扣除"+bean.price+"H币");
                    //    设置一个PositiveButton
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //去购买本个视频
                            //请求接口
                            buyVideoPresenter.reqeust(uid,sid,bean.id,bean.price,bean.categoryId);
                            buypopuyvideo.dismiss();
                        }
                    });
                    //    设置一个NegativeButton
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "购买取消", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //显示出该对话框
                    builder.show();
                }
            });
            //设置popup的点击事件----消失popup
            holder.videopopuoonefinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buypopuyvideo.dismiss();
                }
            });
            //立即充值点击
            holder.videopopuptexttwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转充值页面

                }
            });

            //如果没有购买，播放试看结束后进行弹出
            holder.mp_video.setVideoCallBack(new MyVideoPlayer.VideoCallBack() {
                @Override
                public void getdata(int i) {
                    if (bean.whetherBuy == 2) {//2是没有购买
                        holder.videoendlayout.setVisibility(View.VISIBLE);
                    }
                }
            });
            //没有购买视频播放最后弹出的框
            holder.videoendlayoutbuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入购买页面
                    //创建弹框
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    //    设置Title的内容
                    builder.setTitle("提示");
                    //    设置Content来显示一个信息
                    builder.setMessage("购买本视频将扣除"+bean.price+"H币");
                    //    设置一个PositiveButton
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //去购买本个视频
                            //请求接口
                            buyVideoPresenter.reqeust(uid,sid,bean.id,bean.price,bean.categoryId);
                            holder.videoendlayout.setVisibility(View.GONE);
                            //进行重新数据
                        }
                    });
                    //    设置一个NegativeButton
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "购买取消", Toast.LENGTH_SHORT).show();
                            holder.videoendlayout.setVisibility(View.GONE);
                        }
                    });
                    //显示出该对话框
                    builder.show();
                }
            });
            //重新播放
            holder.videoendimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.videoendlayout.setVisibility(View.GONE);
                }
            });
            ///弹幕popup
            holder.videopopupsend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取值
                    String popuptext=holder.videopopupedit.getText().toString();
                    //进行非空判断
                    if(TextUtils.isEmpty(popuptext)){
                        Toast.makeText(context, "不能为空", Toast.LENGTH_SHORT).show();
                    }{
                        videoSendBulletscreen.reqeust(uid,sid,bean.id,popuptext,position);
                        Glide.with(getContext()).load(R.drawable.common_icon_comment_samll_s).into(holder.videobtbuy);
                    }
                }
            });
        }
        @Override
        public VideoViewHolder onCreateHolder() {
            return new VideoViewHolder(getViewByRes(R.layout.video_item));
        }
    }
    public class VideoViewHolder extends BaseRecViewHolder {
        public View rootView;
        public MyVideoPlayer mp_video;
        public TextView videotitle, videotext, videobuytext, videoitemifbuy,videoendlayoutbuy;
        public RadioButton videobtcollect;
        private CheckBox videobtbulletscreen;
        private ImageView videobtbuy,videoendimage;
        private View popupone,popuptwo;//popupwind
        private RelativeLayout videoendlayout;//结束的布局
        //------------购买popu控件
        ImageView videopopupimage, videopopuoonefinish;
        TextView videopopupmyprice, videopopuptextone, videopopuptexttwo;
        Button videopopupbuybt;
        //------------评论popu控件
        EditText videopopupedit;
        ImageView videopopupsend;
        public VideoViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mp_video = rootView.findViewById(R.id.mp_video);
            this.videotitle = rootView.findViewById(R.id.videotitle);
            this.videotext = rootView.findViewById(R.id.videotext);
            this.videobuytext = rootView.findViewById(R.id.videobuytext);
            this.videobtcollect = rootView.findViewById(R.id.videobtcollect);
            this.videoitemifbuy = rootView.findViewById(R.id.videoitemifbuy);
            this.videobtbuy = rootView.findViewById(R.id.videobtbuy);
            this.videobtbulletscreen = rootView.findViewById(R.id.videobtbulletscreen);
            this.videoendlayoutbuy = rootView.findViewById(R.id.videoendlayoutbuy);
            this.videoendlayout = rootView.findViewById(R.id.videoendlayout);
            this.videoendimage = rootView.findViewById(R.id.videoendimage);
            //-------------------------------------------购买的popup
            popupone = View.inflate(getContext(), R.layout.videopopupone, null);//popup
            videopopupimage = popupone.findViewById(R.id.videopopupimage);
            videopopuoonefinish = popupone.findViewById(R.id.videopopuoonefinish);
            videopopupmyprice = popupone.findViewById(R.id.videopopupmyprice);
            videopopuptextone = popupone.findViewById(R.id.videopopuptextone);
            videopopuptexttwo = popupone.findViewById(R.id.videopopuptexttwo);
            videopopupbuybt = popupone.findViewById(R.id.videopopupbuybt);
            //-------------------------------------------评论的popup
            popuptwo = View.inflate(getContext(), R.layout.videopopuptwo, null);//popup
            videopopupedit = popuptwo.findViewById(R.id.videopopupedit);
            videopopupsend = popuptwo.findViewById(R.id.videopopupsend);
        }
    }
    //------------------------------------------------------------------------------Resume
    //获取焦点的时候去请求数据
    @Override
    public void onResume() {
        super.onResume();
        //进行用户判断//判断用户时候登陆这
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(getContext());
        if (intt == null) {
            uid=null;
            sid=null;
        } else {
            uid = intt.get(0);
            sid = intt.get(1);
        }
    }
    //------------------------------------------------------------------------------销毁
    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        topPresenter.unBind();//解绑
        videoPresenter.unBind();
        videoGetPricePresenter.unBind();
        buyVideoPresenter.unBind();
        videoCollectPresenter.unBind();
        videoSendBulletscreen.unBind();
        bulletscreenPresenter.unBind();
        uid=null;
        sid=null;
        urlList=null;
    }
    //------------------------------------------------------------------------------hindshow，fragment显示
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //不可见的状态弹幕停止，视频停止
            JZVideoPlayer.goOnPlayOnPause();//视频暂停
            //vidoebarr.destroy();//清除弹幕
        }else{
            videoPresenter.reqeust(uid, sid, 1, 1, 10);
            //可见的状态
            JZVideoPlayer.goOnPlayOnResume();
            //判断是否为空
            if (uid == null) {

            }else{
                videoGetPricePresenter.reqeust(uid,sid);//请求用户余额
            }
            //为顶部栏发送handle
            videotoprecycler.setVisibility(View.VISIBLE);//顶部栏
            handler.sendEmptyMessageDelayed(1, 1000);//顶部栏发送消息
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.goOnPlayOnPause();//视频暂停
    }
}
