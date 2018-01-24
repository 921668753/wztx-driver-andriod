package com.ruitukeji.zwbs.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
import com.ruitukeji.zwbs.entity.main.MineBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.mine.dialog.InAuthenticationBouncedDialog;
import com.ruitukeji.zwbs.mine.abnormalrecords.AbnormalRecordsActivity;
import com.ruitukeji.zwbs.mine.helpcenter.HelpCenterActivity;
import com.ruitukeji.zwbs.mine.dialog.CustomerServiceTelephoneBouncedDialog;
import com.ruitukeji.zwbs.mine.identityauthentication.IdentityAuthenticationActivity;
import com.ruitukeji.zwbs.mine.mywallet.MyWalletActivity;
import com.ruitukeji.zwbs.mine.personaldata.PersonalDataActivity;
import com.ruitukeji.zwbs.mine.recommendcourteous.RecommendCourteousActivity;
import com.ruitukeji.zwbs.mine.setting.SettingsActivity;
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

@SuppressLint("NewApi")
public class MineFragment extends BaseFragment implements MineContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, View.OnScrollChangeListener, EasyPermissions.PermissionCallbacks {

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


    @BindView(id = R.id.ll_personalData1, click = true)
    private LinearLayout ll_personalData1;

    @BindView(id = R.id.tv_name1)
    private TextView tv_name1;

    @BindView(id = R.id.tv_licensePlateNumber1)
    private TextView tv_licensePlateNumber1;

    @BindView(id = R.id.img_avatar1)
    private ImageView img_avatar1;

    @BindView(id = R.id.tv_alwaysSingular1)
    private TextView tv_alwaysSingular1;

    @BindView(id = R.id.tv_driverLevel1)
    private TextView tv_driverLevel1;

    @BindView(id = R.id.tv_serviceLevel)
    private TextView tv_serviceLevel;

    @BindView(id = R.id.tv_serviceLevel1)
    private TextView tv_serviceLevel1;

    @BindView(id = R.id.tv_complaintsNumber)
    private TextView tv_complaintsNumber;

    @BindView(id = R.id.tv_complaintsNumber1)
    private TextView tv_complaintsNumber1;

    @BindView(id = R.id.sv_mine)
    private ScrollView sv_mine;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;
    @BindView(id = R.id.ll_divider1)
    private LinearLayout ll_divider1;

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
     * 帮助中心
     */
    @BindView(id = R.id.ll_helpCenter, click = true)
    private LinearLayout ll_helpCenter;

    /**
     * 设置
     */
    @BindView(id = R.id.ll_settings, click = true)
    private LinearLayout ll_settings;
    private CustomerServiceTelephoneBouncedDialog customerServiceTelephoneBouncedDialog = null;
    private Handler handler = null;
    private InAuthenticationBouncedDialog inAuthenticationBouncedDialog = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine, null);
    }

    @Override
    protected void initData() {
        super.initData();
        handler = new Handler();
        mPresenter = new MinePresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        // ActivityTitleUtils.initToolbar(parentView, getString(R.string.mine), R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, getActivity(), false);
        sv_mine.setOnScrollChangeListener(this);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int height = dm.heightPixels;
        if (height >= sv_mine.getHeight() + 49) {
            tv_divider.setVisibility(View.GONE);
            ll_personalData1.setVisibility(View.GONE);
            ll_divider1.setVisibility(View.GONE);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sv_mine.scrollTo(0, 2);
                mRefreshLayout.beginRefreshing();
            }
        }, 400);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_personalData:
                ((MineContract.Presenter) mPresenter).isLogin(1);
                break;
            case R.id.ll_personalData1:
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
            case R.id.ll_helpCenter:
                aty.showActivity(aty, HelpCenterActivity.class);
                break;
            case R.id.ll_settings:
                getSuccess("", 5);
                break;
        }
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
                GlideImageLoader.glideLoader(aty, R.mipmap.headload, img_avatar1, 0);
            } else {
                GlideImageLoader.glideLoader(aty, mineBean.getResult().getAvatar() + "?imageView2/1/w/70/h/70", img_avatar, 0);
                GlideImageLoader.glideLoader(aty, mineBean.getResult().getAvatar() + "?imageView2/1/w/70/h/70", img_avatar1, 0);
            }
            if (StringUtils.isEmpty(mineBean.getResult().getReal_name())) {
                tv_name.setText(mineBean.getResult().getPhone());
                tv_name1.setText(mineBean.getResult().getPhone());
            } else {
                tv_name.setText(mineBean.getResult().getReal_name());
                tv_name1.setText(mineBean.getResult().getReal_name());
            }
            if (StringUtils.isEmpty(mineBean.getResult().getCard_number())) {
                tv_licensePlateNumber.setVisibility(View.GONE);
                tv_licensePlateNumber1.setVisibility(View.GONE);
            } else {
                tv_licensePlateNumber.setText(getString(R.string.licenseNumber2) + mineBean.getResult().getCard_number());
                tv_licensePlateNumber1.setText(getString(R.string.licenseNumber2) + mineBean.getResult().getCard_number());
            }
            tv_alwaysSingular.setText(mineBean.getResult().getAll_total_order());
            tv_alwaysSingular1.setText(mineBean.getResult().getAll_total_order());
            tv_driverLevel.setText(mineBean.getResult().getDr_level());
            tv_driverLevel1.setText(mineBean.getResult().getDr_level());
            tv_serviceLevel.setText(mineBean.getResult().getServer_level());
            tv_serviceLevel1.setText(mineBean.getResult().getServer_level());
            tv_complaintsNumber.setText(mineBean.getResult().getAll_complaints_num());
            tv_complaintsNumber1.setText(mineBean.getResult().getAll_complaints_num());

            tv_identityAuthentication.setVisibility(View.VISIBLE);
            String auth_status = mineBean.getResult().getAuth_status();
            if (auth_status == null || auth_status.equals("init")) {
                tv_identityAuthentication.setText(getString(R.string.unauthorized));
            } else if (auth_status != null && auth_status.equals("check")) {
                tv_identityAuthentication.setText(getString(R.string.inAuthentication));
            } else if (auth_status != null && auth_status.equals("pass")) {
                tv_identityAuthentication.setText(getString(R.string.pass));
            } else if (auth_status != null && auth_status.equals("refuse")) {
                tv_identityAuthentication.setText(getString(R.string.authenticationFailure));
            } else {
//                tv_identityAuthentication.setText(getString(R.string.delete));
                tv_identityAuthentication.setText(getString(R.string.unauthorized));
            }
            tv_vehicleCertification.setVisibility(View.VISIBLE);
            String car_auth_status = mineBean.getResult().getCar_auth_status();
            if (car_auth_status == null || car_auth_status.equals("init")) {
                tv_vehicleCertification.setText(getString(R.string.unauthorized));
            } else if (car_auth_status != null && car_auth_status.equals("check")) {
                tv_vehicleCertification.setText(getString(R.string.inAuthentication));
            } else if (car_auth_status != null && car_auth_status.equals("pass")) {
                tv_vehicleCertification.setText(getString(R.string.pass));
            } else if (car_auth_status != null && car_auth_status.equals("refuse")) {
                tv_vehicleCertification.setText(getString(R.string.authenticationFailure));
            } else {
//                tv_identityAuthentication.setText(getString(R.string.delete));
                tv_vehicleCertification.setText(getString(R.string.unauthorized));
            }
            if (StringUtils.isEmpty(mineBean.getResult().getBalance()) || mineBean.getResult().getBalance().equals("0.00")) {
                tv_money.setVisibility(View.INVISIBLE);
            } else {
                tv_money.setVisibility(View.VISIBLE);
                tv_money.setText(mineBean.getResult().getBalance());
            }
            PreferenceHelper.write(aty, StringConstants.FILENAME, "id", mineBean.getResult().getId());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "phone", mineBean.getResult().getPhone());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", mineBean.getResult().getSex());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "avatar", mineBean.getResult().getAvatar());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "real_name", mineBean.getResult().getReal_name());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "auth_status", auth_status);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "car_auth_status", mineBean.getResult().getCar_auth_status());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "recomm_code", mineBean.getResult().getRecomm_code());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "bond_status", mineBean.getResult().getBond_status());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "card_id", mineBean.getResult().getCar_id());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "card_number", mineBean.getResult().getCard_number());
            if (!StringUtils.isEmpty(mineBean.getResult().getCard_number())) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "province_short", mineBean.getResult().getCard_number().substring(0, 1));
            } else {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "province_short", "");
            }
            PreferenceHelper.write(aty, StringConstants.FILENAME, "online", mineBean.getResult().getOnline());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "map_code", mineBean.getResult().getMap_code());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "is_pay_password", mineBean.getResult().getIs_pay_password());
        } else if (flag == 1) {
            aty.showActivity(aty, PersonalDataActivity.class);
        } else if (flag == 2) {
            aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 3) {
            String car_auth_status = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "car_auth_status");
            if (car_auth_status != null && car_auth_status.equals("pass")) {
                aty.showActivity(aty, VehicleCertificationActivity.class);
            } else if (car_auth_status != null && car_auth_status.equals("check")) {
                inAuthenticationBouncedDialog();
            } else {
                aty.showActivity(aty, VehicleCertificationActivity.class);
            }
        } else if (flag == 4) {
            aty.showActivity(aty, RecommendCourteousActivity.class);
        } else if (flag == 5) {
            aty.showActivity(aty, SettingsActivity.class);
        } else if (flag == 6) {
            String auth_status = PreferenceHelper.readString(MyApplication.getContext(), StringConstants.FILENAME, "auth_status");
            if (auth_status != null && auth_status.equals("pass")) {
                aty.showActivity(aty, IdentityAuthenticationActivity.class);
            } else if (auth_status != null && auth_status.equals("check")) {
                inAuthenticationBouncedDialog();
            } else {
                aty.showActivity(aty, IdentityAuthenticationActivity.class);
            }
        } else if (flag == 7) {
            aty.showActivity(aty, AbnormalRecordsActivity.class);
        }
        dismissLoadingDialog();
    }

    /**
     * 认证中弹框
     */
    private void inAuthenticationBouncedDialog() {
        inAuthenticationBouncedDialog = null;
        inAuthenticationBouncedDialog = new InAuthenticationBouncedDialog(aty) {
            @Override
            public void confirm() {
                this.cancel();
            }
        };
        inAuthenticationBouncedDialog.show();
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (msg.equals("" + NumericConstants.TOLINGIN)) {
            mRefreshLayout.setPullDownRefreshEnable(false);
            img_avatar.setImageResource(R.mipmap.avatar_default);
            img_avatar1.setImageResource(R.mipmap.avatar_default);
            tv_name.setText(getString(R.string.loginregister));
            tv_name1.setText(getString(R.string.loginregister));
            tv_licensePlateNumber.setVisibility(View.GONE);
            tv_licensePlateNumber1.setVisibility(View.GONE);
            tv_alwaysSingular.setText("0");
            tv_alwaysSingular1.setText("0");
            tv_driverLevel.setText("0");
            tv_driverLevel1.setText("0");
            tv_serviceLevel.setText("0");
            tv_serviceLevel1.setText("0");
            tv_complaintsNumber.setText("0");
            tv_complaintsNumber1.setText("0");
            tv_identityAuthentication.setVisibility(View.INVISIBLE);
            tv_vehicleCertification.setVisibility(View.INVISIBLE);
            tv_money.setVisibility(View.INVISIBLE);
            if (flag != 0) {
                aty.showActivity(aty, LoginActivity.class);
            }
            return;
        }
        mRefreshLayout.setPullDownRefreshEnable(true);
        ViewInject.toast(msg);
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
            EasyPermissions.requestPermissions(this, getString(R.string.phoneCallPermissions), REQUEST_CODE_PERMISSION_CALL, perms);
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
            ViewInject.toast(getString(R.string.phoneCallPermissions1));
        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent")) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sv_mine.scrollTo(0, 2);
                    mRefreshLayout.beginRefreshing();
                }
            }, 600);
        } else if (((String) msgEvent.getData()).equals("RxBusAvatarEvent")) {
            //  img_headPortrait.setImageURI(Uri.parse(msgEvent.getMsg() + "?imageView2/1/w/70/h/70"));
            //   GlideImageLoader.glideLoader(KJActivityStack.create().topActivity(), msgEvent.getMsg() + "?imageView2/1/w/70/h/70", img_headPortrait, 0);
        } else if (((String) msgEvent.getData()).equals("RxBusIdentityAuthenticationEvent")) {
            tv_identityAuthentication.setText(getString(R.string.inAuthentication));
            String real_name = PreferenceHelper.readString(aty, StringConstants.FILENAME, "real_name", "");
            if (!StringUtils.isEmpty(real_name)) {
                tv_name.setText(real_name);
                tv_name1.setText(real_name);
            }
        } else if (((String) msgEvent.getData()).equals("RxBusVehicleCertificationEvent")) {
            tv_vehicleCertification.setText(getString(R.string.inAuthentication));
        }
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.d("tag", "scrollY" + scrollY);
        Log.d("tag", "oldScrollY" + oldScrollY);
        if (scrollY == 0) {
            ll_personalData1.setVisibility(View.GONE);
            ll_divider1.setVisibility(View.GONE);
        } else {
            ll_personalData1.setVisibility(View.VISIBLE);
            ll_divider1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        if (inAuthenticationBouncedDialog != null) {
            inAuthenticationBouncedDialog.cancel();
            inAuthenticationBouncedDialog = null;
        }

    }
}
