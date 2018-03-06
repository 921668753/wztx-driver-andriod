package com.ruitukeji.zwbs.adapter.getorder.selectioncity.addresssearch;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.getorder.selectioncity.InlandBean.ResultBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 地址搜索  适配器
 * Created by Admin on 2017/9/8.
 */

public class AddressSearchViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public AddressSearchViewAdapter(Context context) {
        super(context, R.layout.item_addresssearch);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 类别
         */
        helper.setText(R.id.tv_addressName, model.getName());

    }
}
