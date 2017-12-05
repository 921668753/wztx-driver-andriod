package com.ruitukeji.zwbs.adapter.mission;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.OrderBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 任务`适配器
 * Created by Administrator on 2017/2/13.
 */

public class TaskViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public TaskViewAdapter(Context context) {
        super(context, R.layout.item_mission);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.img_navigation);
        viewHolderHelper.setItemChildClickListener(R.id.img_phone);
        viewHolderHelper.setItemChildClickListener(R.id.img_start);
        viewHolderHelper.setItemChildClickListener(R.id.img_car);
        viewHolderHelper.setItemChildClickListener(R.id.img_end);
        viewHolderHelper.setItemChildClickListener(R.id.tv_submitDocuments);
        viewHolderHelper.setItemChildClickListener(R.id.tv_exceptionReporting);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 名称
         */
        viewHolderHelper.setText(R.id.tv_companyName, "");
        /**
         *标签
         */
        viewHolderHelper.setImageResource(R.id.img_shishi, 0);
        viewHolderHelper.setImageResource(R.id.img_zaipao, 0);
        /**
         *时间
         */
        viewHolderHelper.setText(R.id.tv_time, "");
        /**
         * 货物名称
         */
        viewHolderHelper.setText(R.id.tv_goodName, "");
        /**
         * 订单号
         */
        viewHolderHelper.setText(R.id.tv_orderNumber, "");
        /**
         * 订单价格
         */
        viewHolderHelper.setText(R.id.tv_orderPrice, "");

        /**
         * 起运地
         */
        viewHolderHelper.setText(R.id.tv_thePlace, "");

        /**
         * 目的地
         */
        viewHolderHelper.setText(R.id.tv_destination, "");



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
//            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city());
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
//            viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city());
//        }
//        /**
//         * 货物名称  重量
//         */
//        viewHolderHelper.setText(R.id.tv_nameWeight, listBean.getGoods_name() + "/" + listBean.getWeight() + "吨");
//        /**
//         * 运价
//         */
//        viewHolderHelper.setText(R.id.tv_freight, "运价：￥" + listBean.getFinal_price());
//
//        /**
//         * 状态
//         */
//        TextView tv_navigation = viewHolderHelper.getTextView(R.id.tv_navigation);
//        if (listBean.getStatus() == null) {
//        } else if (listBean.getStatus().equals("quote")) {
//            viewHolderHelper.setText(R.id.tv_freight, "运价：待货主确定");
//            tv_navigation.setText(mContext.getString(R.string.quote));
//            tv_navigation.setBackground(null);
//        } else if (listBean.getStatus().equals("quoted")) {
//            tv_navigation.setText(mContext.getString(R.string.noDistribution));
//            tv_navigation.setBackground(null);
//        } else if (listBean.getStatus().equals("distribute")) {
//            tv_navigation.setText(mContext.getString(R.string.navigation));
//            tv_navigation.setBackgroundResource(R.drawable.shape_code);
//        } else if (listBean.getStatus().equals("photo")) {
//            tv_navigation.setText(mContext.getString(R.string.distributionComplete));
//            tv_navigation.setBackground(null);
//        } else if (listBean.getStatus().equals("pay_success")) {
//            tv_navigation.setText(mContext.getString(R.string.noEvaluation));
//            tv_navigation.setBackground(null);
//        } else if (listBean.getStatus().equals("comment")) {
//            tv_navigation.setText(mContext.getString(R.string.haveEvaluation));
//            tv_navigation.setBackground(null);
//        } else if (listBean.getStatus().equals("hang")) {
//            tv_navigation.setText(mContext.getString(R.string.hang));
//            tv_navigation.setBackground(null);
//        }
    }
}
