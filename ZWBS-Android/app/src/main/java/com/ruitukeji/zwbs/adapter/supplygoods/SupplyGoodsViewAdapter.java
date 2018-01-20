package com.ruitukeji.zwbs.adapter.supplygoods;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.supplygoods.SupplyGoodsBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 货源列表适配器
 * Created by Administrator on 2017/2/13.
 */

public class SupplyGoodsViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public SupplyGoodsViewAdapter(Context context) {
        super(context, R.layout.item_getorder);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_getorder);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_reject);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_sendQuotation);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getGoods_name());

        /**
         * 标签
         */
        if (StringUtils.isEmpty(listBean.getType())) {
            viewHolderHelper.setImageDrawable(R.id.img_state, null);
        } else if (listBean.getType().equals("often")) {
            viewHolderHelper.setImageResource(R.id.img_state, R.mipmap.label_shishi);
        } else if (listBean.getType().equals("urgent")) {
            viewHolderHelper.setImageResource(R.id.img_state, R.mipmap.label_jiaji);
        } else if (listBean.getType().equals("appoint")) {
            viewHolderHelper.setImageResource(R.id.img_state, R.mipmap.label_yuyue);
        }
        viewHolderHelper.setVisibility(R.id.img_z, View.GONE);

        /**
         * 时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getAppoint_at());

        /**
         * 货物名称
         */
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getGoods_name());

        /**
         * 订单号
         */
        viewHolderHelper.setText(R.id.tv_orderNumber, listBean.getOrder_code());

        /**
         * 重量
         */
        viewHolderHelper.setText(R.id.tv_weight, listBean.getWeight());

        /**
         * 车长
         */
        viewHolderHelper.setText(R.id.tv_conductor, listBean.getCar_style_length());

        /**
         *车型
         */
        viewHolderHelper.setText(R.id.tv_models, listBean.getCar_style_type());

        /**
         * 重量
         */
        viewHolderHelper.setText(R.id.tv_weight, listBean.getWeight());

        /**
         * 体积
         */
        viewHolderHelper.setText(R.id.tv_volume, listBean.getVolume());

        /**
         * 起运地
         */
        viewHolderHelper.setText(R.id.tv_thePlace, listBean.getOrg_city());

        /**
         * 目的地
         */
        viewHolderHelper.setText(R.id.tv_destination, listBean.getDest_city());

        /**
         *预计公里数
         */
        viewHolderHelper.setText(R.id.tv_expectedMileage, listBean.getKilometres());

        /**
         *预计耗时
         */
        if (StringUtils.isEmpty(listBean.getUsecar_time())) {
            viewHolderHelper.setVisibility(R.id.ll_estimatedTime, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.ll_estimatedTime, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_estimatedTime, listBean.getUsecar_time());
        }

        /**
         * 实际价格
         */
        if (listBean.getMind_price().equals("0.00")) {
            viewHolderHelper.setText(R.id.tv_actualPrice, "￥" + listBean.getSystem_price());
        } else {
            viewHolderHelper.setText(R.id.tv_actualPrice, "￥" + listBean.getMind_price());
        }

        /**
         * 是否有签收单
         */
        if (listBean.getIs_driver_dock() == 0) {
            viewHolderHelper.setText(R.id.tv_orderNeeds, mContext.getString(R.string.orderNotNeeds));
        } else {
            viewHolderHelper.setText(R.id.tv_orderNeeds, mContext.getString(R.string.orderNeeds));
        }

        if (!StringUtils.isEmpty(listBean.getStatus()) && listBean.getStatus().equals("quote")) {
            viewHolderHelper.setVisibility(R.id.tv_getorder, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_reject, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sendQuotation, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_getorder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_reject, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sendQuotation, View.GONE);
        }
    }

}