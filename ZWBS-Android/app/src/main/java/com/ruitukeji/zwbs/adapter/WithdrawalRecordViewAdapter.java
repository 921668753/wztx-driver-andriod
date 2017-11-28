package com.ruitukeji.zwbs.adapter;

import android.content.Context;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.WithdrawalRecordBean.ResultBean.ListBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 提现记录适配器
 * Created by Administrator on 2017/2/13.
 */

public class WithdrawalRecordViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public WithdrawalRecordViewAdapter(Context context) {
        super(context, R.layout.item_withdrawalrecord);
    }

//    @Override
//    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
//      //  viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
//     //   viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
//    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        /**
         * 名称
         */
        viewHolderHelper.setText(R.id.tv_name, listBean.getBank_name());

        /**
         * 金额
         */
        viewHolderHelper.setText(R.id.tv_money, listBean.getWithdrawal_amount());

        /**
         * 成功 失败
         */
        if (listBean.getStatus().equals("init")) {
            viewHolderHelper.setTextColorRes(R.id.tv_balance, R.color.hintcolors);
            viewHolderHelper.setText(R.id.tv_balance, "未处理");
        } else if (listBean.getStatus().equals("agree")) {
            viewHolderHelper.setTextColorRes(R.id.tv_balance, R.color.hintcolors);
            // viewHolderHelper.setText(R.id.tv_money, "+" + listBean.getReal_amount());
            viewHolderHelper.setText(R.id.tv_balance, "后台同意");
        } else if (listBean.getStatus().equals("refuse")) {
             viewHolderHelper.setTextColorRes(R.id.tv_balance, R.color.lonincolors);
            viewHolderHelper.setText(R.id.tv_balance, "已拒绝");
        } else if (listBean.getStatus().equals("pay_success")) {
            viewHolderHelper.setTextColorRes(R.id.tv_balance, R.color.certificationcolors1);
         //   viewHolderHelper.setText(R.id.tv_money, "-" + listBean.getWithdrawal_amount());
            viewHolderHelper.setText(R.id.tv_balance, "提现成功");
        } else if (listBean.getStatus().equals("pay_fail")) {
            viewHolderHelper.setTextColorRes(R.id.tv_balance, R.color.lonincolors);
            viewHolderHelper.setText(R.id.tv_balance, "提现失败");
        }

        /**
         *时间
         */
        if (StringUtils.isEmpty(listBean.getCreate_at())) {
            viewHolderHelper.setText(R.id.tv_time, "");
        } else {
            viewHolderHelper.setText(R.id.tv_time, listBean.getCreate_at().substring(0, 16));
        }
    }

}