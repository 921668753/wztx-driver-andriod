package com.ruitukeji.zwbs.supplygoods;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface SetTheLineContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取路线列表信息
         */
        void getRouteList();

        /**
         * 删除路线信息
         */
        void postDeleteRoute(int routeId);
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
