package com.songyuwong.sqldbmetadatamanager.util;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class JedisUtil {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.100.101");
        jedis.set("wong","asdfasd");
        jedis.set("liu","baibai");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("wong"));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("liu"));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.get("lius"));
    }
}
