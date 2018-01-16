package com.ruitukeji.zwbs.mine.mywallet.incomedetails.incomedetailsfragment;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface IncomeDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取收入明细信息
         */
        void getIncomeDetails(int page, int type);

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
