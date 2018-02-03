package com.ruitukeji.zwbs.getorder.message;


import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.adapter.getorder.message.SystemMessageViewAdapter;
import com.ruitukeji.zwbs.common.BaseActivity;
import com.ruitukeji.zwbs.common.BindView;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.entity.getorder.message.SystemMessageBean;
import com.ruitukeji.zwbs.loginregister.LoginActivity;
import com.ruitukeji.zwbs.utils.ActivityTitleUtils;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.RefreshLayoutUtil;
import com.ruitukeji.zwbs.utils.rx.MsgEvent;

import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 系统消息
 * Created by Administrator on 2017/12/12.
 */

public class SystemMessageActivity extends BaseActivity implements SystemMessageContract.View, AbsListView.OnScrollListener, AdapterView.OnItemClickListener, BGAOnItemChildClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

//    /**
//     * 系统消息
//     */
//    @BindView(id = R.id.ll_systemMessage, click = true)
//    private TextView ll_systemMessage;
//    @BindView(id = R.id.tv_systemMessage)
//    private TextView tv_systemMessage;
//    @BindView(id = R.id.tv_systemMessage1)
//    private TextView tv_systemMessage1;

    /**
     * 订单消息
     */
    @BindView(id = R.id.ll_orderMessage, click = true)
    private TextView ll_orderMessage;
    @BindView(id = R.id.tv_orderMessage)
    private TextView tv_orderMessage;
    @BindView(id = R.id.tv_orderMessage1)
    private TextView tv_orderMessage1;


    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;


    @BindView(id = R.id.lv_systemmessage)
    private ListView lv_systemmessage;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;
    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;

    /**
     * 全选  标记已读
     */
    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;

    @BindView(id = R.id.tv_generalElection, click = true)
    private TextView tv_generalElection;

    @BindView(id = R.id.tv_markedRead, click = true)
    private TextView tv_markedRead;

    @BindView(id = R.id.tv_delete, click = true)
    private TextView tv_delete;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private String push_type = "system";

    /**
     * 是否是编辑
     */
    private int isSelected = 0;

    /**
     * 是否是选中
     */
    private int isEdit = 0;

    private SystemMessageViewAdapter mAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_systemmessage);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new SystemMessagePresenter(this);
        mAdapter = new SystemMessageViewAdapter(this);
        tv_orderMessage.setTextColor(getResources().getColor(R.color.typecolors));
        tv_orderMessage1.setBackgroundResource(R.color.white);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, this, true);
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
                if (isEdit == 0) {
                    mAdapter.closeOpenedSwipeItemLayoutWithAnim();
                    isEdit = 1;
                    visibilityImg(isEdit, mAdapter.getData());
                    mAdapter.notifyDataSetChanged();
                    titlebar.setRightText(getString(R.string.complete));
                    ll_bottom.setVisibility(View.VISIBLE);
                } else {
                    isEdit = 0;
                    isSelected = 0;
                    visibilityImg(isEdit, mAdapter.getData());
                    mAdapter.notifyDataSetChanged();
                    titlebar.setRightText(getString(R.string.edit));
                    ll_bottom.setVisibility(View.GONE);
                }

//                Intent intent = new Intent(aty, AboutUsActivity.class);
//                intent.putExtra("type", "type");
//                showActivity(aty, intent);
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.message), getString(R.string.edit), R.id.titlebar, simpleDelegate);
        lv_systemmessage.setAdapter(mAdapter);
        lv_systemmessage.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        lv_systemmessage.setOnScrollListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_orderMessage:
                showActivity(aty, OrderMessageActivity.class);
                overridePendingTransition(0, 0);
                break;
            case R.id.tv_generalElection:
                isSelected = 1;
                visibilityImg(isEdit, mAdapter.getData());
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_markedRead:
                //  showLoadingDialog(getString(R.string.dataLoad));
                ((SystemMessageContract.Presenter) mPresenter).postReadMessage(mAdapter.getData());
                break;
            case R.id.tv_delete:
                //  showLoadingDialog(getString(R.string.dataLoad));
                ((SystemMessageContract.Presenter) mPresenter).postDeleteMessage(mAdapter.getData());
                break;
            case R.id.tv_hintText:
                mRefreshLayout.beginRefreshing();
                break;
        }
    }


    @Override
    public void setPresenter(SystemMessageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String s, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
            SystemMessageBean messageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(s, SystemMessageBean.class);
            mMorePageNumber = messageBean.getResult().getPage();
            totalPageNumber = messageBean.getResult().getPageTotal();
            if (messageBean.getResult().getList() == null || messageBean.getResult().getList().size() == 0) {
                errorMsg(getString(R.string.serverReturnsDataNull), 0);
                return;
            }
            visibilityImg(isEdit, messageBean.getResult().getList());
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(messageBean.getResult().getList());
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(messageBean.getResult().getList());
                mRefreshLayout.endLoadingMore();
            }
            dismissLoadingDialog();
        } else if (flag == 1 || flag == 2) {
            mRefreshLayout.beginRefreshing();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        if (flag == 0) {
            isShowLoadingMore = false;
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setText(msg + getString(R.string.clickRefresh));
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
        } else {
            ViewInject.toast(msg);
        }
        dismissLoadingDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isEdit == 1) {
            return;
        }
        Intent intent = new Intent(aty, SystemMessageDetailsActivity.class);
        intent.putExtra("messageId", mAdapter.getItem(position).getId());
        showActivity(aty, intent);
    }

    /**
     * 是否显示
     */
    private void visibilityImg(int isEdit, List<SystemMessageBean.ResultBean.ListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsSelected(isSelected);
            list.get(i).setIsEdit(isEdit);
        }
    }


    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.img_checkbox) {
            ImageView img_checkbox = (ImageView) childView.findViewById(R.id.img_checkbox);
            if (mAdapter.getItem(position).getIsSelected() == 0) {
                img_checkbox.setImageResource(R.mipmap.ic_checkbox_select);
                mAdapter.getItem(position).setIsSelected(1);
                return;
            }
            img_checkbox.setImageResource(R.mipmap.ic_checkbox_unselect);
            mAdapter.getItem(position).setIsSelected(0);
        } else if (childView.getId() == R.id.tv_markedRead) {
            //    showLoadingDialog(getString(R.string.dataLoad));
            mAdapter.getItem(position).setIsSelected(1);
            ((SystemMessageContract.Presenter) mPresenter).postReadMessage(mAdapter.getData());
        } else if (childView.getId() == R.id.tv_delete) {
            //   showLoadingDialog(getString(R.string.dataLoad));
            mAdapter.getItem(position).setIsSelected(1);
            ((SystemMessageContract.Presenter) mPresenter).postDeleteMessage(mAdapter.getData());
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((SystemMessageContract.Presenter) mPresenter).getMessage(push_type, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((SystemMessageContract.Presenter) mPresenter).getMessage(push_type, mMorePageNumber);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) {
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusSystemMessageDetailsEvent")) {
            mRefreshLayout.beginRefreshing();
        }
//        else if (((String) msgEvent.getData()).equals("RxBusAvatarEvent")) {
////            img_headPortrait.setImageURI(Uri.parse(msgEvent.getMsg() + "?imageView2/1/w/70/h/70"));
//            GlideImageLoader.glideLoader(KJActivityStack.create().topActivity(), msgEvent.getMsg() + "?imageView2/1/w/70/h/70", img_headPortrait, 0);
//        }
    }


    /**
     * 关闭页面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                KJActivityStack.create().finishActivity(OrderMessageActivity.class);
                aty.finish();
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
