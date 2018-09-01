package com.study.redis;

import redis.clients.jedis.Jedis;

public class Test {
    static String constr = "127.0.0.1" ;
    public static Jedis getRedis(){
        Jedis jedis = new Jedis(constr) ;
//        jedis.auth("123");
        return jedis ;
    }
    public static void main(String[] args){
        Jedis j = Test. getRedis() ;
        String output ;
        j.set( "hello", "world" ) ;
        output = j.get( "hello") ;
        System. out.println(output) ;
    }
}
