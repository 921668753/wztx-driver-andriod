package com.ruitukeji.zwbs.adapter.mine.helpcenter;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.mine.helpcenter.HelpCenterBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 帮助中心适配器
 * Created by Administrator on 2017/2/13.
 */

public class HelpCenterViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public HelpCenterViewAdapter(Context context) {
        super(context, R.layout.item_helpcenter);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getTitle());

    }

}