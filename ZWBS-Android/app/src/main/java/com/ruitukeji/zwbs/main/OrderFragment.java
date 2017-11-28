package com.ruitukeji.zwbs.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.order.AllOrderFragment;
import com.ruitukeji.zwbs.order.CustomerEvaluationOrderFragment;
import com.ruitukeji.zwbs.order.DistributionCompleteOrderFragment;
import com.ruitukeji.zwbs.order.InDistributionOrderFragment;
import com.ruitukeji.zwbs.order.NoDistributionOrderFragment;
import com.ruitukeji.zwbs.order.QuotedPricesFragment;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;


/**
 * 订单
 * Created by Administrator on 2017/2/13.
 */
public class OrderFragment extends BaseFragment {
    private MainActivity aty;

    @BindView(id = R.id.tv_allOrder, click = true)
    private TextView tv_allOrder;
    @BindView(id = R.id.tv_allOrder1)
    private TextView tv_allOrder1;

    @BindView(id = R.id.tv_quotedPrices, click = true)
    private TextView tv_quotedPrices;
    @BindView(id = R.id.tv_quotedPrices1)
    private TextView tv_quotedPrices1;

    @BindView(id = R.id.tv_noDistribution, click = true)
    private TextView tv_noDistribution;
    @BindView(id = R.id.tv_noDistribution1)
    private TextView tv_noDistribution1;


    @BindView(id = R.id.tv_inDistribution, click = true)
    private TextView tv_inDistribution;
    @BindView(id = R.id.tv_inDistribution1)
    private TextView tv_inDistribution1;

    @BindView(id = R.id.tv_distributionComplete, click = true)
    private TextView tv_distributionComplete;
    @BindView(id = R.id.tv_distributionComplete1)
    private TextView tv_distributionComplete1;

    @BindView(id = R.id.tv_customerEvaluation, click = true)
    private TextView tv_customerEvaluation;
    @BindView(id = R.id.tv_customerEvaluation1)
    private TextView tv_customerEvaluation1;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;
    private BaseFragment contentFragment2;
    private BaseFragment contentFragment3;
    private BaseFragment contentFragment4;
    private BaseFragment contentFragment5;


    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_order, null);
    }

    @Override
    protected void initData() {
        super.initData();
        contentFragment = new AllOrderFragment();
        contentFragment1 = new QuotedPricesFragment();
        contentFragment2 = new NoDistributionOrderFragment();
        contentFragment3 = new InDistributionOrderFragment();
        contentFragment4 = new DistributionCompleteOrderFragment();
        contentFragment5 = new CustomerEvaluationOrderFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 20);
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        ActivityTitleUtils.initToolbar(parentView, getString(R.string.order), R.id.titlebar);
        if (chageIcon == 20) {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
        } else if (chageIcon == 21) {
            chageIcon = 21;
            cleanColors(21);
            changeFragment(contentFragment1);
        } else if (chageIcon == 22) {
            chageIcon = 22;
            cleanColors(22);
            changeFragment(contentFragment2);
        } else if (chageIcon == 23) {
            chageIcon = 23;
            cleanColors(23);
            changeFragment(contentFragment3);
        } else if (chageIcon == 24) {
            chageIcon = 24;
            cleanColors(24);
            changeFragment(contentFragment4);
        } else if (chageIcon == 25) {
            chageIcon = 25;
            cleanColors(25);
            changeFragment(contentFragment5);
        }
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.fragment_content, targetFragment);
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_allOrder:
                chageIcon = 20;
                cleanColors(20);
                changeFragment(contentFragment);
               // ViewInject.toast(chageIcon + "");
                break;
            case R.id.tv_quotedPrices:
                chageIcon = 21;
                cleanColors(21);
                changeFragment(contentFragment1);
               // ViewInject.toast(chageIcon + "");
                break;
            case R.id.tv_noDistribution:
                chageIcon = 22;
                cleanColors(22);
                changeFragment(contentFragment2);
               // ViewInject.toast(chageIcon + "");
                break;
            case R.id.tv_inDistribution:
                chageIcon = 23;
                cleanColors(23);
                changeFragment(contentFragment3);
              //  ViewInject.toast(chageIcon + "");
                break;
            case R.id.tv_distributionComplete:
                chageIcon = 24;
                cleanColors(24);
                changeFragment(contentFragment4);
               // ViewInject.toast(chageIcon + "");
                break;
            case R.id.tv_customerEvaluation:
                chageIcon = 25;
                cleanColors(25);
                changeFragment(contentFragment5);
              //  ViewInject.toast(chageIcon + "");
                break;
            default:
                break;
        }
    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
    }


    /**
     * 清除颜色，并添加颜色
     */
    public void cleanColors(int chageIcon) {
        tv_allOrder.setTextColor(getResources().getColor(R.color.typecolors));
        tv_allOrder1.setBackgroundResource(R.color.mainColor);
        tv_quotedPrices.setTextColor(getResources().getColor(R.color.typecolors));
        tv_quotedPrices1.setBackgroundResource(R.color.mainColor);
        tv_noDistribution.setTextColor(getResources().getColor(R.color.typecolors));
        tv_noDistribution1.setBackgroundResource(R.color.mainColor);
        tv_inDistribution.setTextColor(getResources().getColor(R.color.typecolors));
        tv_inDistribution1.setBackgroundResource(R.color.mainColor);
        tv_distributionComplete.setTextColor(getResources().getColor(R.color.typecolors));
        tv_distributionComplete1.setBackgroundResource(R.color.mainColor);
        tv_customerEvaluation.setTextColor(getResources().getColor(R.color.typecolors));
        tv_customerEvaluation1.setBackgroundResource(R.color.mainColor);
        if (chageIcon == 20) {
            tv_allOrder.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_allOrder1.setBackgroundResource(R.color.lonincolors);
        } else if (chageIcon == 21) {
            tv_quotedPrices.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_quotedPrices1.setBackgroundResource(R.color.lonincolors);
        } else if (chageIcon == 22) {
            tv_noDistribution.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_noDistribution1.setBackgroundResource(R.color.lonincolors);
        } else if (chageIcon == 23) {
            tv_inDistribution.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_inDistribution1.setBackgroundResource(R.color.lonincolors);
        } else if (chageIcon == 24) {
            tv_distributionComplete.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_distributionComplete1.setBackgroundResource(R.color.lonincolors);
        } else if (chageIcon == 25) {
            tv_customerEvaluation.setTextColor(getResources().getColor(R.color.lonincolors));
            tv_customerEvaluation1.setBackgroundResource(R.color.lonincolors);
        }
    }


}
