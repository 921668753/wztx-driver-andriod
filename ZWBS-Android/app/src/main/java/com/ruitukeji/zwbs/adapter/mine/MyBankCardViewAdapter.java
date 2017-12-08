package com.ruitukeji.zwbs.adapter.mine;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.ConductorModelsBean.ResultBean.LengthBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的银行卡列表适配器
 * Created by Administrator on 2017/2/22.
 */

public class MyBankCardViewAdapter extends BGAAdapterViewAdapter<LengthBean> {

    public MyBankCardViewAdapter(Context context) {
        super(context, R.layout.item_bankcard);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, LengthBean lengthBean) {
        helper.setText(R.id.tv_bankCardName, lengthBean.getName());
        helper.setText(R.id.tv_tail, lengthBean.getName());
    }
}
