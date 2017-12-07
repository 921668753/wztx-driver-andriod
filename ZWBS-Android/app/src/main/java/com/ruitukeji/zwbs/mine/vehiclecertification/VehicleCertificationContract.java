package com.ruitukeji.zwbs.mine.vehiclecertification;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface VehicleCertificationContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取认证信息
         */
        //void postUpLoadImg(String path, int flag);

        /**
         * 上传照片
         */
        void postUpLoadImg(String path, int flag);

        /**
         * 提交认证信息
         */
        void postVehicleCertification(String name, int flag);
    }

    interface View extends BaseNewView<Presenter, String> {
//        /**
//         * http请求正确
//         *
//         * @param s
//         */
//        void getSuccess(String s);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg);
    }

}
