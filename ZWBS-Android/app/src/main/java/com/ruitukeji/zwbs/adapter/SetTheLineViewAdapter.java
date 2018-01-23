package com.ruitukeji.zwbs.adapter;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.RefreshModel;
import com.ruitukeji.zwbs.entity.SetTheLineBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 设定路线适配器
 * Created by Administrator on 2017/2/13.
 */

public class SetTheLineViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public SetTheLineViewAdapter(Context context) {
        super(context, R.layout.item_settheline);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.iv_delete);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 苏州-郑州
         */
        viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city());
        viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city());
    }

}