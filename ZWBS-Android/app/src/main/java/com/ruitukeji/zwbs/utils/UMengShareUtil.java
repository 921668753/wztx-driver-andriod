package com.ruitukeji.zwbs.utils;

import android.app.Activity;

import com.ruitukeji.zwbs.R;
import com.ruitukeji.zwbs.application.MyApplication;
import com.ruitukeji.zwbs.common.ViewInject;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

/**
 * Created by Administrator on 2017/2/18.
 */

public class UMengShareUtil {
    /**
     * 直接分享
     */
    public void umShare(Activity activity, SHARE_MEDIA platform, String url, String title, String description) {
        UMImage thumb = new UMImage(activity, R.drawable.ic_launcher);
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(description);//描述
        new ShareAction(activity).setPlatform(platform)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            ViewInject.toast(MyApplication.getContext().getString(R.string.shareSuccess));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ViewInject.toast(MyApplication.getContext().getString(R.string.shareError));
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ViewInject.toast(MyApplication.getContext().getString(R.string.shareError));
        }
    };
}
