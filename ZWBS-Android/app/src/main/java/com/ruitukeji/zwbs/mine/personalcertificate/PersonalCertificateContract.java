package com.ruitukeji.zwbs.mine.personalcertificate;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/14.
 */

public interface PersonalCertificateContract {

    interface Presenter extends BasePresenter {
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
        void getSuccess(String s);

        /**
         * http请求错误
         */
        void error(String msg);
    }


}
