package com.ruitukeji.zwbs.mission.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.weiget.CalendarView;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseDialog;
import com.ruitukeji.zwbs.utils.DataUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

/**
 * 今日任务---日历弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class CalendarBouncedDialog extends BaseDialog implements View.OnClickListener {

    private long singleDate;
    private Context context;
    private CalendarView calendarView;


    public CalendarBouncedDialog(Context context, long singleDate) {
        super(context, R.style.dialog);
        this.context = context;
        this.singleDate = singleDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendarbounced);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {

        TextView tv_todayTask = (TextView) findViewById(R.id.tv_todayTask);
        tv_todayTask.setOnClickListener(this);

        TextView tv_completeMission = (TextView) findViewById(R.id.tv_completeMission);
        tv_completeMission.setOnClickListener(this);

        TextView tv_today = (TextView) findViewById(R.id.tv_today);
        tv_today.setOnClickListener(this);

        TextView tv_arrowLeft = (TextView) findViewById(R.id.tv_arrowLeft);
        tv_arrowLeft.setOnClickListener(this);

        TextView tv_data = (TextView) findViewById(R.id.tv_data);
        tv_data.setOnClickListener(this);

        TextView tv_arrowRight = (TextView) findViewById(R.id.tv_arrowRight);
        tv_arrowRight.setOnClickListener(this);

        TextView tv_calendar = (TextView) findViewById(R.id.tv_calendar);
        tv_calendar.setOnClickListener(this);

        LinearLayout ll_calendar = (LinearLayout) findViewById(R.id.ll_calendar);
        ll_calendar.setOnClickListener(this);
        TextView tv_transparent = (TextView) findViewById(R.id.tv_transparent);
        tv_transparent.setOnClickListener(this);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
//        tv_cancel.setOnClickListener(this);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        String singleDateStr = DataUtil.formatData(singleDate, "yyyy.MM.dd");
        calendarView.setStartEndDate("2017.12", "2099.12")
                .setInitDate(singleDateStr.substring(0, 8))
                .setSingleDate(singleDateStr)
                .init();
        DateBean dateBean = calendarView.getSingleDate();
        String dateBeanString = null;
        if (dateBean.getSolar()[1] < 10 & dateBean.getSolar()[2] < 10) {
            dateBeanString = dateBean.getSolar()[0] + "-0" + dateBean.getSolar()[1] + "-0" + dateBean.getSolar()[2];
        } else if (dateBean.getSolar()[1] < 10 & dateBean.getSolar()[2] >= 10) {
            dateBeanString = dateBean.getSolar()[0] + "-0" + dateBean.getSolar()[1] + "-" + dateBean.getSolar()[2];
        } else if (dateBean.getSolar()[1] >= 10 & dateBean.getSolar()[2] < 10) {
            dateBeanString = dateBean.getSolar()[0] + "-" + dateBean.getSolar()[1] + "-0" + dateBean.getSolar()[2];
        } else {
            dateBeanString = dateBean.getSolar()[0] + "-" + dateBean.getSolar()[1] + "-" + dateBean.getSolar()[2];
        }
        tv_title.setText(dateBeanString);
        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                String dateString = null;
                if (date[1] < 10 & date[2] < 10) {
                    dateString = date[0] + "-0" + date[1] + "-0" + date[2];
                } else if (date[1] < 10 & date[2] >= 10) {
                    dateString = date[0] + "-0" + date[1] + "-" + date[2];
                } else if (date[1] >= 10 & date[2] < 10) {
                    dateString = date[0] + "-" + date[1] + "-0" + date[2];
                } else {
                    dateString = date[0] + "-" + date[1] + "-" + date[2];
                }
                tv_title.setText(dateString);
            }
        });
        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                String dateString = null;
                if (date.getSolar()[1] < 10 & date.getSolar()[2] < 10) {
                    dateString = date.getSolar()[0] + "-0" + date.getSolar()[1] + "-0" + date.getSolar()[2];
                } else if (date.getSolar()[1] < 10 & date.getSolar()[2] >= 10) {
                    dateString = date.getSolar()[0] + "-0" + date.getSolar()[1] + "-" + date.getSolar()[2];
                } else if (date.getSolar()[1] >= 10 & date.getSolar()[2] < 10) {
                    dateString = date.getSolar()[0] + "-" + date.getSolar()[1] + "-0" + date.getSolar()[2];
                } else {
                    dateString = date.getSolar()[0] + "-" + date.getSolar()[1] + "-" + date.getSolar()[2];
                }
                confirm(dateString, DataUtil.getStringToDate(dateString, "yyyy-MM-dd") / 1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_calendar:
                cancel();
                break;
            case R.id.tv_todayTask:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusTodayTaskEvent"));
                break;
            case R.id.tv_completeMission:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusCompleteMissionEvent"));
                break;
            case R.id.tv_today:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusTodayEvent"));
                break;
            case R.id.tv_arrowLeft:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusArrowLeftEvent"));
                break;
            case R.id.tv_data:
                cancel();
                break;
            case R.id.tv_arrowRight:
                cancel();
                RxBus.getInstance().post(new MsgEvent<String>("RxBusArrowRightEvent"));
                break;
            case R.id.tv_calendar:
                cancel();
                break;
            case R.id.tv_transparent:
                cancel();
                break;
        }
    }

    public void setSingleDate(long date) {
        String singleDateStr = DataUtil.formatData(date, "yyyy.MM.dd");
        calendarView.setSingleDate(singleDateStr);
    }

    /**
     * @param dataStr
     * @param data
     */
    public abstract void confirm(String dataStr, long data);

}
