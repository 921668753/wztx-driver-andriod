package com.ruitukeji.zwbs.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.ViewInject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.util.Map;

/**
 * Created by Administrator on 2017/2/17.
 */

public class PayUtils {

    private final Activity context;
    private final Activity activity;

    public PayUtils(Activity context, Activity activity) {
        this.context = context;
        this.activity = activity;

    }

    private static final int RQF_PAY = 1;


    private void doPay(String orderParam) {
        final String payInfo = orderParam;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(context);
                // 调用支付接口
                Map<String, String> result = alipay.payV2(payInfo, true);
                Message msg = new Message();
                msg.what = RQF_PAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            String result = msg.obj.toString();
            if (!StringUtils.isEmpty(result)) {
                if (result.equals("9000")) {// 操作成功
//                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_succeed));
                    context.startActivity(new Intent(context, activity.getClass()));
                } else if (result.equals("4000")) {// 系统异常
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_system_exception));
                } else if (result.equals("4001")) {// 数据格式不正确
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_system_exception));
                } else if (result.equals("4003")) {// 该用户绑定的支付宝账户被冻结或不允许支付
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_account_exception));
                } else if (result.equals("4004")) {// 该用户已解除绑定
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_has_unbound));
                } else if (result.equals("4005")) {// 绑定失败或没有绑定
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_system_exception));
                } else if (result.equals("4006")) {// 订单支付失败
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_order_error));
                } else if (result.equals("4010")) {// 重新绑定账户
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_system_exception));
                } else if (result.equals("6000")) {// 支付服务正在进行升级操作
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_system_exception));
                } else if (result.equals("6001")) {// 用户中途取消支付操作
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_order_cancel));
                } else if (result.equals("7001")) {// 网页支付失败
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_order_error));
                } else if (result.equals("8000")) {// 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                    ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_system_exception));
                } else {
                    ViewInject.toast(MyApplication.getContext().getString(R.string.pay_error));
                }
            } else {
                ViewInject.toast(MyApplication.getContext().getString(R.string.alipay_system_exception));
            }
        }
    };

    private void doPayment(String appId, String partnerId, String prepayId, String packages, String nonceStr, String timeStamp, String paySign) {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(context, appId);
        if (msgApi.isWXAppInstalled() && msgApi.isWXAppSupportAPI()) {
            msgApi.registerApp(appId);
            PayReq request = new PayReq();
            request.appId = appId;
            request.partnerId = partnerId;
            request.prepayId = prepayId;
            request.packageValue = packages;
            request.nonceStr = nonceStr;
            request.timeStamp = timeStamp;
            request.sign = paySign;
            msgApi.sendReq(request);
        } else {
            ViewInject.toast(MyApplication.getContext().getString(R.string.wxappinstalled));
        }
    }


}
