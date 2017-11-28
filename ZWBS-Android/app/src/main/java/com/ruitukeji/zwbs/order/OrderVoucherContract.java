package com.ruitukeji.zwbs.order;

import com.lzy.imagepicker.bean.ImageItem;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface OrderVoucherContract {

    interface Presenter extends BasePresenter {

        /**
         * 上传到货凭证
         */
        void postOrderVoucher(int order_id, ArrayList<ImageItem> imgList);

        /**
         * 上传图片
         */
        void postUpLoadImg(String path, int code);


    }

    interface View extends BaseView<Presenter> {
        /**
         * http请求正确
         *
         * @param s
         */
        void getSuccess(String s, int flag);

        /**
         * http请求错误
         */
        void error(String msg);
    }
}
