package com.ruitukeji.zwbs.mine.setting;

import com.kymjs.rxvolley.client.ProgressListener;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.MathUtil;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

/**
 * Created by Administrator on 2017/2/11.
 */

public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View mView;

    public SettingsPresenter(SettingsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getUpdateApp() {

    }

    /**
     * @param updateAppUrl 下载app
     */
    @Override
    public void downloadApp(String updateAppUrl) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download));
        ProgressListener progressListener = new ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                String size = MathUtil.keepZero(((double) transferredBytes) * 100 / totalSize) + "%";
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download) + size);
            }
        };
        RequestClient.downloadApp(updateAppUrl, progressListener, new ResponseListener<String>() {
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

    @Override
    public void isLogin(int flag) {
        RequestClient.isLogin(new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }


}
