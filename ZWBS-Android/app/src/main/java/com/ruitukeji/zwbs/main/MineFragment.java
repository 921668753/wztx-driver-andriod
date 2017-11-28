package com.ruitukeji.zwbs.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.ruitukeji.zwbs.entity.MineBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.loginregister.NewUserInformationActivity;
import com.ruitukeji.zwbs.mine.aboutus.AboutUsActivity;
import com.ruitukeji.zwbs.mine.myquote.MyQuoteActivity;
import com.ruitukeji.zwbs.mine.mywallet.MyWalletActivity;
import com.ruitukeji.zwbs.mine.onlineservice.OnlineServiceActivity;
import com.ruitukeji.zwbs.mine.otherservices.OtherServicesActivity;
import com.ruitukeji.zwbs.mine.personalcertificate.PersonalCertificateActivity;
import com.ruitukeji.zwbs.mine.personaldata.PersonalDataActivity;
import com.ruitukeji.zwbs.mine.recommendcourteous.RecommendCourteousActivity;
import com.ruitukeji.zwbs.mine.settings.SettingsActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;

import cn.bingoogolapple.imageview.BGAImageView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * 个人中心
 * Created by Administrator on 2017/2/13.
 */

public class MineFragment extends BaseFragment implements MineContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    private MainActivity aty;

    @BindView(id = R.id.img_user)
    private BGAImageView img_user;

    @BindView(id = R.id.tv_name)
    private TextView tv_name;

    @BindView(id = R.id.tv_phone)
    private TextView tv_phone;

    @BindView(id = R.id.tv_status)
    private TextView tv_status;

    @BindView(id = R.id.personalData, click = true)
    private LinearLayout personalData;

    @BindView(id = R.id.myWallet, click = true)
    private LinearLayout myWallet;

    @BindView(id = R.id.myQuote, click = true)
    private LinearLayout myQuote;

    @BindView(id = R.id.personalCertificate, click = true)
    private LinearLayout personalCertificate;

    @BindView(id = R.id.onlineService, click = true)
    private LinearLayout onlineService;

    @BindView(id = R.id.recommendCourteous, click = true)
    private LinearLayout recommendCourteous;

    @BindView(id = R.id.aboutUs, click = true)
    private LinearLayout aboutUs;

    @BindView(id = R.id.settings, click = true)
    private LinearLayout settings;

    @BindView(id = R.id.otherServices, click = true)
    private LinearLayout otherServices;

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
        ActivityTitleUtils.initToolbar(parentView, getString(R.string.mine), R.id.titlebar);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, getActivity(), false);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.personalData:
                ((MineContract.Presenter) mPresenter).isLogin(1);
                break;
            case R.id.myWallet:
                ((MineContract.Presenter) mPresenter).isLogin(2);
                break;

            case R.id.myQuote:
                ((MineContract.Presenter) mPresenter).isLogin(6);
                break;

            case R.id.personalCertificate:
                ((MineContract.Presenter) mPresenter).isLogin(3);
                break;
            case R.id.onlineService:
                aty.showActivity(aty, OnlineServiceActivity.class);
                break;
            case R.id.recommendCourteous:
                ((MineContract.Presenter) mPresenter).isLogin(4);
                break;
            case R.id.aboutUs:
                Intent intent = new Intent(aty, AboutUsActivity.class);
                intent.putExtra("type", "driver_about");
                aty.showActivity(aty, intent);
                break;
            case R.id.settings:
                ((MineContract.Presenter) mPresenter).isLogin(5);
                break;
            case R.id.otherServices:
                aty.showActivity(aty, OtherServicesActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isRefreshAvatar = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshAvatar", false);
        if (isRefreshAvatar) {
            String avatar = PreferenceHelper.readString(aty, StringConstants.FILENAME, "avatar");
            GlideImageLoader.glideLoader(aty, avatar, img_user, 0);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshAvatar", false);
        }
        boolean isRefreshInfo = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshInfo", false);
        if (isRefreshInfo) {
            mRefreshLayout.beginRefreshing();
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
                GlideImageLoader.glideLoader(aty, R.mipmap.headload, img_user, 0);
            } else {
                GlideImageLoader.glideLoader(aty, mineBean.getResult().getAvatar() + "?imageView2/1/w/70/h/70", img_user, 0);
            }
            if (StringUtils.isEmpty(mineBean.getResult().getReal_name())) {
                tv_phone.setVisibility(View.GONE);
                tv_name.setText(mineBean.getResult().getPhone());
            } else {
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText(mineBean.getResult().getReal_name());
                tv_phone.setVisibility(View.VISIBLE);
                tv_phone.setText(mineBean.getResult().getPhone());
            }
            tv_status.setVisibility(View.VISIBLE);
            if (mineBean.getResult().getAuth_status() == null || mineBean.getResult().getAuth_status().equals("init")) {
                tv_status.setText(getString(R.string.unauthorized));
            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("check")) {
                tv_status.setText(getString(R.string.inAuthentication));
            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("pass")) {
                tv_status.setText(getString(R.string.pass));
            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("refuse")) {
                tv_status.setText(getString(R.string.authenticationFailure));
            } else if (mineBean.getResult().getAuth_status() != null && mineBean.getResult().getAuth_status().equals("delete")) {
                tv_status.setText(getString(R.string.delete));
            }
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
                Intent newUserInformation = new Intent(aty, NewUserInformationActivity.class);
                newUserInformation.putExtra("auth_status", auth_status);
                aty.showActivity(aty, newUserInformation);
            }
        } else if (flag == 4) {
            aty.showActivity(aty, RecommendCourteousActivity.class);
        } else if (flag == 5) {
            aty.showActivity(aty, SettingsActivity.class);
        } else if (flag == 6) {
            aty.showActivity(aty, MyQuoteActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg, int flag) {
        if (flag == 0) {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                mRefreshLayout.setPullDownRefreshEnable(false);
                img_user.setImageResource(R.mipmap.headportrait);
                tv_name.setText("登录/注册");
                tv_phone.setVisibility(View.GONE);
                tv_status.setVisibility(View.INVISIBLE);
            } else {
                mRefreshLayout.setPullDownRefreshEnable(true);
            }
        } else if (flag == 1 || flag == 2 || flag == 3 || flag == 4 || flag == 5 || flag == 6) {
            if (msg.equals("" + NumericConstants.TOLINGIN)) {
                PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "MineFragment");
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

    @Override
    public void error(String msg) {

    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
        boolean isRefreshInfo1 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshInfo1", false);
        if (isRefreshInfo1) {
            mRefreshLayout.beginRefreshing();
        }
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
}
