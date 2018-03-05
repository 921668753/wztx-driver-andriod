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
        viewHolderHelper.setItemChildClickListener(R.id.tv_sendQuotation);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getOrg_send_name());

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
         * 司机装卸货
         */
        if (listBean.getIs_driver_dock() == 0) {
            viewHolderHelper.setText(R.id.tv_driverCargo, mContext.getString(R.string.noNeed));
        } else {
            viewHolderHelper.setText(R.id.tv_driverCargo, mContext.getString(R.string.need));
        }

        /**
         * 配送点/配送点费用
         */
        if (listBean.getSpot() == 0) {
            viewHolderHelper.setVisibility(R.id.ll_peiSongDian, View.GONE);
            viewHolderHelper.setVisibility(R.id.ll_costDistribution, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.ll_peiSongDian, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.ll_costDistribution, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_peiSongDian, listBean.getSpot() + mContext.getString(R.string.ge));
            viewHolderHelper.setText(R.id.tv_costDistribution, listBean.getSpot_cost() + mContext.getString(R.string.ge1));
        }

        /**
         *预计公里数
         */
        viewHolderHelper.setText(R.id.tv_expectedMileage, listBean.getKilometres());

        /**
         *预计耗时
         */
        if (StringUtils.isEmpty(listBean.getUsecar_time()) || listBean.getEffective_time() == 0) {
            viewHolderHelper.setVisibility(R.id.ll_estimatedTime, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.ll_estimatedTime, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_estimatedTime, listBean.getUsecar_time());
        }

        /**
         * 实际价格
         */
        if (StringUtils.isEmpty(listBean.getMind_price()) || StringUtils.toDouble(listBean.getMind_price()) == 0) {
            viewHolderHelper.setText(R.id.tv_actualPrice, mContext.getString(R.string.renminbi) + listBean.getSystem_price());
        } else {
            viewHolderHelper.setText(R.id.tv_actualPrice, mContext.getString(R.string.renminbi) + listBean.getMind_price());
        }

        /**
         * 是否有签收单
         */
        if (listBean.getIs_cargo_receipt() == 1 && listBean.getIs_express() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_orderNeeds, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_orderNeeds, mContext.getString(R.string.orderNeeds));
        } else if (listBean.getIs_cargo_receipt() == 1 && listBean.getIs_express() == 0) {
            viewHolderHelper.setVisibility(R.id.tv_orderNeeds, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_orderNeeds, mContext.getString(R.string.orderNeeds1));
        } else if (listBean.getIs_cargo_receipt() == 0) {
            viewHolderHelper.setVisibility(R.id.tv_orderNeeds, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_orderNeeds, mContext.getString(R.string.orderNotNeeds));
        } else {
            viewHolderHelper.setVisibility(R.id.tv_orderNeeds, View.GONE);
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
        viewHolderHelper.setVisibility(R.id.ll_button1, View.GONE);
    }

}