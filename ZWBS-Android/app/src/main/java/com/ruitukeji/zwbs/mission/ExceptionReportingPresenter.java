package com.ruitukeji.zwbs.mission;

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
 * Created by Administrator on 2017/2/19.
 */

public class ExceptionReportingPresenter implements ExceptionReportingContract.Presenter {

    private ExceptionReportingContract.View mView;

    public ExceptionReportingPresenter(ExceptionReportingContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postAbnormalInsert(int goods_id, ArrayList<ImageItem> imgList, String place, String content) {
        if (imgList == null || imgList.size() < 1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData1), 0);
            return;
        }
        if (StringUtils.isEmpty(content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseFillOut) + KJActivityStack.create().topActivity().getString(R.string.abnormalReason), 0);
            return;
        }
        String img_url = "";
        for (int i = 0; i < imgList.size(); i++) {
            img_url += imgList.get(i).path + "|";
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goods_id", goods_id);
        map.put("img", img_url.substring(0, img_url.length() - 1));
        map.put("place", place);
        map.put("content", content);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.getTask(httpParams, new ResponseListener<String>() {
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
