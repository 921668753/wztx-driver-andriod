package com.ruitukeji.zwbs.constant;

/**
 * 用于存放url常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class URLConstants {

    /**
     * 服务器地址URL
     */
    //  public static String APIURL = "http://atwwg.api.antiwearvalve.com/";

    /**
     * 测试服务器地址URL
     */
    public static String APIURL = "http://wuzaidriver.wuzaitianxia56.com/";

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
     * 第三方登录
     */
    public static String THIRDLOGIN = APIURL + "User/thirdLogin";

    /**
     * 第三方登录绑定手机号
     */
    public static String THIRDLOGINADD = APIURL + "User/thirdLoginAdd";

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
     * 得到全部城市
     */
    public static String ALLCITY = APIURL + "Address/getAllCity";

    /**
     * 得到热门城市
     */
    public static String ALLHOTCITY = APIURL + "Address/getAllHotCity";

    /**
     * 接单--公告
     */
    public static String HOME = APIURL + "index/home";

    /**
     * 任务--今日任务
     */
    public static String TASK = APIURL + "goods/todayTask";

    /**
     * 任务--填写快递单
     */
    public static String FILLCOURIER = APIURL + "goods/fillCourier";

    /**
     * 货源--地址
     */
    public static String ADDRESS = APIURL + "Help/getAddress";

    /**
     * 公告详情
     */
    public static String ANNOUNCEMENT = APIURL + "index/getAdverDetail";

    /**
     * 获取文章内容
     */
    public static String GETARTICLE = APIURL + "index/getArticle";

    /**
     * 可接单类型
     */
    public static String ORDERTYPE = APIURL + "Help/orderType";

    /**
     * 司机认证
     */
    public static String DRIVERAUTH = APIURL + "User/driverAuth";

    /**
     * 获取司机认证信息
     */
    public static String GETDRIVERAUTHINFO = APIURL + "user/getDriverAuthInfo";

    /**
     * 车辆认证
     */
    public static String CARAUTH = APIURL + "User/carAuth";

    /**
     * 车辆品牌
     */
    public static String VEHICLEBRAND = APIURL + "User/carBand";

    /**
     * 获取车辆认证信息
     */
    public static String GETCARAUTHINFO = APIURL + "user/getCarAuthInfo";

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
     * 我的消息-详情
     */
    public static String MESSAGEDETAIL = APIURL + "message/detail";

    /**
     * 删除消息
     */
    public static String DELMESSAGE = APIURL + "message/delMessage";

    /**
     * 标记已读消息
     */
    public static String READMESSAGE = APIURL + "message/UpdateRead";

    /**
     * 司机接单（可以报价）列表
     */
    public static String QUOTELIST = APIURL + "goods/enableQuoteList";

    /**
     * 获得报价信息
     */
    public static String QUOTEGETINFO = APIURL + "quote/getInfo";

    /**
     * 拒绝接单
     */
    public static String REFUSEORDER = APIURL + "goods/refuseOrder";

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
     * 到达起运地时间
     */
    public static String ARRORGTIME = APIURL + "goods/arrOrgTime";

    /**
     * 司机进行发货动作
     */
    public static String SHHIPPING = APIURL + "order/shipping";

    /**
     * 获取用户信息
     */
    public static String MEMBERINFO = APIURL + "user/info";

    /**
     * 获取异常信息
     */
    public static String GRTABNORMAL = APIURL + "Abnormal/GetAbnormal";

    /**
     * 获取单条异常信息
     */
    public static String GRTABNORMALSITUATION = APIURL + "Abnormal/GetOneAbnormal";

    /**
     * 获取认证信息
     */
    public static String GETAUTHINFO = APIURL + "user/getAuthInfo";

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
     * 收入明细
     */
    public static String INCOMEDETAILS = APIURL + "pay/IncomeDetails";

    /**
     * 充值 支付宝支付
     */
    public static String RECHARGEBYALIPAY = APIURL + "pay/rechargeByAlipay";

    /**
     * 充值 微信支付
     */
    public static String RECHARGEBYWEXIN = APIURL + "pay/rechargeByWexin";

    /**
     * 充值记录
     */
    public static String RECHARGERECORD = APIURL + "pay/rechargeRecord";

    /**
     * 获取个人银行卡信息
     */
    public static String MYBANKCARD = APIURL + "Pay/MyBankCard";

    /**
     * 添加银行卡
     */
    public static String ADDBANKCARD = APIURL + "Pay/AddBankCard";

    /**
     * 设置支付密码
     */
    public static String SETPAYPASSEORD = APIURL + "user/PayPassword";

    /**
     * 修改密码校验
     */
    public static String CHECKPAYPASSEORD = APIURL + "user/CheckUpPayPassword";

    /**
     * 获得银行
     */
    public static String GETBANK = APIURL + "Pay/GetBank";

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
     * 帮助中心
     */
    public static String HELPCENTER = APIURL + "Help/HelpMain";

    /**
     * 帮助中心详情
     */
    public static String HELPCENTERDETAIL = APIURL + "Help/getOneHelp";

    /**
     * 修改密码
     */
    public static String UPDATEPWD = APIURL + "User/updatePwd";

}
