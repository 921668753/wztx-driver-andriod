package com.ruitukeji.zwbs.getorder.message;

import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.entity.getorder.message.OrderMessageBean.ResultBean.ListBean;
import com.ruitukeji.zwbs.getorder.message.dialog.MarkedAsReadBouncedDialog;
import com.ruitukeji.zwbs.getorder.message.dialog.SureToDeleteBouncedDialog;
import com.ruitukeji.zwbs.getorder.message.dialog.SureToDeleteBouncedDialog.SureToDeleteDialogCallBack;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */

public class OrderMessagePresenter implements OrderMessageContract.Presenter {


    private OrderMessageContract.View mView;

    public OrderMessagePresenter(OrderMessageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 获取消息列表
     *
     * @param page 页码
     */
    @Override
    public void getMessage(String type, int page) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("push_type", type);
        httpParams.put("page", page);
        httpParams.put("pageSize", 20);
        RequestClient.getMessage(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    /**
     * 删除消息
     */
    @Override
    public void postDeleteMessage(List<ListBean> masageList) {
        String msgStr = getMsgIdList(masageList);
        if (StringUtils.isEmpty(msgStr)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.delete2), 1);
            return;
        }
        SureToDeleteBouncedDialog sureToDeleteBouncedDialog = new SureToDeleteBouncedDialog(KJActivityStack.create().topActivity());
        sureToDeleteBouncedDialog.setSureToDeleteDialogCallBack(new SureToDeleteDialogCallBack() {
            @Override
            public void confirm() {
                sureToDeleteBouncedDialog.cancel();
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                Map map = new HashMap();
                map.put("msg_id", msgStr);
                httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
                RequestClient.postDeleteMessage(httpParams, new ResponseListener<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.getSuccess(response, 1);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.errorMsg(msg, 1);
                    }
                });
            }
        });
        sureToDeleteBouncedDialog.show();
    }


    @Override
    public void postReadMessage(List<ListBean> masageList) {
        String msgStr = getMsgIdList(masageList);
        if (StringUtils.isEmpty(msgStr)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.markedRead1), 2);
            return;
        }
        MarkedAsReadBouncedDialog markedAsReadBouncedDialog = new MarkedAsReadBouncedDialog(KJActivityStack.create().topActivity());
        markedAsReadBouncedDialog.setMarkedAsReadDialogCallBack(new MarkedAsReadBouncedDialog.MarkedAsReadDialogCallBack() {
            @Override
            public void confirm() {
                markedAsReadBouncedDialog.cancel();
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.dataLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                Map map = new HashMap();
                map.put("msg_id", msgStr);
                httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
                RequestClient.postReadMessage(httpParams, new ResponseListener<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.getSuccess(response, 2);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.errorMsg(msg, 2);
                    }
                });
            }
        });
        markedAsReadBouncedDialog.show();
    }

    /**
     * 获取标记的id
     */
    private String getMsgIdList(List<ListBean> masageList) {
        String msgIdStr = "";
        for (int i = 0; i < masageList.size(); i++) {
            if (masageList.get(i).getIsSelected() == 1) {
                msgIdStr = msgIdStr + "," + masageList.get(i).getId();
            }
        }
        if (StringUtils.isEmpty(msgIdStr)) {
            return "";
        }
        msgIdStr = msgIdStr.substring(1);
        return msgIdStr;
    }
}
