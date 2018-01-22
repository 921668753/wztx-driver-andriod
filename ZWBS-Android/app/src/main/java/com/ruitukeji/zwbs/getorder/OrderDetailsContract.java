package com.ruitukeji.zwbs.getorder;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/16.
 */

public interface OrderDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取货源详情信息
         */
        void getGoodDetails(int good_id);

        /**
         * 拒绝订单
         */
        void postRefuseOrder(int id);

    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
