package com.ruitukeji.zwbs.main;

import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.rxvolley.client.HttpParams;
import com.ruitukeji.zwbs.BuildConfig;
import com.ruitukeji.zwbs.common.KJActivityStack;
import com.ruitukeji.zwbs.common.ViewInject;
import com.ruitukeji.zwbs.constant.NumericConstants;
import com.ruitukeji.zwbs.constant.StringConstants;
import com.ruitukeji.zwbs.entity.GaoDeCreateBean;
import com.ruitukeji.zwbs.retrofit.RequestClient;
import com.ruitukeji.zwbs.utils.JsonUtil;
import com.ruitukeji.zwbs.utils.httputil.HttpUtilParams;
import com.ruitukeji.zwbs.utils.httputil.ResponseListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/19.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void isLogin(int flag) {
        RequestClient.isLogin(new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

    @Override
    public void speech(SpeechSynthesizer mTts, String msg) {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人       // 默认发音人"xiaoyan"
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "80");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");
        Log.d("messge", msg);
        int code = mTts.startSpeaking(msg, mTtsListener);
        mTts.resumeSpeaking();
        if (code != ErrorCode.SUCCESS) {
            if (code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED) {
            } else {
                ViewInject.toast("语音合成失败");
            }
        }
    }

    /**
     * 更新司机当前位置信息
     */
    @Override
    public void postDriverLocation(String id, String location, String address) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.putHeaders("Content-Type", "application/x-www-form-urlencoded");
        httpParams.put("tableid", StringConstants.NearTableid);
        httpParams.put("key", BuildConfig.GAODE_WEBKEY);
        httpParams.put("loctype", 1);
        httpParams.put("_id", id);
        Map map = new HashMap();
        map.put("_id", id);
        map.put("_location", location);
        map.put("_address", address);
        httpParams.put("data", JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postDriverLocation(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                GaoDeCreateBean gaoDeCreateBean = (GaoDeCreateBean) JsonUtil.getInstance().json2Obj(response, GaoDeCreateBean.class);
                if (gaoDeCreateBean.getStatus() == NumericConstants.STATUS) {
                    mView.getSuccess(response, 6);
                } else {
                    Log.d("nearbySearch", gaoDeCreateBean.getStatus() + "");
                    mView.errorMsg("更新当前位置信息出现异常,云端返回数据错误", 1);
                }
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    @Override
    public void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }


    /**
     * 合成回调监听。
     */
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        public int mPercentForPlaying;
        public int mPercentForBuffering;

        @Override
        public void onSpeakBegin() {
            //  ViewInject.toast("开始播放");
        }

        @Override
        public void onSpeakPaused() {
            //  ViewInject.toast("暂停播放");
        }

        @Override
        public void onSpeakResumed() {
            //  ViewInject.toast("继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
            // 合成进度
            mPercentForBuffering = percent;
            //  ViewInject.toast(String.format(getString(R.string.tts_toast_format), mPercentForBuffering, mPercentForPlaying));
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
//            // 播放进度
            mPercentForPlaying = percent;
            // ViewInject.toast(String.format(getString(R.string.tts_toast_format), mPercentForBuffering, mPercentForPlaying));
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                //    ViewInject.toast("播放完成");
            } else if (error != null) {
                Log.d("messge", error.getPlainDescription(true));
                //  ViewInject.toast(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
                //	Log.d(TAG, "session id =" + sid);
                Log.d("messge", sid);
            }
        }
    };


    @Override
    public void postWorkingState(int online) {
        //  4  User/changeWork
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("online", online);
        httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postWorkingState(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isGoWork", online);
                mView.getSuccess(response, 4);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getWorkingState() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getWorkingState(httpParams, new ResponseListener<String>() {
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

}
