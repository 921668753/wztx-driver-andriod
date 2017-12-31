package com.ruitukeji.zwbs.getorder.message;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.entity.getorder.message.OrderMessageBean.ResultBean.ListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface OrderMessageContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取消息列表
         */
        void getMessage(String type, int page);

        /**
         * 删除消息
         */
        void postDeleteMessage(List<ListBean> masageList);

        /**
         * 标记已读
         */
        void postReadMessage(List<ListBean> masageList);
    }

    interface View extends BaseNewView<Presenter, String> {

    }

}
