package com.zimu.study.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class Test {
    static String constr = "127.0.0.1" ;
    public static Jedis getRedis(){
        Jedis jedis = new Jedis(constr) ;
//        jedis.auth("123");
        return jedis ;
    }
    public static void main(String[] args){
//        Jedis j = Test. getRedis() ;
//        String output ;
//        j.set( "hello", "world" ) ;
//        output = j.get( "hello") ;
//        System. out.println(output) ;

        List list = new ArrayList<>();
        list.add(1);
        String s = JSON.toJSONString(list);
        System.out.println(JSON.parseArray(s,Integer.class));
    }

}
