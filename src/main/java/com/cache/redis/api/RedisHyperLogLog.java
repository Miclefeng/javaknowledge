package com.cache.redis.api;

import redis.clients.jedis.Jedis;

public class RedisHyperLogLog
{

    public static void main(String args[])
    {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("Ps!s5tlshitsaym");

        int i = 0;
        for (; i < 1000; i++) {
            jedis.pfadd("codeHole", "User_" + i);
        }

        long total = jedis.pfcount("codeHole");
        System.out.println(i + " , " + total);
        jedis.del("codeHole");
    }
}
