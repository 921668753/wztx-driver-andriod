package com.ruitukeji.zwbs.main;

import android.os.Handler;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;
import com.ruitukeji.zwbs.entity.NationalCity;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface SupplyGoodsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取订单列表信息
         */
        void getSupplyGoods(String startPoint, String endPoint, int vehicleLength, int vehicleModel, int page);

        /**
         * 是否登录
         */
        void isLogin(int flag);

        /**
         * 是否认证
         */
        void isCertification(SweetAlertDialog sweetAlertDialog, int flag);


        /**
         * 解析数据
         */

        void initJsonData(Handler mHandler, ArrayList<NationalCity> options1Items, ArrayList<ArrayList<String>> options2Items, ArrayList<ArrayList<ArrayList<String>>> options3Items);


        /**
         * 解析数据
         */
        void ShowPickerView(ArrayList<NationalCity> options1Items, ArrayList<ArrayList<String>> options2Items, ArrayList<ArrayList<ArrayList<String>>> options3Items);
    }

    interface View extends BaseNewView<Presenter, String> {
//        /**
//         * http请求正确
//         *
//         * @param s
//         */
//        void getSuccess(String s, int flag);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg, int flag);
    }


}
