package com.ruitukeji.zwbs.adapter;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.RefreshModel;
import com.ruitukeji.zwbs.entity.SetTheLineBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 设定路线适配器
 * Created by Administrator on 2017/2/13.
 */

public class SetTheLineViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public SetTheLineViewAdapter(Context context) {
        super(context, R.layout.item_settheline);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.iv_delete);
        //   viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 苏州-郑州
         */
        viewHolderHelper.setText(R.id.tv_enthesis, listBean.getOrg_city() + "—" + listBean.getDest_city());
        //  KjBitmapUtil.getInstance().getKjBitmap().display((BGAImageView) viewHolderHelper.getView(R.id.img_user), model.detail, 90, 90, R.mipmap.ic_launcher);
        // 这里不知道当前图片的尺寸，加载成功后会乱跳
//        Glide.with(mContext).load(model.icon).placeholder(R.mipmap.staggered_holder).error(R.mipmap.staggered_holder).dontAnimate().into((ImageView) viewHolderHelper.getView(R.id.iv_item_staggered_icon));

    }

}