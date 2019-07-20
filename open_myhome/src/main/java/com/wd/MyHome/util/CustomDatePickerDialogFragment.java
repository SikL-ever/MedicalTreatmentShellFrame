package com.wd.MyHome.util;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.MyHome.R;
import com.wd.MyHome.R2;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 佀常勇
 *
 * @Data:2019/7/19 12:16
 * 描述：
 */
    public class CustomDatePickerDialogFragment extends DialogFragment implements DatePicker.OnDateChangedListener, View.OnClickListener{
        public static final String CURRENT_DATE = "datepicker_current_date";
        public static final String START_DATE = "datepicker_start_date";
        public static final String END_DATE = "datepicker_end_date";
        Calendar currentDate;
        Calendar startDate;
        Calendar endDate;

        DatePicker datePicker;
        TextView backButton;
        TextView ensureButton;
        View splitLineV;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setCancelable(false);
            Bundle bundle = getArguments();
            currentDate = (Calendar) bundle.getSerializable(CURRENT_DATE);
            startDate = (Calendar) bundle.getSerializable(START_DATE);
            endDate = (Calendar) bundle.getSerializable(END_DATE);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (inflater == null) {
                return super.onCreateView(inflater, container, savedInstanceState);
            }
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setDimAmount(0.8f);
            View view  = inflater.inflate(R.layout.data_chooser,container,false);
            return view;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int style;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                style = R.style.ZZBDatePickerDialogLStyle;
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                style = R.style.ZZBDatePickerDialogLStyle;
            } else {
                style = getTheme();
            }
            return new Dialog(getActivity(), style);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if (view != null) {
                datePicker = view.findViewById(R.id.datePickerView);
                backButton = view.findViewById(R.id.back);
                backButton.setOnClickListener(this);
                ensureButton = view.findViewById(R.id.ensure);
                ensureButton.setOnClickListener(this);
                splitLineV = view.findViewById(R.id.splitLineV);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    //bug1:日历模式，在5.0以下设置的可选时间区间如果与当前日期在同一栏会crash，所以只能用滚轮模式
                    datePicker.setCalendarViewShown(false);
                    datePicker.setSpinnersShown(true);
                    //滚轮模式必须使用确定菜单
                    ensureButton.setVisibility(View.VISIBLE);
                    splitLineV.setVisibility(View.VISIBLE);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                        && Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                    //bug2:LOLLIPOP上OnDateChangedListener回调无效(5.0存在，5.1修复),必须使用确定菜单回传选定日期
                    ensureButton.setVisibility(View.VISIBLE);
                    splitLineV.setVisibility(View.VISIBLE);
                    //如果只要日历部分，隐藏header
                    ViewGroup mContainer = (ViewGroup) datePicker.getChildAt(0);
                    View header = mContainer.getChildAt(0);
                    header.setVisibility(View.GONE);
                } else {
                    //bug4:LOLLIPOP和Marshmallow上，使用spinner模式，然后隐藏滚轮，显示日历(spinner模式下的日历没有头部)，日历最底部一排日期被截去部分。所以只能使用calender模式，然后手动隐藏header（系统没有提供隐藏header的api）。
                    //如果只要日历部分，隐藏header
                    ViewGroup mContainer = (ViewGroup) datePicker.getChildAt(0);
                    View header = mContainer.getChildAt(0);
                    header.setVisibility(View.GONE);
                    //Marshmallow上底部留白太多，减小间距
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) datePicker.getLayoutParams();
                    layoutParams.bottomMargin = 10;
                    datePicker.setLayoutParams(layoutParams);
                }
                initDatePicker();
            }
        }

        private void initDatePicker() {
            if (datePicker == null) {
                return;
            }
            if (currentDate == null) {
                currentDate = Calendar.getInstance();
                currentDate.setTimeInMillis(System.currentTimeMillis());
            }
            datePicker.init(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH),currentDate.get(Calendar.DAY_OF_MONTH),this);
            if (startDate != null) {
                datePicker.setMinDate(startDate.getTimeInMillis());
            }
            if (endDate != null) {
                //bug5:5.1上，maxdate不可选。由于5.0有bug3，所以可能bug5被掩盖了。4.x和6.0+版本没有这个问题。
                //bug5在6.0+上有另一个表现形式：初始化时会触发一次onDateChanged回调。通过源码分析一下原因：init方法只会设置控件当前日期的
                //年月日，而时分秒默认使用现在时间的时分秒，所以当前日期大于>最大日期，执行setMaxDate方法时，就会触发一次onDateChanged回调。
                //同理，setMinDate方法也面临同样的方法。所以设置范围时，MinDate取0时0分0秒，MaxDate取23时59分59秒。
                endDate.set(Calendar.HOUR_OF_DAY,23);
                endDate.set(Calendar.MINUTE,59);
                endDate.set(Calendar.SECOND,59);
                datePicker.setMaxDate(endDate.getTimeInMillis());
            }
        }

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.back) {
                dismiss();

            } else if (i == R.id.ensure) {
                returnSelectedDateUnderLOLLIPOP();

            }
        }

        private void returnSelectedDateUnderLOLLIPOP() {
            //bug3:5.0上超过可选区间的日期依然能选中,所以要手动校验.5.1上已解决，但是为了与5.0保持一致，也采用确定菜单返回日期
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                    && Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),0,0,0);
                selectedDate.set(Calendar.MILLISECOND,0);
                if (selectedDate.before(startDate) || selectedDate.after(endDate)) {
                    Toast.makeText(getActivity(), "日期超出有效范围", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (onSelectedDateListener != null) {
                onSelectedDateListener.onSelectedDate(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            }
            dismiss();
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            onSelectedDateListener = null;
        }

        public interface OnSelectedDateListener {
            void onSelectedDate(int year, int monthOfYear, int dayOfMonth);
        }

        OnSelectedDateListener onSelectedDateListener;

        public void setOnSelectedDateListener(OnSelectedDateListener onSelectedDateListener) {
            this.onSelectedDateListener = onSelectedDateListener;
        }

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                    && Build.VERSION.SDK_INT < Build.VERSION_CODES.M){ //LOLLIPOP上，这个回调无效，排除将来可能的干扰
                return;
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) { //5.0以下，必须采用滚轮模式，所以需借助确定菜单回传选定值
                return;
            }
            if (onSelectedDateListener != null) {
                onSelectedDateListener.onSelectedDate(year, monthOfYear, dayOfMonth);
            }
            dismiss();
        }
    }
