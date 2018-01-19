package com.ruitukeji.zwbs.adapter.getorder.dialog;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.getorder.dialog.AvailableTypeBean.ResultBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * Created by Administrator on 2018/1/19.
 */

public class AvailableTypeViewAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public AvailableTypeViewAdapter(Context context) {
        super(context, R.layout.item_models);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, ResultBean resultBean) {
        if (resultBean.getStatus() == 1) {
            helper.setBackgroundRes(R.id.tv_divider, R.color.announcementCloseColors);
            helper.setTextColor(R.id.tv_modelsName, mContext.getResources().getColor(R.color.announcementCloseColors));
        } else {
            helper.setBackgroundRes(R.id.tv_divider, R.color.dividercolors);
            helper.setTextColor(R.id.tv_modelsName, mContext.getResources().getColor(R.color.titletextcolors));
        }
        helper.setText(R.id.tv_modelsName, resultBean.getValue());
    }
}
