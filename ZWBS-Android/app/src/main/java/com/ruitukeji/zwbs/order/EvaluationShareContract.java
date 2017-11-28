package com.ruitukeji.zwbs.order;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/16.
 */

public interface EvaluationShareContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取评价信息
         */
        void getEvaluationShare(int order_id);
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
