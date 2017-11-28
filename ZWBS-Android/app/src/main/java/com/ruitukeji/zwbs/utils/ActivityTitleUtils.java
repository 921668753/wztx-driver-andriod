

package com.ruitukeji.zwbs.utils;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityTitleUtils {
    /**
     * Configure Toolbar
     */
    public static void initToolbar(AppCompatActivity appCompatActivity,
                                   String title, boolean displayHomeAsUp, int id) {
        initToolbar(appCompatActivity, title, true, displayHomeAsUp, id);
    }

    public static void initToolbar(final AppCompatActivity appCompatActivity,
                                   String title, boolean asSupportActionbar, boolean displayHomeAsUp, int id) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setTitleText(title);
        titlebar.setRightText("");
        titlebar.setRightDrawable(null);
        titlebar.setDelegate(new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                appCompatActivity.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                //  ViewInject.toast("right");
            }
        });
    }

    /**
     * 设置右边按钮
     */
    public static void initToolbar(AppCompatActivity appCompatActivity,
                                   String title, String rightText, int id, SimpleDelegate simpleDelegate) {
        initToolbar(appCompatActivity, title, rightText, true, id, simpleDelegate);
    }

    /**
     * 设置右边按钮
     *
     * @param appCompatActivity
     * @param title
     * @param id
     * @param simpleDelegate
     */

    public static void initToolbar(final AppCompatActivity appCompatActivity,
                                   String title, String rightText, boolean asSupportActionbar, int id, SimpleDelegate simpleDelegate) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setTitleText(title);
        titlebar.setRightText(rightText);
        titlebar.setRightDrawable(null);
        titlebar.setDelegate(simpleDelegate);
    }

    /**
     * activity
     * 设置标题
     */
    public static void initToolbar(AppCompatActivity appCompatActivity, String title, int id) {
        initToolbar(appCompatActivity, title, "", true, id);
    }

    /**
     * 设置标题
     *
     * @param appCompatActivity
     * @param title
     * @param id
     */

    public static void initToolbar(final AppCompatActivity appCompatActivity,
                                   String title, String rightText, boolean asSupportActionbar, int id) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setLeftDrawable(null);
        titlebar.setTitleText(title);
        titlebar.setRightText(rightText);
        titlebar.setRightDrawable(null);
    }

    /**
     * fragment
     * 设置标题
     */
    public static void initToolbar(View appCompatActivity, String title, int id) {
        initToolbar(appCompatActivity, title, "", true, id);
    }

    /**
     * 设置标题
     *
     * @param appCompatActivity
     * @param title
     * @param id
     */

    public static void initToolbar(final View appCompatActivity,
                                   String title, String rightText, boolean asSupportActionbar, int id) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setLeftDrawable(null);
        titlebar.setTitleText(title);
        titlebar.setRightText(rightText);
        titlebar.setRightDrawable(null);
    }

    /**
     * fragment 设置右边按钮
     * 设置标题
     */
    public static void initToolbar(View appCompatActivity, String title,String rightText, int id, SimpleDelegate simpleDelegate) {
        initToolbar(appCompatActivity, title, rightText, true, id, simpleDelegate);
    }

    /**
     * 设置标题
     *
     * @param appCompatActivity
     * @param title
     * @param id
     */

    public static void initToolbar(final View appCompatActivity, String title, String rightText, boolean asSupportActionbar, int id, SimpleDelegate simpleDelegate) {
        BGATitleBar titlebar = (BGATitleBar) appCompatActivity.findViewById(id);
        titlebar.setLeftDrawable(null);
        titlebar.setTitleText(title);
        titlebar.setRightText(rightText);
        titlebar.setRightDrawable(null);
        titlebar.setDelegate(simpleDelegate);
    }
}
