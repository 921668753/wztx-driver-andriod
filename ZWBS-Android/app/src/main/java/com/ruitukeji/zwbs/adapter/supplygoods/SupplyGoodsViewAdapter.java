package com.ruitukeji.zwbs.adapter.supplygoods;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.SupplyGoodsBean.ResultBean.ListBean;

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
        viewHolderHelper.setText(R.id.tv_name, listBean.getReal_name());
        /**
         * 标签
         */
        viewHolderHelper.setText(R.id.img_state, listBean.getReal_name());
        viewHolderHelper.setText(R.id.img_z, listBean.getReal_name());
        /**
         * 时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getReal_name());
//        /**
//         * 时间
//         */
//        int orgprovince = listBean.getOrg_city().indexOf("省");
//        int orgcity = listBean.getOrg_city().indexOf("市");
//        viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgprovince) + listBean.getOrg_city().substring(orgprovince + 1, orgcity));
//        /**
//         * 终点
//         */
//        int destprovince = listBean.getDest_city().indexOf("省");
//        int destcity = listBean.getDest_city().indexOf("市");
//        viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destprovince) + listBean.getDest_city().substring(destprovince + 1, destcity));
        /**
         * 货物名称
         */
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getGoods_name());

        /**
         * 订单号
         */
        viewHolderHelper.setText(R.id.tv_orderNumber, listBean.getGoods_name());

        /**
         * 重量
         */
        viewHolderHelper.setText(R.id.tv_weight, listBean.getWeight() + "吨");
//        /**
//         *用车时间
//         */
//        viewHolderHelper.setText(R.id.tv_time, listBean.getUsecar_time() + "");
        /**
         * 车长
         */
        viewHolderHelper.setText(R.id.tv_conductor, listBean.getCar_style_length());

        /**
         *车型
         */
        viewHolderHelper.setText(R.id.tv_models,  listBean.getCar_style_type());


        /**
         * 重量
         */
        viewHolderHelper.setText(R.id.tv_weight, listBean.getWeight() + "吨" );

        /**
         * 体积
         */
        viewHolderHelper.setText(R.id.tv_volume, listBean.getVolume() );

        /**
         * 起运地
         */
        viewHolderHelper.setText(R.id.tv_thePlace, listBean.getWeight() + "吨" );

        /**
         * 目的地
         */
        viewHolderHelper.setText(R.id.tv_destination, listBean.getVolume() );
        /**
         *预计公里数
         */
        viewHolderHelper.setText(R.id.tv_expectedMileage, listBean.getUsecar_time() + "");

        /**
         *预计耗时
         */
        viewHolderHelper.setText(R.id.tv_estimatedTime, listBean.getUsecar_time() + "");

        /**
         * 实际价格
         */
        if (listBean.getMind_price().equals("0.00")) {
            viewHolderHelper.setText(R.id.tv_actualPrice, "￥" + listBean.getSystem_price());
        } else {
            viewHolderHelper.setText(R.id.tv_actualPrice, "￥" + listBean.getMind_price());
        }

    }

}