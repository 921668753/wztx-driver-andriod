package com.ruitukeji.zwbs.mine.mywallet.paymentpasswordmanagement.setpaymentpassword;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface SetPaymentPasswordContract {

    interface Presenter extends BasePresenter {
        /**
         * 设置支付密码
         */
        void postSetPaymentPassword(String oldPaymentPassword, String paymentPassword);
    }

    interface View extends BaseNewView<Presenter, String> {

    }

}
