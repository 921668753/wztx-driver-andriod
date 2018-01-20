package com.ruitukeji.zwbs.adapter.supplygoods.dialog;

import android.content.Context;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.supplygoods.dialog.AddressBean.ResultBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 地址选择列表---市适配器
 * Created by Administrator on 2017/2/13.
 */

public class CityViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    public CityViewAdapter(Context context) {
        super(context, R.layout.item_city);
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
         * 城市名称
         */
        viewHolderHelper.setText(R.id.tv_CityName, listBean.getName());
        TextView tv_divider = (TextView) viewHolderHelper.getTextView(R.id.tv_divider);
        if (listBean.getStatus() == 0) {
            viewHolderHelper.setTextColorRes(R.id.tv_CityName, R.color.titletextcolors);
            tv_divider.setBackgroundResource(R.color.strokecolors);
            tv_divider.setHeight(1);
        } else {
            viewHolderHelper.setTextColorRes(R.id.tv_CityName, R.color.announcementCloseColors);
            tv_divider.setBackgroundResource(R.color.announcementCloseColors);
            tv_divider.setHeight(2);
        }
    }

}