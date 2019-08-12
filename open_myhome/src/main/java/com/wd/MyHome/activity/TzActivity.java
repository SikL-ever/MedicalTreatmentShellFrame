package com.wd.MyHome.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;

import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.NstPresenter;
import com.wd.MyHome.util.IndicatorSeekBar;
import com.wd.MyHome.util.IndicatorStayLayout;
import com.wd.MyHome.util.IndicatorType;
import com.wd.MyHome.util.OnSeekChangeListener;
import com.wd.MyHome.util.SeekParams;
import com.wd.MyHome.util.TickMarkType;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TzActivity extends AppCompatActivity {

    @BindView(R2.id.tz_top)
    TopView tzTop;
    @BindView(R2.id.tz_wc)
    TextView tzWc;
    /*@BindView(R2.id.tex12)
    TextView tex12;
    @BindView(R2.id.tex1)
    TextView tex1;*/
    @BindView(R2.id.g)
    ImageView g;
    @BindView(R2.id.sg)
    RelativeLayout sg;
    @BindView(R2.id.t)
    ImageView t;
  /*  @BindView(R2.id.seekbar_tg)
    SeekBar seekbarTg;*/
    @BindView(R2.id.tz)
    RelativeLayout tz;
    @BindView(R2.id.n)
    ImageView n;
    /*@BindView(R2.id.seekbar_nl)
    SeekBar seekbarNl;*/
 //   private View_A viewById,viewById1,viewById2;
    private int h=0;
    private int height,weight,age;
    private List<String> intt;
    private String userId;
    private String sessionId;
    private IndicatorSeekBar continuous2TickTexts1;
    private IndicatorSeekBar continuous2TickTexts2;
    private IndicatorSeekBar continuous2TickTexts3;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tz);
        ButterKnife.bind(this);
        tzTop.setTitle("我的体征");

        height = getIntent().getIntExtra("height", 0);
        weight = getIntent().getIntExtra("weight", 0);
        age = getIntent().getIntExtra("age", 0);
        intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);
        IndicatorStayLayout indicatorStayLayout = findViewById(R.id.custom_indicator_by_java);
        IndicatorStayLayout indicatorStayLayout1 = findViewById(R.id.custom_indicator_by_java1);
        IndicatorStayLayout indicatorStayLayout2 = findViewById(R.id.custom_indicator_by_java2);
       /*continuous2TickTexts1.setIndicatorTextFormat("${PROGRESS} %");
        continuous2TickTexts2.setIndicatorTextFormat("${PROGRESS} %");
        continuous2TickTexts3.setIndicatorTextFormat("${PROGRESS} %");*/

        // final String[] h = {"50cm","250cm"};
        continuous2TickTexts1 = IndicatorSeekBar
                .with(this)
                .max(250)
                .min(50)
                .progress(height)
                .tickCount(2)
                //.tickTextsArray(h)
                .showTickMarksType(TickMarkType.NONE)
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .thumbDrawable(getResources().getDrawable(R.drawable.bg_seek_bar_thumb2, null))
                .thumbSize(18)
                .trackProgressColor(getResources().getColor(R.color.color_blue, null))
                .trackProgressSize(5)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(5)
                .build();

        IndicatorStayLayout continuousStayLayout1 = new IndicatorStayLayout(this);
        continuousStayLayout1.attachTo(continuous2TickTexts1);
        indicatorStayLayout.addView(continuousStayLayout1);
        continuous2TickTexts1.setIndicatorTextFormat("${PROGRESS} cm");
        continuous2TickTexts1.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                h = 1;
                continuous2TickTexts1.setIndicatorTextFormat("${PROGRESS} cm");
                tzWc.setBackgroundColor(Color.parseColor("#3087ea"));
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        continuous2TickTexts2 = IndicatorSeekBar
                .with(this)
                .max(150)
                .min(30)
                .progress(weight)
                .tickCount(2)
                .showTickMarksType(TickMarkType.NONE)
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .thumbDrawable(getResources().getDrawable(R.drawable.bg_seek_bar_thumb2, null))
                .thumbSize(18)
                .trackProgressColor(getResources().getColor(R.color.color_blue, null))
                .trackProgressSize(5)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(5)
                .build();

        IndicatorStayLayout continuousStayLayout2 = new IndicatorStayLayout(this);
        continuousStayLayout2.attachTo(continuous2TickTexts2);
        indicatorStayLayout1.addView(continuousStayLayout2);
        continuous2TickTexts2.setIndicatorTextFormat("${PROGRESS} kg");
        continuous2TickTexts2.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                h = 1;
                continuous2TickTexts2.setIndicatorTextFormat("${PROGRESS} kg");
                tzWc.setBackgroundColor(Color.parseColor("#3087ea"));
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        continuous2TickTexts3 = IndicatorSeekBar
                .with(this)
                .max(120)
                .min(18)
                .progress(age)
                .tickCount(2)
                .showTickMarksType(TickMarkType.NONE)
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .thumbDrawable(getResources().getDrawable(R.drawable.bg_seek_bar_thumb2, null))
                .thumbSize(18)
                .trackProgressColor(getResources().getColor(R.color.color_blue, null))
                .trackProgressSize(5)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(5)
                .build();

        IndicatorStayLayout continuousStayLayout3 = new IndicatorStayLayout(this);
        continuousStayLayout3.attachTo(continuous2TickTexts3);
        indicatorStayLayout2.addView(continuousStayLayout3);
        continuous2TickTexts3.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                h = 1;
                tzWc.setBackgroundColor(Color.parseColor("#3087ea"));
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

        /*.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {

            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {
                //only callback on discrete series SeekBar type.
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });*/

//        int tiz = continuous2TickTexts1.getProgress();
      // IndicatorStayLayout indicatorStayLayout = findViewById(R.id.indicator);
        /*IndicatorSeekBar percent_indicator = findViewById(R.id.custom_indicator_by_java);

        percent_indicator.setIndicatorTextFormat("${PROGRESS} %");*/
        //custom indicator text by java code
        /*IndicatorSeekBar seekBarWithProgress = findViewById(R.id.custom_indicator_by_java_code);
        seekBarWithProgress.setIndicatorTextFormat("${PROGRESS} %");*/

        //custom indicator text by java code
       /* IndicatorSeekBar seekBarWithTickText = findViewById(R.id.custom_indicator_by_java);
        seekBarWithTickText.setIndicatorTextFormat("${TICK_TEXT} --");*/
/*        String[] h = {"50cm","250cm"};
        continuous2TickTexts1 = IndicatorSeekBar
                .with(this)
                .max(250)
                .min(50)
                .progress(60)
                .tickCount(2)
                .tickTextsArray(h)
                .showTickMarksType(TickMarkType.NONE)
                .showTickTexts(true)
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
                .tickTextsColor(getResources().getColor(R.color.default_background, null))
                .indicatorColor(getResources().getColor(R.color.color_blue, null))
                .thumbDrawable(getResources().getDrawable(R.drawable.bg_seek_bar_thumb2, null))
                .thumbSize(18)
                .trackProgressColor(getResources().getColor(R.color.color_blue, null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray, null))
                .trackBackgroundSize(2)
                .build();

        IndicatorStayLayout continuousStayLayout1 = new IndicatorStayLayout(this);
        continuousStayLayout1.attachTo(continuous2TickTexts1);
        indicatorStayLayout.addView(continuousStayLayout1);*/




  /*      //seekbarSg.setProgress(height-50);
        seekbarTg.setProgress(weight-30);
        seekbarNl.setProgress(age-18);



      //  viewById = findViewById(R.id.view_a);
        viewById1 = findViewById(R.id.view_a1);
        viewById2 = findViewById(R.id.view_a2);

  //      viewById.setPadding(10,10,10,30);
        viewById1.setPadding(10,10,10,30);
        viewById2.setPadding(10,10,10,30);


      //  tex123.setText(height+"cm");
        tex12.setText(weight+"kg");
        tex1.setText(age+"岁");
*/



       /* seekbarSg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = progress+50;
                tex123.setText(a+"cm");
                height = a;
                int quota = progress;
                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                tex123.measure(spec, spec);
                int quotaWidth = tex123.getMeasuredWidth();

                int spec2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                tex123.measure(spec2, spec2);
                int sbWidth = seekbarSg.getMeasuredWidth();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = (int) (((double) progress / seekbarSg.getMax()) * sbWidth - (double) quotaWidth * progress / seekbarSg.getMax());
                viewById.setLayoutParams(params);

                tzWc.setBackgroundColor(Color.parseColor("#619fe5"));
                h = 1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbarTg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = progress+30;
                tex12.setText(a+"kg");
                weight = a;
                int quota = progress;
                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                tex12.measure(spec, spec);
                int quotaWidth = tex12.getMeasuredWidth();

                int spec2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                tex12.measure(spec2, spec2);
                int sbWidth = seekbarTg.getMeasuredWidth();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = (int) (((double) progress / seekbarTg.getMax()) * sbWidth - (double) quotaWidth * progress / seekbarTg.getMax());
                viewById1.setLayoutParams(params);

                tzWc.setBackgroundColor(Color.parseColor("#619fe5"));
                h =1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbarNl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = progress+18;
                tex1.setText(a+"岁");
                age = a;
                int quota = progress;
                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                tex1.measure(spec, spec);
                int quotaWidth = tex1.getMeasuredWidth();

                int spec2 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                tex1.measure(spec2, spec2);
                int sbWidth = seekbarNl.getMeasuredWidth();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = (int) (((double) progress / seekbarNl.getMax()) * sbWidth - (double) quotaWidth * progress / seekbarNl.getMax());
                viewById2.setLayoutParams(params);

                tzWc.setBackgroundColor(Color.parseColor("#619fe5"));
                h = 1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
*/
        tzWc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (h==1) {
                    NstPresenter nstPresenter = new NstPresenter(new DataCall() {
                        @Override
                        public void success(Object data, Object... args) {
                           // Toast.makeText(TzActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void fail(ApiException data, Object... args) {
                           // Toast.makeText(TzActivity.this, "失败" + data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    int tiz = continuous2TickTexts1.getProgress();
                    int sg = continuous2TickTexts2.getProgress();
                    int ag = continuous2TickTexts3.getProgress();

                    nstPresenter.reqeust(userId, sessionId, ag, tiz, sg);

                    tzWc.setBackgroundColor(Color.parseColor("#B0CFF2"));
                }else {

                }
            }
        });
    }

    /**
     * 根据手机分辨率从 px(像素) 单位 转成 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机分辨率从 dp 单位 转成 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
