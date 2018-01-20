package com.ruitukeji.zwbs.adapter.supplygoods.dialog;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 地址选择列表---省适配器
 * Created by Administrator on 2017/2/13.
 */

public class ProvinceViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    public ProvinceViewAdapter(Context context) {
        super(context, R.layout.item_province);
    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
//        viewHolderHelper.setItemChildClickListener(R.id.tv_getorder);
//        viewHolderHelper.setItemChildLongClickListener(R.id.tv_reject);
//        viewHolderHelper.setItemChildLongClickListener(R.id.tv_sendQuotation);
//    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {

        /**
         * 省名称
         */
        viewHolderHelper.setText(R.id.tv_provinceName, listBean.getName());
        if (listBean.getStatus() == 0) {
            viewHolderHelper.setTextColorRes(R.id.tv_provinceName, R.color.c0C0CColors);
            viewHolderHelper.setBackgroundRes(R.id.tv_provinceName, R.color.f5F5F5colors);
        } else {
            viewHolderHelper.setTextColorRes(R.id.tv_provinceName, R.color.fF4D51Colors);
            viewHolderHelper.setBackgroundRes(R.id.tv_provinceName, R.color.white);
        }
    }

}