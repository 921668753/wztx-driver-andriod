package com.ruitukeji.zwbs.adapter.supplygoods.dialog;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 货源---添加路线省弹框
 * Created by Administrator on 2018/1/23.
 */

public class AddLineProvinceViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    public AddLineProvinceViewAdapter(Context context) {
        super(context, R.layout.item_addlineaddress);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {

        /**
         * 城市名称
         */
        viewHolderHelper.setText(R.id.tv_adress, listBean.getName());

//        TextView tv_divider = (TextView) viewHolderHelper.getTextView(R.id.tv_divider);
//        if (listBean.getStatus() == 0) {
//            viewHolderHelper.setTextColorRes(R.id.tv_CityName, R.color.titletextcolors);
//            tv_divider.setBackgroundResource(R.color.strokecolors);
//            tv_divider.setHeight(1);
//        } else {
//            viewHolderHelper.setTextColorRes(R.id.tv_CityName, R.color.announcementCloseColors);
//            tv_divider.setBackgroundResource(R.color.announcementCloseColors);
//            tv_divider.setHeight(2);
//        }
    }

}