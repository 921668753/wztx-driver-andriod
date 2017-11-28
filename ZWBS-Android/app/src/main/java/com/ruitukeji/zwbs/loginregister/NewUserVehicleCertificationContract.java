package com.ruitukeji.zwbs.loginregister;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface NewUserVehicleCertificationContract {

    interface Presenter extends BasePresenter {
        /**
         * 提交车辆信息
         */
        void postVehicleCertification(SweetAlertDialog sweetAlertDialog,String vehicleLength, int car_style_type_id, String vehicleModel, int car_style_length_id, String load,
                                      String carrierProduct, String licensePlateNumber, String index_pic,
                                      long policy_startline, long policy_deadline, String policy_pic,
                                      long license_startline, long license_deadline, String vehicle_license_pic,
                                      long driving_startline, long driving_deadline, String driving_licence_pic,
                                      long operation_startline, long operation_deadline, String operation_pic);

        /**
         * 上传图片
         */
        void upLoadImg(String path, int flag);

        /**
         * 获取个人认证信息
         */
        void getPersonalCertificate();
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
