package com.zimu.study.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

/**
 * @Description: 简单限流(断尾求生)
 * @Author jianhua.wang
 * @Date 2018年9月7日 上午10:44:03
 */
public class SimpleRateLimiter {

    private Jedis jedis = JedisUtil.getInstance().getJedis();

    /**
     * 一分钟同一个用户的同一个操作 限制60s 5次
     *
     * @param userId
     * @param actionKey
     * @param period
     * @param maxCount
     * @return
     * @throws IOException
     */
    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) throws IOException {
        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        String value = nowTs + "" + Math.random();  //value保证唯一性
        Pipeline pipe = jedis.pipelined();
        pipe.multi(); //多事务起始
        pipe.zadd(key, nowTs, value); //根据毫秒值进行排序
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000); //删除60之前的请求
        Response<Long> count = pipe.zcard(key); //目前请求次数
        pipe.expire(key, period + 1); //由于网络延时，有效期宽限1秒，根据实际情况考虑
        pipe.exec(); //多事务执行
        pipe.close();
        return count.get() <= maxCount;
    }

    public static void main(String[] args) throws IOException {
        SimpleRateLimiter limiter = new SimpleRateLimiter();
        for (int i = 0; i < 20; i++) {
            System.out.println(i + 1 + ":" + limiter.isActionAllowed("laoqian", "reply", 60, 5));
        }
    }


    /**
     * 因为这几个连续的 Redis 操作都是针对同一个 key 的，使用 pipeline 可以显著提升 Redis 存取效率。
     * 但这种方案也有缺点，因为它要记录时间窗口内所有的行为记录，
     * 如果这个量很大，比如限定 60s 内操作不得超过 100w 次这样的参数，它是不适合做这样的限流的，因为会消耗大量的存储空间。
     *
     * https://juejin.im
     * 掘金 — 一个帮助开发者成长的社区
     */

    /**
     * 除了控制流量，限流还有一个应用目的是用于控制用户行为，避免垃圾请求。
     * 比如在 UGC 社区，用户的发帖、回复、点赞等行为都要严格受控，一般要严格限定某行为在规定时间内允许的次数，超过了次数那就是非法行为。
     * 对非法行为，业务必须规定适当的惩处策略。
     *
     * https://juejin.im
     * 掘金 — 一个帮助开发者成长的社区
     */
}
