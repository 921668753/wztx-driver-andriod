package com.ruitukeji.zwbs.common;

import android.content.Intent;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;
import com.ruitukeji.zwbs.utils.rx.RxManager;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 公用的父Fragment
 * 防止除向其他共用时增加
 * Created by ruitu on 2016/9/14.
 */

public abstract class BaseFragment extends KJFragment implements LoadingDialogView {

    public Object mPresenter = null;
    public Subscription subscription = null;
    private SweetAlertDialog mLoadingDialog;


    /**
     * 必须此处创建订阅者 Subscription subscription
     */
    @Override
    protected void initData() {
        super.initData();
        subscription = RxBus.getInstance().register(MsgEvent.class).subscribe(new Action1<MsgEvent>() {
            @Override
            public void call(MsgEvent msgEvent) {
                callMsgEvent(msgEvent);
            }
        });
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if (subscription != null && !subscription.isUnsubscribed()) {
            RxManager.get().add(this.getClass().getName(), subscription);
        }
    }


    @SuppressWarnings("deprecation")
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.lonincolors));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText(title);
        }
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
    }


    @Override
    public void onPause() {
        super.onPause();
        dismissLoadingDialog();
        //    MobclickAgent.onPause(this);
    }

    public void toLigon(String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = getString(R.string.otherError);
        }
        if (msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
            return;
        }
    }

    public void callMsgEvent(MsgEvent msgEvent) {

    }

    @Override
    public void onDestroy() {
        RxVolley.getRequestQueue().cancelAll(KJActivityStack.create().getClass().getName());
        RxManager.get().cancel(this.getClass().getName());
        super.onDestroy();
        subscription = null;
        mLoadingDialog = null;
        mPresenter = null;
    }
}
