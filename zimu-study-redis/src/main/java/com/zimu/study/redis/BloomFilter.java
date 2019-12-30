package com.zimu.study.redis;

import redis.clients.jedis.Jedis;

/**
 *
 * @Description: 布隆过滤器
 * @Author jianhua.wang
 * @Date 2018年9月7日 上午10:44:03
 */
public class BloomFilter {
    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getInstance().getJedis();

    }
}
