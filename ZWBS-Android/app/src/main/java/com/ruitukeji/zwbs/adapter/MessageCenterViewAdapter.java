package com.ruitukeji.zwbs.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.MessageCenterBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 消息中心适配器
 * Created by Administrator on 2017/2/13.
 */

public class MessageCenterViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public MessageCenterViewAdapter(Context context) {
        super(context, R.layout.item_messagecenter);
    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
//      //  viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
//     //   viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
//    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        /**
         * 图标  img_icon
         */
        if (listBean.getPush_type().equals("system")) {
            GlideImageLoader.glideOrdinaryLoader(mContext, R.mipmap.xitong, (ImageView) viewHolderHelper.getView(R.id.img_icon));
            /**
             *内容
             */
            //  viewHolderHelper.setText(R.id.tv_content, listBean.getSystemmsg());
        } else if (listBean.getPush_type().equals("private")) {
            GlideImageLoader.glideOrdinaryLoader(mContext, R.mipmap.sirenxiaoxi, (ImageView) viewHolderHelper.getView(R.id.img_icon));
            /**
             *内容
             */
            // viewHolderHelper.setText(R.id.tv_content, listBean.getPrivatemsg());
        }


        /**
         * 标记
         */
        String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
        if (StringUtils.isEmpty(accessToken)) {
            viewHolderHelper.setVisibility(R.id.tv_tag, View.GONE);
        } else {
            if (listBean.getUnread() == 0) {
                viewHolderHelper.setVisibility(R.id.tv_tag, View.GONE);
            } else {
                viewHolderHelper.setVisibility(R.id.tv_tag, View.VISIBLE);
                viewHolderHelper.setText(R.id.tv_tag, listBean.getUnread() + "");
            }
        }
        /**
         *标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getName());


        //  KjBitmapUtil.getInstance().getKjBitmap().display((ImageView) viewHolderHelper.getView(R.id.img_user), model.detail, 90, 90, R.mipmap.ic_launcher);
        // 这里不知道当前图片的尺寸，加载成功后会乱跳
//        Glide.with(mContext).load(model.icon).placeholder(R.mipmap.staggered_holder).error(R.mipmap.staggered_holder).dontAnimate().into((ImageView) viewHolderHelper.getView(R.id.iv_item_staggered_icon));

    }
}
