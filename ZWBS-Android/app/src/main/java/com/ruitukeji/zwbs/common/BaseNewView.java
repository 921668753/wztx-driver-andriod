package com.ruitukeji.zwbs.common;

/**
 * Created by Administrator on 2017/11/27.
 */

public interface BaseNewView<T, E> extends LoadingDialogView {

    @SuppressWarnings("unchecked")
    void setPresenter(T presenter);

    /**
     * http请求正确
     *
     * @param success 成功的信息
     * @param flag    用于区别请求
     */
    @SuppressWarnings("unchecked")
    void getSuccess(E success, int flag);

    /**
     * http请求错误
     */
    @SuppressWarnings("unchecked")
    void errorMsg(E msg, int flag);

}