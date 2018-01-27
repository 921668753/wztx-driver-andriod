package com.ruitukeji.zwbs.main;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface GetOrderContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取首页轮播图信息
         */
        void getHome();

        /**
         * 未读消息数量
         */
        void getUnRead();

        /**
         * 获取订单报价列表信息
         */
        void getQuoteOrder(int page, String city, int car_type_id, int car_length_id, String order_type);

        /**
         * 下载app
         */
        void downloadApp(String updateAppUrl);

        /**
         * 是否登录
         */
        void isLogin(int flag);

        /**
         * 是否认证
         */
        void isCertification(int flag);

        /**
         * 拒绝订单
         */
        void postRefuseOrder(int id);

        /**
         * 模拟点击
         *
         * @param view
         * @param x
         * @param y
         */
        void setSimulateClick(android.view.View view, float x, float y);
    }

    interface View extends BaseNewView<Presenter, String> {
//        /**
//         * http请求正确
//         *
//         * @param s
//         */
//        void getSuccess(String s, int flag);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg, int flag);
    }
}
