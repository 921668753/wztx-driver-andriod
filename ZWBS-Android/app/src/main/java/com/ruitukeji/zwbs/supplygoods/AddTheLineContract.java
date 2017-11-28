package com.ruitukeji.zwbs.supplygoods;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface AddTheLineContract {


    interface Presenter extends BasePresenter {
        /**
         * 添加路线
         */
        void postRoute(String startPoint, String endPoint);
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
