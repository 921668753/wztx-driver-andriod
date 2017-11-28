package com.ruitukeji.zwbs.mine.personalcertificate;

import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/3/10.
 */

public interface CheckDocumentsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取证件信息
         */
        void getCheckDocuments();

        /**
         * 上传司机证件图片
         */
        void upLoadImg(String path, int flag);

        /**
         * 提交司机证件信息
         */
        void postCheckDocuments(SweetAlertDialog sweetAlertDialog, List list);
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
