package com.ruitukeji.zwbs.adapter.getorder.message;

import android.content.Context;
import android.view.View;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.MessageBean.ResultBean.ListBean;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;
import cn.bingoogolapple.swipeitemlayout.BGASwipeItemLayout;

/**
 * 系统消息列表适配器
 * Created by Administrator on 2017/2/13.
 */
public class SystemMessageViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    /**
     * 当前处于打开状态的item
     */
    private List<BGASwipeItemLayout> mOpenedSil = new ArrayList<>();

    public SystemMessageViewAdapter(Context context) {
        super(context, R.layout.item_systemmessage);
    }


    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        BGASwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.item_systemMessage);
        swipeItemLayout.setDelegate(new BGASwipeItemLayout.BGASwipeItemLayoutDelegate() {
            @Override
            public void onBGASwipeItemLayoutOpened(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutClosed(BGASwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onBGASwipeItemLayoutStartOpen(BGASwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        viewHolderHelper.setItemChildClickListener(R.id.tv_markedRead);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_delete);
    }


    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        /**
         * 姓名
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getTitle());
        /**
         * 标记
         */
        String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
        if (StringUtils.isEmpty(accessToken)) {
            viewHolderHelper.setVisibility(R.id.iv_tag, View.GONE);
        } else {
            if (listBean.getIsRead() == 0) {
                viewHolderHelper.setVisibility(R.id.iv_tag, View.VISIBLE);
            } else {
                viewHolderHelper.setVisibility(R.id.iv_tag, View.GONE);
            }
        }
        /**
         *时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getPushTime());

        BGASwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.item_systemMessage);
        if (position % 3 == 0) {
            swipeItemLayout.setSwipeAble(false);
        } else {
            swipeItemLayout.setSwipeAble(true);
        }
    }

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    public void closeOpenedSwipeItemLayout() {
        for (BGASwipeItemLayout sil : mOpenedSil) {
            sil.close();
        }
        mOpenedSil.clear();
    }

}
