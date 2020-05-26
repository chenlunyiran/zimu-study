package com.zimu.study.netty2.service;

import com.zimu.study.netty2.enums.ModuleEnum;
import com.zimu.study.netty2.handler.MessageHandler;

/**
 * 处理不同的请求
 *
 * @author James Shen
 * @since 2018/10/7
 */
public class MessageHandlerFactory {

    public static MessageHandler createMessageHandler(ModuleEnum moduleEnum) {

        switch (moduleEnum) {
//            case FIRST_OPTION_DATA:
//                return FullSpotIndexHandler.getInstance();
//            case SPOT_INDEX_HISTORY:
//                return SpotIndexHistoryHandler.getInstance();
//            case BALANCE:
//                return BalanceQueryHandler.getInstance();
//            case ORDER_BOOK:
//                return OrderBookHandler.getInstance();
//            case SETTLED_ORDER:
//                return SettledOrderQueryHandler.getInstance();
//            case UNSETTLED_ORDER:
//                return UnsettledOrderQueryHandler.getInstance();
//            case UPDATE_VFOTA_BALANCE:
//                return UpdateVFOTABalanceHandler.getInstance();
//            case CANCEL_SUBSCRIBE:
//                return CancelSubscribeHandle.getInstance();
//            case FRONT_CONFIG:
//                return FrontConfigHandler.getInstance();
//            case UPDATE_USERNAME:
//                return UpdateUserNameHandler.getInstance();
//            case ANTI_MACHINE:
//                return AntiMachineQueryHandler.getInstance();
            default:
                return null;
        }
    }


}
