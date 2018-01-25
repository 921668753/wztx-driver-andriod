package com.ruitukeji.zwbs.mission.dialog;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * 取消订单弹框
 * Created by Administrator on 2017/2/21.
 */

public interface CancelOrderBouncedContract {


    interface Presenter extends BasePresenter {
        /**
         * 获取取消订单弹框
         */
        void postCancelOrder(int orderId);
    }

    interface View extends BaseNewView<Presenter, String> {

    }

}
