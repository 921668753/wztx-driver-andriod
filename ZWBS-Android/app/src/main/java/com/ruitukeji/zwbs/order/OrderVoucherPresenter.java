package com.ruitukeji.zwbs.order;

import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.lzy.imagepicker.bean.ImageItem;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */

public class OrderVoucherPresenter implements OrderVoucherContract.Presenter {

    private OrderVoucherContract.View mView;

    public OrderVoucherPresenter(OrderVoucherContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postOrderVoucher(int order_id, ArrayList<ImageItem> imgList) {

        if (imgList == null || imgList.size() < 1) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.noData1));
            return;
        }
        String img_url = "";
        for (int i = 0; i < imgList.size(); i++) {
            img_url += imgList.get(i).path + "|";
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        String currentLocationLatitudeLongitude = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "currentLocationLatitudeLongitude");
        map.put("order_id", order_id);
        map.put("img_url", img_url.substring(0, img_url.length() - 1));
        map.put("dest_address_maps", currentLocationLatitudeLongitude);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postUploadCerPic(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
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
            mView.error(KJActivityStack.create().topActivity().getString(R.string.noData));
            return;
        }
        File oldFile = new File(path);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.imagePathError));
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
                mView.error(msg);
            }
        });
    }

}
