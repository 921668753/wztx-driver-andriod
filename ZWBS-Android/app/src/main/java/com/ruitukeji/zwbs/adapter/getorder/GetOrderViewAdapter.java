package com.ruitukeji.zwbs.adapter.getorder;

import android.content.Context;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.GetOrderBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 接单列表适配器
 * Created by Administrator on 2017/2/13.
 */

public class GetOrderViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public GetOrderViewAdapter(Context context) {
        super(context, R.layout.item_getorder);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_getorder);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_sendQuotation);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         *  客户名称
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getGoods_name());

        /**
         * 订单属性
         */
        if (StringUtils.isEmpty(listBean.getType())) {
            viewHolderHelper.setVisibility(R.id.img_state, View.GONE);
        } else if (listBean.getType().equals("often")) {
            viewHolderHelper.setImageResource(R.id.img_state, R.mipmap.label_shishi);
        } else if (listBean.getType().equals("urgent")) {
            viewHolderHelper.setImageResource(R.id.img_state, R.mipmap.label_jiaji);
        } else if (listBean.getType().equals("appoint")) {
            viewHolderHelper.setImageResource(R.id.img_state, R.mipmap.label_yuyue);
        }


        /**
         * 订单时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getOrg_city());

        /**
         * 货物名称
         */
        viewHolderHelper.setText(R.id.tv_goodName, listBean.getGoods_name() );

        /**
         * 订单号
         */
        viewHolderHelper.setText(R.id.tv_orderNumber, listBean.getGoods_name() );

        /**
         * 车长
         */
        viewHolderHelper.setText(R.id.tv_conductor, listBean.getCar_style_length() );

        /**
        * 车型
        */
        viewHolderHelper.setText(R.id.tv_models, listBean.getCar_style_type() );

        /**
         * 重量
         */
        viewHolderHelper.setText(R.id.tv_weight, listBean.getWeight() + "吨" );

        /**
         * 体积
         */
        viewHolderHelper.setText(R.id.tv_volume, listBean.getVolume() );


        /**
         * 起点
         */
//        int orgprovince = listBean.getOrg_city().indexOf("省");
//        int orgcity = listBean.getOrg_city().indexOf("市");
//        if (orgprovince == -1 && orgcity != -1) {
////            int orgarea = listBean.getOrg_city().indexOf("区");
////            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgcity) + listBean.getOrg_city().substring(orgcity + 1, orgarea));
//            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgcity));
//        } else if (orgprovince != -1 && orgcity != -1) {
//            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgprovince) + listBean.getOrg_city().substring(orgprovince + 1, orgcity));
//        } else {
            viewHolderHelper.setText(R.id.tv_thePlace, listBean.getOrg_city());
//        }

        /**
         * 终点
         */
//        int destprovince = listBean.getDest_city().indexOf("省");//("市")
//        int destcity = listBean.getDest_city().indexOf("市");
//        if (destprovince == -1 && destcity != -1) {
//            // int destarea = listBean.getDest_city().indexOf("区");
//            //   viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destcity) + listBean.getDest_city().substring(destcity + 1, destarea));
//            viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destcity));
//        } else if (destprovince != -1 && destcity != -1) {
//            viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destprovince) + listBean.getDest_city().substring(destprovince + 1, destcity));
//        } else {
            viewHolderHelper.setText(R.id.tv_destination, listBean.getDest_city());
  //      }

        /**
         *用车时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getUsecar_time() + "");

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