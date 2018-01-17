package com.ruitukeji.zwbs.mine.helpcenter;

import android.widget.TextView;

import com.kymjs.common.StringUtils;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.entity.mine.helpcenter.HelpCenterDetailsBean;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.myview.WebViewLayout;

/**
 * 帮助中心详情
 * Created by Administrator on 2017/12/12.
 */

public class HelpCenterDetailsActivity extends BaseActivity implements HelpCenterDetailsContract.View {

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;
    private int id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_helpcenterdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new HelpCenterDetailsPresenter(this);
        id = getIntent().getIntExtra("id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((HelpCenterDetailsContract.Presenter) mPresenter).getHelpCenterDetails(id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.helpCenter), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(HelpCenterDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getSuccess(String s) {
        HelpCenterDetailsBean helpCenterDetailsBean = (HelpCenterDetailsBean) JsonUtil.json2Obj(s, HelpCenterDetailsBean.class);
        initView(helpCenterDetailsBean.getResult().getTitle(), helpCenterDetailsBean.getResult().getContent());
        dismissLoadingDialog();
    }

    @Override
    public void error(String msg) {
        initView("", msg);
        dismissLoadingDialog();
    }

    public void initView(String title, String content) {
        webViewLayout.setTitleVisibility(false);
        if (StringUtils.isEmpty(title)) {
            tv_title.setText(getString(R.string.app_name));
        } else {
            tv_title.setText(title);
        }
        if (!StringUtils.isEmpty(content)) {
            String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><title>" + title + "</title></head><body>" + content
                    + "</body></html>";
            webViewLayout.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
        }
    }
}
