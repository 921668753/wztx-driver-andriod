package com.ruitukeji.zwbs.mission;

import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface UploadReceiptVoucherContract {

    interface Presenter extends BasePresenter {

        /**
         * 上传到货凭证
         */
        void uploadCerPic(int order_id, String img_url);

        /**
         * 上传图片
         */
        void postUpLoadImg(String path, int code);
    }

    interface View extends BaseNewView<Presenter, String> {

    }
}
