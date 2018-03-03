package com.ruitukeji.zwbs.getorder.message;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.entity.getorder.message.SystemMessageBean.ResultBean.ListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface SystemMessageContract {

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

        /**
         * 是否登录
         */
        void isLogin(int flag);
    }

    interface View extends BaseNewView<Presenter,String> {

    }

}
