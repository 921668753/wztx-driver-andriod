package com.ruitukeji.zwbs.mine.otherservices;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/3/14.
 */

public class OtherServicesContract {


    interface Presenter extends BasePresenter {
        /**
         * 获取信息
         */
        void getOtherServices();
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
