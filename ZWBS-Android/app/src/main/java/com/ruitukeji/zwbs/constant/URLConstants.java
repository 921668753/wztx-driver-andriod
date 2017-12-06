package com.ruitukeji.zwbs.constant;

/**
 * 用于存放url常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class URLConstants {

    /**
     * 服务器地址URL
     */
    public static String SERVERURL = "http://atwwg.api.antiwearvalve.com/";

    /**
     * 测试服务器地址URL
     */
    public static String SERVERURL1 = "http://wztx.drv.api.zenmechi.cc/";

    /**
     * 请求地址URL
     */
    public static String APIURL = SERVERURL1;

    /**
     * 上传头像
     */
    public static String UPLOADAVATAR = APIURL + "user/uploadAvatar";

    /**
     * 上传图片
     */
    public static String UPLOADQFCTIMG = APIURL + "file/uploadImg";
//    public static String UPLOADQFCTIMG = "http://wztx.shp.api.ruitukeji.com/file/uploadImg";


    /**
     * 应用配置参数
     */
    public static String APPCONFIG = APIURL + "appConfig";

    /**
     * 获取最新apk下载地址
     */
    public static String LASTAPK = APIURL + "lastApk";

    /**
     * 轮播图接口
     */
    public static String BANNERS = APIURL + "index/getAdvertisement";

    /**
     * 置换Token  get请求
     */
    public static String REFRESHTOKEN = APIURL + "member/refresh_token";

    /**
     * 登录
     */
    public static String MEMBERLOGIN = APIURL + "User/login";

    /**
     * 获取车辆车长信息以及车型
     */
    public static String GETALLCARSTYLE = APIURL + "car/getAllCarStyle";

    /**
     * 发送验证码
     */
    public static String SENDCAPTCHA = APIURL + "index/sendCaptcha";

    /**
     * 注册
     */
    public static String MEMBERREGISTER = APIURL + "User/reg";

    /**
     * 获取文章内容
     */
    public static String GETARTICLE = APIURL + "index/getArticle";


    /**
     * 司机认证
     */
    public static String DRIVERAUTH = APIURL + "User/driverAuth";

    /**
     * 车辆认证
     */
    public static String CARAUTH = APIURL + "User/carAuth";


    /**
     * 重置密码
     */
    public static String MEMBERRESETPWD = APIURL + "User/forget";

    /**
     * 未读消息数量
     */
    public static String GETUNREAD = APIURL + "message/getUnRead";

    /**
     * 我的消息-列表/详情
     */
    public static String MESSAGE = APIURL + "message";

    /**
     * 删除消息
     */
    public static String DELMESSAGE = APIURL + "message/delMessage";

    /**
     * 我的消息详情
     */
    public static String MESSAGEDETAIL = APIURL + "message/detail";

    /**
     * 司机接单（可以报价）列表
     */
    public static String QUOTELIST = APIURL + "goods/enableQuoteList";

    /**
     * 获得报价信息
     */
    public static String QUOTEGETINFO = APIURL + "quote/getInfo";

    /**
     * 提交货源报价done
     */
    public static String QUOTEADD = APIURL + "quote/saveQuote";

    /**
     * 改变工作状态
     */
    public static String CHANGEWORK = APIURL + "User/changeWork";

    /**
     * 获取当前工作状态
     */
    public static String ISWORK = APIURL + "User/isWork";

    /**
     * 司机进行发货动作
     */
    public static String SHHIPPING = APIURL + "order/shipping";

    /**
     * 获取用户信息
     */
    public static String MEMBERINFO = APIURL + "user/info";

    /**
     * 获取认证信息
     */
    public static String GETAUTHINFO = APIURL + "user/getAuthInfo";

    /**
     * 回调地址
     */
    public static String ZFBHUIDIAO = "http://115.159.227.219:8088/fanfou-api/alinotify";

    /**
     * 创建轨迹请求
     */
    public static String TABLEIDTRACK = "http://yuntuapi.amap.com/datamanage/data/create";

    /**
     * 更新司机当前位置信息
     */
    public static String TABLEIUPDATE = "http://yuntuapi.amap.com/datamanage/data/update";

    /**
     * 我的钱包
     */
    public static String PAY = APIURL + "pay";

    /**
     * 我的报价
     */
    public static String MYQUOTELIST = APIURL + "quote/quoteList";

    /**
     * 余额提现
     */
    public static String WITHDRAWAL = APIURL + "pay/withDraw";

    /**
     * 提现记录
     */
    public static String SHOWCASHRECORD = APIURL + "pay/showCashRecord";

    /**
     * 显示我的推荐列表
     */
    public static String SHOWMYRECOMMLIST = APIURL + "recommend/showMyRecommList";

    /**
     * 查看账单
     */
    public static String SHOWPAYRECORD = APIURL + "pay/showPayRecord";

    /**
     * 充值
     */
    public static String RECHARGE = APIURL + "pay/recharge";

    /**
     * 货源列表
     */
    public static String GOODLIST = APIURL + "Goods/goodsList";

    /**
     * 订单列表
     */
    public static String ORDERLISTINFO = APIURL + "order/listInfo";

    /**
     * 订单详情
     */
    public static String ORDERDETAIL = APIURL + "order/detail";

    /**
     * 货源详情
     */
    public static String GOODSDETAIL = APIURL + "goods/detail";

    /**
     * 获取评论内容
     */
    public static String COMMENTINFO = APIURL + "comment/commentInfo";

    /**
     * 获取路线信息列表
     */
    public static String SHOWLINE = APIURL + "linelist/showline";

    /**
     * 删除路线信息
     */
    public static String DELLINE = APIURL + "linelist/delline";

    /**
     * 添加路线信息
     */
    public static String SETLINE = APIURL + "linelist/setline";


    /**
     * 上传到货凭证
     */
    public static String UPLOADCERPIC = APIURL + "order/uploadCerPic";

    /**
     * 修改密码
     */
    public static String UPDATEPWD = APIURL + "User/updatePwd";

}