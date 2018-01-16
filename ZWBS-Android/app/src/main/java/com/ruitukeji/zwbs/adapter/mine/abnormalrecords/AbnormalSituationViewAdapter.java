package com.ruitukeji.zwbs.adapter.mine.abnormalrecords;

import android.content.Context;
import android.widget.ImageView;

import com.lzy.imagepicker.bean.ImageItem;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 异常情况图片适配器
 * Created by Administrator on 2017/2/13.
 */

public class AbnormalSituationViewAdapter extends BGAAdapterViewAdapter<ImageItem> {

    public AbnormalSituationViewAdapter(Context context) {
        super(context, R.layout.item_abnormalsituation);
    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
//      //  viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
//     //   viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
//    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ImageItem imageItem) {

        // 这里不知道当前图片的尺寸，加载成功后会乱跳
        //   Glide.with(mContext).load(model.icon).placeholder(R.mipmap.staggered_holder).error(R.mipmap.staggered_holder).dontAnimate().into((ImageView) viewHolderHelper.getView(R.id.iv_item_staggered_icon));
        GlideImageLoader.glideOrdinaryLoader(mContext, imageItem.path, (ImageView) viewHolderHelper.getView(R.id.img_abnormalsituation));

    }

}