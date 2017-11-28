package com.ruitukeji.zwbs.getorder.message;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface MessageCenterContract {

    interface Presenter extends BasePresenter {
        /**
         * 未读消息数量
         */
        void getUnRead();

        /**
         * 是否登录
         */
        void isLogin(int flag);
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
