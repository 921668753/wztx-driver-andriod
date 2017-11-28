package com.ruitukeji.zwbs.adapter;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.SupplyGoodsBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


/**
 * 货源列表适配器
 * Created by Administrator on 2017/2/13.
 */

public class SupplyGoodsViewAdapter extends BGAAdapterViewAdapter<ListBean> {
    public SupplyGoodsViewAdapter(Context context) {
        super(context, R.layout.item_getorder);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
      //  viewHolderHelper.setItemChildClickListener(R.id.rl_getorder);
        //   viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        /**
         * 头像
         */
        // GlideImageLoader.glideLoader(mContext, listBean.getAvatar(), (BGAImageView) viewHolderHelper.getView(R.id.img_user), 0);
//        /**
//         * 姓名
//         */
//        viewHolderHelper.setText(R.id.tv_name, model.title);
        /**
         * 起点
         */
        int orgprovince = listBean.getOrg_city().indexOf("省");
        int orgcity = listBean.getOrg_city().indexOf("市");
        viewHolderHelper.setText(R.id.tv_start, listBean.getOrg_city().substring(0, orgprovince) + listBean.getOrg_city().substring(orgprovince + 1, orgcity));
        /**
         * 终点
         */
        int destprovince = listBean.getDest_city().indexOf("省");
        int destcity = listBean.getDest_city().indexOf("市");
        viewHolderHelper.setText(R.id.tv_stop, listBean.getDest_city().substring(0, destprovince) + listBean.getDest_city().substring(destprovince + 1, destcity));
        /**
         * 货物名称
         */
    //    viewHolderHelper.setText(R.id.tv_goodName, listBean.getGoods_name() + "/" + listBean.getWeight() + "吨");
        /**
         * 重量
         */
        viewHolderHelper.setText(R.id.tv_weight, listBean.getCar_style_type() + "/" + listBean.getCar_style_length());
        /**
         *用车时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getUsecar_time() + "");
        /**
         * 运价
         */
        if (listBean.getMind_price().equals("0.00")) {
            viewHolderHelper.setText(R.id.tv_freight, "￥" + listBean.getSystem_price());
        } else {
            viewHolderHelper.setText(R.id.tv_freight, "￥" + listBean.getMind_price());
        }

        // 这里不知道当前图片的尺寸，加载成功后会乱跳
//        Glide.with(mContext).load(model.icon).placeholder(R.mipmap.staggered_holder).error(R.mipmap.staggered_holder).dontAnimate().into((ImageView) viewHolderHelper.getView(R.id.iv_item_staggered_icon));

    }

}