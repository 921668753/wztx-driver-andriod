package com.ruitukeji.zwbs.mine.personalcertificate;

import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.BitmapCoreUtil;
import com.ruitukeji.zwbs.utils.DataCleanManager;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/3/10.
 */

public class CheckDocumentsPresenter implements CheckDocumentsContract.Presenter {

    private CheckDocumentsContract.View mView;

    public CheckDocumentsPresenter(CheckDocumentsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getCheckDocuments() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getAuthInfo(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });

    }


    @Override
    public void upLoadImg(String path, int flag) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad));
        File oldFile = new File(path);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.error(KJActivityStack.create().topActivity().getString(R.string.imagePathError));
            return;
        }
        long fileSize = 0;
        try {
            fileSize = DataCleanManager.getFolderSize(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileSize = 0;
        }
        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
            oldFile = BitmapCoreUtil.customCompression(oldFile);
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("file", oldFile);
        RequestClient.upLoadImg(httpParams, 1, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.error(msg);
            }
        });
    }

    @Override
    public void postCheckDocuments(SweetAlertDialog sweetAlertDialog, List list) {
//        List<ListBean> list1 = new ArrayList<ListBean>();
//        for (int i = 0; i < list.size(); i++) {
//            if (!(StringUtils.isEmpty(list.get(i).getImg())) && !(StringUtils.isEmpty(list.get(i).getTermStart())) && !(StringUtils.isEmpty(list.get(i).getTermEnd()))) {
//                ListBean listBean = new ListBean();
//                listBean.setId(list.get(i).getId());
//                listBean.setCodeX(list.get(i).getCodeX());
//                listBean.setTermStart(list.get(i).getTermStart());
//                listBean.setImg(list.get(i).getImg());
//                listBean.setTermEnd(list.get(i).getTermEnd());
//                listBean.setName(list.get(i).getName());
//                list1.add(listBean);
//            }
//        }
//        if (list1.size() == 0) {
//            mView.error(KJActivityStack.create().topActivity().getString(R.string.confirmSubmission3));
//            return;
//        }
        String title = null;
//        if (list1.size() == list.size()) {
//            title = KJActivityStack.create().topActivity().getString(R.string.confirmSubmission2);
//        } else {
//            title = KJActivityStack.create().topActivity().getString(R.string.confirmSubmission1);
//        }
        submitSupplierQualification(sweetAlertDialog, title, list);
    }

    private void submitSupplierQualification(SweetAlertDialog sweetAlertDialog, String title, List list) {
        sweetAlertDialog.setTitle(title);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.submissionLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                Map<String, List> map = new HashMap<String, List>();
                map.put("list", list);
                httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
                RequestClient.postLogin(httpParams, new ResponseListener<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.getSuccess(response, 1);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.error(msg);
                    }
                });
            }
        }).show();
    }

}
