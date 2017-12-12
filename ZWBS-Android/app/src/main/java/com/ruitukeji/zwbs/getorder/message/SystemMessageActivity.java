package com.ruitukeji.zwbs.getorder.message;


import android.view.View;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 系统消息
 * Created by Administrator on 2017/12/12.
 */

public class SystemMessageActivity extends BaseActivity {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    /**
     * 系统消息
     */
    @BindView(id = R.id.ll_systemMessage, click = true)
    private TextView ll_systemMessage;
    @BindView(id = R.id.tv_systemMessage)
    private TextView tv_systemMessage;
    @BindView(id = R.id.tv_systemMessage1)
    private TextView tv_systemMessage1;

    /**
     * 订单消息
     */
    @BindView(id = R.id.ll_orderMessage, click = true)
    private TextView ll_orderMessage;
    @BindView(id = R.id.tv_orderMessage)
    private TextView tv_orderMessage;
    @BindView(id = R.id.tv_orderMessage1)
    private TextView tv_orderMessage1;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_systemmessage);
    }


    @Override
    public void initData() {
        super.initData();
        tv_orderMessage.setTextColor(getResources().getColor(R.color.typecolors));
        tv_orderMessage1.setBackgroundResource(R.color.white);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                KJActivityStack.create().finishActivity(OrderMessageActivity.class);
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                titlebar.setRightText(getString(R.string.complete));
//                Intent intent = new Intent(aty, AboutUsActivity.class);
//                intent.putExtra("type", "type");
//                showActivity(aty, intent);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.message), getString(R.string.edit), R.id.titlebar, simpleDelegate);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_orderMessage:
                showActivity(aty, OrderMessageActivity.class);
                overridePendingTransition(0, 0);
                break;
        }
    }
}
