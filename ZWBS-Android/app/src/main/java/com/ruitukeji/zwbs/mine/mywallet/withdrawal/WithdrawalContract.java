package com.ruitukeji.zwbs.mine.mywallet.withdrawal;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface WithdrawalContract {

    interface Presenter extends BasePresenter {
        /**
         * 发送提现信息
         */
        void postWithdrawal(String withdrawalAmount, int bankId);

        /**
         * 是否登录
         */
        void isLogin(int flag);
    }

    interface View extends BaseNewView<Presenter, String> {
//        /**
//         * http请求正确
//         *
//         * @param s
//         */
//        void getSuccess(String s);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg);
    }


}
