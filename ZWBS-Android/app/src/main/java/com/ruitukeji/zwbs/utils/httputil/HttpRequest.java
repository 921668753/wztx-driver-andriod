package com.ruitukeji.zwbs.utils.httputil;

import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.BaseResult;
import com.ruitukeji.zwbs.utils.JsonUtil;

/**
 * Created by Administrator on 2017/3/8.
 */

public class HttpRequest {
    private static RxVolley.Builder builder = null;

    public static void requestHttp(String url, int httpMethod, int contentType, HttpParams params, boolean isCache, ResponseListener responseListener) {
        if (builder == null) {
            builder = new RxVolley.Builder();
        }
        //http请求的回调，内置了很多方法，详细请查看源码
//包括在异步响应的onSuccessInAsync():注不能做UI操作
//网络请求成功时的回调onSuccess()
//网络请求失败时的回调onFailure():例如无网络，服务器异常等
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                builder = null;
                doSuccess(t, responseListener);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                builder = null;
                doFailure(errorNo, strMsg, responseListener);
            }
        };
        builder.url(url) //接口地址
                //请求类型，如果不加，默认为 GET 可选项：
                //POST/PUT/DELETE/HEAD/OPTIONS/TRACE/PATCH
                .httpMethod(httpMethod)
                .timeout(10 * 60 * 1000)
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
//                .cacheTime(1)
                //内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
                .contentType(contentType)
                .params(params) //上文创建的HttpParams请求参数集
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(false)
                .setTag(KJActivityStack.create().getClass().getName())
                //    .progressListener(listener) //上传进度
                .callback(callback) //响应回调
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }

    /**
     * get请求
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestGetHttp(String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, RxVolley.Method.GET, RxVolley.ContentType.FORM, params, true, responseListener);
    }

    /**
     * postjosn  请求
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestPostHttp(String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, RxVolley.Method.POST, RxVolley.ContentType.JSON, params, true, responseListener);
    }

    public static void requestPostFORMHttp(String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, RxVolley.Method.POST, RxVolley.ContentType.FORM, params, true, responseListener);
    }

    /**
     * PUTjosn请求
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestPutHttp(String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, RxVolley.Method.PUT, RxVolley.ContentType.FORM, params, true, responseListener);
    }

    /**
     * DELETEjosn请求
     *
     * @param url
     * @param params
     * @param responseListener
     */
    public static void requestDeleteHttp(String url, HttpParams params, ResponseListener responseListener) {
        requestHttp(url, RxVolley.Method.DELETE, RxVolley.ContentType.FORM, params, true, responseListener);
    }

    /**
     * 网络请求成功
     *
     * @param s
     * @param listener
     */
    @SuppressWarnings("unchecked")
    public static boolean doSuccess(String s, ResponseListener listener) {
        BaseResult baseResult = (BaseResult) JsonUtil.getInstance().json2Obj(s, BaseResult.class);

        if (baseResult == null) {
            listener.onFailure(KJActivityStack.create().bottomActivity().getString(R.string.jsonError));
            return false;
        }

        if (baseResult.getCode() != NumericConstants.SUCCESS) {
            if (baseResult.getCode() == 4011 || baseResult.getCode() == 4012 || baseResult.getCode() == 4013 || baseResult.getCode() == 4015) {
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "id", 0);
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken", "");
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "refreshToken", "");
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "expireTime", "0");
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "timeBefore", "0");
                listener.onFailure(NumericConstants.TOLINGIN + "");
                return false;
            }
            listener.onFailure(baseResult.getMsg());
            return false;
        }

//        if (baseResult.getResult() == null || JsonUtil.obj2JsonString(baseResult.getResult()) == null || JsonUtil.obj2JsonString(baseResult.getResult()).length() <= 2) {
//            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.serverReturnsDataNull));
//            return false;
//        }
        listener.onSuccess(s);
        return true;
    }

    /**
     * 接口返回失败信息处理
     *
     * @param errorMsg
     * @param listener
     */
    public static void doFailure(int errCode, String errorMsg, ResponseListener listener) {
        Log.d("tag", errCode + "错误原因：" + errorMsg);
        if (errCode == -1) {
            if (StringUtils.isEmpty(errorMsg)) {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.clientError));
            } else if (errorMsg.contains("java.lang.IllegalArgumentException")) {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.illegalArgumentException));
            } else if (errorMsg.contains("NetWork err") || errorMsg.contains("NoConnection error")) {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.checkNetwork));
            } else {
                listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.clientError));
            }
        } else if (errCode == NumericConstants.TOLINGIN) {
            listener.onFailure(NumericConstants.TOLINGIN + "");
        } else if (errCode == 400) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.grammarError));
        } else if (errCode == 401 || errCode == 407) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.authenticationError));
        } else if (errCode == 403) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.serverRejectsRequest));
        } else if (errCode == 404) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.urlError));
        } else if (errCode > 404 && errCode < 500) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.requestError));
        } else if (errCode >= 500) {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.serverError));
        } else {
            listener.onFailure(KJActivityStack.create().topActivity().getString(R.string.otherError));
        }
    }
}
