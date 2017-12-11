package com.ruitukeji.zwbs.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.MyQuoteBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 订单适配器
 * Created by Administrator on 2017/2/13.
 */

public class MyQuoteViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public MyQuoteViewAdapter(Context context) {
        super(context, R.layout.item_myquote);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.rl_navigation);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        /**
         * 头像
         */
        //  GlideImageLoader.glideLoader(mContext, listBean.getAvatar(), (BGAImageView) viewHolderHelper.getView(R.id.img_user), 0);
//        /**
//         * 姓名
//         */
//        viewHolderHelper.setText(R.id.tv_name, model.title);
        /**
         * 起点
         */
        /**
         * 起点
         */
        int orgprovince = listBean.getOrg_city().indexOf("省");
        int orgcity = listBean.getOrg_city().indexOf("市");
        if (orgprovince == -1 && orgcity != -1) {
//            int orgarea = listBean.getOrg_city().indexOf("区");
//            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgcity) + listBean.getOrg_city().substring(orgcity + 1, orgarea));
            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgcity));
        } else if (orgprovince != -1 && orgcity != -1) {
            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgprovince) + listBean.getOrg_city().substring(orgprovince + 1, orgcity));
        } else {
            viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city());
        }

        /**
         * 终点
         */
        int destprovince = listBean.getDest_city().indexOf("省");//("市")
        int destcity = listBean.getDest_city().indexOf("市");
        if (destprovince == -1 && destcity != -1) {
            // int destarea = listBean.getDest_city().indexOf("区");
            //   viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destcity) + listBean.getDest_city().substring(destcity + 1, destarea));
            viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destcity));
        } else if (destprovince != -1 && destcity != -1) {
            viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destprovince) + listBean.getDest_city().substring(destprovince + 1, destcity));
        } else {
            viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city());
        }
        /**
         * 货物名称  重量
         */
        viewHolderHelper.setText(R.id.tv_nameWeight, listBean.getGoods_name() + "/" + listBean.getWeight() + "吨");
        /**
         * 运价
         */
//        viewHolderHelper.setVisibility(R.id.tv_freight, View.GONE);

        /**
         * 状态
         */
        if (listBean.getStatus() != null && listBean.getStatus().equals("init") && listBean.getIs_receive() == 0) {
            TextView tv_navigation = viewHolderHelper.getTextView(R.id.tv_navigation);
            viewHolderHelper.setText(R.id.tv_freight, "运价：待货主确定");
            tv_navigation.setText(mContext.getString(R.string.quote));
            tv_navigation.setTextColor(mContext.getResources().getColor(R.color.lonincolors));
            tv_navigation.setBackground(null);
        } else if (listBean.getStatus() != null && listBean.getStatus().equals("complete") && listBean.getIs_receive() == 1) {
            TextView tv_navigation = viewHolderHelper.getTextView(R.id.tv_navigation);
            viewHolderHelper.setText(R.id.tv_freight, "运价：待货主确定");
            tv_navigation.setText(mContext.getString(R.string.theBid));
            tv_navigation.setTextColor(mContext.getResources().getColor(R.color.certificationcolors1));
            tv_navigation.setBackground(null);
        } else {
            TextView tv_navigation = viewHolderHelper.getTextView(R.id.tv_navigation);
            viewHolderHelper.setText(R.id.tv_freight, "运价：待货主确定");
            tv_navigation.setText(mContext.getString(R.string.offerFailure));
            tv_navigation.setTextColor(mContext.getResources().getColor(R.color.textColor));
            tv_navigation.setBackground(null);
        }

    }
}
