package com.wd.health.util;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.danikula.videocache.HttpProxyCacheServer;
import com.dingtao.common.core.WDApplication;
import com.wd.health.R;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 作者： ch
 * 时间： 2018/8/17 0017-下午 5:14
 * 描述：
 * 来源：
 */


public class MyVideoPlayer extends JZVideoPlayerStandard {
    private RelativeLayout rl_touch_help;
    private ImageView iv_start,mystate;
    private LinearLayout ll_start;

    private Context context;


    public MyVideoPlayer(Context context) {
        super(context);
        this.context = context;
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onAutoCompletion() {
        thumbImageView.setVisibility(View.GONE);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            onStateAutoComplete();
            setUp((String) getCurrentUrl(), JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN);
        } else {
            super.onAutoCompletion();
            setUp((String) getCurrentUrl(), JZVideoPlayer.CURRENT_STATE_NORMAL);
        }
        //循环播放
        //startVideo();
        //播放完暂停
        mystopp();
        //播放完成后返回值
        videoCallBack.getdata(1);
    }
    //-------------------------------------------------------------------------------------------------------
    //从头播放
    public void mystopp(){
        mystate.setVisibility(VISIBLE);
        mystate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startVideo();
                mystate.setVisibility(GONE);
            }
        });
    }
    //我的点继续播放
    public void mystop(){
        mystate.setVisibility(VISIBLE);
        mystate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goOnPlayOnResume();
                mystate.setVisibility(GONE);
            }
        });
    }
    //播放最后弹出的狂
    public void lostbeg(){
        mystate.setVisibility(GONE);
        startVideo();
    }
    //滑动监听
    public void continuevideo(){
        goOnPlayOnResume();
    }
    //设置接口回调
    public interface VideoCallBack{
        void getdata (int i);
    }
    public VideoCallBack videoCallBack;

    public void setVideoCallBack(VideoCallBack videoCallBack) {
        this.videoCallBack = videoCallBack;
    }
    //-------------------------------------------------------------------------------------------------------
    @Override
    public void setUp(String url, int screen, Object... objects) {
        if (url.startsWith("http")) {
            HttpProxyCacheServer proxy = WDApplication.getProxy(context);
            String proxyUrl = proxy.getProxyUrl(url);
            super.setUp(proxyUrl, screen, objects);
        } else {
            super.setUp(url, screen, objects);
        }
    }
    @Override
    public void init(final Context context) {
        super.init(context);
        rl_touch_help = findViewById(R.id.rl_touch_help);
        ll_start = findViewById(R.id.ll_start);
        iv_start = findViewById(R.id.iv_start);
        mystate = findViewById(R.id.mystate);
        resetPlayView();

        rl_touch_help.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //fullscreenButton.performClick();扩大屏幕
                if (isPlay()) {
                    goOnPlayOnPause();
                    mystop();
                }
            }
        });
        ll_start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay()) {
                    goOnPlayOnPause();
                } else {
                    //暂停
                    if (currentState == JZVideoPlayer.CURRENT_STATE_PAUSE) {
                        goOnPlayOnResume();
                    } else {
                        startVideo();
                    }
                }
                resetPlayView();
            }
        });
    }
    @Override
    public void startVideo() {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            initTextureView();
            addTextureView();
            AudioManager mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            JZUtils.scanForActivity(getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            JZMediaManager.setDataSource(dataSourceObjects);
            JZMediaManager.setCurrentDataSource(JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex));
            JZMediaManager.instance().positionInList = positionInList;
            onStatePreparing();
            ll_start.setVisibility(VISIBLE);
        } else {
            super.startVideo();
            ll_start.setVisibility(GONE);
        }
        resetPlayView();
    }
    //播放暂听循环
    @Override
    public void startWindowTiny() {
    }
    private void resetPlayView() {
        if (isPlay()) {
            iv_start.setBackgroundResource(R.drawable.video_play_parse);
        } else {
            iv_start.setBackgroundResource(R.drawable.stop);
        }
    }

    /**
     * 是否播放
     *
     * @return
     */
    private boolean isPlay() {
        if (currentState == CURRENT_STATE_PREPARING || currentState == CURRENT_STATE_PLAYING || currentState == -1) {
            return true;
        }
        return false;
    }

}
