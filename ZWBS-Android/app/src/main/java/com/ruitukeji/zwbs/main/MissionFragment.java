package com.ruitukeji.zwbs.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.mission.fragment.CompleteTaskFragment;
import com.ruitukeji.zwbs.mission.fragment.TodayTaskFragment;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;

/**
 * 任务
 * Created by Administrator on 2017/11/29.
 */

public class MissionFragment extends BaseFragment {

    private MainActivity aty;

    /**
     * 今日任务
     */
    @BindView(id = R.id.rl_todayTask, click = true)
    private RelativeLayout rl_todayTask;
    @BindView(id = R.id.tv_todayTask)
    private TextView tv_todayTask;
    @BindView(id = R.id.tv_dividerTodayTask)
    private TextView tv_dividerTodayTask;

    /**
     * 完成任务
     */
    @BindView(id = R.id.rl_completeMission, click = true)
    private RelativeLayout rl_completeMission;
    @BindView(id = R.id.tv_completeMission)
    private TextView tv_completeMission;
    @BindView(id = R.id.tv_dividerCompleteMission)
    private TextView tv_dividerCompleteMission;

    public BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon = 20;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mission, null);
    }

    @Override
    protected void initData() {
        super.initData();
        contentFragment = new TodayTaskFragment();
        contentFragment1 = new CompleteTaskFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 20);
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if (chageIcon == 20) {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        } else if (chageIcon == 21) {
            chageIcon = 21;
            cleanColors(21);
            changeFragment(contentFragment1);
        } else {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        }
    }

    public void setChageIcon(int chageIcon) {
        if (contentFragment == null || contentFragment1 != null) {
            return;
        }
        this.chageIcon = chageIcon;
        if (chageIcon == 20) {
            cleanColors(20);
            changeFragment(contentFragment);
            ((TodayTaskFragment) contentFragment).showCancelOrderNoticBouncedDialog();
        } else if (chageIcon == 21) {
            cleanColors(21);
            changeFragment(contentFragment1);
        } else {
            cleanColors(20);
            changeFragment(contentFragment);
        }
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fragment_contentMission, targetFragment);
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.rl_todayTask:
                chageIcon = 20;
                cleanColors(20);
                changeFragment(contentFragment);
                break;
            case R.id.rl_completeMission:
                chageIcon = 21;
                cleanColors(21);
                changeFragment(contentFragment1);
                break;
            default:
                chageIcon = 20;
                cleanColors(20);
                changeFragment(contentFragment);
                break;
        }
    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
    }


    /**
     * 清除颜色，并添加颜色
     */
    public void cleanColors(int chageIcon) {
        tv_todayTask.setTextColor(getResources().getColor(R.color.hintcolors));
        tv_dividerTodayTask.setBackgroundResource(R.color.mainColor);
        tv_completeMission.setTextColor(getResources().getColor(R.color.hintcolors));
        tv_dividerCompleteMission.setBackgroundResource(R.color.mainColor);
        if (chageIcon == 20) {
            tv_todayTask.setTextColor(getResources().getColor(R.color.announcementCloseColors));
            tv_dividerTodayTask.setBackgroundResource(R.color.announcementCloseColors);
        } else if (chageIcon == 21) {
            tv_completeMission.setTextColor(getResources().getColor(R.color.announcementCloseColors));
            tv_dividerCompleteMission.setBackgroundResource(R.color.announcementCloseColors);
        } else {
            tv_todayTask.setTextColor(getResources().getColor(R.color.announcementCloseColors));
            tv_dividerTodayTask.setBackgroundResource(R.color.announcementCloseColors);
        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusTodayTaskEvent")) {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        } else if (((String) msgEvent.getData()).equals("RxBusCompleteMissionEvent")) {
            chageIcon = 21;
            cleanColors(21);
            changeFragment(contentFragment1);
        } else {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        }
    }
}
