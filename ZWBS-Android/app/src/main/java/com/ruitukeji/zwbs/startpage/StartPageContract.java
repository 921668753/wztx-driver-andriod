package com.ruitukeji.zwbs.startpage;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2016/11/29.
 */

public interface StartPageContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取启动页和广告页
         */
        void getAppConfig();
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
