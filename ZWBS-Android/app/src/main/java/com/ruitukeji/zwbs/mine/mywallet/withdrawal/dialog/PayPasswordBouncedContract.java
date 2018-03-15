package com.ruitukeji.zwbs.mine.mywallet.withdrawal.dialog;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * 支付密码弹框
 * Created by Administrator on 2017/2/21.
 */

public interface PayPasswordBouncedContract {


    interface Presenter extends BasePresenter {
        /**
         * 验证旧支付密码
         */
        void postOldPayPassword(String oldPaymentPassword);

        /**
         * 发送提现信息
         */
        void postWithdrawal(String withdrawalAmount, int bankId);

    }

    interface View extends BaseNewView<Presenter, String> {

    }

}
