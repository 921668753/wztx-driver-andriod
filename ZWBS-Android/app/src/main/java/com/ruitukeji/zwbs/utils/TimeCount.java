package com.ruitukeji.zwbs.utils;

import android.os.CountDownTimer;

/**
 * Created by Administrator on 2018/1/24.
 */

public abstract class TimeCount extends CountDownTimer {


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }



}
