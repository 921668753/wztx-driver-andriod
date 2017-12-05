package com.ruitukeji.zwbs.getorder.dialog;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * 可接单类型
 * Created by Administrator on 2017/2/21.
 */

public interface AvailableTypeBouncedContract {


    interface Presenter extends BasePresenter {
        /**
         * 获取可接单类型
         */
        void getAvailableType();
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
