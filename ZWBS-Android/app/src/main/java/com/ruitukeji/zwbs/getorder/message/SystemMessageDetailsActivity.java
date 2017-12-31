package com.ruitukeji.zwbs.getorder.message;

import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.entity.getorder.message.SystemMessageDetailsBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;
import com.ruitukeji.zwbs.utils.rx.RxBus;

/**
 * 系统消息详情
 * Created by Administrator on 2017/2/15.
 */

public class SystemMessageDetailsActivity extends BaseActivity implements SystemMessageDetailsContract.View {


    /**
     * 标题
     */
    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    /**
     * 时间
     */
    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    /**
     * 内容
     */
    @BindView(id = R.id.web_content)
    private WebView web_content;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;


    private int messageId;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_messagedetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SystemMessageDetailsPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.messageDetails), true, R.id.titlebar);
        messageId = getIntent().getIntExtra("messageId", 0);
        showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
        ((SystemMessageDetailsContract.Presenter) mPresenter).getMessageDetails(messageId);
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_hintText:
                showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
                ((SystemMessageDetailsContract.Presenter) mPresenter).getMessageDetails(messageId);
                break;
        }
    }


    @Override
    public void getSuccess(String s) {
        ll_commonError.setVisibility(View.GONE);
        tv_title.setVisibility(View.VISIBLE);
        tv_time.setVisibility(View.VISIBLE);
        web_content.setVisibility(View.VISIBLE);
        SystemMessageDetailsBean messageDetailsBean = (SystemMessageDetailsBean) JsonUtil.getInstance().json2Obj(s, SystemMessageDetailsBean.class);
        if (StringUtils.isEmpty(messageDetailsBean.getResult().getTitle())) {
            tv_title.setText("");
        } else {
            tv_title.setText(messageDetailsBean.getResult().getTitle());
        }

        if (StringUtils.isEmpty(messageDetailsBean.getResult().getPushTime())) {
            tv_time.setText("");
        } else {
            tv_time.setText(messageDetailsBean.getResult().getPushTime());
        }

        if (StringUtils.isEmpty(messageDetailsBean.getResult().getContent())) {
            web_content.setVisibility(View.GONE);
        } else {
            web_content.setVisibility(View.VISIBLE);
            String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"></head><body>" + messageDetailsBean.getResult().getContent()
                    + "</body></html>";
            web_content.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
        }
        RxBus.getInstance().post(new MsgEvent<String>("RxBusSystemMessageDetailsEvent"));
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        tv_title.setVisibility(View.GONE);
        tv_time.setVisibility(View.GONE);
        web_content.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setText(msg + getString(R.string.clickRefresh));
        dismissLoadingDialog();
    }

    @Override
    public void setPresenter(SystemMessageDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
