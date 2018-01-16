package com.ruitukeji.zwbs.adapter.mine.mywallet.incomedetails;

import android.content.Context;
import android.view.View;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.mine.mywallet.incomedetails.IncomeDetailsBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 收入明细列表适配器
 * Created by Administrator on 2017/12/22.
 */

public class IncomeDetailsViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public IncomeDetailsViewAdapter(Context context) {
        super(context, R.layout.item_getorder);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, ListBean listBean) {
        helper.setVisibility(R.id.ll_button, View.GONE);

        /**
         *名字
         */
        helper.setText(R.id.tv_name, listBean.getGoods_name());





    }
}
