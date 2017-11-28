package com.ruitukeji.zwbs.loginregister;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface NewUserInformationContract {

    interface Presenter extends BasePresenter {

        /**
         * 提交基本信息
         */
        void postInformation(String name, int sex, String IdNumber, String address, int wltype, String hold_pic);

        /**
         * 上传图片
         */
        void upLoadImg(String path);

        /**
         * 获取个人认证信息
         */
        void getPersonalCertificate();
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
