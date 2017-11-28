package com.ruitukeji.zwbs.utils;

import android.content.Context;

import com.ruitukeji.zwbs.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/2/4.
 */

public class DialogUtil {

    public static void showAlertDialog(Context context, int tittle, OnDialogSelectListener onDialogSelectListener) {

//        SweetAlertDialog.confirmBackgroundResource = R.drawable.shape_login;
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        //   sweetAlertDialog.findViewById(cn.pedant.SweetAlert.R.id.confirm_button).setBackgroundResource(R.drawable.shape_login);
        sweetAlertDialog.setTitleText(context.getString(tittle))
                .setCancelText(context.getString(R.string.cancel))
                .setConfirmText(context.getString(R.string.confirm))
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        sDialog = null;
                        onDialogSelectListener.onDialogSelect();
                    }
                }).show();
        sweetAlertDialog.setCancelable(false);
    }

    public interface OnDialogSelectListener {
        void onDialogSelect();
    }

}
