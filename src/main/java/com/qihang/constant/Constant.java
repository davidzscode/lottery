package com.qihang.constant;

/**
 * @author qihang
 * Email：evi2009kt@gmail.com
 * 飞机Telegram：qihang9981
 */

public class Constant {
    /**
     * token密钥
     */
    public static String SECRET = "byte-qihang";

    /**
     * 刷新token密钥
     */
    public static String REFRESH_SECRET = "byte-qihang-refresh-token";

    /**
     * token失效时间 7天
     */
    public static final int JWT_TTL = 604800;

    /**
     * 验证码的redis key
     */
    public static String REDIS_CODE = "code-key";


    /**
     * ip的redis key
     */
    public static String IP_KEY = "ip-key";
    /**
     * 一分钟请求次数
     */
    public static Integer FREQUENCY = 1;

    public static final String FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";

    /**
     * 支付宝pid前缀
     */
    public static final String ALIPAY_APPID_KAY = "alipay_appid_kay";
    /**
     * 支付宝公钥前缀
     */
    public static final String ALIPAY_PUBLIC_KAY = "alipay_public_kay";
    /**
     * 支付宝私钥前缀
     */
    public static final String ALIPAY_PRIVATE_KAY = "alipay_private_kay";
}
