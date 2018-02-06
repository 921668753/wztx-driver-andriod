package com.ruitukeji.zwbs.adapter.mine.mywallet.incomedetails;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.mine.mywallet.incomedetails.IncomeDetailsBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 收入明细列表适配器
 * Created by Administrator on 2017/12/22.
 */

public class IncomeDetailsViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public IncomeDetailsViewAdapter(Context context) {
        super(context, R.layout.item_getorder);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, ListBean listBean) {
        helper.setVisibility(R.id.ll_button, View.GONE);

        /**
         *名字
         */
        helper.setText(R.id.tv_name, listBean.getName());

        /**
         *标签
         */
        if (StringUtils.isEmpty(listBean.getType())) {
            helper.setImageDrawable(R.id.img_state, null);
        } else if (listBean.getType().equals("often")) {
            helper.setImageResource(R.id.img_state, R.mipmap.label_shishi);
        } else if (listBean.getType().equals("urgent")) {
            helper.setImageResource(R.id.img_state, R.mipmap.label_jiaji);
        } else if (listBean.getType().equals("appoint")) {
            helper.setImageResource(R.id.img_state, R.mipmap.label_yuyue);
        }

        if (listBean.getIs_assigned() == 1) {
            helper.setImageResource(R.id.img_z, R.mipmap.label_zhipai);
        } else {
            helper.setImageDrawable(R.id.img_z, null);
        }

        /**
         *时间
         */
        helper.setText(R.id.tv_time, listBean.getCreate_at());

        /**
         *货物名称
         */
        helper.setText(R.id.tv_goodName, listBean.getGoods_name());

        /**
         *货物名称
         */
        helper.setText(R.id.tv_orderNumber, listBean.getOrder_code());

        /**
         *车长
         */
        helper.setText(R.id.tv_conductor, listBean.getCar_style_length());

        /**
         *车型
         */
        helper.setText(R.id.tv_models, listBean.getCar_style_type());

        /**
         *重量
         */
        helper.setText(R.id.tv_weight, listBean.getWeight());

        /**
         *体积
         */
        helper.setText(R.id.tv_volume, listBean.getVolume());

        /**
         *起始地
         */
        helper.setText(R.id.tv_thePlace, listBean.getOrg_address_detail());

        /**
         *目的地
         */
        helper.setText(R.id.tv_destination, listBean.getDest_address_detail());

        /**
         *距离
         */
        helper.setText(R.id.tv_expectedMileage, listBean.getKilometres());

        /**
         *時間
         */
//        int hours = StringUtils.toInt(listBean.getEffective_time()) / 60;
//        int minutes = StringUtils.toInt(listBean.getEffective_time()) % 60;
//        if (hours > 0) {
//            helper.setText(R.id.tv_estimatedTime, hours + mContext.getString(R.string.hours) + minutes + mContext.getString(R.string.minutes));
//        } else {
        helper.setText(R.id.tv_estimatedTime, listBean.getEffective_time());
        //     }

        /**
         *金额
         */
        if (StringUtils.isEmpty(listBean.getFinal_price())) {
            helper.setText(R.id.tv_actualPrice, "0.00" + mContext.getString(R.string.rmb));
        } else {
            helper.setText(R.id.tv_actualPrice, listBean.getFinal_price() + mContext.getString(R.string.rmb));
        }

        /**
         *是否有签收单
         */
        if (listBean.getIs_driver_dock() == 0) {
            helper.setText(R.id.tv_orderNeeds, mContext.getString(R.string.orderNotNeeds));
        } else {
            helper.setText(R.id.tv_orderNeeds, mContext.getString(R.string.orderNeeds));
        }

    }
}
