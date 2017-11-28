package com.ruitukeji.zwbs.mine.personaldata;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface PersonalDataContract {
    interface Presenter extends BasePresenter {
        /**
         * 上传头像
         */
        void postUpLoadImg(String path);

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


