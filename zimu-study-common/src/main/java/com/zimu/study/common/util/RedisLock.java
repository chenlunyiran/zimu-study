package com.zimu.study.common.util;

import redis.clients.jedis.Jedis;

/**
 * @Classname RedisLock
 * @Description TODO
 * @Date 2019/3/6 23:12
 * @Created by twotiger
 */
public class RedisLock {

    /**
     * 保存每个线程独有的token
     */
    private static ThreadLocal<String> tokenMap = new ThreadLocal<>();

    /**
     * redis实现分布式可重入锁,并不保证在过期时间内完成锁定内的任务，需根据业务逻辑合理分配seconds
     *
     * @param lock
     *            锁的名称
     * @param seconds
     *            锁定时间，单位 秒
     *  token
     *            对于同一个lock,相同的token可以再次获取该锁，不相同的token线程需等待到unlock之后才能获取
     * @return
     */
    public boolean lock(final String lock,final  int seconds) {
        // @param token 对于同一个lock,相同的token可以再次获取该锁，不相同的token线程需等待到unlock之后才能获取
        long token = System.currentTimeMillis();

        boolean flag = false;
        Jedis client = null;
        try {
            client = jedisPool.getResource();
            String ret = client.set(lock, token, "NX", "EX", seconds);
            if (ret == null) {// 该lock的锁已经存在
                // String origToken = client.get(lock);// 即使lock已经过期也可以
                // if (token.equals(origToken)||origToken==null) {// token相同默认为同一线程，所以token应该尽量长且随机，保证不同线程的该值不相同
                //  ret = client.set(lock, token, "NX", "EX", seconds);//
                //  if ("OK".equalsIgnoreCase(ret))
                //     flag = true;
                // }                        ret=client.cas(lock,origToken,token,seconds);                         if("OK".equalsIgnoreCase(ret)){                             flag=true;                          }
            } else if ("OK".equalsIgnoreCase(ret))
                flag = true;
        } catch (Exception e) {
            logger.error(" lock{}  失败");
            throw new RedisException(e);
        } finally {
            if (client != null)
                client.close();
        }
        return flag;
    }

    /**
     * redis可以保证lua中的键的原子操作 unlock:lock调用完之后需unlock,否则需等待lock自动过期
     *
     * @param lock
     *  token
     *            只有线程已经获取了该锁才能释放它（token相同表示已获取）
     */
    public void unlock(final String lock) {
        Jedis client = null;
        final String token = tokenMap.get();
        if (StringUtil.isBlank(token))
            return;
        try {
            client = jedisPool.getResource();
            final String script = "if redis.call(\"get\",\"" + lock + "\") == \"" + token + "\"then  return redis.call(\"del\",\"" + lock + "\") else return 0 end ";
            client.eval(script);
        } catch (Exception e) {
            logger.error(" unlock{}  失败");
            throw new RedisException(e);
        } finally {
            if (client != null)
                client.close();
        }

    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        final RedisUtil redis = ctx.getBean(RedisUtil.class);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                String key = "cheng";

                @Override
                public void run() {
                    boolean lock = redis.lock(key, 30);
                    System.out.print(lock + "-");

                }
            }).start();
            ;
        }
        // redis.unlock(key);
        // ctx.close();
    }
}
