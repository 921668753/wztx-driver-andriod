package com.ruitukeji.zwbs.loginregister;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface NewUserIdentityAuthenticationContract {

    interface Presenter extends BasePresenter {

        /**
         * 提交身份认证
         */
        void postIdentityAuthentication(String name, int sex, String IdNumber, String address, int wltype, String hold_pic, String front_pic, String back_pic);

        /**
         * 上传图片
         */
        void upLoadImg(String path, int flag);
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
