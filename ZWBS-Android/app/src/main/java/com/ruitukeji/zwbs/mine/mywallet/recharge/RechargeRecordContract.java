package com.ruitukeji.zwbs.mine.mywallet.recharge;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface RechargeRecordContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取充值记录列表
         */
        void getRechargeRecord(int page);
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
