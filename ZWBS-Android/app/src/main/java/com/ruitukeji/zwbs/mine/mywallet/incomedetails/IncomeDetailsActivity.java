package com.ruitukeji.zwbs.mine.mywallet.incomedetails;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BaseFragment;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.mine.mywallet.incomedetails.incomedetailsfragment.NotPaidIncomeFragment;
import com.ruitukeji.zwbs.mine.mywallet.incomedetails.incomedetailsfragment.PaidIncomeFragment;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

/**
 * 收入明细
 * Created by Administrator on 2017/12/1.
 */

public class IncomeDetailsActivity extends BaseActivity {

    /**
     * 已支付收入
     */
    @BindView(id = R.id.ll_paidIncome, click = true)
    private LinearLayout ll_paidIncome;
    @BindView(id = R.id.tv_paidIncome)
    private TextView tv_paidIncome;
    @BindView(id = R.id.tv_paidIncome1)
    private TextView tv_paidIncome1;

    /**
     * 未支付收入
     */
    @BindView(id = R.id.ll_notPaidIncome, click = true)
    private LinearLayout ll_notPaidIncome;
    @BindView(id = R.id.tv_notPaidIncome, click = true)
    private TextView tv_notPaidIncome;
    @BindView(id = R.id.tv_notPaidIncome1)
    private TextView tv_notPaidIncome1;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_incomedetails);
    }

    @Override
    public void initData() {
        super.initData();
        contentFragment = new PaidIncomeFragment();
        contentFragment1 = new NotPaidIncomeFragment();
        chageIcon = getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.incomeDetails), true, R.id.titlebar);
        if (chageIcon == 0) {
            chageIcon = 0;
            cleanColors(0);
            changeFragment(contentFragment);
        } else if (chageIcon == 1) {
            chageIcon = 1;
            cleanColors(1);
            changeFragment(contentFragment1);
        }
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
        //  transaction.commit();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_paidIncome:
                chageIcon = 0;
                cleanColors(0);
                changeFragment(contentFragment);
                break;
            case R.id.ll_notPaidIncome:
                chageIcon = 1;
                cleanColors(1);
                changeFragment(contentFragment1);
                break;

            default:
                break;
        }
    }

    /**
     * 清除颜色，并添加颜色
     */
    public void cleanColors(int chageIcon) {
        tv_paidIncome.setTextColor(getResources().getColor(R.color.typecolors));
        tv_paidIncome1.setBackgroundResource(R.color.mainColor);
        tv_notPaidIncome.setTextColor(getResources().getColor(R.color.typecolors));
        tv_notPaidIncome1.setBackgroundResource(R.color.mainColor);
        if (chageIcon == 0) {
            tv_paidIncome.setTextColor(getResources().getColor(R.color.announcementCloseColors));
            tv_paidIncome1.setBackgroundResource(R.color.announcementCloseColors);
        } else if (chageIcon == 1) {
            tv_notPaidIncome.setTextColor(getResources().getColor(R.color.announcementCloseColors));
            tv_notPaidIncome1.setBackgroundResource(R.color.announcementCloseColors);
        }
    }

}
