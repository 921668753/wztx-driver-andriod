package com.ruitukeji.zwbs.common;

import android.content.Context;


import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.kymjs.common.FileUtils;
import com.ruitukeji.zwbs.constant.StringConstants;


/**
 * Created by YaphetZhao
 * on 2016/12/19.
 * <p>
 * QQ:11613371
 * GitHub:https://github.com/YaphetZhao
 * Email:yaphetzhao@foxmail.com
 * Email_EN:yaphetzhao@gmail.com
 * <p>
 * GlideConfiguration
 */
@GlideModule
public final class GlideConfiguration extends AppGlideModule {

    // 需要在AndroidManifest.xml中声明
    // <meta-data
    //    android:name="com.yaphetzhao.glidecatchsimple.glide.GlideConfiguration"
    //    android:value="GlideModule" />

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //自定义缓存目录
//        //设置磁盘缓存目录（和创建的缓存目录相同）
//        ExternalCacheDiskCacheFactory代表/sdcard/Android/data/<application package>/cache
//        InternalCacheDiskCacheFactory代表/data/data/<application package>/cache
     //   String downloadDirectoryPath = FileNewUtil.getFolder(StringConstants.PHOTOCACHE).getAbsolutePath();
        String downloadDirectoryPath = FileUtils.getSaveFolder(StringConstants.PHOTOCACHE).getAbsolutePath();
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, StringConstants.GLIDE_CATCH_SIZE));
    }

//    @Override
//    public void registerComponents(Context context, Registry registry) {
//         registry.append(Api.GifResult.class, InputStream.class, new GiphyModelLoader.Factory());
//    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
