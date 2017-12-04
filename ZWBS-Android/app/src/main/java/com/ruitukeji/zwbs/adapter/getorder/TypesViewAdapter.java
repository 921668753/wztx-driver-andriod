package com.ruitukeji.zwbs.adapter.getorder;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.TypeBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

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
            helper.setBackgroundRes(R.id.tv_divider, R.drawable.shape_conductormodels1);
        } else {
            helper.setBackgroundRes(R.id.tv_divider, R.drawable.shape_conductormodels);
        }
        helper.setText(R.id.tv_type, typeBean.getName());
    }
}
