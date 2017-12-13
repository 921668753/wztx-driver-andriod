package com.ruitukeji.zwbs.mine.mywallet.paymentpasswordmanagement.modifypaymentpassword;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface ModifyPaymentPasswordContract {

    interface Presenter extends BasePresenter {


        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String type);

        /**
         * 验证验证码
         */
        void postVerificationCode(String phone, String code);


        /**
         * 验证身份证号
         */
        void postVerifyIdNumber(String idNumber);

        /**
         * 设置支付密码
         */
        void postModifyPaymentPassword(String oldPaymentPassword, String paymentPassword);
    }

    interface View extends BaseNewView<Presenter, String> {

    }

}
