package com.ruitukeji.zwbs.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.GlideImageLoader;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.MineBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.loginregister.NewUserInformationActivity;
import com.ruitukeji.zwbs.mine.abnormalrecords.AbnormalRecordsActivity;
import com.ruitukeji.zwbs.mine.aboutus.AboutUsActivity;
import com.ruitukeji.zwbs.mine.dialog.CustomerServiceTelephoneBouncedDialog;
import com.ruitukeji.zwbs.mine.identityauthentication.IdentityAuthenticationActivity;
import com.ruitukeji.zwbs.mine.mywallet.MyWalletActivity;
import com.ruitukeji.zwbs.mine.personalcertificate.PersonalCertificateActivity;
import com.ruitukeji.zwbs.mine.personaldata.PersonalDataActivity;
import com.ruitukeji.zwbs.mine.recommendcourteous.RecommendCourteousActivity;
import com.ruitukeji.zwbs.mine.settings.SettingsActivity;
import com.ruitukeji.zwbs.mine.vehiclecertification.VehicleCertificationActivity;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 个人中心
 * Created by Administrator on 2017/2/13.
 */

public class MineFragment extends BaseFragment implements MineContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, EasyPermissions.PermissionCallbacks {

    private MainActivity aty;

    private final int REQUEST_CODE_PERMISSION_CALL = 1;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_personalData, click = true)
    private LinearLayout ll_personalData;

    @BindView(id = R.id.tv_name)
    private TextView tv_name;

    @BindView(id = R.id.tv_licensePlateNumber)
    private TextView tv_licensePlateNumber;

    @BindView(id = R.id.img_avatar)
    private ImageView img_avatar;

    @BindView(id = R.id.tv_alwaysSingular)
    private TextView tv_alwaysSingular;

    @BindView(id = R.id.tv_driverLevel)
    private TextView tv_driverLevel;

    @BindView(id = R.id.tv_serviceLevel, click = true)
    private TextView tv_serviceLevel;

    @BindView(id = R.id.tv_complaintsNumber, click = true)
    private TextView tv_complaintsNumber;

    /**
     * 身份认证
     */
    @BindView(id = R.id.ll_identityAuthentication, click = true)
    private LinearLayout ll_identityAuthentication;
    @BindView(id = R.id.tv_identityAuthentication)
    private TextView tv_identityAuthentication;


    /**
     * 车辆认证
     */
    @BindView(id = R.id.ll_vehicleCertification, click = true)
    private LinearLayout ll_vehicleCertification;
    @BindView(id = R.id.tv_vehicleCertification)
    private TextView tv_vehicleCertification;


    /**
     * 我的钱包
     */
    @BindView(id = R.id.ll_myWallet, click = true)
    private LinearLayout ll_myWallet;
    @BindView(id = R.id.tv_money)
    private TextView tv_money;

    /**
     * 异常记录
     */
    @BindView(id = R.id.ll_abnormalRecords, click = true)
    private LinearLayout ll_abnormalRecords;

    /**
     * 分享有礼
     */
    @BindView(id = R.id.ll_sharePolite, click = true)
    private LinearLayout ll_sharePolite;

    /**
     * 客服电话
     */
    @BindView(id = R.id.ll_customerServiceTelephone, click = true)
    private LinearLayout ll_customerServiceTelephone;
    @BindView(id = R.id.tv_customerServiceTelephone)
    private TextView tv_customerServiceTelephone;

    /**
     * 关于我们
     */
    @BindView(id = R.id.ll_aboutUs, click = true)
    private LinearLayout ll_aboutUs;

    /**
     * 设置
     */
    @BindView(id = R.id.ll_settings, click = true)
    private LinearLayout ll_settings;
    private CustomerServiceTelephoneBouncedDialog customerServiceTelephoneBouncedDialog = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MinePresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        // ActivityTitleUtils.initToolbar(parentView, getString(R.string.mine), R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, getActivity(), false);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_personalData:
                ((MineContract.Presenter) mPresenter).isLogin(1);
                break;
            case R.id.ll_identityAuthentication:
                ((MineContract.Presenter) mPresenter).isLogin(6);
                break;
            case R.id.ll_vehicleCertification:
                ((MineContract.Presenter) mPresenter).isLogin(3);
                break;
            case R.id.ll_myWallet:
                ((MineContract.Presenter) mPresenter).isLogin(2);
                break;
            case R.id.ll_abnormalRecords:
                ((MineContract.Presenter) mPresenter).isLogin(7);
                break;
            case R.id.ll_sharePolite:
                ((MineContract.Presenter) mPresenter).isLogin(4);
                break;
            case R.id.ll_customerServiceTelephone:
                choiceCallWrapper(tv_customerServiceTelephone.getText().toString().trim());
                break;
            case R.id.ll_aboutUs:
                Intent intent = new Intent(aty, AboutUsActivity.class);
                intent.putExtra("type", "driver_about");
                aty.showActivity(aty, intent);
                break;
            case R.id.ll_settings:
                ((MineContract.Presenter) mPresenter).isLogin(5);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        boolean isRefreshAvatar = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshAvatar", false);
//        if (isRefreshAvatar) {
//            String avatar = PreferenceHelper.readString(aty, StringConstants.FILENAME, "avatar");
//            GlideImageLoader.glideLoader(aty, avatar, img_user, 0);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshAvatar", false);
//        }
//        boolean isRefreshInfo = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshInfo", false);
//        if (isRefreshInfo) {
//            mRefreshLayout.beginRefreshing();
//        }
    }


    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshInfo1", false);
            mRefreshLayout.setPullDownRefreshEnable(true);
            MineBean mineBean = (MineBean) JsonUtil.getInstance().json2Obj(s, MineBean.class);
            if (StringUtils.isEmpty(mineBean.getResult().getAvatar())) {
                GlideImageLoader.glideLoader(aty, R.mipmap.headload, img_avatar, 0);
            } else {
                GlideImageLoader.glideLoader(aty, mineBean.getResult().getAvatar() + "?imageView2/1/w/70/h/70", img_avatar, 0);
            }
            if (StringUtils.isEmpty(mineBean.getResult().getReal_name())) {
                tv_name.setText(mineBean.getResult().getPhone());
            } else {
                tv_name.setText(mineBean.getResult().getReal_name());
            }
            //   tv_status.setVisibility(View.VISIBLE);
//            if (mineBean.getResult().getAuth_status() == null || mineBean.getResult().getAuth_status().equals("init")) {
//                tv_status.setText(getString(R.string.unauthorized));
//            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("check")) {
//                tv_status.setText(getString(R.string.inAuthentication));
//            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("pass")) {
//                tv_status.setText(getString(R.string.pass));
//            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("refuse")) {
//                tv_status.setText(getString(R.string.authenticationFailure));
//            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("delete")) {
//                tv_status.setText(getString(R.string.delete));
//            }
            PreferenceHelper.write(aty, StringConstants.FILENAME, "id", mineBean.getResult().getId());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "phone", mineBean.getResult().getPhone());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", mineBean.getResult().getSex());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "avatar", mineBean.getResult().getAvatar());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "real_name", mineBean.getResult().getReal_name());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "auth_status", mineBean.getResult().getAuth_status());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "recomm_code", mineBean.getResult().getRecomm_code());
        } else if (flag == 1) {
            aty.showActivity(aty, PersonalDataActivity.class);
        } else if (flag == 2) {
            aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 3) {
            String auth_status = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "auth_status");
            if (auth_status != null && auth_status.equals("pass")) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "MineFragment");
                aty.showActivity(aty, PersonalCertificateActivity.class);
            } else if (auth_status != null && auth_status.equals("check")) {
                ViewInject.toast(getString(R.string.inAuthentication) + "," + getString(R.string.pleaseWait));
            } else {
                Intent newUserInformation = new Intent(aty, VehicleCertificationActivity.class);
                newUserInformation.putExtra("auth_status", auth_status);
                aty.showActivity(aty, newUserInformation);
            }
        } else if (flag == 4) {
            aty.showActivity(aty, RecommendCourteousActivity.class);
        } else if (flag == 5) {
            aty.showActivity(aty, SettingsActivity.class);
        } else if (flag == 6) {
            aty.showActivity(aty, IdentityAuthenticationActivity.class);
        } else if (flag == 7) {
            aty.showActivity(aty, AbnormalRecordsActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (flag == 0) {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                mRefreshLayout.setPullDownRefreshEnable(false);
                img_avatar.setImageResource(R.mipmap.avatar_default);
                tv_name.setText(getString(R.string.loginregister));
                tv_licensePlateNumber.setVisibility(View.GONE);
                tv_alwaysSingular.setText("0");
                tv_driverLevel.setText("0");
                tv_serviceLevel.setText("0");
                tv_complaintsNumber.setText("0");
                tv_identityAuthentication.setVisibility(View.INVISIBLE);
                tv_vehicleCertification.setVisibility(View.INVISIBLE);
                tv_money.setVisibility(View.INVISIBLE);
            } else {
                mRefreshLayout.setPullDownRefreshEnable(true);
            }
        } else if (flag == 1 || flag == 2 || flag == 3 || flag == 4 || flag == 5 || flag == 6 || flag == 7) {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                //      PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "MineFragment");
                //   Intent intent = new Intent(aty, LoginActivity.class);
                //   intent.putExtra("name", "MineFragment");
                aty.showActivity(aty, LoginActivity.class);
            } else {
                mRefreshLayout.setPullDownRefreshEnable(true);
            }
        }
        dismissLoadingDialog();

    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = presenter;
    }


    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
//        boolean isRefreshInfo1 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshInfo1", false);
//        if (isRefreshInfo1) {
//            mRefreshLayout.beginRefreshing();
//        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(MyApplication.getContext().getString(R.string.dataLoad));
        ((MineContract.Presenter) mPresenter).getInfo();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        mRefreshLayout.endLoadingMore();
        return false;
    }


    @AfterPermissionGranted(REQUEST_CODE_PERMISSION_CALL)
    private void choiceCallWrapper(String phone) {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(aty, perms)) {
            if (customerServiceTelephoneBouncedDialog == null) {
                customerServiceTelephoneBouncedDialog = new CustomerServiceTelephoneBouncedDialog(getActivity(), phone);
            }
            customerServiceTelephoneBouncedDialog.show();
        } else {
            EasyPermissions.requestPermissions(this, "拨打电话选择需要以下权限:\n\n访问设备上的电话拨打权限", REQUEST_CODE_PERMISSION_CALL, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == REQUEST_CODE_PERMISSION_CALL) {
            ViewInject.toast("您拒绝了「拨打电话」所需要的相关权限!");
        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusRefreshMineEvent")) {
            mRefreshLayout.beginRefreshing();
        }
    }
}
