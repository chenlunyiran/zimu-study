package com.zimu.study.netty2.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author James Shen
 * @since 2018/10/7
 */
public enum ModuleEnum {
    /**
     * none
     */
    NONE("nonce", -1000),

    /**
     * sessionId
     */
    SESSION_ID("sessionId", -1),

    /**
     * 实时现货指数推送
     */
    SPOT_INDEX("spotIndex", 1),

    /**
     * 现货指数历史
     */
    SPOT_INDEX_HISTORY("history", 2),

    /**
     * 首次现货指数数据加载
     */
    FIRST_OPTION_DATA("first_option_data", 3),

    /**
     * 用户资产
     */
    BALANCE("balance", 4),

    /**
     * 下单
     */
    ORDER_BOOK("order_book", 5),

    /**
     * 已结算订单推送
     */
    SETTLED_ORDER("settled_order", 6),

    /**
     * 未结算订单推送
     */
    UNSETTLED_ORDER("unsettled_order", 7),

    /**
     * 交易所买一卖一价平均值推送
     */
    PLATE("plate", 8),

    /**
     * 行权价推送
     */
    STRIKE_PRICE("strike_price", 9),


    /**
     * 重置模拟金账户金额
     */
    UPDATE_VFOTA_BALANCE("update_vfota_balance", 10),

    /**
     * 取消订阅
     */
    CANCEL_SUBSCRIBE("cancel_subscribe", 11),

    /**
     * 收益推送
     */
    PROFIT("profit",12),

    /**
     * 前端配置推送
     */
    FRONT_CONFIG("front config", 13),

    /**
     * 修改用户昵称
     */
    UPDATE_USERNAME("update_username", 14),

    ANTI_MACHINE("antimachine", 15),

    ;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int code;

    ModuleEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    private static final Map<Integer, ModuleEnum> codeEnumMap = Arrays.stream(values()).collect(Collectors.toMap(ModuleEnum::getCode, Function.identity()));

    public static ModuleEnum getEnumByCode(int code) {
        ModuleEnum moduleEnum = null;
        return codeEnumMap.get(code);
    }
}
