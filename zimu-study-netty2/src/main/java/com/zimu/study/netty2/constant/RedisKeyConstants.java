package com.zimu.study.netty2.constant;

/**
 * @author James Shen
 * @since 2018/10/8
 */
public class RedisKeyConstants {
    public static final String HEARTBEAT = "option:heartbeat";
    public static final String BULLISH_COUNT = "option:bullish:count:";
    public static final String BEARISH_COUNT = "option:bearish:count:";
    public static final String ORDER_UNSETTLED_COUNT = "option:order:unsettled:count:";
    public static final String CONFIG = "option:config";
    public static final String LATEST_OPTION_INFO = "option:priceindex";

    /**
     * 用户登录密码token缓存key
     */
    public static final String REDIS_KEY_LOGIN_TOKEN_PREFIX = "FOTA_APP_ACCT_LOGIN_TOKEN_";
    public static final String BROKER_ORDER_AMOUNT_PREFIX = "option:order:amount:";
    public static final String USER_ORDER_AMOUNT_PREFIX = "option:order:amount:user";
    public static final String USER_ORDER_COUNT_SECOND_PREFIX = "option:order:count:user";
    public static final String PERIOD_SUB_KEY = "period";
    public static final String DAY_SUB_KEY = "day";
    public static final String VERIFICATION_SUB_KEY = "verification";
    public static final String REDIS_KEY_LOGIN_TOKEN_USER_ID_LIST_PREFIX = "FOTA_APP_ACCT_LOGIN_TOKEN_USER_ID_LIST_";

    public static final String ORDER_SWITCH_STATUS = "option:order_switch_status";
    public static final String DRIFT_CONTEXT_PREFIX = "option:drift:context:";

    public static final String getTokenKey(Long userId) {
        return "opt:token:user:" + userId;
    }
}
