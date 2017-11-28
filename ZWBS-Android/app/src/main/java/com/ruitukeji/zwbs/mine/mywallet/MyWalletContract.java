package com.ruitukeji.zwbs.mine.mywallet;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface MyWalletContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取我的钱包信息
         */
        void getMyWallet();
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
