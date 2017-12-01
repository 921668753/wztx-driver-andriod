package com.ruitukeji.zwbs.loginregister;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter {
        /**
         * 账号登录
         */
        void postToLogin( String phone, String pwd);

        /**
         * 第三方账号登录
         */
        void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex);

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


