package com.zimu.study.netty2.constant;

import org.mockito.internal.util.collections.Sets;

import java.util.Set;

/**
 * @author liudingshun
 * @date 2019/4/2
 */
public class GlobalConstant {
    public static final long DEFAULT_FOTA_BROKER_ID = 1L;
    public static final long ATTEX_BROKER_ID = 508090;
    private static final Set<Long> mainSiteBrokers = Sets.newSet(DEFAULT_FOTA_BROKER_ID, ATTEX_BROKER_ID);
    public static final boolean isMainSiteBrokers(Long brokerId) {
        if (null == brokerId) {
            return true;
        }
        return mainSiteBrokers.contains(brokerId);
    }
}
