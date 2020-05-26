package com.zimu.study.netty2.enums;

/**
 * @Author huangtao 2019/1/21 2:38 PM
 * @Description 错误码
 */
public enum ErrorEnumOpen {

    // 公共错误码
    REQUEST_ERROR(100000, "请求参数错误"),
    API_KEY_ERROR(100001, "apikey有误"),
    SIGNATURE_ERROR(100002, "无效的签名"),
    TIMESTAMP_EXPIRATION(100003, "请求时间戳过期"),
    REQUEST_FREQUENT(100004, "请求太频繁"),
    REQUEST_LIMIT(100005, "禁止访问"),
    NO_RESOURCES(100006, "未找到请求的资源"),
    METHOD_ERROR(100007, "使用的HTTP方法不适用于请求的资源"),
    CONTENT_FORMAT_ERROR(100008, "请求的内容格式不是JSON"),
    SERVICE_INTERNAL_ERROR(100009, "服务内部错误，请稍后再进行尝试"),
    SERVICE_UNAVAILABILITY(100010, "服务不可用，请稍后再进行尝试"),
    SERIAL_NUM_ERROR(100011, "同一个流水号只能使用一次"),
    SUID_BROKERID_EXIST(100012, "相同的suid和brokerId只能注册一个账户"),
    REQUEST_CALLBACK_FAILED(100013, "请求回调校验失败"),

    // ====用户相关的错误码====
    LOGIN_FAILED(200000, "登录失败，用户ID或密码不对"),
    USER_WALLET_INSUFFICIENT_BALANCE(200001, "该币种的资产余额不足"),
    USER_NOT_EXIST(200002, "用户不存在"),
    USER_WALLET_IS_NULL(200003, "用户资产信息为空，请先转入资产"),
    USER_WALLET_ASSET_ID_ERROR(200004, "用户资产里面没有该币种的资产信息，请先转入该币种资产"),
    USER_AND_BROKER_ID_ERROR(200005, "userId和brokerId不对应，请检查apikey是否有误"),

    // 请求参数错误时的具体提示
    REQUEST_PARAM_ERROR_USER_ID(300000, "userId参数错误"),
    REQUEST_PARAM_ERROR_AMOUNT(300001, "amount参数错误"),
    REQUEST_PARAM_ERROR_ASSET_ID(300002, "assetId参数错误"),
    REQUEST_PARAM_ERROR_ASSET_NAME(300003, "assetName参数错误"),
    REQUEST_PARAM_ERROR_SERIAL_NUM(300004, "serialNum参数错误"),
    REQUEST_PARAM_ERROR_SERIAL_SUID(300005, "suid参数错误"),
    REQUEST_PARAM_ERROR_SERIAL_EMAIL(300006, "email参数错误"),
    REQUEST_PARAM_ERROR_SERIAL_PHONE(300007, "phone参数错误"),
    REQUEST_PARAM_ERROR_SERIAL_NAME(300008, "name参数错误"),
    REQUEST_PARAM_ERROR_SERIAL_EMAIL_PHONE(300009, "email和phone必须要有一个"),
    REQUEST_PARAM_ERROR_PASSWORD(300011, "password参数错误"),
    REQUEST_PARAM_ERROR_PAGE_NUM(300012, "pageNum参数错误"),
    REQUEST_PARAM_ERROR_PAGE_SIZE(300013, "pageSize参数错误"),
    REQUEST_PARAM_ERROR_START_TIME(300014, "startTime参数错误"),
    REQUEST_PARAM_ERROR_END_TIME(300015, "endTime参数错误"),
    REQUEST_PARAM_ERROR_BROKER_ID(300016, "brokerId参数错误"),
    REQUEST_PARAM_ERROR_ASSET_ID_NAME(300017, "assetId/assetName参数错误"),

    // bwb活动
    REQUEST_PARAM_ERROR_IEO_TIME(300019, "活动时间段受限"),
    REQUEST_PARAM_ERROR_IEO_QUOTA(300020, "活动名额受限"),
    REQUEST_PARAM_ERROR_IEO_ADMISSION_FEE(300021, "活动入场费受限"),
    REQUEST_PARAM_ERROR_IEO_WITHDRAW(300022, "活动期间不可提现"),

    // ws的错误码
    WS_OK(0, "ok"),
    WS_REQUEST_ERROR(-1, "ws request error"),
    WS_NEED_LOGIN(401,"need login"),
    WS_UNKNOWN_ERROR(4000000,"未知错误"),
    WS_VERIFICATION_FAIL(400002, "验签失败"),
    WS_OP_URL_ERROR(400003, "op或url有误"),
    WS_CHANNEL_ERROR(400004, ""),// 该用户登录信息已失效，channel中没有userId 后面再测试
    WS_USER_LACK_NAME(400006, "缺少昵称"),
    WS_USER_ID_ERROR(400007, "userId有误"),
    WS_UPDATE_USERNAME_FAIL(400008, "更新用户昵称失败"),
    WS_LOGIN_NO_USER_ID(400009, "login error!没有userId"),
    WS_LOGIN_NO_TOKEN(400010, "login error!没有token"),
    WS_RESET_BALANCE_ERROR(400011, "resetBalance error"),
    WS_ORDER_FAIL_BROKER_ID_ERROR(400012, "order fail! brokerId error"),
    WS_ORDER_FAIL_CURRENCY_ERROR(400013, "order fail! currency error"),

    // ====限制的错误码====
    REQUEST_PARAM_ERROR_USER_ID_TOP_LIMIT(300018, "userId数量超过上线，一次最多只能100"),
    REQUEST_PARAM_ERROR_PAGE_SIZE_TOP_LIMIT(500001, "pageSize超过上线，一次最多只能500"),
    REQUEST_PARAM_ERROR_ASSET_ID_TOP_LIMIT(500002, "assetId数量超过上线，一次最多只能50"),
            ;

    private Integer code;

    private String msg;

    ErrorEnumOpen(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
