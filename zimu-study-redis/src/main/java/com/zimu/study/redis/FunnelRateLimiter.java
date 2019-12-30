package com.zimu.study.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 漏斗限流
 * @Author jianhua.wang
 * @Date 2018年9月7日 上午10:44:03
 */
public class FunnelRateLimiter {

    static class Funnel {
        int capacity;
        float leakingRate;
        int leftQuota;
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            int deltaQuota = (int) (deltaTs * leakingRate);
            if (deltaQuota < 0) {
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            if (deltaQuota < 1) {
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }

        }

        boolean waterting(int quota) {
            makeSpace();
            if (this.leftQuota > quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String, Funnel> funnels = new HashMap<String, Funnel>();

    public boolean isActionAllowed(String usrId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", usrId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.waterting(1);
    }
}
