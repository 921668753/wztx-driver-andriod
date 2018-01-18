package com.ruitukeji.zwbs.retrofit;

import android.content.Context;

import com.kymjs.common.FileUtils;
import com.kymjs.common.Log;
import com.kymjs.common.NetworkUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.http.VolleyError;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.constant.URLConstants;
import com.ruitukeji.zwbs.entity.BaseResult;
import com.ruitukeji.zwbs.entity.loginregister.LoginBean;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpRequest;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.ArrayList;
import java.util.List;

import static com.ruitukeji.zwbs.utils.httputil.HttpRequest.doFailure;
import static com.ruitukeji.zwbs.utils.httputil.HttpRequest.doSuccess;


/**
 * Created by ruitu on 2016/9/17.
 */

public class RequestClient {
    /**
     * @param httpParams 上传图片
     */
    public static void upLoadImg(HttpParams httpParams, int type, final ResponseListener<String> listener) {
//        for (int i = 0; i < files.size(); i++) {
//            File file = new File(files.get(i));
//            params.put("file" + i, file);
//        }
//        httpParams.putHeaders("Content-Type", "application/x-www-form-urlencoded");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                if (type == 0) {
                    HttpRequest.requestPostFORMHttp(URLConstants.UPLOADAVATAR, httpParams, listener);
                } else {
                    HttpRequest.requestPostFORMHttp(URLConstants.UPLOADQFCTIMG, httpParams, listener);
                }
            }
        }, listener);
    }

    /**
     * 刷新Token
     */
    public static void doRefreshToken(String refreshToken, TokenCallback callback, ResponseListener listener) {
        Log.d("tag", "doRefreshToken");
        Context context = KJActivityStack.create().topActivity();
        HttpParams params = HttpUtilParams.getInstance().getHttpParams();
        params.put("refreshToken", refreshToken);
        RxVolley.get(URLConstants.REFRESHTOKEN, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                BaseResult baseResult = (BaseResult) JsonUtil.getInstance().json2Obj(t, BaseResult.class);
                if (baseResult.getCode() != NumericConstants.SUCCESS) {
                    unDoList.clear();
                    isRefresh = false;
                    PreferenceHelper.write(context, StringConstants.FILENAME, "userId", 0);
                    PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", "");
                    PreferenceHelper.write(context, StringConstants.FILENAME, "refreshToken", "");
                    PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", "0");
                    PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", "0");
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                LoginBean response1 = (LoginBean) JsonUtil.getInstance().json2Obj(t, LoginBean.class);
//                PreferenceHelper.write(context, StringConstants.FILENAME, "userId", response1.getResult().getUserId());
                PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", response1.getResult().getAccessToken());
                PreferenceHelper.write(context, StringConstants.FILENAME, "refreshToken", response1.getResult().getRefreshToken());
                PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", response1.getResult().getExpireTime() + "");
                PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
                for (int i = 0; i < unDoList.size(); i++) {
                    unDoList.get(i).execute();
                }
                unDoList.clear();
                isRefresh = false;
                callback.execute();
            }

            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
                Log.d("tag", "onSuccess");
                unDoList.clear();
                isRefresh = false;
                PreferenceHelper.write(context, StringConstants.FILENAME, "userId", 0);
                PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", "");
                PreferenceHelper.write(context, StringConstants.FILENAME, "refreshToken", "");
                PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", "0");
                PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", "0");
                listener.onFailure(NumericConstants.TOLINGIN + "");
            }
        });
    }


    /**
     * 应用配置参数
     */
    public static void getAppConfig(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.APPCONFIG, httpParams, listener);
    }

    /**
     * 轮播图接口
     */
    public static void getBanners(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.BANNERS, httpParams, listener);
    }

    /**
     * 登录
     */
    public static void postLogin(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostHttp(URLConstants.MEMBERLOGIN, httpParams, listener);
    }

    /**
     * 第三方登录
     */
    public static void postThirdLogin(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostHttp(URLConstants.THIRDLOGIN, httpParams, listener);
    }

    /**
     * 第三方登录绑定手机号
     */
    public static void postThirdLoginAdd(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostHttp(URLConstants.THIRDLOGINADD, httpParams, listener);
    }

    /**
     * 发送验证码
     */
    public static void postCaptcha(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostHttp(URLConstants.SENDCAPTCHA, httpParams, listener);
    }

    /**
     * 注册
     */
    public static void postRegister(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostHttp(URLConstants.MEMBERREGISTER, httpParams, listener);
    }

    /**
     * 得到全部城市
     */
    public static void getAllCity(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.ALLCITY, httpParams, listener);
    }

    /**
     * 得到热门城市
     */
    public static void getHotCity(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.ALLHOTCITY, httpParams, listener);
    }

    /**
     * 获取文章内容
     */
    public static void getArticle(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.GETARTICLE, httpParams, listener);
    }


    /**
     * 新用户注册----基本信息
     */
    public static void postInformation(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postInformation");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.DRIVERAUTH, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 个人中心----车辆认证
     */
    public static void postVehicleCertification(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postVehicleCertification");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.CARAUTH, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 个人中心----车辆认证----获取车辆认证信息
     */
    public static void getCarAuthInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getCarAuthInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GETCARAUTHINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取车长车型
     */
    public static void getConductorModels(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.GETALLCARSTYLE, httpParams, listener);
    }

    /**
     * 重置密码
     */
    public static void postResetpwd(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostHttp(URLConstants.MEMBERRESETPWD, httpParams, listener);
    }


    /**
     * 获取未读消息数量
     */
    public static void getUnRead(HttpParams httpParams, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        if (!StringUtils.isEmpty(accessToken)) {
            httpParams.putHeaders("authorization-token", accessToken);
        }
        HttpRequest.requestGetHttp(URLConstants.GETUNREAD, httpParams, listener);
    }

    /**
     * 获取消息列表
     */
    public static void getMessage(HttpParams httpParams, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        if (!StringUtils.isEmpty(accessToken)) {
            httpParams.putHeaders("authorization-token", accessToken);
        }
        HttpRequest.requestGetHttp(URLConstants.MESSAGE, httpParams, listener);
    }

    /**
     * 删除消息
     */
    public static void postDeleteMessage(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.DELMESSAGE, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取消息详情
     */
    public static void getMessageDetails(HttpParams httpParams, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        if (!StringUtils.isEmpty(accessToken)) {
            httpParams.putHeaders("authorization-token", accessToken);
        }
        HttpRequest.requestGetHttp(URLConstants.MESSAGEDETAIL, httpParams, listener);
    }


    /**
     * 标记已读消息
     */
    public static void postReadMessage(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    // PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoneBanner", false);
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.READMESSAGE, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 改变工作状态
     */
    public static void postWorkingState(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postWorkingState");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.CHANGEWORK, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取当前工作状态
     */
    public static void getWorkingState(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getWorkingState");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.ISWORK, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获得订单报价列表
     */
    public static void getQuoteList(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getQuoteList");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.QUOTELIST, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 得到国内全部城市
     */
    public static void getAllCityInHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        //  HttpRequest.requestGetHttp(URLConstants.ALLCITY, httpParams, true, listener);
    }

    /**
     * 得到全部城市
     */
    public static void getAllCityByCountryId(HttpParams httpParams, int countryId, final ResponseListener<String> listener) {
        if (countryId == 0) {
            //  HttpRequest.requestGetHttp(URLConstants.GETALLCOUNTRYCITY, httpParams, true, listener);
        } else {
            //  HttpRequest.requestGetHttp(URLConstants.GETALLCITYBYCOUNTRY + "&countryId=" + countryId, httpParams, true, listener);
        }
    }

    /**
     * 得到热门城市
     */
    public static void getHotCityByCountryId(HttpParams httpParams, int countryId, final ResponseListener<String> listener) {
        if (countryId == 0) {
            //   HttpRequest.requestGetHttp(URLConstants.GETHOTCITYBYCOUNTRY, httpParams, true, listener);
        } else {
            //  HttpRequest.requestGetHttp(URLConstants.GETHOTCITYBYCOUNTRY + "&countryId=" + countryId, httpParams, true, listener);
        }
    }

    /**
     * 获得报价信息
     */
    public static void getQuoteInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getQuoteInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.QUOTEGETINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 提交司机报价
     */
    public static void getQuoteAdd(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getQuoteAdd");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.QUOTEADD, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 司机进行发货动作
     */
    public static void postShipping(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postShipping");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.SHHIPPING, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取货源列表
     */
    public static void getGoodsList(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getGoodsList");
        HttpRequest.requestGetHttp(URLConstants.GOODLIST, httpParams, listener);
    }

    /**
     * 获取订单列表
     */
    public static void getOrder(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOrder");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.ORDERLISTINFO, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 上传到货凭证
     */
    public static void postUploadCerPic(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postUploadCerPic");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.UPLOADCERPIC, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取订单详情
     */
    public static void getOrderDetails(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOrderDetails");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.ORDERDETAIL, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取货源详情
     */
    public static void getGoodDetails(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getGoodDetails");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GOODSDETAIL, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取评论详情
     */
    public static void getEvaluationShare(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getEvaluationShare");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.COMMENTINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取路线信息列表
     */
    public static void getShowLine(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getShowLine");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.SHOWLINE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 删除路线信息
     */
    public static void postDelLine(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postDelLine");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.DELLINE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 添加路线信息
     */
    public static void postSetLine(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postSetLine");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.SETLINE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 创建订单轨迹信息
     */
    public static void postTrackOrders(HttpParams httpParams, final ResponseListener<String> listener) {
        RxVolley.post(URLConstants.TABLEIDTRACK, httpParams, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                listener.onSuccess(t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                doFailure(errorNo, strMsg, listener);
            }
        });
    }

    /**
     * 更新司机当前位置信息
     */
    public static void postDriverLocation(HttpParams httpParams, final ResponseListener<String> listener) {
//        HttpRequest.requestPostFORMHttp(URLConstants.UPLOADAVATAR, httpParams, listener);
        RxVolley.post(URLConstants.TABLEIUPDATE, httpParams, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                listener.onSuccess(t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                doFailure(errorNo, strMsg, listener);
            }
        });
    }


    /**
     * 获取用户信息
     */
    public static void getInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.MEMBERINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 个人中心----获取异常信息
     */
    public static void getAbnormal(HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postDriverAuth");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GRTABNORMAL, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 个人中心----获取异常信息详情
     */
    public static void getAbnormalSituation(HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postDriverAuth");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GRTABNORMALSITUATION, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 个人中心----提交身份认证信息
     */
    public static void postDriverAuth(HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postDriverAuth");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.DRIVERAUTH, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取司机认证信息
     */
    public static void getDriverAuthInfo(HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getDriverAuthInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GETDRIVERAUTHINFO, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 我的钱包
     */
    public static void getMyWallet(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.PAY, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 收入明细
     */
    public static void getIncomeDetails(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.INCOMEDETAILS, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 充值
     */
    public static void postRecharge(HttpParams httpParams, int type, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    //       PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoneBanner", false);
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                if (type == 1) {
                    HttpRequest.requestPostHttp(URLConstants.RECHARGEBYALIPAY, httpParams, listener);
                } else if (type == 2) {
                    HttpRequest.requestPostHttp(URLConstants.RECHARGEBYWEXIN, httpParams, listener);
                }
            }
        }, listener);
    }

    /**
     * 充值记录
     */
    public static void getRechargeRecord(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    //        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoneBanner", false);
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.RECHARGERECORD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 提现记录
     */
    public static void showCashRecord(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.SHOWCASHRECORD, httpParams, listener);
            }
        }, listener);

    }


    /**
     * 获取个人银行卡信息
     */
    public static void getMyBankCard(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.MYBANKCARD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 添加银行卡
     */
    public static void postAddBankCard(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.ADDBANKCARD, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 设置支付密码
     */
    public static void postSetPayPassword(HttpParams httpParams, ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.SETPAYPASSEORD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 修改密码校验
     */
    public static void getCheckPayPassword(HttpParams httpParams, ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.CHECKPAYPASSEORD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获得银行
     */
    public static void getBank(HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.GETBANK, httpParams, listener);
    }

    /**
     * 我的报价
     */
    public static void getMyQuote(HttpParams httpParams, ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.MYQUOTELIST, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 查看账单
     */
    public static void getPayRecord(HttpParams httpParams, ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.SHOWPAYRECORD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 余额提现
     */
    public static void postWithdrawal(HttpParams httpParams, ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.WITHDRAWAL, httpParams, listener);
            }
        }, listener);

    }


    /**
     * 显示我的推荐列表
     */
    public static void showMyRecommList(HttpParams httpParams, ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.SHOWMYRECOMMLIST, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 帮助中心
     */
    public static void getHelpCenter(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.HELPCENTER, httpParams, listener);
    }


    /**
     * 帮助中心详情
     */
    public static void getHelpCenterDetails(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.HELPCENTERDETAIL, httpParams, listener);
    }

    /**
     * 修改密码
     */
    public static void postChangePassword(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostHttp(URLConstants.UPDATEPWD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 下载App
     */
    @SuppressWarnings("unchecked")
    public static void downloadApp(String updateAppUrl, ProgressListener progressListener, final ResponseListener<String> listener) {
        RxVolley.download(FileUtils.getSaveFolder(StringConstants.DOWNLOADPATH).getAbsolutePath() + "/WZTXS.apk", updateAppUrl, progressListener, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                BaseResult result = new BaseResult();
                result.setResult(FileUtils.getSaveFolder(StringConstants.DOWNLOADPATH).getAbsolutePath() + "/WZTXS.apk");
                result.setMsg("SUCCESS");
                result.setCode(NumericConstants.SUCCESS);
                doSuccess(JsonUtil.getInstance().obj2JsonString(result), listener);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.d(errorNo + "====failure" + strMsg);
                doFailure(errorNo, strMsg, listener);
            }
        });
    }


    /**
     * 是否登录
     */
    public static void isLogin(final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                listener.onSuccess("");
            }
        }, listener);
    }

    /**
     * 刷新token回调
     */
    public static boolean isRefresh = false;

    public static void doServer(final TokenCallback callback, ResponseListener listener) {
        final Context context = KJActivityStack.create().topActivity();
        if (!NetworkUtils.isNetWorkAvailable(context)) {
            doFailure(-1, "NetWork err", listener);
            return;
        }
        Log.d("tag", "isNetWorkAvailable" + true);
        String accessToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken", "");
        if (StringUtils.isEmpty(accessToken)) {
            Log.d("tag", "onFailure");
            PreferenceHelper.write(context, StringConstants.FILENAME, "id", 0);
            PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", "");
            PreferenceHelper.write(context, StringConstants.FILENAME, "refreshToken", "");
            PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", "0");
            PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", "0");
            listener.onFailure(NumericConstants.TOLINGIN + "");
            return;
        }
        long nowTime = System.currentTimeMillis();
//        String timebefore = PreferenceHelper.readString(context, StringConstants.FILENAME, "timebefore", "0");
//        long timebefore1 = 0;
//        if (StringUtils.isEmpty(timebefore)) {
//            timebefore1 = 0;
//        } else {
//            timebefore1 = Long.decode(timebefore);
//        }
        String expireTime = PreferenceHelper.readString(context, StringConstants.FILENAME, "expireTime");
        long expireTime1 = 0;
        if (StringUtils.isEmpty(expireTime)) {
            expireTime1 = 0;
        } else {
            expireTime1 = Long.decode(expireTime);
        }
        long refreshTime = nowTime - expireTime1 * 1000 - 2000;
        if (refreshTime >= 0) {
            if (isRefresh) {
                unDoList.add(callback);
                return;
            }
            isRefresh = true;
            String refreshToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "refreshToken");
            doRefreshToken(refreshToken, callback, listener);
        } else {
            Log.d("tag", "onSuccess");
            callback.execute();
        }
    }

    public interface TokenCallback {
        void execute();
    }

    private static List<TokenCallback> unDoList = new ArrayList<>();

}
