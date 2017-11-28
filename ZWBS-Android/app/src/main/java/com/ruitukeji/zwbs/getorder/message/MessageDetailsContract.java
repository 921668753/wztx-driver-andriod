package com.ruitukeji.zwbs.getorder.message;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface MessageDetailsContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取消息详情
         */
        void getMessageDetails(int massageId);

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
