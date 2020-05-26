package com.zimu.study.netty2.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author huangtao 2019/1/15 9:57 PM
 * @Description url和reqType的对应关系
 */
public enum UrlRelationReqType {
    /**
     * 下单，api
     */
    ORDER_BOOK("v1/order", 5),

    /**
     * 全量推送现货指数，api
     */
    FIRST_OPTION_DATA("v1/spot/index/history", 3),

    /**
     * 推送实时现货指数以及下单结果点，查询+订阅
     */
    SPOT_INDEX("v1/spot/index/timely", 1),

    /**
     * 现货指数历史 api == 拖动时使用，暂时未使用
     */
    SPOT_INDEX_HISTORY("--", 2),

    /**
     * 已结算订单，查询+订阅
     */
    SETTLED_ORDER("v1/traded/history", 6),

    /**
     * 推送用户账户余额，查询+MQ
     */
    BALANCE("v1/account/balance", 4),

    /**
     * 推送交易所现货指数信息，查询+订阅
     */
    PLATE("v1/exchange/spot/index", 8),

    /**
     * 推送未结算订单，api
     */
    UNSETTLED_ORDER("v1/unsettled/trade", 7),

    /**
     * 行权价推送，查询+订阅
     */
    STRIKE_PRICE("v1/exercise/price", 9),

    /**
     * 推送结算点，查询+订阅
     */
    PROFIT("v1/settled/trade", 12),

    ANTI_MACHINE("v1/antimachine/status", 15),
    ;

    @Getter
    @Setter
    public  String url;
    @Getter
    @Setter
    public  int reqType;

    UrlRelationReqType(String url, int reqType) {
        this.url = url;
        this.reqType = reqType;
    }

}
