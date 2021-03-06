package com.ruitukeji.zwbs.mission;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface FillOutExpressContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取签收单信息
         */
        void getReceiptInformation(int g_id);

        /**
         * 填写快递单
         */
        void postFillOutExpress(int g_id, String num, String company);

    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
