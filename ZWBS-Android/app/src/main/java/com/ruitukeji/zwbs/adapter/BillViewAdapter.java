package com.ruitukeji.zwbs.adapter;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.BillBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 账单适配器
 * Created by Administrator on 2017/2/13.
 */

public class BillViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public BillViewAdapter(Context context) {
        super(context, R.layout.item_bill);
    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
//      //  viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
//     //   viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
//    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getReal_name());
        /**
         * 运价
         */
        viewHolderHelper.setText(R.id.tv_freight, listBean.getFinal_price());
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
         *时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getUsecar_time().substring(0, 16));
    }

}