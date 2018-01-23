package com.ruitukeji.zwbs.mission;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface ExceptionReportingContract {

    interface Presenter extends BasePresenter {

        /**
         * 填写快递单
         */
        void postFillOutExpress(int page, long time, int type);

    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
