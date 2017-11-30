package com.ruitukeji.zwbs.mine.mywallet.withdrawal;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface WithdrawalContract {

    interface Presenter extends BasePresenter {
        /**
         * 发送提现信息
         */
        void postWithdrawal(SweetAlertDialog sweetAlertDialog, String withdrawalAmount, String bankName, String paymentAccount, String accountName);
    }

    interface View extends BaseView<Presenter> {
        /**
         * http请求正确
         *
         * @param s
         */
        void getSuccess(String s);

        /**
         * http请求错误
         */
        void error(String msg);
    }


}
