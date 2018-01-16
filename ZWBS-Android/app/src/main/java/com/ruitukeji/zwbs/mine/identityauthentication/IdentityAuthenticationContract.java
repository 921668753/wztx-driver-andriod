package com.ruitukeji.zwbs.mine.identityauthentication;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface IdentityAuthenticationContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取司机认证信息
         */
        void getDriverAuthInfo();

        /**
         * 上传头像
         */
        void postUpLoadImg(String path, int flag);

        /**
         * 提交认证信息
         */
        void postIdentityAuthentication(String real_name, String identity, long identity_pic_time, String front_pic, String back_pic, String hold_pic, long license_pic_time,
                                        String license_pic, long transport_pic_time, String transport_pic, String faith_pic);
    }

    interface View extends BaseNewView<Presenter, String> {
//        /**
//         * http请求正确
//         *
//         * @param s
//         */
//        void getSuccess(String s);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg);
    }

}
