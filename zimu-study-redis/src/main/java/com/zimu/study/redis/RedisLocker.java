package com.zimu.study.redis;

import redis.clients.jedis.Jedis;

/**
 * 
 * @Description: TODO(这里描述这个类的作用) 
 * @Author jianhua.wang
 * @Date 2018年9月1日 上午10:44:03
 */
public class RedisLocker {
     
    private static String port = "127.0.0.1";
    private static Jedis redis;
    
    {
        redis = new Jedis(port);
    }
    
    public void lock(String key){
        try {
            Long isExist = redis.setnx(key, "");
            
        } catch (Exception e) {
            unLock(key);
        }
        
    }
    
    public void unLock(String key){
        redis.del(key);
    }
    
}
