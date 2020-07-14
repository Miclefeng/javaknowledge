package redis.api;

import redis.clients.jedis.Jedis;

public class RedisBloom {

    public static void main(String args[])
    {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("Ps!s5tlshitsaym");


    }
}
