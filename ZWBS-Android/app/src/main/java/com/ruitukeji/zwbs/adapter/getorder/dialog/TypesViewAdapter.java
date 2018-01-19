package com.ruitukeji.zwbs.adapter.getorder.dialog;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.mine.vehiclecertification.ConductorModelsBean.ResultBean.TypeBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 车型适配器
 * Created by Administrator on 2017/2/22.
 */

public class TypesViewAdapter extends BGAAdapterViewAdapter<TypeBean> {

    public TypesViewAdapter(Context context) {
        super(context, R.layout.item_models);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, TypeBean typeBean) {
        if (typeBean.getStatus() == 1) {
            helper.setBackgroundRes(R.id.tv_divider, R.color.announcementCloseColors);
            helper.setTextColor(R.id.tv_modelsName, mContext.getResources().getColor(R.color.announcementCloseColors));
        } else {
            helper.setBackgroundRes(R.id.tv_divider, R.color.dividercolors);
            helper.setTextColor(R.id.tv_modelsName, mContext.getResources().getColor(R.color.titletextcolors));
        }
        helper.setText(R.id.tv_modelsName, typeBean.getName());
    }
}
