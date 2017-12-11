package com.ruitukeji.zwbs.adapter;

import android.content.Context;
import android.view.View;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.MessageBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 消息列表适配器
 * Created by Administrator on 2017/2/13.
 */

public class MessageViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public MessageViewAdapter(Context context) {
        super(context, R.layout.item_message);
    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
//      //  viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
//     //   viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
//    }

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


        //  KjBitmapUtil.getInstance().getKjBitmap().display((ImageView) viewHolderHelper.getView(R.id.img_user), model.detail, 90, 90, R.mipmap.ic_launcher);
        // 这里不知道当前图片的尺寸，加载成功后会乱跳
//        Glide.with(mContext).load(model.icon).placeholder(R.mipmap.staggered_holder).error(R.mipmap.staggered_holder).dontAnimate().into((ImageView) viewHolderHelper.getView(R.id.iv_item_staggered_icon));

    }
}
