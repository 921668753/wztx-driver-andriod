package com.ruitukeji.zwbs.mission.fragment;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface TaskContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单列表信息
         */
        void getTask(int page, long time, int type);

        /**
         * 是否登录
         */
        void isLogin(int flag);

        /**
         * 到达起运地时间/到达目的地时间
         */
        void postArrOrgTime(int id, int type);

        /**
         * 司机进行发货动作
         */
        void postShipping(int id);

    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
