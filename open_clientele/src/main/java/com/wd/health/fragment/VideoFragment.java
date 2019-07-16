package com.wd.health.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.video.DanBean;
import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.videoadapter.TopRecyclerAdapter;
import com.wd.health.presenter.videopresenter.BulletscreenPresenter;
import com.wd.health.presenter.videopresenter.TopPresenter;
import com.wd.health.presenter.videopresenter.VideoPresenter;
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
    @BindView(R2.id.topimage)
    ImageView topimage;
    @BindView(R2.id.topimagetwo)
    ImageView topimagetwo;
    @BindView(R2.id.toplayout)
    RelativeLayout toplayout;
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
    ;
    private PagerSnapHelper snapHelper;
    private ListVideoAdapter videoAdapter;
    private LinearLayoutManager layoutManager;

    private String uid;
    private String sid;
    int page = 3;
    //创建hind
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            page--;
            handler.sendEmptyMessageDelayed(1, 1000);
            if (page == 0) {
                videotoprecycler.setVisibility(View.GONE);
                page = 3;
                handler.removeMessages(1);
            }
        }
    };
    private SharedPreferences sp;


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
        topPresenter = new TopPresenter(new gettopdata());//请求顶部栏的数据
        videoPresenter = new VideoPresenter(new getvideodata());
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
                videolayout.setVisibility(View.GONE);//浮层小时
                //进行存值
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("videoflay", true);
                edit.commit();
            }
        });

    }


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
            /*for (int i = 0; i < data.size(); i++) {
                VideoBean videoBean = data.get(i);
                    urlList.add(videoBean.originalUrl);
            }*/
            urlList.addAll(data);
            videoAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //浮层
    private void supernatant() {
        //创建sp
        sp = getActivity().getSharedPreferences("videosupernatant", MODE_PRIVATE);
        boolean flay = sp.getBoolean("videoflay", false);
        if (flay) {
            videolayout.setVisibility(View.GONE);//浮层小时
        } else {
            videolayout.setVisibility(View.VISIBLE);//浮层小时
        }
    }


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


    class ListVideoAdapter extends BaseRecAdapter<VideoBean, VideoViewHolder> {

        private PopupWindow buypopuyvideo;

        public ListVideoAdapter(List<VideoBean> list) {
            super(list);
        }

        @Override
        public void onHolder(final VideoViewHolder holder, final VideoBean bean, int position) {
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
            //设置弹幕进入一个item进行判断，如果是显示弹幕进行请求显示，否则的话就是不显示弹幕+
            holder.bulletscreenPresenter.reqeust(bean.id);//进行请求弹幕
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
                    //进行一个判断如果是购买的进行评论，如果不是购买的进行购买处理
                    if (bean.whetherBuy == 2) {
                        //2是没有购买1是购买了
                        //购买
                        //创建popup
                        //弹出输入框
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
                        buypopuyvideo.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        buypopuyvideo.showAtLocation(getActivity().findViewById(R.id.layout_main),
                                Gravity.BOTTOM, 0, 0);
                        //设置值
                        //图片
                        holder.videopopupmyprice.setText(bean.price + "");//价格
                    } else if (bean.whetherBuy == 1) {
                        //评论

                    }
                }
            });
            //设置popup的点击事件
            holder.videopopuoonefinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buypopuyvideo.dismiss();
                }
            });
            //设置购买
            holder.videopopupbuybt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入购买页面
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
        public TextView videotitle, videotext, videobuytext, videoitemifbuy;
        public RadioButton videobtcollect;
        private CheckBox videobtbulletscreen;
        private BulletscreenPresenter bulletscreenPresenter;//弹幕
        private ImageView videobtbuy;
        private View popupone;
        //popu控件
        ImageView videopopupimage, videopopuoonefinish;
        TextView videopopupmyprice, videopopuptextone, videopopuptexttwo;
        Button videopopupbuybt;

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
            bulletscreenPresenter = new BulletscreenPresenter(new getdan());

            popupone = View.inflate(getContext(), R.layout.popupone, null);//popup
            videopopupimage = popupone.findViewById(R.id.videopopupimage);
            videopopuoonefinish = popupone.findViewById(R.id.videopopuoonefinish);
            videopopupmyprice = popupone.findViewById(R.id.videopopupmyprice);
            videopopuptextone = popupone.findViewById(R.id.videopopuptextone);
            videopopuptexttwo = popupone.findViewById(R.id.videopopuptexttwo);
            videopopupbuybt = popupone.findViewById(R.id.videopopupbuybt);
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

    //获取焦点的时候去请求数据
    @Override
    public void onResume() {
        super.onResume();
        //为顶部栏发送handle
        videotoprecycler.setVisibility(View.VISIBLE);
      /*  boolean vdeodata = ((MainActivity) getActivity()).vdeodata();
        if (vdeodata) {*/
        handler.sendEmptyMessageDelayed(1, 1000);
        /* }*/
        //进行用户判断//判断用户时候登陆这
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(getContext());
        if (intt == null) {

        } else {
            uid = intt.get(0);
            sid = intt.get(1);
            //Log.i("aaa", "onResume: "+intt.get(0)+"---"+intt.get(1));
            videoPresenter.reqeust(intt.get(0), intt.get(1), 1, 1, 10);
            toplayout.setVisibility(View.GONE);
        }
        topPresenter.reqeust();
    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        topPresenter.unBind();//解绑
        videoPresenter.unBind();
    }

    //失去焦点
    @Override
    public void onPause() {
        super.onPause();
    }

}
