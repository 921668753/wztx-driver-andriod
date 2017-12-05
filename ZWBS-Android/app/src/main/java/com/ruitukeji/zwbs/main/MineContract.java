package com.ruitukeji.zwbs.main;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/3/7.
 */

public interface MineContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取用户信息
         */
        void getInfo();

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
//        void getSuccess(String s, int flag);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg, int flag);
    }
}
