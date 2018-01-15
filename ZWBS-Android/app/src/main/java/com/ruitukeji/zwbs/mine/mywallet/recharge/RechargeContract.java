package com.ruitukeji.zwbs.mine.mywallet.recharge;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface RechargeContract {


    interface Presenter extends BasePresenter {
        /**
         * 获取充值信息
         */
        void postRecharge(String money, int type);
    }

    interface View extends BaseView<Presenter> {
        /**
         * http请求正确
         *
         * @param s
         */
        void getSuccess(String s, int flag);

        /**
         * http请求错误
         */
        void error(String msg);
    }


}
