package com.ruitukeji.zwbs.constant;

/**
 * 用于存放字符串常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class StringConstants {
    /**
     * 存放的文件名
     */
    public static String FILENAME = "wztxsShare";
    /**
     * 文件缓存路径存放的文件名-----图片以及URL请求缓存路径
     */
    public static String CACHEPATH = "WZTXS/Cache";
    /**
     * 图片缓存路径 存放的文件名
     */
    public static String PHOTOCACHE = "WZTXS/PhotoCache";
    /**
     * 图片保存路径存放的文件名
     */
    public static String PHOTOPATH = "WZTXS/PhonePhoto";
    /**
     * 下载文件保存路径的文件名
     */
    public static String DOWNLOADPATH = "WZTXS/Download";
    /**
     * 错误日志存放位置ERRORLOG
     */
    public static String ERRORLOG = "WZTXS/PhoneLog";

    // 图片缓存最大容量，150M，根据自己的需求进行修改
    public static final int GLIDE_CATCH_SIZE = 550 * 1000 * 1000;
    // 图片压缩，5M，根据自己的需求进行修改(单位bt)
    public static final long COMPRESSION_SIZE = 2 * 1000 * 1000;
    /**
     * 微信
     */
    public static String WEIXINAppKey = "wx2e4a2d8081da3490";
    public static String WEIXINAppSecret = "68fa29baaf3d7625eb2e520652eff611";
    /**
     * 讯飞语音
     */
    public static String SpeechAPPID = "596d6b27";
    /**
     * 腾讯QQ
     */
    public static String QQAppID = "1106317358";
    public static String QQAppKey = "V18PSlnL3G3BrAuR";
    /**
     * 高德地图 云图表tableid
     *
     * NearTableid   附近周边表
     *LogisticsPositioningTableid   物流轨迹表
     */
    public static String NearTableid = "596892e77bbf190cbd32058b";
    public static String LogisticsPositioningTableid = "5950e0947bbf190cbdfb5e46";


}
