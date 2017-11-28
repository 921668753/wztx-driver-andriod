package com.ruitukeji.zwbs.getorder.message;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface MessageContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取消息列表
         */
        void getMessage(String type, int page);

        /**
         * 删除消息
         */
        void postDeleteMessage(int masageId);
    }

    interface View extends BaseNewView<Presenter, String> {

    }

}
