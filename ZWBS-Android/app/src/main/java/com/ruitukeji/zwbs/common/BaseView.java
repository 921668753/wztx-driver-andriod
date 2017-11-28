package com.ruitukeji.zwbs.common;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface BaseView<T> extends LoadingDialogView {

    void setPresenter(T presenter);

    /**
     * http请求错误
     */
    void error(String msg);
}