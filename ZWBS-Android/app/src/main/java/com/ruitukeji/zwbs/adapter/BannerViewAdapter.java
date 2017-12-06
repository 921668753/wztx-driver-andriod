package com.ruitukeji.zwbs.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.entity.BannerBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 其他服务
 * Created by Admin on 2017/7/25.
 */

public class BannerViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public BannerViewAdapter(Context context) {
        super(context, R.layout.item_imageview);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
//        ImageView img_imgView = (ImageView) viewHolderHelper.getView(R.id.img_imgView);
//        img_imgView.setImageResource(R.mipmap.default_image);
//        img_imgView.setScaleType(ImageView.ScaleType.FIT_XY);
//        GlideImageLoader.glideLoader(mContext, listBean.getSrc(), img_imgView, 1);
    }


}
