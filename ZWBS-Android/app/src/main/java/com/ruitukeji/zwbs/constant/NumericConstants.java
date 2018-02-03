package com.ruitukeji.zwbs.constant;

/**
 * 用于存放数字常量的类
 * 有时可能会用的到
 * Created by ruitu ck on 2016/9/14.
 */

public class NumericConstants {
    /**
     * 网络请求状态码
     * 2000为成功
     */
    public final static int STATUS = 1;
    public final static int SUCCESS = 2000;
    public final static int ERRCODE = 0;
    public final static int TOLINGIN = -10001;

    /**
     * 刷新起始页码
     */
    public final static int START_PAGE_NUMBER = 1;

    /**
     * 拨打电话
     */
    public final static int REQUEST_CODE_PERMISSION_CALL = 10000;

    //微信支付返回码
    public final static int ERR_OK = 0;
    public final static int ERR_AUTH_DENIED = -4;
    public final static int ERR_COMM = -1;//一般错误
    public final static int ERR_SENT_FAILED = -3;
    public final static int ERR_UNSUPPORT = -5;
    public final static int ERR_USER_CANCEL = -2;

    //支付返回码
    public final static int RESULT_CODE_GET = 1000;
    public final static int RESULT_CODE_PRODUCT = 1005;
    public final static int RESULT_CODE_BASKET_ADD = 1001;
    public final static int RESULT_CODE_BASKET_MINUS = 1002;
    public final static int RESULT_CODE_BASKET_MINUSALL = 1003;
    public final static int RESULT_CODE_BASKET_MOVE = 1004;
    public final static int REQUEST_CODE = 100;
    public final static int RESULT_CODE_ORDER = 801;
    public final static int RESULT_CODE_PAYMENT_SUCCEED = 802;
    public final static int RESULT_CODE_PAYMENT_CANCEL = 816;
    public final static int RESULT_CODE_PAYMENT_ERROR = 824;

    //图片权限所需参数
    public final static int REQUEST_CODE_PERMISSION_PHOTO_PICKER = 1;
    public final static int REQUEST_CODE_CHOOSE_PHOTO = 1000;
    public final static int REQUEST_CODE_PHOTO_PREVIEW = 1001;
    public final static int REQUEST_CODE_PHOTO_PREVIEW1 = 1003;
    public final static int REQUEST_CODE_PHOTO_PREVIEW2 = 1004;
    public final static int REQUEST_CODE_PHOTO_PREVIEW3 = 1005;
    public final static int REQUEST_CODE_PHOTO_PREVIEW4 = 1006;
    public final static int REQUEST_CODE_PHOTO_PREVIEW5 = 1007;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int REQUEST_CODE_PREVIEW1 = 102;
    public static final int REQUEST_CODE_PREVIEW2 = 103;
    public static final int REQUEST_CODE_PREVIEW3 = 104;
    public static final int REQUEST_CODE_PREVIEW4 = 105;
    public static final int REQUEST_CODE_PREVIEW5 = 106;
    public static final int REQUEST_CODE_PREVIEW6 = 107;

    //SD卡读写权限权限所需参数
    public final static int READ_AND_WRITE_CODE = 100;

}
