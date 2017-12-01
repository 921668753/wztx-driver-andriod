package com.ruitukeji.zwbs.loginregister.bindphone;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface BindPhoneContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String type);

        /**
         * 重置密码请求
         */
        void postResetpwd(String phone, String code, String pwd);

        /**
         * 注册
         */
        void postRegister(String phone, String code, String pwd, String recommendcode);
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