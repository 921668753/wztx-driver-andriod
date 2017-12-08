package com.ruitukeji.zwbs.adapter.getorder;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.LengthBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 车长适配器
 * Created by Administrator on 2017/2/22.
 */

public class LengthsViewAdapter extends BGAAdapterViewAdapter<LengthBean> {


    public LengthsViewAdapter(Context context) {
        super(context, R.layout.item_models);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, LengthBean lengthBean) {
        if (lengthBean.getStatus() == 1) {
            helper.setBackgroundRes(R.id.tv_divider, R.color.announcementCloseColors);
            helper.setTextColor(R.id.tv_modelsName, mContext.getResources().getColor(R.color.announcementCloseColors));
        } else {
            helper.setBackgroundRes(R.id.tv_divider, R.color.dividercolors);
            helper.setTextColor(R.id.tv_modelsName, mContext.getResources().getColor(R.color.titletextcolors));
        }
        helper.setText(R.id.tv_modelsName, lengthBean.getName());
    }
}
