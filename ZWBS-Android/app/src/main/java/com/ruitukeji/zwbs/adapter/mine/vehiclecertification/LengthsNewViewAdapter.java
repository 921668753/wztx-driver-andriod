package com.ruitukeji.zwbs.adapter.mine.vehiclecertification;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.LengthBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 车长适配器
 * Created by Administrator on 2017/2/22.
 */

public class LengthsNewViewAdapter extends BGAAdapterViewAdapter<LengthBean> {


    public LengthsNewViewAdapter(Context context) {
        super(context, R.layout.item_conductormodels);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, LengthBean lengthBean) {
        if (lengthBean.getStatus() == 1) {
            helper.setBackgroundRes(R.id.tv_type, R.drawable.shape_conductormodels1);
        } else {
            helper.setBackgroundRes(R.id.tv_type, R.drawable.shape_conductormodels);
        }
        helper.setText(R.id.tv_type, lengthBean.getName());
    }
}
