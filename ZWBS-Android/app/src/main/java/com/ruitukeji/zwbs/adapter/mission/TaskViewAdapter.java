package com.ruitukeji.zwbs.adapter.mission;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.mission.TaskBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 任务`适配器
 * Created by Administrator on 2017/2/13.
 */

public class TaskViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownCounters;

    public TaskViewAdapter(Context context) {
        super(context, R.layout.item_mission);
        this.countDownCounters = new SparseArray<>();
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.img_navigation);
        viewHolderHelper.setItemChildClickListener(R.id.img_phone);
        viewHolderHelper.setItemChildClickListener(R.id.img_start);
        viewHolderHelper.setItemChildClickListener(R.id.img_car);
        viewHolderHelper.setItemChildClickListener(R.id.img_end);
        viewHolderHelper.setItemChildClickListener(R.id.tv_submitDocuments);
        viewHolderHelper.setItemChildClickListener(R.id.tv_submitDeliveryReceipt);
        viewHolderHelper.setItemChildClickListener(R.id.tv_cancelOrder);
        viewHolderHelper.setItemChildClickListener(R.id.tv_exceptionReporting);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 名称
         */
        viewHolderHelper.setText(R.id.tv_companyName, listBean.getReal_name());

        /**
         *标签
         */
        if (StringUtils.isEmpty(listBean.getType())) {
            viewHolderHelper.setImageDrawable(R.id.img_shishi, null);
        } else if (listBean.getType().equals("often")) {
            viewHolderHelper.setImageResource(R.id.img_shishi, R.mipmap.label_shishi);
        } else if (listBean.getType().equals("urgent")) {
            viewHolderHelper.setImageResource(R.id.img_shishi, R.mipmap.label_jiaji);
        } else if (listBean.getType().equals("appoint")) {
            viewHolderHelper.setImageResource(R.id.img_shishi, R.mipmap.label_yuyue);
        } else if (listBean.getType().equals("appoint")) {
            viewHolderHelper.setImageResource(R.id.img_shishi, R.mipmap.label_yuyue);
        }

        /**
         * 订单状态 arrive
         */
        if (listBean.getStatus() != null && listBean.getStatus().equals("distribute") || listBean.getStatus() != null && listBean.getStatus().equals("arrive")) {
            viewHolderHelper.setImageResource(R.id.img_zaipao, R.mipmap.label_zaipao);
        } else {
            viewHolderHelper.setImageDrawable(R.id.img_zaipao, null);
        }

        /**
         *时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getAppoint_at_str());
        /**
         * 货物名称
         */
        viewHolderHelper.setText(R.id.tv_goodName, mContext.getString(R.string.descriptionGoods1) + listBean.getGoods_name());
        /**
         * 订单号
         */
        viewHolderHelper.setText(R.id.tv_orderNumber, mContext.getString(R.string.orderNumber1) + listBean.getOrder_code());
        /**
         * 订单价格
         */
        viewHolderHelper.setText(R.id.tv_orderPrice, mContext.getString(R.string.renminbi) + listBean.getFinal_price());

        /**
         * 起运地
         */
        viewHolderHelper.setText(R.id.tv_thePlace, listBean.getOrg_address_name() + listBean.getOrg_address_detail());

        /**
         * 目的地
         */
        viewHolderHelper.setText(R.id.tv_destination, listBean.getDest_address_name() + listBean.getDest_address_detail());

        if (StringUtils.isEmpty(listBean.getArr_org_time_str())) {
            viewHolderHelper.setVisibility(R.id.tv_onArrival, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime1, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.VISIBLE);
            viewHolderHelper.setImageResource(R.id.img_start, R.mipmap.ic_start_unselected);
        } else {
            viewHolderHelper.setImageResource(R.id.img_start, R.mipmap.ic_start_selected);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime1, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_onArrival, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_onArrival, listBean.getArr_org_time_str());
            long org_time = listBean.getArr_org_time() + 60 * 60 * 2 - (System.currentTimeMillis() / 1000);
            CountDownTimer countDownTimer = countDownCounters.get(((TextView) viewHolderHelper.getTextView(R.id.tv_remainingTime1)).hashCode());
            if (countDownTimer != null) {
                //将复用的倒计时清除
                countDownTimer.cancel();
            }
            //expirationTime 与系统时间做比较，timer 小于零，则此时倒计时已经结束。
            if (org_time > 0) {
                countDownTimer = new CountDownTimer(org_time * 1000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        viewHolderHelper.setText(R.id.tv_remainingTime1, formatLongToTimeStr(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        viewHolderHelper.setText(R.id.tv_remainingTime1, "00:00:00");
                    }
                }.start();
                //将此 countDownTimer 放入list.
                countDownCounters.put(((TextView) viewHolderHelper.getTextView(R.id.tv_remainingTime1)).hashCode(), countDownTimer);
            } else {
                viewHolderHelper.setText(R.id.tv_remainingTime1, "00:00:00");
            }
        }

        if (StringUtils.isEmpty(listBean.getSend_time())) {
            viewHolderHelper.setVisibility(R.id.tv_start, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime1, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_start, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_start, listBean.getSend_time());
        }
        if (StringUtils.isEmpty(listBean.getArr_time())) {
            viewHolderHelper.setImageResource(R.id.img_end, R.mipmap.ic_end_unselected);
            viewHolderHelper.setVisibility(R.id.tv_destination1, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_destination1, View.VISIBLE);
            viewHolderHelper.setImageResource(R.id.img_end, R.mipmap.ic_end_selected);
            viewHolderHelper.setText(R.id.tv_destination1, listBean.getArr_time());
        }
        if (!StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("quoted") && listBean.getIs_cancel() == 0) {
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_submitDocuments, View.INVISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_submitDeliveryReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_exceptionReporting, View.GONE);
            viewHolderHelper.setVisibility(R.id.img_navigation, View.VISIBLE);
            viewHolderHelper.setImageResource(R.id.img_car, R.mipmap.ic_car_unselected);
            viewHolderHelper.setImageResource(R.id.img_end, R.mipmap.ic_end_unselected);
        } else if (!StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("quoted") && listBean.getIs_cancel() == 1
                || !StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("quoted") && listBean.getIs_cancel() == 2) {
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.GONE);
            viewHolderHelper.setVisibility(R.id.img_navigation, View.VISIBLE);
            viewHolderHelper.setImageResource(R.id.img_car, R.mipmap.ic_car_unselected);
            viewHolderHelper.setImageResource(R.id.img_end, R.mipmap.ic_end_unselected);
        } else if (!StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("distribute") || !StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("arrive")) {
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.img_navigation, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_submitDocuments, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_submitDeliveryReceipt, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_exceptionReporting, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_onArrival, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_start, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime1, View.GONE);
            viewHolderHelper.setImageResource(R.id.img_start, R.mipmap.ic_start_selected);
            viewHolderHelper.setImageResource(R.id.img_car, R.mipmap.ic_car_selected);
        } else if (!StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("photo") && listBean.getIs_cargo_receipt() == 1) {
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_cancelOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_submitDocuments, View.INVISIBLE);
            viewHolderHelper.setVisibility(R.id.img_navigation, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_submitDeliveryReceipt, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_exceptionReporting, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_onArrival, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_start, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime1, View.GONE);
            viewHolderHelper.setImageResource(R.id.img_start, R.mipmap.ic_start_selected);
            viewHolderHelper.setImageResource(R.id.img_car, R.mipmap.ic_car_selected);
        } else {
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_onArrival, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_start, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.img_navigation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_destination1, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_remainingTime1, View.GONE);
        }
        if (!StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("pay_success") || !StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("comment")
                || !StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("cancel") && listBean.getIs_cancel() == 1) {
            viewHolderHelper.setVisibility(R.id.ll_right, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_divider, View.GONE);
        }
        if (!StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("quoted") && listBean.getIs_cancel() == 2) {
            viewHolderHelper.setVisibility(R.id.tv_remainingTime1, View.GONE);
            PreferenceHelper.write(mContext, StringConstants.FILENAME, "isShowingOrderNotic", 1);
            PreferenceHelper.write(mContext, StringConstants.FILENAME, "orderId", listBean.getId());
            PreferenceHelper.write(mContext, StringConstants.FILENAME, "orderCode", listBean.getOrder_code());
        }
    }

    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String hour1 = "";
        if (hour < 10) {
            hour1 = "0" + hour;
        } else {
            hour1 = "" + hour;
        }
        String minute1 = "";
        if (minute < 10) {
            minute1 = "0" + minute;
        } else {
            minute1 = "" + minute;
        }
        String second1 = "";
        if (second < 10) {
            second1 = "0" + second;
        } else {
            second1 = "" + second;
        }
        String strtime = "";
//        if (l.intValue() % 2 == 0) {
        strtime = hour1 + ":" + minute1 + ":" + second1;
//        } else {
//            strtime = hour1 + " " + minute1 + " " + second1;
//        }
        return strtime;
    }


    /**
     * 清空当前 CountTimeDown 资源
     */
    public void cancelAllTimers() {
        if (countDownCounters == null) {
            return;
        }
        Log.e("TAG", "size :  " + countDownCounters.size());
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            CountDownTimer cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }

}
