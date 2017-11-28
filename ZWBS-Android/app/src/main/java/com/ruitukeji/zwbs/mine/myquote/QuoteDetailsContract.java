package com.ruitukeji.zwbs.mine.myquote;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/16.
 */

public interface QuoteDetailsContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取订单详情信息
         */
        void getOrderDetails(int order_id);


        /**
         * 提交司机报价
         */
        void postQuoteAdd(int quote_id, String dr_price, int is_place_order);

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
