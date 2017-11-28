package com.ruitukeji.zwbs.common;

import android.os.Bundle;
import android.view.View;

import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.loginregister.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;


//import com.umeng.analytics.MobclickAgent;

//import org.kymjs.kjframe.KJActivity;

/**
 * 公用的父Activity
 * 防止除向其他共用时增加
 * Created by ruitu on 2016/9/13.
 */

public abstract class BaseActivity extends KJActivity implements LoadingDialogView {
    public Object mPresenter;
    private SweetAlertDialog mLoadingDialog = null;

    /**
     * 解决framenet重影
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //  super.onSaveInstanceState(outState);
    }

//    /**
//     * session的统计
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //   MobclickAgent.onResume(this);
//    }


    @SuppressWarnings("deprecation")
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(KJActivityStack.create().topActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.lonincolors));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
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

    public void toLigon(String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = getString(R.string.otherError);
        }
        if (msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            skipActivity(aty, LoginActivity.class);
            return;
        }
    }

    public boolean toLigon1(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return true;
        }
        if (msg.equals("" + NumericConstants.TOLINGIN)) {
            dismissLoadingDialog();
            skipActivity(aty, LoginActivity.class);
            return false;
        }
        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        RxVolley.getRequestQueue().cancelAll(KJActivityStack.create().getClass().getName());
        dismissLoadingDialog();
        //    MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoadingDialog = null;
        mPresenter = null;
    }
}
