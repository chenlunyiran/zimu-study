package com.zimu.study.netty2.enums;

/**
 * @author mawanqun
 */
public enum ErrorEnum {
    SYSTEM_ERROR(1000, "系统异常"),
    PARAM_ERROR(160000, "参数错误"),
    BOOK_ORDER_ERROR(160001, "下单异常"),
    MORE_THEN_ONCE_LIMIT(160002, "超过单次限额"),
    MORE_THEN_ONE_DAY_LIMIT(160003, "超过单日限额"),
    INSUFFICIENT_BALANCE(160004, "余额不足"),
    GET_INDEX_EXCEPTION(160005, "获取指数异常"),
    GET_RATE_OF_RETURN_EXCEPTION(160006, "获取收益率异常"),
    GET_UNIT_PRICE_EXCEPTION(160007, "获取单价异常"),
    GET_OPTION_CONFIG_EXCEPTION(160008, "获取预言家配置异常"),
    OPTION_TRADING_SWITCH_NOT_OPEN(160009, "预言家交易开关未开启"),
    GET_BALANCE_EXCEPTION(160010, "获取用户余额发生异常"),
    NEED_LOGIN(160011, "未登录"),
    PAYMENT_FAILED(160012, "支付失败"),
    ASSET_UNABLE_TO_TRADE(160013, "标的物无法交易"),
    STRIKE_PRICE_NOT_MATCH(160014, "行权价不匹配"),
    USER_AMOUNT_NOT_ENOUGH(160015, "余额不足"),
    OPTION_UNDER_MAINTENANCE(160016, "预言家维护中，马上回来"),
    MORE_THEN_ONE_PERIOD_LIMIT(160017, "超过单期下单限额"),
    VFOTA_NOT_SHARED(160018, "模拟账户不支持分享"),
    CURRENCY_NOT_EXIST(160019, "该账户不存在"),
    ;

    private Integer code;

    private String msg;

    ErrorEnum(Integer code, String msg) {
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
