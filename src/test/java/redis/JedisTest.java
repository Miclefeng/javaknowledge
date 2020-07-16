package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author miclefengzss
 */
public class JedisTest {

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("zss123456");

        jedis.set("name", "zss");

        System.out.println(jedis.get("name"));

        jedis.close();
    }
}
