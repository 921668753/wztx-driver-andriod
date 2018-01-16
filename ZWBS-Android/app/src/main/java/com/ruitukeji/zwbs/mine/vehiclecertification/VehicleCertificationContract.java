package com.ruitukeji.zwbs.mine.vehiclecertification;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface VehicleCertificationContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取车辆认证信息
         */
        void getCarAuthInfo();

        /**
         * 上传照片
         */
        void postUpLoadImg(String path, int flag);

        /**
         * 提交认证信息
         */
        void postVehicleCertification(String card_number, String car_type, int car_style_type_id, String car_length, int car_style_length_id,long car_registered_time,String car_front_pic,String car_bank_pic,
                                      String car_tail_pic, long license_time,String license_pic,long transport_pic_time,String transport_pic,long policy_time,String policy_pic,long insurance_time,
                                      String insurance_pic);
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
