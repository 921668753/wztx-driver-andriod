package com.ruitukeji.zwbs.order;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface OrderContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取订单列表信息
         */
        void getOrder(int page, String type);
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
