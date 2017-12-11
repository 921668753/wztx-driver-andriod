package com.ruitukeji.zwbs.adapter;

import android.content.Context;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.RefreshModel;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 查看证件适配器
 * Created by Administrator on 2017/6/20.
 */

public class CheckDocumentsAdapter extends BGAAdapterViewAdapter<RefreshModel> {
    public CheckDocumentsAdapter(Context context) {
        super(context, R.layout.item_checkdocuments);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.iv_certificate);
        viewHolderHelper.setItemChildClickListener(R.id.ll_startDate);
        viewHolderHelper.setItemChildClickListener(R.id.ll_asOfTheDate);
    }


    @SuppressWarnings("deprecation")
    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, RefreshModel listBean) {
        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getTitle());
        /**
         *标记
         */
//        if (StringUtils.isEmpty(listBean.getImg())) {
//            if (listBean.getCodeX().equals("biz_lic")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.yingyezhizhao, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("tax_reg_ctf")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.taxregistration, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("org_code_ctf")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.zuzhijigou, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("iso90001")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.ios9001, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("ts_lic")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.ts, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("ped_lic")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.ped, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("api_lic")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.api, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("ce_lic")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.ce, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("sil_lic")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.sil, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("bam_lic")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.bam, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else if (listBean.getCodeX().equals("other")) {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.other, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            } else {
//                GlideImageLoader.glideLoader(mContext, R.mipmap.zizhirenzheng, (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//            }
//        } else {
//            GlideImageLoader.glideLoader(mContext, listBean.getImg(), (ImageView) viewHolderHelper.getView(R.id.iv_certificate), 1);
//        }
//        if (StringUtils.isEmpty(listBean.getTermStart())) {
//            viewHolderHelper.setText(R.id.tv_startDate, mContext.getString(R.string.pleaseSelect));
//        } else {
//            viewHolderHelper.setText(R.id.tv_startDate, DataUtil.getStringTime(listBean.getTermStart()));
//        }
//        if (StringUtils.isEmpty(listBean.getTermEnd())) {
//            viewHolderHelper.setText(R.id.tv_asOfTheDate, mContext.getString(R.string.pleaseSelect));
//        } else {
//            viewHolderHelper.setText(R.id.tv_asOfTheDate, DataUtil.getStringTime(listBean.getTermEnd()));
//        }
    }
}
