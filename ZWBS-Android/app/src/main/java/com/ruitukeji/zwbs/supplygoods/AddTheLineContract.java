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
        void postRoute(String startPoint, String endPoint, String org_address, String dest_address, int origin_province, int origin_city, int origin_area, int destination_province, int destination_city, int destination_area);
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
