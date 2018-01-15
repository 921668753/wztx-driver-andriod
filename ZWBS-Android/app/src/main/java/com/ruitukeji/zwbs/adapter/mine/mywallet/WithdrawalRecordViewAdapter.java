package com.ruitukeji.zwbs.adapter.mine.mywallet;

import android.content.Context;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.entity.mine.mywallet.withdrawal.WithdrawalRecordBean.ResultBean.ListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 提现记录适配器
 * Created by Administrator on 2017/2/13.
 */

public class WithdrawalRecordViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public WithdrawalRecordViewAdapter(Context context) {
        super(context, R.layout.item_withdrawalrecord);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        /**
         * 名称
         */
        if (listBean.getStatus().equals("pay_success")) {
            viewHolderHelper.setText(R.id.tv_name, mContext.getString(R.string.withdrawalSuccess));
            viewHolderHelper.setTextColorRes(R.id.tv_name, R.color.titletextcolors);
        } else if (listBean.getStatus().equals("init") || listBean.getStatus().equals("agree")) {
            viewHolderHelper.setText(R.id.tv_name, mContext.getString(R.string.withdrawalProcessing));
            //   viewHolderHelper.setTextColorRes(R.id.tv_name, R.color.announcementCloseColors);
        } else {
            viewHolderHelper.setText(R.id.tv_name, mContext.getString(R.string.withdrawalFailure));
            viewHolderHelper.setTextColorRes(R.id.tv_name, R.color.announcementCloseColors);
        }

        /**
         * 提现金额
         */
        viewHolderHelper.setText(R.id.tv_money, listBean.getAmount());

        /**
         *时间
         */
        if (StringUtils.isEmpty(listBean.getResult_time())) {
            viewHolderHelper.setText(R.id.tv_time, listBean.getResult_time().substring(0, 10));
        }

        /**
         * 余额
         */
        viewHolderHelper.setText(R.id.tv_balance, listBean.getBalance());

    }

}