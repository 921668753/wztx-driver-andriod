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

    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
