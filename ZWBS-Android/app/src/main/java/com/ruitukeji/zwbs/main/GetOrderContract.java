package com.ruitukeji.zwbs.main;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface GetOrderContract {
    interface Presenter extends BasePresenter {
        /**
         * 未读消息数量
         */
        void getUnRead();

        /**
         * 获取订单报价列表信息
         */
        void getQuoteOrder(int page, String line_id);

        /**
         * 发送工作状态
         */
        void postWorkingState(int status);

        /**
         * 获取工作状态
         */
        void getWorkingState();

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
        void isCertification(SweetAlertDialog sweetAlertDialog, int flag);

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
        void error(String msg, int flag);
    }
}
