package com.ruitukeji.zwbs.mission;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.BitmapCoreUtil;
import com.ruitukeji.zwbs.utils.DataCleanManager;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */

public class UploadReceiptVoucherPresenter implements UploadReceiptVoucherContract.Presenter {

    private UploadReceiptVoucherContract.View mView;

    public UploadReceiptVoucherPresenter(UploadReceiptVoucherContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void uploadCerPic(int order_id, String img_url) {
//        if (StringUtils.isEmpty(img_url)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData1), 0);
//            return;
//        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        String dest_address_maps = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "currentLocationLatitudeLongitude");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_id", order_id);
        map.put("img_url", "http://ot090bmn8.bkt.clouddn.com/37bfbbf2e59ee54286762726db5881c5.png");
        map.put("dest_address_maps", dest_address_maps);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postUploadCerPic(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


    /**
     * @param path 上传图片
     */
    @Override
    public void postUpLoadImg(String path, int code) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad));
        if (StringUtils.isEmpty(path)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
            return;
        }
        File oldFile = new File(path);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), 0);
            return;
        }

        long fileSize = 0;
        try {
            fileSize = DataCleanManager.getFileSize(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileSize = 0;
        }
        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
            oldFile = BitmapCoreUtil.customCompression(oldFile);
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("file", oldFile);
        RequestClient.upLoadImg(httpParams, 1, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, code);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}
