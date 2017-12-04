package com.ruitukeji.zwbs.main;

import android.view.View;

import com.iflytek.cloud.SpeechSynthesizer;
import com.ruitukeji.zwbs.common.BaseNewView;
import com.ruitukeji.zwbs.common.BasePresenter;
import com.ruitukeji.zwbs.common.BaseView;

/**
 * Created by Administrator on 2017/2/19.
 */

public interface MainContract {
    interface Presenter extends BasePresenter {

        /**
         * 更新司机当前位置信息
         */
        void postDriverLocation(String id, String location, String address);

        /**
         * 模拟点击
         *
         * @param view
         * @param x
         * @param y
         */
        void setSimulateClick(android.view.View view, float x, float y);

        /**
         * 是否登录
         */
        void isLogin(int flag);


        /**
         * 语音播报
         */
        void speech(SpeechSynthesizer mTts, String msg);

        /**
         * 发送工作状态
         */
        void postWorkingState(int status);

        /**
         * 获取工作状态
         */
        void getWorkingState();
    }

    interface View extends BaseNewView<Presenter, String> {
//        /**
//         * http请求正确
//         *
//         * @param s
//         */
//        void getSuccess(String s, int flag);
//
//        /**
//         * http请求错误
//         */
//        void error(String msg, int flag);
    }
}
